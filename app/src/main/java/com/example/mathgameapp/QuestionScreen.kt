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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun QuestionScreen(navController: NavHostController, count: Int, viewModel: GameViewModel = viewModel()) {
    val questions by viewModel.questions.collectAsState()
    var userAnswer by remember { mutableStateOf("") }
    val currentQuestionIndex = viewModel.correctAnswers + viewModel.wrongAnswers

    LaunchedEffect(Unit) { viewModel.generateQuestions(count) }

    if (questions.isEmpty()) {
        Text("Loading questions...", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        return
    }

    if (currentQuestionIndex >= count) {
        navController.navigate("result/${viewModel.correctAnswers}/${viewModel.wrongAnswers}")
        return
    }

    val (a, b) = questions[currentQuestionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ScoreCard(
                text = "Correct: ${viewModel.correctAnswers}",
                color = Color(0xFF4CAF50),
                emoji = "🏆"
            )
            Spacer(modifier = Modifier.width(12.dp))
            ScoreCard(
                text = "Wrong: ${viewModel.wrongAnswers}",
                color = Color(0xFFF44336),
                emoji = "👎"
            )
        }

        // Question Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF333333)),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$a + $b = ?",
                    style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.sp, fontWeight = FontWeight.Bold),
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(12.dp))


                OutlinedTextField(
                    value = userAnswer,
                    onValueChange = { newValue -> userAnswer = newValue.filter { it.isDigit() } },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(Alignment.CenterHorizontally),
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color.Black,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray
                    ),
                    placeholder = {
                        Text("Enter answer", color = Color.Gray, fontSize = 20.sp)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            viewModel.checkAnswer(userAnswer.toIntOrNull())
                            userAnswer = ""
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .width(140.dp)
                            .padding(horizontal = 8.dp)
                    ) {
                        Text("➡ Next", fontSize = 18.sp)
                    }

                    Button(
                        onClick = { navController.navigate("start") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF44336),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .width(140.dp)
                            .padding(horizontal = 8.dp)
                    ) {
                        Text("Cancel", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

// 🎯 Reusable Score Card
@Composable
fun ScoreCard(text: String, color: Color, emoji: String) {
    Card(
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, fontSize = 20.sp) // Emoji Icon
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = text,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
