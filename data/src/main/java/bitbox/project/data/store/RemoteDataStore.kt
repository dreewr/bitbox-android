package bitbox.project.data.store

import io.reactivex.Observable
import bitbox.project.data.repository.Remote
import bitbox.project.domain.model.machine.BitboxItems
import bitbox.project.domain.model.user.User
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import bitbox.project.domain.model.user.Pin
import bitbox.project.domain.model.user.PinResponse
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
    override fun getTransaction(transactionID: String): Observable<Transaction> {
        return remote.getTransaction(transactionID)
    }

    override fun getBitboxItems(bitboxID: String): Observable<BitboxItems> {
        return remote.getBitboxItems(bitboxID)
    }

    override fun executeUserLogin(username: String, password: String): Observable<User> {
        return remote.executeUserLogin(username, password)
    }

    override fun createTransaction(newTransaction: Transaction): Observable<TransactionResponse> {
        return remote.createTransaction(newTransaction)
    }
//
    override fun checkPin(userID: String, pin: Pin): Observable<PinResponse> {
        return remote.checkPin(userID, pin)
    }

}