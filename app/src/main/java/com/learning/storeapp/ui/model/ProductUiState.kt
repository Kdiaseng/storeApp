package com.learning.storeapp.ui.model

import com.learning.storeapp.data.model.ProductApiModel


data class ProductUiState(
    val isFetchingProducts: Boolean = false,
    val products: List<ItemUiState> = listOf()
)

data class ItemUiState(
    val title: String,
    val description: String,
    val itemMarked: Boolean = false
)

fun ProductApiModel.toProductUiState() = ItemUiState(this.title, this.description)
fun List<ProductApiModel>.toProductUiStaList() = this.map { it.toProductUiState() }
