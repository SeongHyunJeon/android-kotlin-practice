package com.example.restaurant.data.local

import com.example.restaurant.R
import com.example.restaurant.data.DistrictType
import com.example.restaurant.data.Restaurant

object LocalRestaurantsDataProvider {
    val allRestaurants = listOf(
        Restaurant(
            id = 0L,
            name = R.string.jangteo_name,
            map = R.drawable.naver_map,
            foodPainting = R.drawable.barbecue,
            weekdayOpeningHours = R.string.jangteo_openinghours_weekday,
            weekendOpeningHours = R.string.jangteo_openinghours_weekend,
            number = R.string.jangteo_number,
            simpleAddress = R.string.jangteo_address_simple,
            detailedAddress = R.string.jangteo_address_detail,
            signature = R.string.jangteo_signature,
            customers = LocalCustomersDataProvider.getProperCustomers(0L),
            district = DistrictType.Seogu
        ),
        Restaurant(
            id = 1L,
            name = R.string.araenyeok_name,
            map = R.drawable.naver_map,
            foodPainting = R.drawable.chickensoup,
            weekdayOpeningHours = R.string.araenyeok_openinghours_weekday,
            weekendOpeningHours = R.string.araenyeok_openinghours_weekend,
            number = R.string.araenyeok_number,
            simpleAddress = R.string.araenyeok_address_simple,
            detailedAddress = R.string.araenyeok_address_detail,
            signature = R.string.araenyeok_signature,
            customers = LocalCustomersDataProvider.getProperCustomers(1L),
            district = DistrictType.Seogu
        ),
        Restaurant(
            id = 2L,
            name = R.string.jincheon_name,
            map = R.drawable.naver_map,
            foodPainting = R.drawable.sundae,
            weekdayOpeningHours = R.string.jincheon_openinghours_weekday,
            weekendOpeningHours = R.string.jincheon_openinghours_weekend,
            number = R.string.jincheon_number,
            simpleAddress = R.string.jincheon_address_simple,
            detailedAddress = R.string.jincheon_address_detail,
            signature = R.string.jincheon_signature,
            customers = LocalCustomersDataProvider.getProperCustomers(2L),
            district = DistrictType.Seogu
        ),
        Restaurant(
            id = 3L,
            name = R.string.cheongshill_name,
            map = R.drawable.naver_map,
            foodPainting = R.drawable.mandu,
            weekdayOpeningHours = R.string.cheongshill_openinghours_weekday,
            weekendOpeningHours = R.string.cheongshill_openinghours_weekend,
            number = R.string.cheongshill_number,
            simpleAddress = R.string.cheongshill_address_simple,
            detailedAddress = R.string.cheongshill_address_detail,
            signature = R.string.cheongshill_signature,
            customers = LocalCustomersDataProvider.getProperCustomers(3L),
            district = DistrictType.Junggu
        ),
        Restaurant(
            id = 4L,
            name = R.string.inhyun_name,
            map = R.drawable.naver_map,
            foodPainting = R.drawable.samgyetang,
            weekdayOpeningHours = R.string.inhyun_openinghours_weekday,
            weekendOpeningHours = R.string.inhyun_openinghours_weekend,
            number = R.string.inhyun_number,
            simpleAddress = R.string.inhyun_address_simple,
            detailedAddress = R.string.inhyun_address_detail,
            signature = R.string.inhyun_signature,
            customers = LocalCustomersDataProvider.getProperCustomers(4L),
            district = DistrictType.Junggu
        ),
    )
}