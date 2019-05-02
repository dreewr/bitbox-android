//package bitbox.project.presentation.viewmodel
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import io.reactivex.observers.DisposableObserver
//import bitbox.project.domain.model.BlockResponse
//
//import javax.inject.Inject
//
//import bitbox.project.domain.usecase.GetBlock
//import bitbox.project.presentation.state.Resource
//import bitbox.project.presentation.state.ResourceState
//
//
///**
// * Created by André Santos
// * andre.santos@ubivis.io
// * on ubivisnb10
// * on 3/30/19.
// */
//open class ProcessingViewModel @Inject internal constructor(
//        private val getBlock: GetBlock?): ViewModel() {
//
//    private val blockResponse: MutableLiveData<Resource<BlockResponse>> = MutableLiveData()
//
////    //código de inicialização
////    init {
////        Log.d("DEBUGANDO", "MainViewModel inicializado")
////
////    }
//
//    override fun onCleared() {
//        getBlock?.dispose()
//        super.onCleared()
//    }
//
//    fun getBlock(): LiveData<Resource<BlockResponse>> {
//        return blockResponse
//    }
//
//    fun fetchBlock(blockHash: String) {
//        blockResponse.postValue(Resource(ResourceState.LOADING, null, null))
//        //Peço pro caso de uso ser executado passando como parâmetro uma instância da classe interna
//
//        getBlock?.execute(LoginSubscriber(),
//                GetBlock.Params.forBlock(blockHash))
//    }
//
//    inner class LoginSubscriber: DisposableObserver<BlockResponse>() {
//        override fun onNext(response: BlockResponse) {
//            blockResponse.postValue(Resource(ResourceState.SUCCESS,
//                   response, null))
//        }
//
//        override fun onComplete() { }
//
//        override fun onError(e: Throwable) {
//            blockResponse.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
//        }
//
//    }
//}