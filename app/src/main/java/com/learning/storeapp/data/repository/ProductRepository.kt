package com.learning.storeapp.data.repository

import com.learning.storeapp.data.local.datasource.ProductLocalDataSource
import com.learning.storeapp.data.model.ProductApiModel
import com.learning.storeapp.data.model.toEntity
import com.learning.storeapp.data.repository.datasource.ProductRemoteDataSource

class ProductRepository(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductLocalDataSource
) {
    suspend fun fetch(): List<ProductApiModel> {
        try {
            val response = remoteDataSource.fetchProducts()
            val productsArray = response.toEntity().toTypedArray()
            localDataSource.saveProducts(*productsArray)
            return response
        } catch (ex: Exception) {
            throw ex
        }
    }
}