package bitbox.projec.remote.service


import io.reactivex.Observable
import bitbox.project.domain.model.BlockResponse
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/11/19.
 */


interface RetrofitService {

    @GET("block/{blockHash}")
    fun getBlock(@Path("blockHash") blockHash: String)
            : Observable<BlockResponse>

}