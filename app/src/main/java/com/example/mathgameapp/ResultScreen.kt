package com.example.mathgameapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ResultScreen(navController: NavHostController, correct: Int, wrong: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🏆 Game Over!", style = MaterialTheme.typography.headlineLarge, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF444444)),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your Score:",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "🎉 Correct: $correct",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF4CAF50)
                )
                Text(
                    text = "😞 Wrong: $wrong",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFFFF5722)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.navigate("start") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0), contentColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(0.6f)
        ) {
            Text("🔄 Try Again")
        }
    }
}
