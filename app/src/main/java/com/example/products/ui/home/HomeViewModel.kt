package com.example.products.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.products.data.api.model.Product
import com.example.products.data.repository.ProductsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productsRepo: ProductsRepo) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<Product>())
    val state: MutableStateFlow<List<Product>>
        get() = _state

    init {
        viewModelScope.launch {
            val products = productsRepo.getProductList()
            _state.value = products
        }
    }
}