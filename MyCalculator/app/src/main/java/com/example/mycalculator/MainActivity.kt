package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.mycalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var firstInput: Double = 0.0
    private var isTyping: Boolean = true
    private  var operator: String = ""

    private lateinit var tvInput: TextView

    private lateinit var btnZero: Button
    private lateinit var btnOne: Button
    private lateinit var btnTwo: Button
    private lateinit var btnThree: Button
    private lateinit var btnFour: Button
    private lateinit var btnFive: Button
    private lateinit var btnSix: Button
    private lateinit var btnSeven: Button
    private lateinit var btnEight: Button
    private lateinit var btnNine: Button
    private lateinit var btnDot: Button
    private lateinit var btnPlus: Button
    private lateinit var btnMinus: Button
    private lateinit var btnDivide: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnEqual: Button
    private lateinit var btnClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnZero.setOnClickListener { onDigit(it) }
        binding.btnOne.setOnClickListener { onDigit(it) }
        binding.btnTwo.setOnClickListener { onDigit(it) }
        binding.btnThree.setOnClickListener { onDigit(it) }
        binding.btnFour.setOnClickListener { onDigit(it) }
        binding.btnFive.setOnClickListener { onDigit(it) }
        binding.btnSix.setOnClickListener { onDigit(it) }
        binding.btnSeven.setOnClickListener { onDigit(it) }
        binding.btnEight.setOnClickListener { onDigit(it) }
        binding.btnNine.setOnClickListener { onDigit(it) }
        binding.btnDot.setOnClickListener { onDot(it) }
        binding.btnPlus.setOnClickListener { onOperator(it) }
        binding.btnMinus.setOnClickListener { onOperator(it) }
        binding.btnDivide.setOnClickListener { onOperator(it) }
        binding.btnMultiply.setOnClickListener { onOperator(it) }
        binding.btnEqual.setOnClickListener { onCalculate() }
        binding.btnClear.setOnClickListener { onClear() }
    }

    private fun onDigit(view: View) {
        if(isTyping) {
            tvInput.append((view as Button).text)
        } else {
            tvInput.text = ""
            isTyping = true
            tvInput.text = (view as Button).text
        }
    }

    private fun onDot(view: View) {
        if(isTyping) {
            if(tvInput.text.contains('.').not()) {
                val lastLetter = try {
                    tvInput.text.last()
                } catch (exception: NoSuchElementException) {
                    tvInput.append("0.")
                    (view as Button).text.last()
                }

                if(lastLetter != '.') {
                    tvInput.append((view as Button).text)
                }
            }
        } else {
            tvInput.text = ""
            tvInput.append("0.")
            isTyping = true
        }
    }

    private fun onCalculate() {
        if(firstInput != 0.0 && tvInput.text.isNotEmpty()) {
            val secondInput = tvInput.text.toString().toDouble()
            when(operator) {
                "+" -> { tvInput.text = (firstInput + secondInput).toString() }
                "-" -> { tvInput.text = (firstInput - secondInput).toString() }
                "/" -> { tvInput.text = (firstInput / secondInput).toString() }
                "X" -> { tvInput.text = (firstInput * secondInput).toString() }
            }
            operator = ""
            firstInput = 0.0
            isTyping = false
        }
    }

    private fun onOperator(view: View) {
        if(tvInput.text.isNotEmpty()) {
            if(firstInput != 0.0 && isTyping) {
                onCalculate()
            } else {
                firstInput = tvInput.text.toString().toDouble()
                isTyping = false
            }
            operator = (view as Button).text.toString()
        } else {
            if (operator.isNotEmpty()) {
                operator = (view as Button).text.toString()
            }
        }
    }

    private fun onClear() {
        firstInput = 0.0
        tvInput.text = ""
        operator = ""
    }

}