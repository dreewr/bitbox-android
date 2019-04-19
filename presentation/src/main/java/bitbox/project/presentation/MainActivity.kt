package io.ubivis.tmppres

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.android.AndroidInjection
import io.ubivis.domain.model.BlockResponse
import io.ubivis.domain.model.temporary.UserAuth
import io.ubivis.tmppres.state.Resource
import io.ubivis.tmppres.viewmodel.MainViewModel
import io.ubivis.tmppres.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userAuth: UserAuth
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Pegar dados de Usuário
        AndroidInjection.inject(this)

        userAuth.token = "piripiripiripiri"
        txt_debugtext.text = userAuth.token

        initViewModel()


        //Hardcoded
        mainViewModel.fetchBlock("13320")

        mainViewModel.getBlock().observe(this, Observer<Resource<BlockResponse>>       { response ->

            txt_simpletext.text = response?.data?.block?.hash
        }
        )
    }

    fun initViewModel(){
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

    }

}
