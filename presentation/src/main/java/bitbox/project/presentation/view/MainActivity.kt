package bitbox.project.presentation.view


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

class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mainViewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity", "Created")
        AndroidInjection.inject(this)

        initViews()
        initViewModel()

        btn_scan.setOnClickListener {

            val intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)

        }
    }

    fun initViews(){

        txt_saldo_main.text = intent.getIntExtra("USER_SALDO", 0).toString()
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
