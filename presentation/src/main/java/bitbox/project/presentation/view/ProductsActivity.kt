package bitbox.project.presentation.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bitbox.project.domain.model.machine.BitboxItems
import bitbox.project.presentation.R
import bitbox.project.presentation.state.Resource
import bitbox.project.presentation.state.ResourceState
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
    var enablePurchase: Boolean = false

    companion object {
        fun getStartIntent(context : Context):Intent{
            return Intent(context, ProductsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        AndroidInjection.inject(this)

        initViewModel()

        initViews()

        productsViewModel.getBitboxItems().observe(this, Observer<Resource<BitboxItems>> { response ->

            handleBitboxItems(response)
        })
        productsViewModel.fetchBitboxItems("1")

        initListeners()
    }

    override fun onStart() {
        super.onStart()

        productsViewModel.getSelectedProductPrice().observe(this, Observer<Double> { value ->


            val newBalance: Double = productsViewModel.getUserBalance().value!!.minus(value)

            if (newBalance >= 0){
                txt_saldofinal_products.text = newBalance.toString()
                txt_saldofinal_products.setTextColor(ContextCompat.getColor(this, R.color.accent))
                txt_nofunds_product.visibility = GONE
                enablePurchase = true

            } else {
                txt_saldofinal_products.text = newBalance.toString()
                txt_saldofinal_products.setTextColor(ContextCompat.getColor(this, R.color.secondary_text))
                txt_nofunds_product.visibility = VISIBLE
                enablePurchase = false
            }

        })
    }
    fun initListeners(){
        btn_buy.setOnClickListener {

            val intent = Intent(this, VerificationActivity::class.java)
            Log.i("ProductsActivity", "Entrou aqui")
            startActivity(intent)
        }

        btn_back_products.setOnClickListener {

            MainActivity.getStartIntent(this).let {
                it.putExtra("USER_ID", intent.getIntExtra("USER_ID", 0))
                it.putExtra("USER_SALDO", intent.getIntExtra("USER_SALDO", 0))
                it.putExtra("USER_NAME", intent.getStringExtra("USER_NAME"))

            }.run { startActivity(this)
                finish()}
        }
    }

    fun initViewModel(){
        productsViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductsViewModel::class.java)

    }

    fun initViews(){

        btn_product1.isClickable = false
        btn_product2.isClickable = false
        btn_product3.isClickable = false
        btn_product4.isClickable = false

        productsViewModel.getUserBalance().postValue(intent.getIntExtra("USER_SALDO", 0).toDouble())

        txt_saldo_products.text = intent.getIntExtra("USER_SALDO", 0).toString()
        txt_saldoatual_products.text = intent.getIntExtra("USER_SALDO", 0).toString()

        view_purchaseresult.visibility = GONE

        iv_product1.visibility = GONE
        iv_product2.visibility = GONE
        iv_product3.visibility = GONE
        iv_product4.visibility = GONE

        view_info_product1.visibility= GONE
        view_info_product2.visibility = GONE
        view_info_product3.visibility = GONE
        view_info_product4.visibility = GONE


    }

    fun initProductListeners(items: BitboxItems?){

        //TODO: Criar uma variável no viewModel pra controlar a lógica do Purchase e tirar da lógica dos botões
        btn_product1.setOnClickListener {
            btn_product1.setBackgroundResource(R.drawable.bg_gradient)
            btn_product2.setBackgroundResource(R.color.transparent)
            btn_product3.setBackgroundResource(R.color.transparent)
            btn_product4.setBackgroundResource(R.color.transparent)

            isProductSelected = true

            view_purchaseresult.visibility = VISIBLE

           productsViewModel.getSelectedProductPrice().postValue(items!!.itensDisponiveis[0].produtoPreco)

           if(enablePurchase) enableBuyButton()
        }

        btn_product2.setOnClickListener {

            btn_product1.setBackgroundResource(R.color.transparent)
            btn_product2.setBackgroundResource(R.drawable.bg_gradient)
            btn_product3.setBackgroundResource(R.color.transparent)
            btn_product4.setBackgroundResource(R.color.transparent)

            isProductSelected = true

            view_purchaseresult.visibility = VISIBLE
            productsViewModel.getSelectedProductPrice().postValue(items!!.itensDisponiveis[1].produtoPreco)

            if(enablePurchase) enableBuyButton()
        }

        btn_product3.setOnClickListener {
            btn_product1.setBackgroundResource(R.color.transparent)
            btn_product2.setBackgroundResource(R.color.transparent)
            btn_product3.setBackgroundResource(R.drawable.bg_gradient)
            btn_product4.setBackgroundResource(R.color.transparent)

            isProductSelected = true

            view_purchaseresult.visibility = VISIBLE
            productsViewModel.getSelectedProductPrice().postValue(items!!.itensDisponiveis[2].produtoPreco)
            if(enablePurchase) enableBuyButton()

        }

        btn_product4.setOnClickListener {
            btn_product1.setBackgroundResource(R.color.transparent)
            btn_product2.setBackgroundResource(R.color.transparent)
            btn_product3.setBackgroundResource(R.color.transparent)
            btn_product4.setBackgroundResource(R.drawable.bg_gradient)

            isProductSelected = true

            view_purchaseresult.visibility = VISIBLE

            productsViewModel.getSelectedProductPrice().postValue(666.66)

            if(enablePurchase) enableBuyButton()

        }

    }

    private fun enableBuyButton(){
        btn_buy.alpha = 1f
        btn_buy.isClickable = true
    }

    private fun updateProductsView(items: BitboxItems?){

        btn_product1.isClickable = true
        btn_product2.isClickable = true
        btn_product3.isClickable = true
        btn_product4.isClickable = true

        pgs_product1.visibility = GONE
        pgs_product2.visibility = GONE
        pgs_product3.visibility = GONE
        pgs_product4.visibility = GONE

        iv_product1.visibility = VISIBLE
        iv_product2.visibility = VISIBLE
        iv_product3.visibility = VISIBLE
        iv_product4.visibility = VISIBLE

        view_info_product1.visibility= VISIBLE
        view_info_product2.visibility = VISIBLE
        view_info_product3.visibility = VISIBLE
        view_info_product4.visibility = VISIBLE

        txt_product1_name.text = items!!.itensDisponiveis[0].produtoNome
        txt_product1_price.text = items!!.itensDisponiveis[0].produtoPreco.toString()

        txt_product2_name.text = items!!.itensDisponiveis[1].produtoNome
        txt_product2_price.text = items!!.itensDisponiveis[1].produtoPreco.toString()

        txt_product3_name.text = items!!.itensDisponiveis[2].produtoNome
        txt_product3_price.text = items!!.itensDisponiveis[2].produtoPreco.toString()

        //txt_product4_name.text = items.itensDisponiveis[3].produtoNome
        //txt_product4_price.text = items.itensDisponiveis[3].produtoPreco.toString()

        Glide.with(this).load("https://static.carrefour.com.br/medias/sys_master/images/images/h21/he0/h00/h00/11096531959838.jpg").apply(RequestOptions().centerInside()).into(iv_product1)
        Glide.with(this).load("https://araujo.vteximg.com.br/arquivos/ids/3879991-1000-1000/07892840253745.jpg").apply(RequestOptions().centerInside()).into(iv_product2)
        Glide.with(this).load("https://static.carrefour.com.br/medias/sys_master/images/images/h21/he0/h00/h00/11096531959838.jpg").apply(RequestOptions().centerInside()).into(iv_product3)
        Glide.with(this).load("https://static.carrefour.com.br/medias/sys_master/images/images/h21/he0/h00/h00/11096531959838.jpg").apply(RequestOptions().centerInside()).into(iv_product4)

    }

    private fun handleBitboxItems(resource: Resource<BitboxItems>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                updateProductsView(resource.data)
                initProductListeners(resource.data)
            }
            ResourceState.LOADING -> {
                pgs_product1.visibility = VISIBLE
                pgs_product2.visibility = VISIBLE
                pgs_product3.visibility = VISIBLE
                pgs_product4.visibility = VISIBLE
            }
            ResourceState.ERROR -> {
                Toast.makeText(this, "Houve um erro, tente novamente mais tarde", Toast.LENGTH_SHORT).show()
                pgs_product1.visibility = GONE
                pgs_product2.visibility = GONE
                pgs_product3.visibility = GONE
                pgs_product4.visibility = GONE
            }
        }
    }


}
