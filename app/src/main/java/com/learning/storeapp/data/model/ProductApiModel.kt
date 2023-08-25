package com.learning.storeapp.data.model

import com.learning.storeapp.data.local.ProductEntity


data class ProductsApiModel(val data: List<ProductApiModel>)
data class ProductApiModel(
    val id: Int,
    val title: String = "",
    val description: String = "",
    val price: Double = 2.5,
    val category: String = "",
    val image: String = "",
    val rating: Rating
)

data class Rating(val rate: Double, val count: Int)


fun ProductApiModel.toEntity() = ProductEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    price = this.price,
    image = null,
    ratingCount = this.rating.count,
    ratingRate = this.rating.rate
)

fun List<ProductApiModel>.toEntity() = this.map { it.toEntity() }
