package bitbox.projec.remote.service


import io.reactivex.Observable
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/11/19.
 */


interface RetrofitService {


    //Endpoints de integração
    @POST("transacoes")
    fun createTransaction(@Body newTransaction: Transaction)
            : Observable<TransactionResponse>

    @GET("transacoes/{transactionID}")
    fun getTransaction(@Path("transactionID") transactionID: String)
            : Observable<Transaction>

}