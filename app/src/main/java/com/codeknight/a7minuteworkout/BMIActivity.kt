package com.codeknight.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.codeknight.a7minuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    private var binding: ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnCalculateResults?.setOnClickListener {
            if (validateMetricUnits()) {
                val heightValue: Float = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
                val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

                val bmi = weightValue / (heightValue * heightValue)

                displayBMIResult(bmi)
            } else {
                Toast.makeText(this@BMIActivity,
                "Please enter valid values",
                Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underwieght!"
            bmiDescription = "Eat a fucking burger."
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) < 0) {
            bmiLabel = "Severely underwieght"
            bmiDescription = "Get some food."
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) < 0) {
            bmiLabel = "Underwieght"
            bmiDescription = "Maybe build some muscle"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) < 0) {
            bmiLabel = "Normal"
            bmiDescription = "Doing good."
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) < 0) {
            bmiLabel = "Overweight"
            bmiDescription = "You a bit chubby."
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) < 0) {
            bmiLabel = "Obese"
            bmiDescription = "Run some laps bruh"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) < 0) {
            bmiLabel = "Severely obese!"
            bmiDescription = "Maybe make some big changes."
        } else {
            bmiLabel = "YO MAMA"
            bmiDescription = "bruh."
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription


    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (binding?.etMetricUnitWeight?.text.toString().isEmpty()
            || binding?.etMetricUnitHeight?.text.toString().isEmpty()) {
            isValid = false
        }

        return isValid
    }
}