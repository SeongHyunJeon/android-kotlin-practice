package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import com.example.reply.R
import org.junit.Rule
import org.junit.Test

class ReplyAppStateRestorationTest {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailEmailRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule) //구성 변경으로 인한 UI상태를 테스트하기 위한 객체.
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Compact) }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertIsDisplayed() //세 번째 이메일이 출력되는지 확인.

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
        ).performClick() //세 번째 이메일을 클릭.

        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists() //이메일의 세부사항 화면인지 확인하기 위해 뒤로 가기 버튼이 존재하는지 확인.

        stateRestorationTester.emulateSavedInstanceStateRestore() //구성 변경 실행.

        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists() //뒤로 가기 버튼이 존재하는지 확인.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertExists() //같은 이메일을 출력하고 있는지 확인.
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_selectedEmailEmailRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule) //구성 변경으로 인한 UI상태를 테스트하기 위한 객체.
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Expanded) }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertIsDisplayed() //세 번째 이메일이 출력되는지 확인.

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
        ).performClick() //세 번째 이메일을 클릭.

        /**
         * onChildren() - 해당 노드의 직계 자식 노드들을 의미한다.
         * assertAny() - 해당 노드를 기준으로 주어진 조건을 만족하는 하위 노드가 존재하는지 확인한다.
         * hasAnyDescendant() - 해당 노드를 기준으로 재귀적으로 모든 하위 노드를 순회하여 조건을 만족하는 노드가 존재하는지 확인한다.
         * hasText() - 직계 자식 노드 중 해당 텍스트를 포함하고 있는 노드를 찾는다.

         = 특정 범위를 두고 어떤 조건을 만족하는 노드가 존재하는지 확인할 수 있는 테스트 코드.
         */
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body))))

        stateRestorationTester.emulateSavedInstanceStateRestore() //구성 변경 실행.

        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body))))
    }
}