package com.learning.storeapp.data.repository

import com.learning.storeapp.data.model.ProductApiModel
import com.learning.storeapp.data.repository.datasource.ProductRemoteDataSource

class ProductRepository(private val remoteDataSource: ProductRemoteDataSource) {
    suspend fun fetch(): List<ProductApiModel> {
        try {
            return remoteDataSource.fetchProducts()
        }catch (ex: Exception){
            throw ex
        }
    }
}