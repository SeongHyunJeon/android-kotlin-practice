package com.gettingold.firebase.ui.screens.addscreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gettingold.firebase.ui.navigation.UserAddDestination
import com.gettingold.firebase.ui.screens.common.UserInformationForm
import com.gettingold.firebase.ui.screens.common.UserTopAppBar
import com.gettingold.firebase.ui.viewmodel.UserAddViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAddScreen(
    viewModel: UserAddViewModel,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val addUiState = viewModel.userAddUiState

    Scaffold(
        modifier = modifier,
        topBar = {
            UserTopAppBar(
                title = stringResource(UserAddDestination.titleRes),
                canNavigateBack = true,
                navigateBack = navigateBack
            )
        }
    ) { paddingValues ->
        UserInformationForm(
            userEntryUiState = addUiState,
            updateUser = viewModel::updateUiState,
            paddingValues = paddingValues,
            addUser = viewModel::addUser,
            navigateBack = navigateBack
        )
    }
}