package com.smartherd.productscanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var scannedResult: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnScan.setOnClickListener {
            run {
                IntentIntegrator(this@MainActivity).initiateScan();
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState?.putString("scannedResult", scannedResult)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.let {
            scannedResult = it.getString("scannedResult").toString()
            txtValueLabel.text = scannedResult
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if(result != null){

            if(result.contents != null){
                scannedResult = result.contents
                txtValueLabel.text = scannedResult
            } else {
                txtValueLabel.text = "Scan failed..!!"
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}