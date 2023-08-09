package com.learning.storeapp.data.api

import com.learning.storeapp.data.repository.datasource.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    private const val BASE_URL = "https://fakestoreapi.com/"

    private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitProductService : ProductApi by lazy {
        retrofit.create(ProductApi::class.java)
    }
}