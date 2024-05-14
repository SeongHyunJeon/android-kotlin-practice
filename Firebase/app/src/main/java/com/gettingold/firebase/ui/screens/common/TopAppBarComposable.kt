package com.gettingold.firebase.ui.screens.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.gettingold.firebase.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTopAppBar(
    title: String,
    canNavigateBack: Boolean, //Boolean 값에 따라 뒤로 가기 아이콘 가시화 설정. 홈 화면만 false가 전달되는데, 이를 활용하여 전체 삭제 아이콘의 가시화 여부도 설정했다.
    navigateBack: () -> Unit = {},
    deleteAllUsers: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = title) },
        scrollBehavior = scrollBehavior,
        actions = {
            if (!canNavigateBack) {
                IconButton(onClick = { deleteAllUsers() }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.user_delete_all),
                        modifier = Modifier.size(dimensionResource(R.dimen.top_app_bar_rubbish_icon_size))
                    )
                }
            }
        },
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = { navigateBack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.navigate_back),
                    )
                }
            }
        },
        modifier = modifier
    )
}