package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    //XML 파일과 코드 사이의 연결을 제공하는 결합 클래스로 XML파일의 이름을 파스칼 표기법으로 변환하고 이름 끝에 'Binding'을 추가한 형태.
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //activity_main.xml의 View에 접근하기 위한 ViewBinding 객체 생성.
        setContentView(binding.root) //binding.root는 해당 뷰들을 감싸고 있는 ConstraintLayout을 의미.
        binding.calculateButton.setOnClickListener{ calculateTip() } //팁을 계산하는 버튼에 대한 이벤트 설정.
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text //TextField의 입력값은 String이 아닌 Editable이지만 toString()으로 변환 가능.
        val cost = stringInTextField.toString().toDoubleOrNull()
        if(cost == null) {
            binding.tipResult.text = ""
            return
        }

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId) { //선택된 버튼의 ID값 가져오기.
            //ID값 비교하여 팁 퍼센트 지정.
            binding.optionTwentyPercent.id -> 0.20
            binding.optionEighteenPercent.id -> 0.18
            else -> 0.15
        }

        var tip = cost * tipPercentage //팁 계산.
        val roundUp = binding.roundUpSwitch.isChecked //팁을 올림 하여 줄 것인지 확인.
        if(roundUp) //그렇다면 팁 다시 계산.
            tip = kotlin.math.ceil(tip)

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip) //통화 양식을 입힌 금액 저장.
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip) //출력.
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean { //keyCode 입력 매개변수가 KeyEvent.KEYCODE_ENTER와 같은 경우 터치 키보드를 숨기는 비공개 도우미 함수
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}