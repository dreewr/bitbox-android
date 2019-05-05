package bitbox.project.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import bitbox.project.presentation.viewmodel.MainViewModel
import bitbox.project.presentation.viewmodel.ProcessingViewModel
import bitbox.project.presentation.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_processing.*
import javax.inject.Inject

//
//Primeira requisição:
//Segunta Requisição:
//Terceira


class ProcessingActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var processingViewModel: ProcessingViewModel

    var IS_PURCHASE_COMPLETED: Boolean = false
    var IS_PRODUCT_DELIVERED: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_processing)

        AndroidInjection.inject(this)

        initViews()

        initViewModel()

        initListeners()

        startProcessing()

        processingViewModel.fectchTransaction("33")

    }

    fun startProcessing(){

        processingViewModel.createTransaction(Transaction(1, 0, 1, 1, 1, null, null))

        processingViewModel.getTransactionResponse().observe(this, Observer<Resource<TransactionResponse>> { response ->

            if (!IS_PURCHASE_COMPLETED) handlePurchaseResponse(response)
            else if (!IS_PRODUCT_DELIVERED) handleDeliveryResponse(response)

        })

    }

    private fun handlePurchaseResponse(resource: Resource<TransactionResponse>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                IS_PURCHASE_COMPLETED = true

                txt_firstmessage_processing.let {
                    it.visibility = VISIBLE
                    it.setText(R.string.processing_secondmessage_success)
                }

                processingViewModel.createTransaction(Transaction(1, 0, 1, 1, 1))
            }

            ResourceState.LOADING -> {


            }
            ResourceState.ERROR -> {


            }
        }
    }

    private fun handleDeliveryResponse(resource: Resource<TransactionResponse>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
               IS_PRODUCT_DELIVERED = true

                txt_secondmessage_processing.let {
                    it.visibility = VISIBLE
                    it.setText(R.string.processing_secondmessage_success)
                }

                pgs_processing.visibility = GONE
                iv_success_processing.visibility = VISIBLE

                enableButtons()


            }

            ResourceState.LOADING -> {

                txt_secondmessage_processing.let {
                    it.visibility = VISIBLE
                    it.setText(R.string.processing_secondmessage_loading)
                }


            }
            ResourceState.ERROR -> {

                pgs_processing.visibility = GONE
                iv_error_processing.visibility = VISIBLE

                txt_secondmessage_processing.let {
                    it.visibility = GONE
                }

                txt_errormessage_processing.visibility = VISIBLE

                enableButtons()

            }
        }
    }

    fun enableButtons(){
        btn_notify_processing.let{
            it.alpha = 1f
            it.isClickable = true
        }

        btn_rebuy_processing.let{
            it.alpha = 1f
            it.isClickable = true
        }
    }

    fun initListeners(){

        btn_rebuy_processing.setOnClickListener {

            val intent = Intent(this, ProductsActivity::class.java)

            startActivity(intent)

            finish()

        }

        btn_notify_processing.setOnClickListener {

            Toast.makeText(this, "Sua notificação de erro foi enviada!", Toast.LENGTH_LONG).show()

        }

        btn_back_processing.setOnClickListener {
            startActivity(Intent(this, ProductsActivity::class.java))
            finish()
        }

    }

    fun initViews(){

        iv_error_processing.visibility = GONE
        iv_success_processing.visibility = GONE
        txt_firstmessage_processing.visibility = VISIBLE
        txt_firstmessage_processing.setText(R.string.processing_firstmessage_loading)
        txt_secondmessage_processing.visibility = GONE
        txt_firstmessage_processing.setText(R.string.processing_secondmessage_loading)
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
