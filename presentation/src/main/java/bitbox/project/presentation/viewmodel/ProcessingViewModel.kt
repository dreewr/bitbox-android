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
import bitbox.project.presentation.state.ViewState


/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/30/19.
 */
open class ProcessingViewModel @Inject internal constructor(
    private val createTransaction: CreateTransaction?,
    private val getTransaction: GetTransaction?): ViewModel() {

    val transaction: MutableLiveData<Resource<Transaction>> = MutableLiveData()
    val transactionResponse: MutableLiveData<Resource<TransactionResponse>> = MutableLiveData()
    var isPurchaseCreated: MutableLiveData<ViewState> = MutableLiveData()
    var isTransactionCompleted: MutableLiveData<ViewState> = MutableLiveData()
    var isProductDelivered: MutableLiveData<ViewState> = MutableLiveData()
    var transactionID: Int = 0

    init {
        isPurchaseCreated.value = ViewState.LOADING
        isTransactionCompleted.value = ViewState.LOADING
        isProductDelivered.value = ViewState.LOADING
    }
    override fun onCleared() {
        createTransaction?.dispose()
        super.onCleared()
    }

    fun getTransactionResponse(): LiveData<Resource<TransactionResponse>> {
        return transactionResponse
    }

    fun getTransaction() : LiveData<Resource<Transaction>> = transaction

    fun fetchTransaction(transactionID: String) : LiveData<Resource<Transaction>>{
        transaction.postValue(Resource(ResourceState.LOADING, null, null))
        getTransaction?.execute(TransactionSubscriber(),
           GetTransaction.Params.forTransaction(transactionID))

        return transaction
    }

    fun createTransaction(newTransaction : Transaction) : LiveData<Resource<TransactionResponse>> {
        transactionResponse.postValue(Resource(ResourceState.LOADING, null, null))
        createTransaction?.execute(TransactionCreationSubscriber(),
            CreateTransaction.Params.forBlock(newTransaction))
        return  transactionResponse
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