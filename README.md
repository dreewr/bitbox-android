package com.example.issuerapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.issuerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Launcher for starting MockWalletApp's activity and receiving result
    private val walletActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the result from MockWalletApp
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            // TODO: Process the data returned from MockWalletApp
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addCardToWalletButton.setOnClickListener {
            // Start MockWalletApp's activity to add the card to wallet
            val intent = Intent("com.example.mockwalletapp.ACTION_ADD_CARD_TO_WALLET")
            walletActivityLauncher.launch(intent)
        }
    }
}

<activity android:name=".MainActivity" />


package com.example.mockwalletapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mockwalletapp.databinding.ActivityWalletAddCardBinding

class WalletAddCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWalletAddCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityWalletAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmButton.setOnClickListener {
            // Prepare result data
            val resultData = Intent().apply {
                putExtra("cardAddedToWallet", true)
            }
            setResult(Activity.RESULT_OK, resultData)
            finish()
        }

        binding.cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}


<activity android:name=".WalletAddCardActivity" android:exported="true">
    <intent-filter>
        <action android:name="com.example.mockwalletapp.ACTION_ADD_CARD_TO_WALLET" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
