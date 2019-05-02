package bitbox.project.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import bitbox.project.domain.model.user.User
import bitbox.project.domain.usecase.ExecuteUserLogin

import javax.inject.Inject

import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.state.ResourceState


/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/30/19.
 */
open class LoginViewModel @Inject internal constructor(
        private val executeLogin: ExecuteUserLogin?): ViewModel() {

    private val loginResponse: MutableLiveData<Resource<User>> = MutableLiveData()

    override fun onCleared() {
        executeLogin?.dispose()
        super.onCleared()
    }

    fun clear(){

        loginResponse.postValue(Resource(ResourceState.ERROR, null, null))

    }
    fun getUser(): LiveData<Resource<User>> {
        return loginResponse
    }

    fun executeUserLogin(username: String, password: String) {
        loginResponse.postValue(Resource(ResourceState.LOADING, null, null))
        executeLogin?.execute(LoginSubscriber(),
                ExecuteUserLogin.Params.forLogin(username, password))
    }

    inner class LoginSubscriber: DisposableObserver<User>() {
        override fun onNext(response: User) {
            loginResponse.postValue(Resource(ResourceState.SUCCESS,
                   response, null))
        }
        override fun onComplete() { }

        override fun onError(e: Throwable) {
            loginResponse.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }
}