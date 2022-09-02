package com.example.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import android.text.Editable
import android.text.TextWatcher
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    //TODO disable input 'unit' is selected
    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

    fun CtoF(d: Double): Double {
        return (d *1.8 + 32).round(2)
    }

    fun FtoC(d: Double): Double {
        return ((d - 32) * 5/9).round(2)
    }

    fun LBStoKG(d: Double): Double {
        return (d / 2.2046).round(2)
    }

    fun KGtoLBS(d: Double): Double {
        return (d * 2.2046).round(2)
    }

    fun KMHtoMPH(d: Double): Double {
        return (d * 0.62137).round(2)
    }

    fun MPHtoKMH(d: Double): Double {
        return (d * 1.60934).round(2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nummin: EditText? = findViewById<EditText>(R.id.numin)
        val outt: TextView?= findViewById<TextView>(R.id.numout)
        // get reference to the string array that we just created
        val units = resources.getStringArray(R.array.units)
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, units)
        // get reference to the autocomplete text view
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.unit_dropdown)
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter)
        findViewById<EditText>(R.id.numin).addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (nummin == null){
                    outt?.text = "0"
                }else{
                    if (autocompleteTV.text.toString() == "Temp(C -> F)"){
                        outt?.text = nummin.text.toString().toDoubleOrNull()
                            ?.let { CtoF(it).toString() }
                    }else if (autocompleteTV.text.toString() == "Temp(F -> C)") {
                        outt?.text = nummin.text.toString().toDoubleOrNull()
                            ?.let { FtoC(it).toString() }
                    }else if (autocompleteTV.text.toString() == "Weight(kg -> lbs)"){
                        outt?.text = nummin.text.toString().toDoubleOrNull()
                            ?.let { KGtoLBS(it).toString() }
                    }else if (autocompleteTV.text.toString() == "Weight(lbs -> kg)") {
                        outt?.text = nummin.text.toString().toDoubleOrNull()
                            ?.let { LBStoKG(it).toString() }
                    }else if(autocompleteTV.text.toString() == "Velocity(kmh -> mph)"){
                        outt?.text = nummin.text.toString().toDoubleOrNull()
                            ?.let { KMHtoMPH(it).toString() }
                    }else if (autocompleteTV.text.toString() == "Velocity(mph -> kmh)") {
                        outt?.text = nummin.text.toString().toDoubleOrNull()
                            ?.let { MPHtoKMH(it).toString() }
                    }
                }
            }
        })

    }
}