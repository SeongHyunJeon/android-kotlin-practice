package com.gettingold.firebase.ui.screens.listscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gettingold.firebase.R
import com.gettingold.firebase.ui.navigation.UserListDestination
import com.gettingold.firebase.ui.screens.common.UserTopAppBar
import com.gettingold.firebase.ui.viewmodel.UserListViewModel

const val TEXT_SIZE = 56
const val CARD_HEIGHT = 56
const val CARD_OFFSET = 112f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    viewModel: UserListViewModel,
    navigateToEditScreen: (String) -> Unit,
    navigateToAddScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val userListUiState = viewModel.userListUiState.collectAsState().value
    val swipedUserIdList = viewModel.swipedUserIdList.collectAsState().value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            UserTopAppBar(
                title = stringResource(UserListDestination.titleRes),
                canNavigateBack = false,
                deleteAllUsers = { viewModel.deleteAllUsers() },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToAddScreen() },
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.floating_button_size))
                    .padding(end = dimensionResource(R.dimen.floating_button_padding_end))
                    .padding(bottom = dimensionResource(R.dimen.floating_button_padding_bottom))
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = stringResource(R.string.floating_button),
                    modifier = Modifier.size(dimensionResource(R.dimen.floating_button_icon_size))
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            items(items = userListUiState.userList, key = { it.id }) { user ->
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SwipeTextButton(
                        textSize = TEXT_SIZE.dp,
                        navigateToEditScreen = { navigateToEditScreen(user.id) },
                        deleteUser = { viewModel.deleteUser(user.id) }
                    )
                    ProfileCard(
                        user = user,
                        isSwiped = swipedUserIdList.contains(user.id),
                        cardHeight = CARD_HEIGHT.dp,
                        cardOffset = CARD_OFFSET,
                        onExpand = { viewModel.addSwipedUserIntoList(user.id) },
                        onCollapse = { viewModel.removeSwipedUserIntoList(user.id) },
                    )
                }
            }
        }
    }
}