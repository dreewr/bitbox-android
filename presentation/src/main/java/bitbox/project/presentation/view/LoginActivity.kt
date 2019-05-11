package bitbox.project.presentation.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bitbox.project.domain.model.user.User
import bitbox.project.domain.model.user.UserInfo

import bitbox.project.presentation.R
import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.state.ResourceState
import bitbox.project.presentation.viewmodel.LoginViewModel
import bitbox.project.presentation.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity() {

//
//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory
//
//    @Inject
//    lateinit var userInfo: UserInfo

    lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        AndroidInjection.inject(this)

        initViews()
        initViewModel()


        loginViewModel.getUser().observe(this, Observer<Resource<User>> { response ->

            handleLoginRequest(response)
        })

        btn_login.setOnClickListener {

            loginViewModel.executeUserLogin(et_username_login.text.toString(), et_password_login.text.toString())

        }

    }

    fun initViews() {
        et_username_login.text = SpannableStringBuilder("DecoRodrigues")
        et_password_login.text = SpannableStringBuilder("123456")
    }

    fun initViewModel() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginViewModel::class.java)

    }

    //Todo: mudar a lógica dos intents para usar um objecto injetável
    private fun handleLoginRequest(resource: Resource<User>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {

                val user: User? = resource.data

                if (user?.erro == 0) {

                    userInfo.userBalance = user.saldo

                    MainActivity.getStartIntent(this).let {
                            it.putExtra("USER_ID", user.idUsuario)
                            it.putExtra("USER_SALDO", user.saldo)
                        it.putExtra("USER_NAME", et_username_login.text.toString())

                    }.run { startActivity(this) }
                    finish()
                } else {
                    pgs_login.visibility = GONE
                    btn_login.visibility = VISIBLE
                    Toast.makeText(this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show()
                }

                et_username_login.isEnabled = true
                et_password_login.isEnabled = true
            }

            ResourceState.LOADING -> {
                pgs_login.visibility = VISIBLE
                btn_login.visibility = GONE
                et_username_login.isEnabled = false
                et_password_login.isEnabled = false

            }
            ResourceState.ERROR -> {

                Toast.makeText(
                    this, "Houve um erro inesperado. Cheque sua conexão e tente novamente", Toast.LENGTH_SHORT
                ).show()
                pgs_login.visibility = GONE
                btn_login.visibility = VISIBLE
                et_username_login.isEnabled = true
                et_password_login.isEnabled = true
            }
        }
    }
}
