package bitbox.project.presentation.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import bitbox.project.presentation.R
import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.state.ResourceState
import bitbox.project.presentation.viewmodel.ProcessingViewModel
import bitbox.project.presentation.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_verification.*
import javax.inject.Inject

class VerificationActivity : BaseActivity() {

    lateinit var mainViewModel: ProcessingViewModel

    companion object {
        fun getStartIntent(context : Context):Intent{
            return Intent(context, VerificationActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        AndroidInjection.inject(this)

        initViewModel()

        initViews()

        mainViewModel.getTransactionResponse().observe(this, Observer<Resource<TransactionResponse>> { response ->

            handleDataState(response)
        })

        btn_verify.setOnClickListener {

            mainViewModel.createTransaction(Transaction(1,0,transactionInfo.machineID , transactionInfo.productID, userInfo.userID))
        }

        btn_back_verification.setOnClickListener {
            onBackPressed()
            finish()
        }

        et_pin_1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 4) enableVerifyButton(true)
                else enableVerifyButton(false)
            }
        })
    }

    private fun handleDataState(resource: Resource<TransactionResponse>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {

                ProcessingActivity.getStartIntent(this).run { startActivity(this) }
                pgs_verify.visibility = View.GONE
                btn_verify.visibility = VISIBLE
            }

            ResourceState.LOADING -> {
                pgs_verify.visibility = View.VISIBLE
                btn_verify.visibility = View.GONE

            }
            ResourceState.ERROR -> {

                pgs_verify.visibility = View.GONE
                btn_verify.visibility = View.VISIBLE

            }
        }
    }

    fun initViewModel() {
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProcessingViewModel::class.java)
    }

    fun initViews() {


    }

    private fun enableVerifyButton(enabled: Boolean) {
        if (enabled) {
            btn_verify.alpha = 1f
            btn_verify.isClickable = true
        } else {
            btn_verify.alpha = 0.6f
            btn_verify.isClickable = false
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
