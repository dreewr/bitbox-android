package bitbox.project.data

import bitbox.project.data.store.DataStoreFactory
import io.reactivex.Observable
import bitbox.project.domain.Repository
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
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
    override fun createTransaction(newTransaction: Transaction): Observable<TransactionResponse> {

        return factory.getDataStore().createTransaction(newTransaction)
    }

}
