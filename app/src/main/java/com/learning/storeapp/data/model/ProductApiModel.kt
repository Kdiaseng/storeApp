package com.learning.storeapp.data.model


data class ProductsApiModel(val data: List<ProductApiModel>)
data class ProductApiModel(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
    val rating: Rating
)

data class Rating(val rate: Double, val count: Int)
