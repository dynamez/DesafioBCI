package cl.rayout.desafiobci.dashboard.network

import androidx.annotation.MainThread
import androidx.paging.PagedList
import cl.rayout.desafiobci.common.extensions.createStatusLiveData
import cl.rayout.desafiobci.common.paging.PagingRequestHelper
import cl.rayout.desafiobci.dashboard.model.DashboardPokemonModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.concurrent.Executor

class HistoryBoundaryCallbackClass(
    private val date: String?,
    private val webservice: PokemonNetworkInterface,
    private val handleResponse: (AllPokemonResponse?) -> Unit,
    private val ioExecutor: Executor,
    private val networkPageSize: Int,
    private val networkPage: Int,
    private val userToken: String?,
    private val user: String?
) : PagedList.BoundaryCallback<DashboardPokemonModel>() {

    val helper =
        PagingRequestHelper(ioExecutor)
    val networkState = helper.createStatusLiveData()

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    @MainThread
    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            webservice.getAllPokemonsWithPaging(
            ).enqueue(createWebserviceCallback(it))
        }
    }

    /**
     * User reached to the end of the list.
     */
    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: DashboardPokemonModel) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {

            webservice.getAllPokemonsWithPaging(

            ).enqueue(createWebserviceCallback(it))

        }
    }

    /**
     * every time it gets new items, boundary callback simply inserts them into the database and
     * paging library takes care of refreshing the list if necessary.
     */
    private fun insertItemsIntoDb(
        response: Response<AllPokemonResponse>,
        it: PagingRequestHelper.Request.Callback
    ) {
        ioExecutor.execute {
            handleResponse(response.body())
            it.recordSuccess()
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: DashboardPokemonModel) {
        // ignored, since we only ever append to what's in the DB
    }

    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback)
            : Callback<AllPokemonResponse> {
        return object : Callback<AllPokemonResponse> {
            override fun onFailure(
                call: Call<AllPokemonResponse>,
                t: Throwable
            ) {
                Timber.d("failure !!!  $t")
                it.recordFailure(t)
            }

            override fun onResponse(
                call: Call<AllPokemonResponse>,
                response: Response<AllPokemonResponse>
            ) {
                insertItemsIntoDb(response, it)
            }
        }
    }
}