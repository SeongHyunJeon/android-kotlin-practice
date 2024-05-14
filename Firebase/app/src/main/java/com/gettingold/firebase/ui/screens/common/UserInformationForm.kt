package com.gettingold.firebase.ui.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.gettingold.firebase.R
import com.gettingold.firebase.data.remote.User
import com.gettingold.firebase.ui.viewmodel.UserEntryUiState

@Composable
fun UserInformationForm(
    userEntryUiState: UserEntryUiState,
    updateUser: (User) -> Unit,
    paddingValues: PaddingValues,
    editUser: () -> Unit = {},
    addUser: () -> Unit = {},
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val user = userEntryUiState.user

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = user.name,
            onValueChange = { updateUser(user.copy(name = it)) },
            label = { Text(text = stringResource(R.string.form_label_user_name)) },
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
        )
        OutlinedTextField(
            value = user.phoneNum,
            onValueChange = { updateUser(user.copy(phoneNum = it)) },
            label = { Text(text = stringResource(R.string.form_label_user_phoneNum)) },
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
        )
        OutlinedTextField(
            value = user.age,
            onValueChange = { updateUser(user.copy(age = it)) },
            label = { Text(text = stringResource(R.string.form_label_user_age)) },
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
        )
        if(user.id.isEmpty()){
            Button(onClick = {
                navigateBack()
                addUser() },
                enabled = userEntryUiState.validInput,
                modifier = Modifier
                    .width(dimensionResource(R.dimen.info_form_button_width))
                    .aspectRatio(3f)
            ) {
                Text(text = stringResource(R.string.form_button_add))
            }
        } else {
            Button(onClick = {
                navigateBack()
                editUser() },
                enabled = userEntryUiState.validInput,
            ) {
                Text(text = stringResource(R.string.form_button_update))
            }
        }
    }
}
