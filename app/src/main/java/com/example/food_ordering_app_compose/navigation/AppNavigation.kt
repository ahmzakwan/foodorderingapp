//package com.example.food_ordering_app_compose.navigation
//
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.food_ordering_app_compose.screens.LoginScreen
//import com.example.food_ordering_app_compose.screens.RegisterScreen
//import com.example.food_ordering_app_compose.viewmodel.AuthViewModel
//import com.example.food_ordering_app_compose.screens.HomeScreen
//
//@Composable
//fun AppNavigation(authViewModel: AuthViewModel) {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "login") {
//        composable("login") {
//            LoginScreen(authViewModel = authViewModel) {
//                navController.navigate("home") {
//                    popUpTo("login") { inclusive = true }
//                }
//            }
//        }
//        composable("register") {
//            RegisterScreen(onRegisterClick = { username, password ->
//                // Here you can call registration logic (to be implemented)
//                navController.navigate("login")
//            })
//        }
//        composable("home") {
//            HomeScreen(onLogout = {
//                authViewModel.logout()
//                navController.navigate("login") {
//                    popUpTo("home") { inclusive = true }
//                }
//            })
//        }
//    }
//}