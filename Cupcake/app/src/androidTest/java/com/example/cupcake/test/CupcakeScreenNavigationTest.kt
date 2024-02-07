/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake.test

import android.icu.util.Calendar
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import com.example.cupcake.R
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale

class CupcakeScreenNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CupcakeApp(navController = navController)
        }
    }

    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    } //시작 화면의 경로 확인.

    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val upBtnText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(upBtnText).assertDoesNotExist()
    } //시작 화면에서 위로 버튼이 존재하지 않음을 확인.

    @Test
    fun cupcakeNavHost_clickOneCupcake_navigateToSelectFlavorScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake).performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    } //시작 화면에서 수량 버튼을 클릭하여 맛 선택 화면으로 이동 했는지 경로 확인.

    @Test
    fun cupcakeNavHost_clickNextOnFlavorScreen_navigatesToPickupScreen() {
        navigateToPickupScreen()
        navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
    } //맛 선택 화면에서 다음 버튼을 눌러 수령 날짜 화면으로 이동 했는지 경로 확인.

    @Test
    fun cupcakeNavHost_clickNextOnPickupScreen_navigatesToPickupScreen() {
        navigateToSummaryScreen()
        navController.assertCurrentRouteName(CupcakeScreen.Summary.name)
    } //수령 날짜 화면에서 다음 버튼을 눌러 주문 요약 화면으로 이동 했는지 경로 확인.

    @Test
    fun cupcakeNavHost_clickBackOnFlavorScreen_navigatesToStartOrderScreen() {
        navigateToFlavorScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    } //맛 선택 화면에서 위로 버튼을 눌러 첫 화면으로 이동 했는지 경로 확인.

    @Test
    fun cupcakeNavHost_clickCancelOnFlavorScreen_navigatesToStartOrderScreen() {
        navigateToFlavorScreen()
        clickCancelButton()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    } //맛 선택 화면에서 취소 버튼을 눌러 첫 화면으로 이동 했는지 경로 확인.

    @Test
    fun cupcakeNavHost_clickBackOnPickupScreen_navigatesToFlavorScreen() {
        navigateToPickupScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    } //수령 날짜 화면에서 위로 버튼을 눌러 맛 선택 화면으로 이동 했는지 경로 확인.

    @Test
    fun cupcakeNavHost_clickCancelOnPickupScreen_navigateToStartOrderScreen() {
        navigateToPickupScreen()
        clickCancelButton()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    } //수령 날짜 화면에서 취소 버튼을 눌러 첫 화면으로 이동 했는지 경로 확인.

    @Test
    fun cupcakeNavHost_clickCancelOnSummaryScreen_navigatesToStartOrderScreen() {
        navigateToSummaryScreen()
        clickCancelButton()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    } //주문 요약 화면에서 취소 버튼을 눌러 첫 화면으로 이동 했는지 경로 확인.

    private fun navigateToFlavorScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake).performClick()
        composeTestRule.onNodeWithStringId(R.string.chocolate).performClick()
    } //맛 화면으로 이동하여 초콜릿 맛을 선택하는 도우미 메서드.

    private fun navigateToPickupScreen() {
        navigateToFlavorScreen()
        clickNextButton()
        composeTestRule.onNodeWithText(getFormattedDate()).performClick()
    } //수령 날짜 선택 화면으로 이동하여 다음 날을 선택하는 도우미 메서드.

    private fun navigateToSummaryScreen() {
        navigateToPickupScreen()
        clickNextButton()
    } //주문 요약 화면으로 이동하는 도우미 메서드.

    private fun getFormattedDate(): String {
        val calendar = java.util.Calendar.getInstance()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())

        calendar.add(java.util.Calendar.DATE, 1)
        return formatter.format(calendar.time)
    } //날짜 형식의 문자열 반환하는 도우미 메서드.

    private fun performNavigateUp() {
        val upBtnText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(upBtnText).performClick()
    } //위로 버튼을 눌러 이전 화면으로 이동하는 도우미 메서드.

    private fun clickNextButton() {
        composeTestRule.onNodeWithStringId(R.string.next).performClick()
    } //다음 버튼을 클릭하는 도우미 메서드.

    private fun clickCancelButton() {
        composeTestRule.onNodeWithStringId(R.string.cancel).performClick()
    } //취소 버튼을 클릭하는 도우미 메서드.
}
