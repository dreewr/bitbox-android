package bitbox.project.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bitbox.project.domain.model.machine.BitboxItems
import bitbox.project.domain.usecase.GetBitboxItems
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject
import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.state.ResourceState


/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/30/19.
 */
open class ProductsViewModel @Inject internal constructor(
        private val getBitboxItems: GetBitboxItems?): ViewModel() {

    private val bitboxItems: MutableLiveData<Resource<BitboxItems>> = MutableLiveData()

//    //código de inicialização
//    init {
//        Log.d("DEBUGANDO", "MainViewModel inicializado")
//
//    }

    override fun onCleared() {
        getBitboxItems?.dispose()
        super.onCleared()
    }

    fun clear(){

        bitboxItems.postValue(Resource(ResourceState.ERROR, null, null))

    }
    fun getBitboxItems(): LiveData<Resource<BitboxItems>> {
        return bitboxItems
    }

    fun fetchBitboxItems(biboxID: String) {
        bitboxItems.postValue(Resource(ResourceState.LOADING, null, null))
        //Peço pro caso de uso ser executado passando como parâmetro uma instância da classe interna

        getBitboxItems?.execute(BitboxItemsSubscriber(),
                GetBitboxItems.Params.forBitbox(biboxID))
    }

    inner class BitboxItemsSubscriber: DisposableObserver<BitboxItems>() {
        override fun onNext(response: BitboxItems) {
            bitboxItems.postValue(Resource(ResourceState.SUCCESS,
                   response, null))
        }

        override fun onComplete() { }

        override fun onError(e: Throwable) {
            bitboxItems.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }
}