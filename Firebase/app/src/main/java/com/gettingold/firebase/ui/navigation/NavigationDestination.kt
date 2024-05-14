package com.gettingold.firebase.ui.navigation

import com.gettingold.firebase.R

interface NavigationDestination {
    val route: String
    val titleRes: Int
}

object UserListDestination: NavigationDestination {
    override val route: String = "UserList"
    override val titleRes: Int = R.string.user_list
}

object UserAddDestination: NavigationDestination {
    override val route: String = "UserAdd"
    override val titleRes: Int = R.string.user_add
}

object UserEditDestination: NavigationDestination {
    override val route: String = "UserEdit"
    override val titleRes: Int = R.string.user_edit
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}