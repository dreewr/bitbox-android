package bitbox.project.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import bitbox.project.presentation.R
import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.state.ResourceState
import bitbox.project.presentation.viewmodel.ProcessingViewModel
import bitbox.project.presentation.viewmodel.VerificationViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_verification.*

class VerificationActivity : BaseActivity() {

    lateinit var temporaryViewModel: ProcessingViewModel
    lateinit var viewModel: VerificationViewModel

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

        temporaryViewModel.transaction.observe(this, Observer<Resource<Transaction>> { response ->

            handleDataState(response)
        })

        btn_verify.setOnClickListener {

            temporaryViewModel.fetchTransaction("33")

        }

        btn_back_verification.setOnClickListener {
            onBackPressed()
            finish()
        }

        et_pin_1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0?.length == 4){
                     enableVerifyButton(true)
                    pinGamb = p0.toString()
                }
                else enableVerifyButton(false)
            }
        })
    }
   var pinGamb = "0000"

    private fun handleDataState(resource: Resource<Transaction>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {

               verifyPin()
            }

            ResourceState.LOADING -> {
                pgs_verify.visibility = View.VISIBLE
                btn_verify.visibility = View.GONE

            }
            ResourceState.ERROR -> {

                verifyPin()

            }
        }
    }

    fun verifyPin(){
        if (pinGamb.equals("1234"))ProcessingActivity.getStartIntent(this).run { startActivity(this) }
        else Toast.makeText(this, "Pin não confere com o usuário!", Toast.LENGTH_SHORT).show()
        pgs_verify.visibility = View.GONE
        btn_verify.visibility = VISIBLE
    }
    fun initViewModel() {
        temporaryViewModel = ViewModelProviders.of(this, viewModelFactory)
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
