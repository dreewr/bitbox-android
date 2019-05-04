package bitbox.project.presentation.view

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bitbox.project.domain.model.machine.BitboxItems
import bitbox.project.domain.model.transaction.TransactionResponse
import bitbox.project.presentation.R
import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.state.ResourceState
import bitbox.project.presentation.viewmodel.MainViewModel
import bitbox.project.presentation.viewmodel.ProductsViewModel
import bitbox.project.presentation.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_products.*
import javax.inject.Inject

class ProductsActivity : AppCompatActivity() {

    //todo: esc
    // rever sobre as libraries
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var productsViewModel: ProductsViewModel

    var isProductSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        AndroidInjection.inject(this)

        initProductListeners()

        initViews()

        initViewModel()

        productsViewModel.getBitboxItems().observe(this, Observer<Resource<BitboxItems>> { response ->

            handleBitboxItems(response)
        })
        productsViewModel.fetchBitboxItems("1")

        btn_buy.setOnClickListener {

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
        productsViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductsViewModel::class.java)

    }

    fun initViews(){}

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

    private fun updateProductsView(items: BitboxItems?){
        pgs_product1.visibility = GONE
        pgs_product2.visibility = GONE
        pgs_product3.visibility = GONE
        pgs_product4.visibility = GONE

        iv_product1.visibility = VISIBLE
        iv_product2.visibility = VISIBLE
        iv_product3.visibility = VISIBLE
        iv_product4.visibility = VISIBLE

        view_info_product1.visibility = VISIBLE
        view_info_product2.visibility = VISIBLE
        view_info_product3.visibility = VISIBLE
        view_info_product4.visibility = VISIBLE

        Glide.with(this).load("https://static.carrefour.com.br/medias/sys_master/images/images/h21/he0/h00/h00/11096531959838.jpg").apply(RequestOptions().centerInside()).into(iv_product1)
        Glide.with(this).load("https://static.carrefour.com.br/medias/sys_master/images/images/h21/he0/h00/h00/11096531959838.jpg").apply(RequestOptions().centerInside()).into(iv_product2)
        Glide.with(this).load("https://static.carrefour.com.br/medias/sys_master/images/images/h21/he0/h00/h00/11096531959838.jpg").apply(RequestOptions().centerInside()).into(iv_product3)
        Glide.with(this).load("https://static.carrefour.com.br/medias/sys_master/images/images/h21/he0/h00/h00/11096531959838.jpg").apply(RequestOptions().centerInside()).into(iv_product4)


    }

    private fun handleBitboxItems(resource: Resource<BitboxItems>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                updateProductsView(resource.data)
            }
            ResourceState.LOADING -> {


            }
            ResourceState.ERROR -> {
                Toast.makeText(this, "Houve um erro, tente novamente mais tarde", Toast.LENGTH_SHORT).show()

            }
        }
    }


}
