package com.example.marsphotos.network

import retrofit2.http.GET

interface MarsApiService {
    @GET("photos") //Retrofit에 이 메서드가 GET요청이고 EndPoint가 photos임을 알린다.
    suspend fun getPhotos(): List<MarsPhoto>
}