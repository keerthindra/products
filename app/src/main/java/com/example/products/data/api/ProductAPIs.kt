package com.example.products.data.api

import com.example.products.data.api.model.ProductVo
import retrofit2.http.GET

interface ProductAPIs {

    @GET(ApiConstants.product_list)
    suspend fun getProducts(): ProductVo

}