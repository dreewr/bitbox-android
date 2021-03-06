package bitbox.project.domain.usecase

import bitbox.project.domain.Repository
import bitbox.project.domain.executor.PostExecutionThread
import bitbox.project.domain.interactor.ObservableUseCase
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import io.reactivex.Observable
import javax.inject.Inject

open class CreateTransaction @Inject constructor(
    private val repository: Repository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<TransactionResponse, CreateTransaction.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<TransactionResponse> {
        if (params == null) throw IllegalArgumentException("Transaction params can't be null!")
        return repository.createTransaction(params.newTransaction)
    }

    data class Params constructor(val newTransaction: Transaction) {
        companion object {
            fun forBlock(newTransaction: Transaction): Params {
                return Params(newTransaction)
            }
        }
    }

}