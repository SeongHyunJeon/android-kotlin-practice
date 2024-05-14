package com.gettingold.firebase.ui.screens.listscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.gettingold.firebase.R

@Composable
fun SwipeTextButton(
    textSize: Dp,
    navigateToEditScreen: () -> Unit,
    deleteUser: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .padding(dimensionResource(R.dimen.swipe_ui_padding_horizontal), dimensionResource(R.dimen.swipe_ui_padding_vertical))
    ) {
        TextButton(
            onClick = { navigateToEditScreen() },
            modifier = Modifier
                .size(textSize)
                .background(color = Color.Gray)
        ) {
            Text(text = stringResource(R.string.user_swipe_button_edit))
        }
        TextButton(
            onClick = { deleteUser() },
            modifier = Modifier
                .size(textSize)
                .background(color = Color.Red)
        ) {
            Text(text = stringResource(R.string.user_swipe_button_delete))
        }
    }
}