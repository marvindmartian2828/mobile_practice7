package com.example.mathgameapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun StartScreen(navController: NavHostController) {
    var questionCount by remember { mutableStateOf("3") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🔢 Math Game App",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Enter number of questions:", style = MaterialTheme.typography.bodyLarge, color = Color.White)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = questionCount,
            onValueChange = { newValue -> questionCount = newValue.filter { it.isDigit() } },
            singleLine = true,
            textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                cursorColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val count = questionCount.toIntOrNull() ?: 3
                navController.navigate("question/$count")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3), contentColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text("🚀 Start Game")
        }
    }
}
