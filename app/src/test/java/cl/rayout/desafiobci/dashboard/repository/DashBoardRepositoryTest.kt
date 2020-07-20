package cl.rayout.desafiobci.dashboard.repository

import cl.rayout.desafiobci.dashboard.network.PokemonNetworkInterface
import junit.framework.TestCase
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DashBoardRepositoryTest : TestCase() {
    private lateinit var server: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var service: PokemonNetworkInterface

    @Before
    override fun setUp() {
        server = MockWebServer()
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(server.url("/"))
            .build()
        service = retrofit.create(PokemonNetworkInterface::class.java)
    }

    @After
    override fun tearDown() {
        server.close()
    }
}