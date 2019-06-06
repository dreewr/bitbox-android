package bitbox.project.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bitbox.project.domain.model.user.Pin
import bitbox.project.domain.model.user.PinResponse
import bitbox.project.domain.model.user.User
import bitbox.project.domain.usecase.CheckPin
import bitbox.project.domain.usecase.ExecuteUserLogin
import bitbox.project.domain.usecase.GetBitboxItems
import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

open class VerificationViewModel @Inject internal constructor(
    private val executeLogin: ExecuteUserLogin?,
    private val checkPin: CheckPin?
): ViewModel() {

    private val loginResponse: MutableLiveData<Resource<User>> = MutableLiveData()
    private val pinResponse: MutableLiveData<Resource<PinResponse>> = MutableLiveData()

    override fun onCleared() {
        executeLogin?.dispose()
        checkPin?.dispose()
        super.onCleared()
    }

    fun clear(){

        loginResponse.postValue(Resource(ResourceState.ERROR, null, null))

    }

    fun checkPin( userID: String, pin: Pin) {
        pinResponse.postValue(Resource(ResourceState.LOADING, null, null))
        //Peço pro caso de uso ser executado passando como parâmetro uma instância da classe interna

        checkPin?.execute(CheckPinSubscriber(),
            CheckPin.Params.forBlock(userID, pin))
    }

    inner class CheckPinSubscriber: DisposableObserver<PinResponse>() {
        override fun onNext(response: PinResponse) {
            pinResponse.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    response, null)
            )
        }
        override fun onComplete() { }

        override fun onError(e: Throwable) {
            pinResponse.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }
}
