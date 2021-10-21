package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButtom.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val costOfService = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        if (costOfService == null) {
            binding.amountToTip.text = ""
            return
        }
        val selectedService = binding.options.checkedRadioButtonId
        val tipPrecentage = when (selectedService) {
            R.id.amazing_option -> 0.2
            R.id.good_option -> 0.18
            else -> 0.15
        }
        var tip = costOfService * tipPrecentage
        if (binding.roundTip.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.amountToTip.text = getString(R.string.tip_amount, formattedTip)
    }


}