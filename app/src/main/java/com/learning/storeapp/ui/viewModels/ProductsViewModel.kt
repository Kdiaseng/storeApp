package com.learning.storeapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.storeapp.data.repository.ProductRepository
import com.learning.storeapp.ui.model.ProductUiState
import com.learning.storeapp.ui.model.toProductUiStaList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    fun fetchProducts() {
        _uiState.update {
            it.copy(isFetchingProducts = true)
        }
        viewModelScope.launch {
            try {
                val products = productRepository.fetch()
                _uiState.update { productUiState ->
                    productUiState.copy(
                        products = products.toProductUiStaList(),
                        isFetchingProducts = false
                    )
                }
                for (product in uiState.value.products) {
                    Log.e("PRODUTO", product.title)
                }
            } catch (ex: Exception) {
                Log.e("PRODUTO", ex.message.toString())
            }
        }

    }
}