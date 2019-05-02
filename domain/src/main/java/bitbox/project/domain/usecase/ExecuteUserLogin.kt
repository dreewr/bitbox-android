package bitbox.project.domain.usecase

import bitbox.project.domain.Repository
import bitbox.project.domain.executor.PostExecutionThread
import bitbox.project.domain.interactor.ObservableUseCase
import bitbox.project.domain.model.user.User
import io.reactivex.Observable
import javax.inject.Inject

open class ExecuteUserLogin @Inject constructor(
    private val repository: Repository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<User, ExecuteUserLogin.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<User> {
        if (params == null) throw IllegalArgumentException("Transaction params can't be null!")
        return repository.executeUserLogin(params.username, params.password)
    }


    data class Params constructor(val username: String, val password: String ) {
        companion object {
            fun forLogin(username: String, password: String): Params {
                return Params(username, password )
            }
        }
    }

}