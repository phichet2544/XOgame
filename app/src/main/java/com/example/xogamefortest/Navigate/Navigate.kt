package com.example.xogamefortest.Navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.xogamefortest.Screen.GameScreen
import com.example.xogamefortest.Screen.HistoryScreen
import com.example.xogamefortest.Screen.MainScreen

@Composable
fun NavigationGrap(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "MainScreen" ){
//          ^^ ตั้งหน้าแรก
        composable("MainScreen"){
            MainScreen(navController)
        }
////            ^^ กดหนดชื่อให้แต่ละหน้าจอ
        composable("GameScreen/{num}") {
            val num = it.arguments?.getString("num")
            GameScreen(navController, num ?: "0")
        }
        composable("HistoryScreen/{time}/{select}/{timeselect}/{result}"){
            val time = it.arguments?.getString("time")
            val select = it.arguments?.getString("select")
            val timeselect = it.arguments?.getString("timeselect")
            val result = it.arguments?.getString("result")
            HistoryScreen(navController,time?:"",select?:"",timeselect?:"",result?:"")
        }


    }
}