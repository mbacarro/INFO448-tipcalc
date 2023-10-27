package edu.uw.ischool.mbacarro.tipcalc

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"
    private lateinit var edtServiceAmt : EditText
    private lateinit var btnTip : Button
    private lateinit var btnTip10 : Button
    private lateinit var btnTip15 : Button
    private lateinit var btnTip18 : Button
    private lateinit var btnTip20 : Button
    private var value = 0.0
    private var tipPerc = 1.15

    private fun enableSubmitIfReady() {
        val isReady: Boolean = edtServiceAmt.getText().toString().isNotEmpty()
        btnTip.setEnabled(isReady)
    }

    private fun totalAfterTip(num: Double): String {
        var total = num * tipPerc
        val rounded = "%.2f".format(total)
        return rounded
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtServiceAmt = findViewById(R.id.edtServiceAmt)
        btnTip = findViewById(R.id.btnTip)
        btnTip10 = findViewById(R.id.btnTip10)
        btnTip15 = findViewById(R.id.btnTip15)
        btnTip18 = findViewById(R.id.btnTip18)
        btnTip20 = findViewById(R.id.btnTip20)

        edtServiceAmt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(arg0: Editable) {
                enableSubmitIfReady()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        btnTip10.setOnClickListener {
            tipPerc = 1.10
            Log.i(TAG, "new tip percent = ${tipPerc}")
        }
        btnTip15.setOnClickListener {
            tipPerc = 1.15
            Log.i(TAG, "new tip percent = ${tipPerc}")
        }
        btnTip18.setOnClickListener {
            tipPerc = 1.18
            Log.i(TAG, "new tip percent = ${tipPerc}")
        }
        btnTip20.setOnClickListener {
            tipPerc = 1.20
            Log.i(TAG, "new tip percent = ${tipPerc}")
        }

        btnTip.setOnClickListener {
            Log.i(TAG, "button ${btnTip.tag} was pushed")

            value = edtServiceAmt.text.toString().toDouble()
            var totalRounded = totalAfterTip(value)

            val toast = Toast.makeText(this, "$${totalRounded}", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}