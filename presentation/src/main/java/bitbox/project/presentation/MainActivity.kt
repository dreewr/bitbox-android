package bitbox.project.presentation

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bitbox.project.domain.model.BlockResponse
import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.viewmodel.MainViewModel
import bitbox.project.presentation.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

   // @Inject
    //lateinit var userAuth: UserAuth

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Pegar dados de Usuário
        AndroidInjection.inject(this)

        //userAuth.token = "piripiripiripiri"
       // txt_debugtext.text = userAuth.token

        initViewModel()


        mainViewModel.fetchBlock("13320")
//
        mainViewModel.getBlock().observe(this, Observer<Resource<BlockResponse>>       { response ->
//
           txt_simpletext.text = response?.data?.block?.hash
        })

    }

    fun initViewModel(){
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

    }

}
