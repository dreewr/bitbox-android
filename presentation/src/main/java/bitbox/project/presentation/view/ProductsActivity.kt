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
import io.reactivex.rxkotlin.toSingle
import kotlinx.android.synthetic.main.activity_products.*
import javax.inject.Inject

class ProductsActivity : BaseActivity() {

    lateinit var productsViewModel: ProductsViewModel
    var isProductSelected: Boolean = false

    companion object {
        fun getStartIntent(context: Context): Intent {
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
        //TODO: Pegar essa referência dinamicamente atra´ves de um objeto injetável
        productsViewModel.fetchBitboxItems(transactionInfo.machineID.toString())
        initListeners()
    }

    override fun onStart() {
        super.onStart()

        productsViewModel.getSelectedProductPrice().observe(this, Observer<Double> { value ->


            val newBalance: Double = productsViewModel.getUserBalance().value!!.minus(value)

            if (newBalance >= 0) {
                txt_saldofinal_products.text = newBalance.toString()
                txt_saldofinal_products.setTextColor(ContextCompat.getColor(this, R.color.accent))
                txt_nofunds_product.visibility = GONE
                //enablePurchase = true
                enableBuyButton(true)

            } else {
                txt_saldofinal_products.text = newBalance.toString()
                txt_saldofinal_products.setTextColor(ContextCompat.getColor(this, R.color.secondary_text))
                txt_nofunds_product.visibility = VISIBLE
                enableBuyButton(false)
            }

        })
    }

    fun initListeners() {
        btn_buy.setOnClickListener { VerificationActivity.getStartIntent(this).run { startActivity(this) } }

        btn_back_products.setOnClickListener {

            MainActivity.getStartIntent(this).run {
                startActivity(this)
                finish()
            }
        }
    }

    fun initProductListeners(items: BitboxItems?) {

        //TODO: Criar uma variável no viewModel pra controlar a lógica do Purchase e tirar da lógica dos botões
        btn_product1.setOnClickListener {
            btn_product1.setBackgroundResource(R.drawable.bg_gradient)
            btn_product2.setBackgroundResource(R.color.transparent)
            btn_product3.setBackgroundResource(R.color.transparent)
            btn_product4.setBackgroundResource(R.color.transparent)

            transactionInfo.productID = items!!.itensDisponiveis[0].produtoId

            isProductSelected = true

            view_purchaseresult.visibility = VISIBLE

            productsViewModel.getSelectedProductPrice().postValue(items!!.itensDisponiveis[0].produtoPreco)

        }

        btn_product2.setOnClickListener {

            btn_product1.setBackgroundResource(R.color.transparent)
            btn_product2.setBackgroundResource(R.drawable.bg_gradient)
            btn_product3.setBackgroundResource(R.color.transparent)
            btn_product4.setBackgroundResource(R.color.transparent)

            transactionInfo.productID = items!!.itensDisponiveis[1].produtoId

            isProductSelected = true

            view_purchaseresult.visibility = VISIBLE
            productsViewModel.getSelectedProductPrice().postValue(items!!.itensDisponiveis[1].produtoPreco)

        }

        btn_product3.setOnClickListener {
            btn_product1.setBackgroundResource(R.color.transparent)
            btn_product2.setBackgroundResource(R.color.transparent)
            btn_product3.setBackgroundResource(R.drawable.bg_gradient)
            btn_product4.setBackgroundResource(R.color.transparent)

            isProductSelected = true

            transactionInfo.productID = items!!.itensDisponiveis[2].produtoId

            view_purchaseresult.visibility = VISIBLE
            productsViewModel.getSelectedProductPrice().postValue(items!!.itensDisponiveis[2].produtoPreco)


        }

        btn_product4.setOnClickListener {
            btn_product1.setBackgroundResource(R.color.transparent)
            btn_product2.setBackgroundResource(R.color.transparent)
            btn_product3.setBackgroundResource(R.color.transparent)
            btn_product4.setBackgroundResource(R.drawable.bg_gradient)

            transactionInfo.productID = items!!.itensDisponiveis[2].produtoId

            isProductSelected = true

            view_purchaseresult.visibility = VISIBLE

            productsViewModel.getSelectedProductPrice().postValue(items!!.itensDisponiveis[3].produtoPreco)

        }

    }

    fun initViewModel() {
        productsViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductsViewModel::class.java)

        productsViewModel.getUserBalance().postValue(userInfo.userBalance)
    }

    fun initViews() {

        btn_product1.isClickable = false
        btn_product2.isClickable = false
        btn_product3.isClickable = false
        btn_product4.isClickable = false

        txt_saldo_products.text = userInfo.userBalance.toString()
        txt_saldoatual_products.text = userInfo.userBalance.toString()

        view_purchaseresult.visibility = GONE

        txt_nofunds_product.visibility = GONE

        iv_product1.visibility = GONE
        iv_product2.visibility = GONE
        iv_product3.visibility = GONE
        iv_product4.visibility = GONE

        view_info_product1.visibility = GONE
        view_info_product2.visibility = GONE
        view_info_product3.visibility = GONE
        view_info_product4.visibility = GONE

    }

    private fun enableBuyButton(isEnabled: Boolean) {
        if (isEnabled) {
            btn_buy.alpha = 1f
            btn_buy.isClickable = true
        } else {
            btn_buy.alpha = 0.6f
            btn_buy.isClickable = false
        }
    }

    private fun updateProductsView(items: BitboxItems?) {

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

        view_info_product1.visibility = VISIBLE
        view_info_product2.visibility = VISIBLE
        view_info_product3.visibility = VISIBLE
        view_info_product4.visibility = VISIBLE

        txt_product1_name.text = items!!.itensDisponiveis[0].produtoNome
        txt_product1_price.text = items!!.itensDisponiveis[0].produtoPreco.toString()
        Glide.with(this)
            .load(items!!.itensDisponiveis[0].produtoImagem)
            .apply(RequestOptions().centerInside()).into(iv_product1)

        txt_product2_name.text = items!!.itensDisponiveis[1].produtoNome
        txt_product2_price.text = items!!.itensDisponiveis[1].produtoPreco.toString()
        Glide.with(this)
            .load(items!!.itensDisponiveis[1].produtoImagem)
            .apply(RequestOptions().centerInside()).into(iv_product2)

        txt_product3_name.text = items!!.itensDisponiveis[2].produtoNome
        txt_product3_price.text = items!!.itensDisponiveis[2].produtoPreco.toString()
        Glide.with(this)
            .load(items!!.itensDisponiveis[2].produtoImagem)
            .apply(RequestOptions().centerInside()).into(iv_product3)

        txt_product4_name.text = items.itensDisponiveis[3].produtoNome
        txt_product4_price.text = items.itensDisponiveis[3].produtoPreco.toString()
        Glide.with(this)
            .load(items!!.itensDisponiveis[3].produtoImagem)
            .apply(RequestOptions().centerInside()).into(iv_product4)
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
