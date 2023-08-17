package com.learning.storeapp.data.local

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity("product")
data class ProductEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "price") val price: Double?,
    @ColumnInfo(name = "image") val image: Bitmap?,
    @ColumnInfo(name = "rating_count") val ratingCount: Int?,
    @ColumnInfo(name = "rating_rate") val ratingRate: Double?,
    @ColumnInfo(name = "itemMarked") val itemMarked: Boolean = false,
)



