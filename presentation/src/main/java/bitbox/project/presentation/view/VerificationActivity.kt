package bitbox.project.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bitbox.project.presentation.R
import kotlinx.android.synthetic.main.activity_verification.*

class VerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        btn_verifiy.setOnClickListener {

            val intent = Intent(this, ProcessingActivity::class.java)

            startActivity(intent)

        }

        btn_back_verification.setOnClickListener {
            onBackPressed()
        }
    }
}
