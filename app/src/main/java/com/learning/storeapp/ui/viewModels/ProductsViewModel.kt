package com.learning.storeapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.storeapp.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductsViewModel(private val productRepository: ProductRepository) : ViewModel() {

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val products = productRepository.fetch()
                for (product in products) {
                    Log.e("PRODUTO", product.title)
                }
            } catch (ex: Exception) {
                Log.e("PRODUTO", ex.message.toString())
            }
        }

    }
}