package bitbox.project.data.store

import io.reactivex.Observable
import bitbox.project.data.repository.Remote
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import javax.inject.Inject


/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/8/19.

 */
open class RemoteDataStore @Inject constructor(
        private val remote: Remote)
    : DataStore {
    override fun createTransaction(newTransaction: Transaction): Observable<TransactionResponse> {
        return remote.createTransaction(newTransaction)
    }



}