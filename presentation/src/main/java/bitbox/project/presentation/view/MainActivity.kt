package bitbox.project.presentation.view


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import bitbox.project.domain.model.transaction.TransactionInfo
import bitbox.project.presentation.R
import bitbox.project.presentation.viewmodel.MainViewModel
import bitbox.project.presentation.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity :BaseActivity() {

    lateinit var mainViewModel: MainViewModel

    companion object {
        fun getStartIntent(context : Context):Intent{
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        //transactionInfo = TransactionInfo() //sempre inicializa
        initViews()
        initViewModel()
        initListeners()
    }

    fun initListeners(){
        //TODO: Atualizar para que seja pega dinamicamente
        transactionInfo.machineID = 1
        btn_scan.setOnClickListener { ProductsActivity.getStartIntent(this).run { startActivity(this) } }
    }

    fun initViews(){
        txt_saldo_main.text = userInfo.userBalance.toString()
        txt_username_main.text = userInfo.userName
    }

    fun initViewModel(){
        //passar o id pra esse cara aqui
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
