package bitbox.project.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import bitbox.project.domain.usecase.CreateTransaction
import bitbox.project.domain.usecase.GetTransaction

import javax.inject.Inject

import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.state.ResourceState


/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/30/19.
 */
open class ProcessingViewModel @Inject internal constructor(
    private val createTransaction: CreateTransaction?,
    private val getTransaction: GetTransaction?): ViewModel() {

    private val transaction: MutableLiveData<Resource<Transaction>> = MutableLiveData()
    private val transactionResponse: MutableLiveData<Resource<TransactionResponse>> = MutableLiveData()

    override fun onCleared() {
        createTransaction?.dispose()
        super.onCleared()
    }

    fun getTransactionResponse(): LiveData<Resource<TransactionResponse>> {
        return transactionResponse
    }

    fun fectchTransaction(transactionID: String){
        transaction.postValue(Resource(ResourceState.LOADING, null, null))
        //Peço pro caso de uso ser executado passando como parâmetro uma instância da classe interna

        getTransaction?.execute(TransactionSubscriber(),
           GetTransaction.Params.forTransaction(transactionID))
    }

    fun createTransaction(newTransaction : Transaction) {
        transactionResponse.postValue(Resource(ResourceState.LOADING, null, null))
        //Peço pro caso de uso ser executado passando como parâmetro uma instância da classe interna

        createTransaction?.execute(TransactionCreationSubscriber(),
            CreateTransaction.Params.forBlock(newTransaction))
    }

    inner class TransactionSubscriber: DisposableObserver<Transaction>() {
        override fun onNext(response: Transaction) {
            transaction.postValue(Resource(ResourceState.SUCCESS,
                response, null))}

        override fun onComplete() { }

        override fun onError(e: Throwable) {
            transactionResponse.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

    inner class TransactionCreationSubscriber: DisposableObserver<TransactionResponse>() {
        override fun onNext(response: TransactionResponse) {
            transactionResponse.postValue(Resource(ResourceState.SUCCESS,
                response, null))}

        override fun onComplete() { }

        override fun onError(e: Throwable) {
            transactionResponse.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }
}