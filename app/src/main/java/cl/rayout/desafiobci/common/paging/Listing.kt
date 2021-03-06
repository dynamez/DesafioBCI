package cl.rayout.desafiobci.common.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList


data class Listing<T>(
    // the LiveData of paged lists for the UI to observe
    val pagedList: LiveData<PagedList<T>>,
    // represents the network request status to show to the user
    val networkState: LiveData<NetworkState>,
    // represents the refresh status to show to the user. Separate from networkState, this
    // value is importantly only when refresh is requested.
    val refreshState: LiveData<NetworkState>,
    // refreshes the whole data and fetches it from scratch.
    val refresh: () -> Unit,
    // retries any failed requests.
    val retry: () -> Unit,

    val totalSales: LiveData<Int>,

    val currentDay: LiveData<String>,

    val currentMonth: LiveData<String>
)

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
    val status: Status,
    val msg: String? = null,
    val type: Throwable? = null
) {
    companion object {
        val LOADED =
            NetworkState(
                Status.SUCCESS
            )
        val LOADING =
            NetworkState(
                Status.RUNNING
            )

        fun error(msg: String?, type: Throwable) =
            NetworkState(
                Status.FAILED,
                msg,
                type
            )
    }
}