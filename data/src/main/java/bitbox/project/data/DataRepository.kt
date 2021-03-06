package bitbox.project.data

import bitbox.project.data.store.DataStoreFactory
import io.reactivex.Observable
import bitbox.project.domain.Repository
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
 *
 * Implementação da interface Repository do módulo Domain
 * In Dagger we bind DataRepository (implementation) and Repository(interface)
 *
 */
class DataRepository @Inject constructor(
        private val factory: DataStoreFactory
)
    : Repository {
    override fun getTransaction(transactionID: String): Observable<Transaction> {
        return factory.getDataStore().getTransaction(transactionID)
    }

    override fun getBitboxItems(bitboxID: String): Observable<BitboxItems> {
        return factory.getDataStore().getBitboxItems(bitboxID)
    }

    override fun executeUserLogin(username: String, password: String): Observable<User> {
        return factory.getDataStore().executeUserLogin(username, password)
    }

    override fun createTransaction(newTransaction: Transaction): Observable<TransactionResponse> {

        return factory.getDataStore().createTransaction(newTransaction)
    }

    override fun checkPin(userID: String, pin: Pin): Observable<PinResponse> {

        return factory.getDataStore().checkPin(userID, pin)
    }


}
