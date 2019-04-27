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
import kotlinx.android.synthetic.main.activity_processing.*
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.activity_products.view.*
import javax.inject.Inject

class ProductsActivity : AppCompatActivity() {

    //todo: esc
    // rever sobre as libraries
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mainViewModel: MainViewModel

    var isProductSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        AndroidInjection.inject(this)

        initProductListeners()

        initViewModel()

        mainViewModel.getBlock().observe(this, Observer<Resource<BlockResponse>> { response ->

            handleDataState(response)
        })

        btn_buy.setOnClickListener {

           // mainViewModel.fetchBlock("13320")

            val intent = Intent(this, VerificationActivity::class.java)
            Log.i("ProductsActivity", "Entrou aqui")
            startActivity(intent)

        }

        btn_back_products.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun initViewModel(){
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)

    }


    fun initProductListeners(){

        btn_product1.setOnClickListener {
            btn_product1.setBackgroundResource(R.drawable.bg_gradient)
            btn_product2.setBackgroundResource(R.color.transparent)
            btn_product3.setBackgroundResource(R.color.transparent)
            btn_product4.setBackgroundResource(R.color.transparent)

            isProductSelected = true

            view_purchaseresult.visibility = VISIBLE

            enableBuyButton()
        }

        btn_product2.setOnClickListener {

            btn_product1.setBackgroundResource(R.color.transparent)
            btn_product2.setBackgroundResource(R.drawable.bg_gradient)
            btn_product3.setBackgroundResource(R.color.transparent)
            btn_product4.setBackgroundResource(R.color.transparent)

            isProductSelected = true

            view_purchaseresult.visibility = VISIBLE
            enableBuyButton()


        }

        btn_product3.setOnClickListener {
            btn_product1.setBackgroundResource(R.color.transparent)
            btn_product2.setBackgroundResource(R.color.transparent)
            btn_product3.setBackgroundResource(R.drawable.bg_gradient)
            btn_product4.setBackgroundResource(R.color.transparent)

            isProductSelected = true
            enableBuyButton()
            view_purchaseresult.visibility = VISIBLE

        }

        btn_product4.setOnClickListener {
            btn_product1.setBackgroundResource(R.color.transparent)
            btn_product2.setBackgroundResource(R.color.transparent)
            btn_product3.setBackgroundResource(R.color.transparent)
            btn_product4.setBackgroundResource(R.drawable.bg_gradient)

            isProductSelected = true

            view_purchaseresult.visibility = VISIBLE

            enableBuyButton()

        }

    }

    private fun enableBuyButton(){
        btn_buy.alpha = 1f
        btn_buy.isClickable = true
    }

    private fun handleDataState(resource: Resource<BlockResponse>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {

                val intent = Intent(this, VerificationActivity::class.java)
                Log.i("ProductsActivity", "Entrou aqui")
                startActivity(intent)

                //pgs_buy.visibility = GONE
                //btn_buy.visibility = VISIBLE
            }

            ResourceState.LOADING -> {
               // pgs_buy.visibility = VISIBLE
                btn_buy.visibility = View.GONE

            }
            ResourceState.ERROR -> {

                //pgs_buy.visibility = GONE
                btn_buy.visibility = VISIBLE

            }
        }
    }


}
