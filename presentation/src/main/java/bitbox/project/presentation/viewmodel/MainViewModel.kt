package io.ubivis.tmppres.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.observers.DisposableObserver
import io.ubivis.domain.model.BlockResponse
import io.ubivis.domain.model.temporary.UserAuth
import javax.inject.Inject

import io.ubivis.domain.usecase.GetBlock
import io.ubivis.tmppres.state.Resource
import io.ubivis.tmppres.state.ResourceState

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/30/19.
 */
open class MainViewModel @Inject internal constructor(
        private val userAuth:UserAuth ,
        private val getBlock: GetBlock?): ViewModel() {

    private val blockResponse: MutableLiveData<Resource<BlockResponse>> = MutableLiveData()

//    //código de inicialização
//    init {
//        Log.d("DEBUGANDO", "MainViewModel inicializado")
//
//    }

    override fun onCleared() {
        getBlock?.dispose()
        super.onCleared()
    }

    fun getBlock(): LiveData<Resource<BlockResponse>> {
        return blockResponse
    }

    fun fetchBlock(blockHash: String) {
        blockResponse.postValue(Resource(ResourceState.LOADING, null, null))
        //Peço pro caso de uso ser executado passando como parâmetro uma instância da classe interna

        getBlock?.execute(BlockSubscriber(),
                GetBlock.Params.forBlock(blockHash))
    }

    inner class BlockSubscriber: DisposableObserver<BlockResponse>() {
        override fun onNext(response: BlockResponse) {
            blockResponse.postValue(Resource(ResourceState.SUCCESS,
                   response, null))
        }

        override fun onComplete() { }

        override fun onError(e: Throwable) {
            blockResponse.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }
}