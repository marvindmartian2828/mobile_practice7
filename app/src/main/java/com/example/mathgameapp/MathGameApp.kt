package com.example.mathgameapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MathGameApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "start") {
        composable("start") { StartScreen(navController) }
        composable("question/{count}") { backStackEntry ->
            val count = backStackEntry.arguments?.getString("count")?.toIntOrNull() ?: 5
            QuestionScreen(navController, count)
        }
        composable("result/{correct}/{wrong}") { backStackEntry ->
            val correct = backStackEntry.arguments?.getString("correct")?.toIntOrNull() ?: 0
            val wrong = backStackEntry.arguments?.getString("wrong")?.toIntOrNull() ?: 0
            ResultScreen(navController, correct, wrong)
        }
    }
}
