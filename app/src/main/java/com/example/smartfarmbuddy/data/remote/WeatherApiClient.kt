package com.example.smartfarmbuddy.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient

object WeatherApiClient {
    // API配置
    private const val BASE_URL = "https://mm78m3crdt.re.qweatherapi.com/"
    const val API_KEY = "00ae4d64dd924ee7b6e105c43b05146a"
    
    // 创建 Moshi 实例
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    
    // 创建 OkHttpClient
    private val okHttpClient = OkHttpClient.Builder()
        .build()
    
    // 创建 Retrofit 实例
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    
    // 创建 API Service
    val apiService: WeatherApiService = retrofit.create(WeatherApiService::class.java)
}