package bitbox.project.domain.usecase

import bitbox.project.domain.Repository
import bitbox.project.domain.executor.PostExecutionThread
import bitbox.project.domain.interactor.ObservableUseCase
import bitbox.project.domain.model.transaction.Transaction
import io.reactivex.Observable
import javax.inject.Inject

open class GetTransaction @Inject constructor(
    private val repository: Repository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<Transaction, GetTransaction.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<Transaction> {
        if (params == null) throw IllegalArgumentException("Transaction params can't be null!")
        return repository.getTransaction(params.transactionID)
    }

    data class Params constructor(val transactionID: String) {
        companion object {
            fun forTransaction(transactionID: String): Params {
                return Params(transactionID)
            }
        }
    }

}