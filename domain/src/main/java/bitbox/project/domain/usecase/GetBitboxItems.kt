package bitbox.project.domain.usecase

import bitbox.project.domain.Repository
import bitbox.project.domain.executor.PostExecutionThread
import bitbox.project.domain.interactor.ObservableUseCase
import bitbox.project.domain.model.machine.BitboxItems
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import io.reactivex.Observable
import javax.inject.Inject

//Params: machineID

open class GetBitboxItems @Inject constructor(
    private val repository: Repository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<BitboxItems, GetBitboxItems.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<BitboxItems> {
        if (params == null) throw IllegalArgumentException("GetBitboxItems params can't be null!")
        return repository.getBitboxItems(params.bitboxID)
    }

    data class Params constructor(val bitboxID: String) {
        companion object {
            fun forBitbox(bitboxID: String): Params {
                return Params(bitboxID)
            }
        }
    }

}