package com.learning.storeapp.ui.model

import android.os.Parcel
import android.os.Parcelable
import com.learning.storeapp.data.model.ProductApiModel
import com.learning.storeapp.data.model.Rating
import kotlinx.android.parcel.Parcelize

data class UserMessage(val id: Long, val message: String)

data class ProductUiState(
    val isFetchingProducts: Boolean = false,
    val isListModeView: Boolean = true,
    val userMessages: List<UserMessage> = emptyList(),
    val products: List<ItemUiState> = listOf()
)

@kotlinx.parcelize.Parcelize
data class ItemUiState(
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val rating: RatingUiState,
    val itemMarked: Boolean = false,
) : Parcelable

@kotlinx.parcelize.Parcelize
data class RatingUiState(val rate: Double, val count: Int) : Parcelable

fun ProductApiModel.toProductUiState() =
    ItemUiState(
        this.title,
        this.description,
        this.price,
        this.image,
        RatingUiState(this.rating.rate, this.rating.count)
    )

fun List<ProductApiModel>.toProductUiStaList() = this.map { it.toProductUiState() }
