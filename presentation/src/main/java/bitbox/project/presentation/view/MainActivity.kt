package bitbox.project.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import bitbox.project.presentation.R
import bitbox.project.presentation.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity :BaseActivity() {

    lateinit var qrScan: IntentIntegrator
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
        initViewModel()
        initViews()
        initScanner()
        initListeners()
    }

    fun initListeners(){
        btn_scan.setOnClickListener {
            qrScan.initiateScan()
           // ProductsActivity.getStartIntent(this).run { startActivity(this) }
        }
    }

    fun initScanner(){
        qrScan = IntentIntegrator(this).let {
            it.setBeepEnabled(false)
                .setPrompt("Coloque o código QR na linha vermelha para escaneá-lo")
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
                .setBarcodeImageEnabled(true)
                .addExtra("USER_ID", userInfo.userID)
                .addExtra("USER_SALDO", userInfo.userBalance)
                .addExtra("USER_NAME", userInfo.userName)
        }
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

    //Getting the scan results
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        data
        IntentIntegrator.parseActivityResult(requestCode, resultCode, data).run {
            // Log.d("MainActivity", this.contents?.toString())
            //TODO> fazer a lógica pra extrair o id da máquina a partir do id
            transactionInfo.machineID = 1

        }

        ProductsActivity.getStartIntent(this).run { startActivity(this) }
    }
}
