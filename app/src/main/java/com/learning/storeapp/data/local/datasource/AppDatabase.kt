package com.learning.storeapp.data.local.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.learning.storeapp.data.local.ProductEntity
import com.learning.storeapp.data.repository.datasource.Converter

@TypeConverters(Converter::class)
@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        fun getInstance(context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java, "store-db"
            ).build()
    }
}