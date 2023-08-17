package com.learning.storeapp.data.local.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.learning.storeapp.data.local.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    suspend fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM PRODUCT WHERE itemMarked =:isFavorite")
    suspend fun getProductById(isFavorite: Boolean): List<ProductEntity>

    @Insert
    suspend fun insertAll(vararg products: ProductEntity)

    @Delete
    suspend fun delete(product: ProductEntity)

    @Update
    suspend fun update(vararg products: ProductEntity)
}