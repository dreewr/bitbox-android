package bitbox.project.domain.usecase

import io.reactivex.Observable
import bitbox.project.domain.Repository
import bitbox.project.domain.executor.PostExecutionThread
import bitbox.project.domain.interactor.ObservableUseCase
import bitbox.project.domain.model.BlockResponse
import javax.inject.Inject

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/29/19.
 *
 * This class executes the fetching of a Block.
 *
 */

open class GetBlock @Inject constructor(
        private val repository: Repository,
        postExecutionThread: PostExecutionThread)

    : ObservableUseCase<BlockResponse, GetBlock.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<BlockResponse> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return repository.getBlock(params.blockHash)
    }

    data class Params constructor(val blockHash: String) {
        companion object {
            fun forBlock(blockHash: String): Params {
                return Params(blockHash)
            }
        }
    }

}