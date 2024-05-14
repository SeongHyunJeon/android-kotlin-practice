package com.gettingold.firebase.ui.screens.editscreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gettingold.firebase.ui.navigation.UserEditDestination
import com.gettingold.firebase.ui.screens.common.UserTopAppBar
import com.gettingold.firebase.ui.screens.common.UserInformationForm
import com.gettingold.firebase.ui.viewmodel.UserEditViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserEditScreen(
    viewModel: UserEditViewModel,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.userEditUiState

    Scaffold(
        modifier = modifier,
        topBar = {
            UserTopAppBar(
                title = stringResource(UserEditDestination.titleRes),
                canNavigateBack = true,
                navigateBack = navigateBack
            )
        }
    ) { paddingValues ->
        UserInformationForm(
            userEntryUiState = uiState,
            updateUser = viewModel::updateUiState,
            paddingValues = paddingValues,
            editUser = viewModel::editUser,
            navigateBack = navigateBack
        )
    }
}