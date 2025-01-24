package com.example.products

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.products.Destinations.PRODUCTS_DETAIL_ID_KEY
import com.example.products.Destinations.PRODUCTS_LIST_ROUTE
import com.example.products.Destinations.PRODUCT_DETAILS_ROUTE
import com.example.products.ui.home.HomeScreen
import com.example.products.ui.home.ProductDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavDetails()
        }
    }
}

@Composable
fun AppNavDetails(startDestination: String = PRODUCTS_LIST_ROUTE) {
    val navController = rememberNavController()
    val actions = remember(navController) {
        AppActions(navController)
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(PRODUCTS_LIST_ROUTE) {
            ProductsHome(selectedProduct = actions.selectedProduct, actions)
        }
        composable("${PRODUCT_DETAILS_ROUTE}/{$PRODUCTS_DETAIL_ID_KEY}",
            arguments = listOf(
                navArgument(PRODUCTS_DETAIL_ID_KEY) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            ProductDetails(
                productId = arguments.getInt(PRODUCTS_DETAIL_ID_KEY),
                navigateUp = actions.navigateUp
            )

        }
    }
}

@Composable
fun ProductsHome(selectedProduct: (Int) -> Unit, actions: AppActions) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Products",
            fontSize = 30.sp,
            modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
        )
        HomeScreen(selectedProduct)
    }
}

class AppActions(navController: NavController) {
    val selectedProduct: (Int) -> Unit = { productId: Int ->
        navController.navigate("${PRODUCT_DETAILS_ROUTE}/$productId")
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

}