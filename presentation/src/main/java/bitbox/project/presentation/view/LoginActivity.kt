package bitbox.project.presentation.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bitbox.project.domain.model.BlockResponse
import bitbox.project.presentation.R
import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.state.ResourceState
import bitbox.project.presentation.viewmodel.MainViewModel
import bitbox.project.presentation.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_products.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    //Temporário - Trocar por um ViewModel próprio da classe

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        AndroidInjection.inject(this)

        initViewModel()

        mainViewModel.getBlock().observe(this, Observer<Resource<BlockResponse>> { response ->

            handleDataState(response)
        })

        btn_login.setOnClickListener {

            mainViewModel.fetchBlock("13320")

        }

    }

    fun initViewModel(){
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)

    }


    private fun handleDataState(resource: Resource<BlockResponse>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {

                val intent = Intent(this, MainActivity::class.java)
                Log.i("LoginActivity", "Entrou aqui")
                startActivity(intent)

                pgs_login.visibility = GONE
                btn_login.visibility = VISIBLE
            }

            ResourceState.LOADING -> {
                pgs_login.visibility = VISIBLE
                btn_login.visibility = GONE

            }
            ResourceState.ERROR -> {

                pgs_login.visibility = GONE
                btn_login.visibility = VISIBLE

            }
        }
    }

}
