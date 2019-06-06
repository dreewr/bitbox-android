package bitbox.project.domain.usecase

import bitbox.project.domain.Repository
import bitbox.project.domain.executor.PostExecutionThread
import bitbox.project.domain.interactor.ObservableUseCase
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import bitbox.project.domain.model.user.Pin
import bitbox.project.domain.model.user.PinResponse
import io.reactivex.Observable
import javax.inject.Inject

open class CheckPin @Inject constructor(
    private val repository: Repository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<PinResponse, CheckPin.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<PinResponse> {
        if (params == null) throw IllegalArgumentException("Transaction params can't be null!")
        return repository.checkPin(params.userID, params.pin)
    }

    data class Params constructor(val userID: String, val pin: Pin) {
        companion object {
            fun forBlock(userID: String, pin: Pin): Params {
                return Params(userID, pin)
            }
        }
    }

}