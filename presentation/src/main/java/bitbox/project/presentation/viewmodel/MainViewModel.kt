package bitbox.project.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import bitbox.project.domain.usecase.CreateTransaction

import javax.inject.Inject

import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.state.ResourceState


/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/30/19.
 */
open class MainViewModel @Inject internal constructor(
        private val createTransaction: CreateTransaction?): ViewModel() {

    private val transactionResponse: MutableLiveData<Resource<TransactionResponse>> = MutableLiveData()

//    //código de inicialização
//    init {
//        Log.d("DEBUGANDO", "MainViewModel inicializado")
//
//    }

    override fun onCleared() {
       createTransaction?.dispose()
        super.onCleared()
    }

    fun getTransactionResponse(): LiveData<Resource<TransactionResponse>> {
        return transactionResponse
    }

    fun createTransaction(newTransaction : Transaction) {
        transactionResponse.postValue(Resource(ResourceState.LOADING, null, null))
        //Peço pro caso de uso ser executado passando como parâmetro uma instância da classe interna

       createTransaction?.execute(TransactionSubscriber(),
               CreateTransaction.Params.forBlock(newTransaction))
    }

    inner class TransactionSubscriber: DisposableObserver<TransactionResponse>() {
        override fun onNext(response: TransactionResponse) {
            transactionResponse.postValue(Resource(ResourceState.SUCCESS,
                   response, null))}

        override fun onComplete() { }

        override fun onError(e: Throwable) {
            transactionResponse.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }
}