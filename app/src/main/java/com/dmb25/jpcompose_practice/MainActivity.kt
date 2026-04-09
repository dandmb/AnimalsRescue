package com.dmb25.jpcompose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dmb25.jpcompose_practice.presentation.mapper.toRoute
import com.dmb25.jpcompose_practice.presentation.navigation.Screen
import com.dmb25.jpcompose_practice.presentation.theme.JpcomposepracticeTheme
import com.dmb25.jpcompose_practice.presentation.ui.detail.DetailScreen
import com.dmb25.jpcompose_practice.presentation.ui.detail.DetailViewModel
import com.dmb25.jpcompose_practice.presentation.ui.home.HomeScreen
import com.dmb25.jpcompose_practice.presentation.ui.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            JpcomposepracticeTheme {
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {

                    composable("home") {
                        val homeViewModel: HomeViewModel = koinViewModel()
                        HomeScreen(
                            onPetClick = { id ->
                                navController.navigate(
                                    Screen.Detail(id).toRoute()
                                )
                            },
                            onToggle = {  },
                            viewModel = homeViewModel
                        )
                    }

                    composable(
                        route = "detail/{petId}",
                        arguments = listOf(
                            navArgument("petId") { type = NavType.IntType }
                        )
                    ) {
                        val detailViewModel: DetailViewModel = koinViewModel()
                        DetailScreen(
                            onNavigateUp = { navController.navigateUp() },
                            viewModel = detailViewModel
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JpcomposepracticeTheme {
        Greeting("Android")
    }
}