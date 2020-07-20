package cl.rayout.desafiobci.common.network

import cl.rayout.desafiobci.BuildConfig
import cl.rayout.desafiobci.common.exceptions.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


object RetrofitFactory : KoinComponent {

    private val liveNetworkMonitor: LiveNetworkMonitor by inject()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(makeOkHttpClient())
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private fun makeOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(noInternetErrorHandlerInterceptor())
        .addInterceptor(errorHandlerInterceptor())
        .addNetworkInterceptor(makeLoggingInterceptor())
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(7, TimeUnit.SECONDS)
        .build()


    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
            HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private fun noInternetErrorHandlerInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            if (!liveNetworkMonitor.isConnected()) {
                Timber.d("NO INTERNET")
                throw NoConnectionException()
            }
//            throw NoConnectionException()
            return@Interceptor chain.proceed(request)
        }
    }

    private fun errorHandlerInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            when (response.code) {
                500 -> throw ServerErrorException()
                404 -> throw NotFoundException()
                401 -> throw UnauthorizedException()
                204 -> throw NoContentException()
                200 -> {
                    Timber.d("Response is ${response.body}")
                    return@Interceptor response
                }
            }
            //throw NotFoundException()
            return@Interceptor response
        }
    }
}
