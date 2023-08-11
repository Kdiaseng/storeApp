package com.learning.storeapp.ui.model

import com.learning.storeapp.data.model.ProductApiModel

data class UserMessage(val id: Long, val message: String)

data class ProductUiState(
    val isFetchingProducts: Boolean = false,
    val userMessages: List<UserMessage> = emptyList(),
    val products: List<ItemUiState> = listOf()
)

data class ItemUiState(
    val title: String,
    val description: String,
    val price: Double,
    val itemMarked: Boolean = false,
)

fun ProductApiModel.toProductUiState() = ItemUiState(this.title, this.description, this.price)
fun List<ProductApiModel>.toProductUiStaList() = this.map { it.toProductUiState() }
