package com.example.products.data.repository

import com.example.products.data.api.ProductAPIs
import com.example.products.data.api.model.Product
import javax.inject.Inject

class ProductsRepo @Inject constructor(private val productApi: ProductAPIs) {
    suspend fun getProductList(): List<Product> {
        return productApi.getProducts().products
    }
}