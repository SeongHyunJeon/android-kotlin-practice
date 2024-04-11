package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private var firstInput: Double = 0.0
    private var isTyping: Boolean = true
    private var operator: String = ""

    private var tvInput: TextView? = null

    private var btnZero: Button? = null
    private var btnOne: Button? = null
    private var btnTwo: Button? = null
    private var btnThree: Button? = null
    private var btnFour: Button? = null
    private var btnFive: Button? = null
    private var btnSix: Button? = null
    private var btnSeven: Button? = null
    private var btnEight: Button? = null
    private var btnNine: Button? = null
    private var btnDot: Button? = null
    private var btnPlus: Button? = null
    private var btnMinus: Button? = null
    private var btnDivide: Button? = null
    private var btnMultiply: Button? = null
    private var btnEqual: Button? = null
    private var btnClear: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tv_input)
        btnZero = findViewById(R.id.btn_zero)
        btnOne = findViewById(R.id.btn_one)
        btnTwo = findViewById(R.id.btn_two)
        btnThree = findViewById(R.id.btn_three)
        btnFour = findViewById(R.id.btn_four)
        btnFive = findViewById(R.id.btn_five)
        btnSix = findViewById(R.id.btn_six)
        btnSeven = findViewById(R.id.btn_seven)
        btnEight = findViewById(R.id.btn_eight)
        btnNine = findViewById(R.id.btn_nine)
        btnDot = findViewById(R.id.btn_dot)
        btnPlus = findViewById(R.id.btn_plus)
        btnMinus = findViewById(R.id.btn_minus)
        btnDivide = findViewById(R.id.btn_divide)
        btnMultiply = findViewById(R.id.btn_multiply)
        btnEqual = findViewById(R.id.btn_equal)
        btnClear = findViewById(R.id.btn_clear)
        btnZero!!.setOnClickListener { onDigit(it) }
        btnOne!!.setOnClickListener { onDigit(it) }
        btnTwo!!.setOnClickListener { onDigit(it) }
        btnThree!!.setOnClickListener { onDigit(it) }
        btnFour!!.setOnClickListener { onDigit(it) }
        btnFive!!.setOnClickListener { onDigit(it) }
        btnSix!!.setOnClickListener { onDigit(it) }
        btnSeven!!.setOnClickListener { onDigit(it) }
        btnEight!!.setOnClickListener { onDigit(it) }
        btnNine!!.setOnClickListener { onDigit(it) }
        btnDot!!.setOnClickListener { onDot(it) }
        btnPlus!!.setOnClickListener { onOperator(it) }
        btnMinus!!.setOnClickListener { onOperator(it) }
        btnDivide!!.setOnClickListener { onOperator(it) }
        btnMultiply!!.setOnClickListener { onOperator(it) }
        btnEqual!!.setOnClickListener { onCalculate() }
        btnClear!!.setOnClickListener { onClear() }
    }

    private fun onDigit(view: View) {
        if(isTyping) {
            tvInput!!.append((view as Button).text)
        } else {
            tvInput!!.text = ""
            isTyping = true
            tvInput!!.text = (view as Button).text
        }
    }

    private fun onDot(view: View) {
        if(isTyping) {
            if(tvInput!!.text.contains('.').not()) {
                val lastLetter = try {
                    tvInput!!.text.last()
                } catch (exception: NoSuchElementException) {
                    tvInput!!.append("0.")
                    (view as Button).text.last()
                }

                if(lastLetter != '.') {
                    tvInput!!.append((view as Button).text)
                }
            }
        } else {
            tvInput!!.text = ""
            tvInput!!.append("0.")
            isTyping = true
        }
    }

    private fun onCalculate() {
        if(firstInput != 0.0 && tvInput!!.text.isNotEmpty()) {
            val secondInput = tvInput!!.text.toString().toDouble()
            when(operator) {
                "+" -> { tvInput!!.text = (firstInput + secondInput).toString() }
                "-" -> { tvInput!!.text = (firstInput - secondInput).toString() }
                "/" -> { tvInput!!.text = (firstInput / secondInput).toString() }
                "X" -> { tvInput!!.text = (firstInput * secondInput).toString() }
            }
            operator = ""
            firstInput = 0.0
            isTyping = false
        }
    }

    private fun onOperator(view: View) {
        if(tvInput!!.text.isNotEmpty()) {
            if(firstInput != 0.0 && isTyping) {
                onCalculate()
            } else {
                firstInput = tvInput!!.text.toString().toDouble()
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
        tvInput!!.text = ""
        operator = ""
    }

}