package com.learning.storeapp.data.model

sealed class ResulRequest<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResulRequest<T>()
    data class Error(val message: String, val cause: Exception? = null) : ResulRequest<Nothing>()
}
