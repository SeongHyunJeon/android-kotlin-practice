package com.example.restaurant.ui

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurant.R
import com.example.restaurant.data.DistrictType
import com.example.restaurant.data.Restaurant
import com.example.restaurant.ui.utils.RestaurantContentType
import com.example.restaurant.ui.utils.RestaurantNavigationType

@Composable
fun RestaurantHomeScreen(
    navigationType: RestaurantNavigationType,
    contentType: RestaurantContentType,
    restaurantUiState: RestaurantUiState,
    onClickBackButton: () -> Unit,
    onClickNavigationTab: (DistrictType) -> Unit,
    onClickDetailsIcon: (Restaurant) -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            districtType = DistrictType.Seogu,
            text = stringResource(R.string.navigation_tab_seogu),
            character = painterResource(R.drawable.seogu)
        ),
        NavigationItemContent(
            districtType = DistrictType.Junggu,
            text = stringResource(R.string.navigation_tab_junggu),
            character = painterResource(R.drawable.junggu)
        ),
        NavigationItemContent(
            districtType = DistrictType.Donggu,
            text = stringResource(R.string.navigation_tab_donggu),
            character = painterResource(R.drawable.donggu)
        ),
        NavigationItemContent(
            districtType = DistrictType.Namdonggu,
            text = stringResource(R.string.navigation_tab_namdonggu),
            character = painterResource(R.drawable.namdonggu)
        ),
        NavigationItemContent(
            districtType = DistrictType.Bupyeonggu,
            text = stringResource(R.string.navigation_tab_bupyeonggu),
            character = painterResource(R.drawable.bupyeonggu)
        ),
        NavigationItemContent(
            districtType = DistrictType.Yeonsugu,
            text = stringResource(R.string.navigation_tab_yeonsugu),
            character = painterResource(R.drawable.yeonsugu)
        ),
        NavigationItemContent(
            districtType = DistrictType.Michuholgu,
            text = stringResource(R.string.navigation_tab_michuholgu),
            character = painterResource(R.drawable.michuholgu)
        ),
        NavigationItemContent(
            districtType = DistrictType.Gyeyanggu,
            text = stringResource(R.string.navigation_tab_gyeyanggu),
            character = painterResource(R.drawable.gyeyanggu)
        )
    )
    if (navigationType == RestaurantNavigationType.PERMANENT_NAVIGATION_DRAWER) { //대형 화면 이라면,
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(modifier = Modifier.width(dimensionResource(R.dimen.restaurant_navigation_permanent_width))) {
                    RestaurantNavigationPermanentContent(
                        currentSelectedDistrictType = restaurantUiState.currentSelectedDistrictType,
                        navigationItemContentList = navigationItemContentList,
                        onClickNavigationTab = onClickNavigationTab,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        ) {
            RestaurantHomeScreenContent(
                navigationType = navigationType,
                restaurantUiState = restaurantUiState,
                navigationItemContentList = navigationItemContentList,
                onClickNavigationTab = onClickNavigationTab,
                onClickDetailsIcon = onClickDetailsIcon,
                contentType = contentType
            )
        }
    } else { //대형 화면이 아니라면
        if (restaurantUiState.isHomeScreen) { //홈 화면 이라면
            RestaurantHomeScreenContent(
                navigationType = navigationType,
                restaurantUiState = restaurantUiState,
                navigationItemContentList = navigationItemContentList,
                onClickNavigationTab = onClickNavigationTab,
                onClickDetailsIcon = onClickDetailsIcon,
                contentType = contentType
            )
        } else {
            RestaurantDetailScreen(
                restaurantUiState = restaurantUiState,
                onBackButtonClick = onClickBackButton,
                contentType = contentType
            )
        }
    }
}

