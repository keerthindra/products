package com.example.products.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.products.data.api.model.Product

@Composable
fun ProductDetails(
    productId: Int, navigateUp: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val product = state.find { it.id == productId }

    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            product?.let { ProductImageHeader(product = it, navigateUp) }
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = product?.title.toString(),
                    fontSize = 30.sp,
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                )
                Text(
                    text = "Description: " + product?.description.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                )
                Text(
                    text = "Rating: " + product?.rating.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                )
                Text(
                    text = "Category: " + product?.title.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                )
                Text(
                    text = "Price: ₹" + product?.title.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                )
            }
        }
    }
}

@Composable
fun ProductImageHeader(product: Product, navigateUp: () -> Unit) {
    Box(
        modifier = Modifier.padding(10.dp)

    ) {
        Icon(
            painter = rememberImagePainter(product.images[0]),
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            tint = Color.Unspecified
        )
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.Black
        ) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back Icon"
                )
            }
        }
    }
}