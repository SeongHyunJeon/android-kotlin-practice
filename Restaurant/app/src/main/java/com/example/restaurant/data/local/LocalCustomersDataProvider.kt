package com.example.restaurant.data.local

import androidx.compose.ui.res.stringResource
import com.example.restaurant.R
import com.example.restaurant.data.Customer

object LocalCustomersDataProvider {
    private val allCustomers = listOf(
        Customer(
            id = 0L,
            visitRestaurantId = 0L,
            name = R.string.jangteo_customer1_name,
            visitingDay = R.string.jangteo_customer1_visit_day,
            comment = R.string.jangteo_customer1_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 1L,
            visitRestaurantId = 0L,
            name = R.string.jangteo_customer2_name,
            visitingDay = R.string.jangteo_customer2_visit_day,
            comment = R.string.jangteo_customer2_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 2L,
            visitRestaurantId = 0L,
            name = R.string.jangteo_customer3_name,
            visitingDay = R.string.jangteo_customer3_visit_day,
            comment = R.string.jangteo_customer3_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 0L,
            visitRestaurantId = 1L,
            name = R.string.araenyeok_customer1_name,
            visitingDay = R.string.araenyeok_customer1_visit_day,
            comment = R.string.araenyeok_customer1_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 1L,
            visitRestaurantId = 1L,
            name = R.string.araenyeok_customer2_name,
            visitingDay = R.string.araenyeok_customer2_visit_day,
            comment = R.string.araenyeok_customer2_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 2L,
            visitRestaurantId = 1L,
            name = R.string.araenyeok_customer3_name,
            visitingDay = R.string.araenyeok_customer3_visit_day,
            comment = R.string.araenyeok_customer3_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 0L,
            visitRestaurantId = 2L,
            name = R.string.jincheon_customer1_name,
            visitingDay = R.string.jincheon_customer1_visit_day,
            comment = R.string.jincheon_customer1_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 1L,
            visitRestaurantId = 2L,
            name = R.string.jincheon_customer2_name,
            visitingDay = R.string.jincheon_customer2_visit_day,
            comment = R.string.jincheon_customer2_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 2L,
            visitRestaurantId = 2L,
            name = R.string.jincheon_customer3_name,
            visitingDay = R.string.jincheon_customer3_visit_day,
            comment = R.string.jincheon_customer3_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 0L,
            visitRestaurantId = 3L,
            name = R.string.cheongshill_customer1_name,
            visitingDay = R.string.cheongshill_customer1_visit_day,
            comment = R.string.cheongshill_customer1_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 1L,
            visitRestaurantId = 3L,
            name = R.string.cheongshill_customer2_name,
            visitingDay = R.string.cheongshill_customer2_visit_day,
            comment = R.string.cheongshill_customer2_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 2L,
            visitRestaurantId = 3L,
            name = R.string.cheongshill_customer3_name,
            visitingDay = R.string.cheongshill_customer3_visit_day,
            comment = R.string.cheongshill_customer3_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 0L,
            visitRestaurantId = 4L,
            name = R.string.inhyun_customer1_name,
            visitingDay = R.string.inhyun_customer1_visit_day,
            comment = R.string.inhyun_customer1_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 1L,
            visitRestaurantId = 4L,
            name = R.string.inhyun_customer2_name,
            visitingDay = R.string.inhyun_customer2_visit_day,
            comment = R.string.inhyun_customer2_comment,
            avatar = R.drawable.profile
        ),
        Customer(
            id = 2L,
            visitRestaurantId = 4L,
            name = R.string.inhyun_customer3_name,
            visitingDay = R.string.inhyun_customer3_visit_day,
            comment = R.string.inhyun_customer3_comment,
            avatar = R.drawable.profile
        ),
    )

    fun getProperCustomers(resId: Long): List<Customer> {
        return allCustomers.filter { it.visitRestaurantId == resId }
    }
}