package com.learning.storeapp.data.repository.datasource

import com.learning.storeapp.data.model.ProductApiModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun fetch(): Response<List<ProductApiModel>>
}