@Composable
fun RestaurantHomeScreenContent(
    navigationType: RestaurantNavigationType,
    restaurantUiState: RestaurantUiState,
    navigationItemContentList: List<NavigationItemContent>,
    onClickNavigationTab: (DistrictType) -> Unit,
    onClickDetailsIcon: (Restaurant) -> Unit,
    contentType: RestaurantContentType,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {
        AnimatedVisibility(visible = navigationType == RestaurantNavigationType.NAVIGATION_RAIL) {
            RestaurantNavigationRail(
                currentSelectedDistrictType = restaurantUiState.currentSelectedDistrictType,
                navigationItemContentList = navigationItemContentList,
                onClickNavigationTab = onClickNavigationTab,
                modifier = Modifier
            )
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (contentType == RestaurantContentType.LIST_AND_DETAIL) {
                RestaurantListAndDetail(
                    restaurantUiState = restaurantUiState,
                    onClickDetailsIcon = onClickDetailsIcon,
                    contentType = contentType
                )
            } else {
                RestaurantListOnly(
                    restaurantUiState = restaurantUiState,
                    onClickDetailsIcon = onClickDetailsIcon
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(visible = navigationType == RestaurantNavigationType.BOTTOM_NAVIGATION) {
                RestaurantNavigationBottom(
                    currentSelectedDistrictType = restaurantUiState.currentSelectedDistrictType,
                    navigationItemContentList = navigationItemContentList,
                    onClickNavigationTab = onClickNavigationTab
                )
            }
        }
    }
}

@Composable
fun RestaurantListAndDetail(
    restaurantUiState: RestaurantUiState,
    onClickDetailsIcon: (Restaurant) -> Unit,
    contentType: RestaurantContentType,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.weight(0.5f)
        ) {
            items(
                restaurantUiState.currentRestaurantList,
                key = { restaurant -> restaurant.id }
            ) {
                RestaurantListItem(
                    foodPainting = it.foodPainting,
                    name = it.name,
                    simpleAddress = it.simpleAddress,
                    signature = it.signature,
                    onClickDetailsIcon = { onClickDetailsIcon(it) }
                )
            }
        }
        val activity = LocalContext.current as Activity
        if (restaurantUiState.isHomeScreen) {
            Spacer(modifier = Modifier.weight(0.5f))
        } else {
            RestaurantDetailScreen(
                restaurantUiState = restaurantUiState,
                onBackButtonClick = { activity.finish() },
                contentType = contentType,
                modifier = Modifier.weight(0.5f)
            )
        }
    }
}

@Composable
fun RestaurantListOnly(
    restaurantUiState: RestaurantUiState,
    onClickDetailsIcon: (Restaurant) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            RestaurantTopBar(
                currentSelectedDistrictType = restaurantUiState.currentSelectedDistrictType,
                isHomeScreen = restaurantUiState.isHomeScreen,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        items(
            restaurantUiState.currentRestaurantList,
            key = { restaurant -> restaurant.id }
        ) {
            RestaurantListItem(
                foodPainting = it.foodPainting,
                name = it.name,
                simpleAddress = it.simpleAddress,
                signature = it.signature,
                onClickDetailsIcon = { onClickDetailsIcon(it) }
            )
        }
    }
}

@Composable
fun RestaurantNavigationPermanentContent(
    currentSelectedDistrictType: DistrictType,
    navigationItemContentList: List<NavigationItemContent>,
    onClickNavigationTab: (DistrictType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.incheonmetropolitancity),
            contentDescription = "",
            modifier = Modifier
                .padding(dimensionResource(R.dimen.restaurant_navigation_permanent_incheon_symbol_padding))
                .fillMaxWidth()
        )
        navigationItemContentList.forEach {
            NavigationDrawerItem(
                label = {
                    Text(
                        text = it.text,
                        style = MaterialTheme.typography.headlineMedium
                        ) },
                icon = {
                    Icon(
                        painter = it.character,
                        contentDescription = "",
                        modifier = Modifier.size(dimensionResource(R.dimen.restaurant_navigation_permanent_icon_size))) },
                colors = NavigationDrawerItemDefaults.colors(selectedIconColor = Color.Unspecified, unselectedIconColor = Color.Unspecified),
                selected = currentSelectedDistrictType == it.districtType,
                onClick = { onClickNavigationTab(it.districtType) },
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.restaurant_navigation_permanent_item_padding_bottom))
            )
        }
    }
}


@Composable
fun RestaurantNavigationBottom(
    currentSelectedDistrictType: DistrictType,
    navigationItemContentList: List<NavigationItemContent>,
    onClickNavigationTab: (DistrictType) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        for (navigationItem in navigationItemContentList) {
            val selected =
                currentSelectedDistrictType == navigationItem.districtType
            NavigationBarItem(
                selected = false,
                onClick = { onClickNavigationTab(navigationItem.districtType) },
                icon = {
                    Text(
                        text = navigationItem.text,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        textAlign = TextAlign.Center,
                    )
                },
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}

@Composable
fun RestaurantNavigationRail(
    currentSelectedDistrictType: DistrictType,
    navigationItemContentList: List<NavigationItemContent>,
    onClickNavigationTab: (DistrictType) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier
    ) {
        navigationItemContentList.forEach {
            NavigationRailItem(
                selected = currentSelectedDistrictType == it.districtType,
                onClick = { onClickNavigationTab(it.districtType) },
                icon = {
                    Image(
                        painter = it.character,
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center,
                        modifier = Modifier
                    )
                },
                modifier = Modifier
                    .padding(vertical = 15.dp)
            )
        }
    }
}

data class NavigationItemContent(
    val districtType: DistrictType,
    val text: String,
    val character: Painter
)