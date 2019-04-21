package bitbox.project.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bitbox.project.presentation.R
import kotlinx.android.synthetic.main.activity_processing.*
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        btn_buy.setOnClickListener {

            val intent = Intent(this, VerificationActivity::class.java)

            startActivity(intent)

        }

        btn_back_products.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
}
