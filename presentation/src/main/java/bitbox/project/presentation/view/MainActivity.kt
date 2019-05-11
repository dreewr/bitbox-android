package bitbox.project.presentation.view


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
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
        Log.i("MainActivity", "Created")
        AndroidInjection.inject(this)

        initViews()
        initViewModel()
        initListeners()

    }

    fun initListeners(){
        btn_scan.setOnClickListener {

            ProductsActivity.getStartIntent(this).let {
                it.putExtra("USER_ID", intent?.getIntExtra("USER_ID", 0))
                it.putExtra("USER_SALDO", intent?.getIntExtra("USER_SALDO", 0))
                it.putExtra("USER_NAME", intent?.getStringExtra("USER_NAME").toString())

            }.run { startActivity(this) }

        }
    }
    fun initViews(){

        txt_saldo_main.text = intent.getFloatExtra("USER_SALDO", 0f).toString()
        txt_username_main.text = intent.getStringExtra("USER_NAME")

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
