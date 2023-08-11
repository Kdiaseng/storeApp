package com.learning.storeapp.data.repository

import com.learning.storeapp.data.model.ProductApiModel
import com.learning.storeapp.data.repository.datasource.ProductRemoteDataSource

class ProductRepository(private val remoteDataSource: ProductRemoteDataSource) {
    suspend fun fetch(): List<ProductApiModel> {
        try {
            return listOf(
                ProductApiModel(1, "fdfd"),
                ProductApiModel(2, "fdfd"),
                ProductApiModel(3, "fdfd"),
                ProductApiModel(4, "fdfd"),
                ProductApiModel(5, "fdfd"),
                ProductApiModel(6, "fdfd"),
                ProductApiModel(7, "fdfd"),
                ProductApiModel(8, "fdfd"),
                ProductApiModel(9, "fdfd"),
                ProductApiModel(10, "fdfd"),
                ProductApiModel(11, "fdfd"),
                ProductApiModel(12, "fdfd"),
                ProductApiModel(13, "fdfd"),
                )
        } catch (ex: Exception) {
            throw ex
        }
    }
}