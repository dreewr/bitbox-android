package bitbox.projec.remote

import io.reactivex.Observable
import bitbox.projec.remote.service.RetrofitService
import bitbox.project.data.repository.Remote
import bitbox.project.domain.model.machine.BitboxItems
import bitbox.project.domain.model.user.User
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import bitbox.project.domain.model.user.Pin
import bitbox.project.domain.model.user.PinResponse
import bitbox.project.domain.model.user.UserAuth
import javax.inject.Inject

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/11/19.
 *
 * Class that implements the Remote interface from Data module. This class uses retrofit and depends on a RetrofitService
 */

class RemoteImpl @Inject constructor(
        private val service: RetrofitService) : Remote {
    override fun checkPin(userID: String, pin: Pin): Observable<PinResponse> {
        return service.checkPin(userID, pin)
    }

    override fun getTransaction(transactionID: String): Observable<Transaction> {
        return service.getTransaction(transactionID)
    }

    override fun getBitboxItems(bitboxID: String): Observable<BitboxItems> {
        return service.getBitboxItems(bitboxID)
    }

    override fun executeUserLogin(username: String, password: String): Observable<User> {
        return service.executeUserLogin(UserAuth(username, password))
    }

    override fun createTransaction(newTransaction: Transaction): Observable<TransactionResponse> {
        return service.createTransaction(newTransaction)
    }


}