package cl.rayout.desafiobci.common.network

import android.content.Context
import android.net.ConnectivityManager


@Suppress("DEPRECATION")
class LiveNetworkMonitor(private val context: Context?) {


    fun isConnected(): Boolean {
        var result = false
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.run {
            cm.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    else -> false
                }
            }
        }
        return result

    }
}