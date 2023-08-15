package com.learning.storeapp.ui.viewModels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.storeapp.data.repository.ProductRepository
import com.learning.storeapp.ui.model.ProductUiState
import com.learning.storeapp.ui.model.UserMessage
import com.learning.storeapp.ui.model.toProductUiStaList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class ProductsViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()


    fun searchProduct(value: String) {
        Log.e("teste", value)
        if (value.isEmpty()) fetchProducts()
        _uiState.update { it.copy(isFetchingProducts = true) }
        _uiState.update { it ->
            val newListProducts = it.products.filter { it.title.contains(value, ignoreCase = true) }
            it.copy(products = newListProducts, isFetchingProducts = false)
        }
    }

    fun changeModeView(){
        _uiState.update {
            it.copy(isListModeView = !it.isListModeView)
        }
    }

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
            } catch (ex: Exception) {
                _uiState.update {
                    it.copy(isFetchingProducts = false)
                }
                _uiState.update { currentState ->
                    val messages = currentState.userMessages + UserMessage(
                        id = UUID.randomUUID().mostSignificantBits,
                        message = ex.message.toString()
                    )
                    currentState.copy(userMessages = messages)
                }
                Log.e("PRODUTO", ex.message.toString())
            }
        }
    }

    fun userMessageShown(messageId: Long) {
        _uiState.update { currentUiState ->
            val messages = currentUiState.userMessages.filterNot { it.id == messageId }
            Log.e("MESSAGE", messages.size.toString())
            currentUiState.copy(userMessages = messages)
        }
    }
}