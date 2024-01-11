package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java) //MainActivity 실행.

    @Test
    fun calculate_20_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text)) //금액 입력 텍스트 필드 참조.
            .perform(typeText("50.00")) //TextInputEditText에 50.00 입력.

        onView(withId(R.id.calculate_button)) //계산 버튼 참조.
            .perform(click()) //클릭.

        onView(withId(R.id.tip_result)) //결과를 보여주는 텍스트 참조.
            .check(matches(withText(containsString("$10.00")))) //"$10.00" 문자열을 포함하고 있는지 확인.
    }
}