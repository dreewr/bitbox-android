package bitbox.project.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import bitbox.project.presentation.R
import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.state.ResourceState
import bitbox.project.presentation.state.ViewState
import bitbox.project.presentation.viewmodel.ProcessingViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_processing.*

//
//Primeira requisição:
//Segunta Requisição:
//Terceira


class ProcessingActivity : BaseActivity() {

    lateinit var processingViewModel: ProcessingViewModel


    companion object {
        fun getStartIntent(context : Context):Intent{
            return Intent(context, ProcessingActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_processing)
        AndroidInjection.inject(this)

        initViewModel()
        initViews()
        initListeners()

        createTransaction()
        startProcessing()
    }

    fun createTransaction(){
        //processingViewModel.createTransaction(Transaction(1,0,transactionInfo.machineID , transactionInfo.productID, userInfo.userID))
        processingViewModel.createTransaction(Transaction(1,0, 1, 1, 20))
            .observe(this, Observer<Resource<TransactionResponse>> { response ->
                handleTransactionCreated(response)
            })

    }

    fun startProcessing(){


        processingViewModel.isPurchaseCreated.observe(this, Observer<ViewState> { state ->
            when(state){
                ViewState.ERROR -> setErrorView()

                ViewState.SUCCESS -> {

                    txt_firstmessage_processing.setText(R.string.processing_first_message_success)

                }
            }
        })

        processingViewModel.isTransactionCompleted.observe(this, Observer<ViewState> { state ->
            when(state){
                ViewState.ERROR -> setErrorView()

                ViewState.SUCCESS -> {


                }
            }
        })

        processingViewModel.isProductDelivered.observe(this, Observer<ViewState> { state ->
            when(state){
                ResourceState.ERROR -> setErrorView()

                ResourceState.SUCCESS -> {



                }
            }

        })


    }

    private fun handleTransactionCreated(resource: Resource<TransactionResponse>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {

                if (resource.data!!.erro.equals("0")){
                    processingViewModel.isPurchaseCreated.value = ViewState.SUCCESS

                } else {
                    //TODO: Trocar, ajustei isso por teste só
                    processingViewModel.isPurchaseCreated.value = ViewState.SUCCESS
                }

                //TODO: Deixar essa string dinâmica
                processingViewModel.fetchTransaction("1")

            }

            ResourceState.ERROR -> {
                processingViewModel.isPurchaseCreated.value = ViewState.ERROR
            }
        }
    }

    private fun handleTransactionState(resource: Resource<TransactionResponse>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {

                if (resource.data!!.erro.equals("0")){
                    processingViewModel.isProductDelivered.value = ViewState.SUCCESS

                } else {
                    //TODO: Trocar, ajustei isso por teste só
                    processingViewModel.isProductDelivered.value = ViewState.SUCCESS
                }

            }
            ResourceState.ERROR -> {

                processingViewModel.isProductDelivered.value = ViewState.SUCCESS

            }


        }
    }

    fun setErrorView(){
        pgs_processing.visibility = GONE
        iv_error_processing.visibility = VISIBLE
        txt_errormessage_processing.visibility = VISIBLE
        enableButtons(true, 1f)
    }

    fun enableButtons(isEnabled: Boolean, alpha : Float){

        btn_notify_processing.let{
            it.alpha = alpha
            it.isClickable = isEnabled
        }

        btn_rebuy_processing.let{
            it.alpha = alpha
            it.isClickable = isEnabled
        }
    }

    fun initListeners(){

        //Rebuy --> Goes to Products
        btn_rebuy_processing.setOnClickListener {

            ProductsActivity.getStartIntent(this).run { startActivity(this) }

            finish()

        }

        btn_notify_processing.setOnClickListener {

            Toast.makeText(this, "Sua notificação de erro foi enviada!", Toast.LENGTH_LONG).show()

        }

        btn_back_processing.setOnClickListener {

            ProductsActivity.getStartIntent(this).run { startActivity(this) }

            finish()
        }

    }

    fun initViews(){

        iv_error_processing.visibility = GONE
        iv_success_processing.visibility = GONE
        txt_firstmessage_processing.visibility = VISIBLE
        txt_firstmessage_processing.setText(R.string.processing_first_message_loading)
        txt_secondmessage_processing.visibility = GONE
        txt_firstmessage_processing.setText(R.string.processing_second_message_loading)
        txt_thirdmessage_processing.visibility = GONE
        txt_thirdmessage_processing.setText(R.string.processing_third_message_loading)

        txt_errormessage_processing.setText(R.string.processing_error)
        txt_errormessage_processing.visibility = GONE
        pgs_processing.visibility = VISIBLE

        btn_notify_processing.alpha = 0.8f
        btn_notify_processing.isClickable = false
        btn_rebuy_processing.alpha = 0.8f
        btn_rebuy_processing.isClickable = false

    }

    fun initViewModel(){
        processingViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProcessingViewModel::class.java)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ProductsActivity::class.java))
        finish()
    }

}
