package com.example.products.data.api.model

data class ProductVo(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)