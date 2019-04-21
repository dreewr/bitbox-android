package bitbox.project.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import bitbox.project.presentation.R
import kotlinx.android.synthetic.main.activity_processing.*


class ProcessingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_processing)

        btn_rebuy.setOnClickListener {

            val intent = Intent(this, ProductsActivity::class.java)

            startActivity(intent)

        }
        btn_notify.setOnClickListener {

           Toast.makeText(this, "Sua notificação de erro foi enviada!", Toast.LENGTH_LONG)

        }

        btn_back_processing.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}
