package com.gettingold.firebase.ui.screens.listscreen

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gettingold.firebase.R
import com.gettingold.firebase.data.remote.User
import kotlin.math.roundToInt

const val ANIMATION_DURATION = 500
const val MIN_DRAG_AMOUNT = 6

@OptIn(ExperimentalTransitionApi::class)
@Composable
fun ProfileCard(
    user: User,
    isSwiped: Boolean,
    cardHeight: Dp,
    cardOffset: Float,
    onExpand: () -> Unit = {},
    onCollapse: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val transitionState = rememberTransition(
        transitionState = MutableTransitionState(initialState = isSwiped)
            .apply { targetState = !isSwiped })
    val transition = updateTransition(transitionState, "cardTransition")
    val cardBgColor = transition.animateColor(
        label = "cardBgColorTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = {
            if (it.currentState) Color.Yellow else Color.Gray
        }
    )
    val offsetTransition = transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (it.currentState) cardOffset else 0f },
    )
    val cardElevation = transition.animateDp(
        label = "cardElevation",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (it.currentState) 40.dp else 2.dp }
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.swipe_ui_padding_horizontal),
                vertical = dimensionResource(R.dimen.swipe_ui_padding_vertical)
            )
            .height(cardHeight)
            .offset(offsetTransition.value.roundToInt().dp, 0.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        dragAmount >= MIN_DRAG_AMOUNT -> onExpand()
                        dragAmount < -MIN_DRAG_AMOUNT -> onCollapse()
                    }
                }
            },
        colors = CardDefaults.cardColors(cardBgColor.value),
        elevation = CardDefaults.cardElevation(cardElevation.value),
        shape = RoundedCornerShape(0.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.profile_card_padding_start))
        ) {
            Row {
                Text(text = user.name, modifier = Modifier.padding(end = dimensionResource(R.dimen.profile_card_name_padding_end)))
                Text(text = user.age)
            }
            Text(text = user.phoneNum)
        }
    }
}