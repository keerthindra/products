package com.example.products.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.products.data.api.model.Product

@Composable
fun HomeScreen(selectedProduct: (Int) -> Unit) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LazyColumn {
        if (state.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }

        items(state) { product: Product ->
            ProductItem(product, selectedProduct)
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    selectedProduct: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 5.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = {
                    selectedProduct(product.id)
                })
        ) {
            ProductImage(product = product)
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Title: " + product.title.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp),
                )
                Text(
                    text = "Description: " + product.description.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp)
                )
                Text(
                    text = "Price: ₹" + product.price.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 10.dp)
                )
            }
//            Divider()
        }

    }
}

@Composable
fun ProductImage(product: Product) {
    Column (modifier = Modifier.padding(10.dp)
    ) {
        Icon(
            painter = rememberImagePainter(product.images[0]),
            contentDescription = null,
            modifier = Modifier
                .size(86.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
            tint = Color.Unspecified
        )
    }

}
