package com.learning.storeapp.data.local.datasource

import com.learning.storeapp.data.local.ProductEntity

class ProductLocalDataSource(private val dao: ProductDao) {

    suspend fun saveProducts(vararg products: ProductEntity) {
        dao.insertAll(*products)
    }
}