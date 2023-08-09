package com.learning.storeapp.data.repository.datasource

import com.learning.storeapp.data.model.ProductApiModel
import kotlin.Exception


class ProductRemoteDataSource(private val serviceAPI: ProductApi) {
    suspend fun fetchProducts(): List<ProductApiModel> {
        try {
            val response = serviceAPI.fetch()
            if (response.isSuccessful && response.body() != null) {
                return response.body()!!
            }
            throw java.lang.Exception("Erro desconhecido!")
        } catch (ex: Exception) {
            throw ex
        }

    }

}