package slee.kotlin.rockpaperscissors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape

class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContent {
                        RockPaperScissorsGame()
                }
        }
}

@Composable
fun RockPaperScissorsGame() {
        var userChoice by remember { mutableStateOf<String?>(null) }
        var computerChoice by remember { mutableStateOf<String?>(null) }
        var result by remember { mutableStateOf<String?>(null) }

        val choices = listOf("Rock", "Paper", "Scissors")

        fun playGame(playerChoice: String) {
                userChoice = playerChoice
                computerChoice = choices.random()

                result = when {
                        userChoice == computerChoice -> "It's a Tie!"
                        (userChoice == "Rock" && computerChoice == "Scissors") ||
                                (userChoice == "Scissors" && computerChoice == "Paper") ||
                                (userChoice == "Paper" && computerChoice == "Rock") -> "You Win!"
                        else -> "Computer Wins!"
                }
        }

        Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {
                Text(text = "Rock Paper Scissors", fontSize = 24.sp)

                Spacer(modifier = Modifier.height(20.dp))

                Row {
                        choices.forEach { choice ->
                                Button(
                                        onClick = { playGame(choice) },
                                        modifier = Modifier.padding(8.dp),
                                        shape = RoundedCornerShape(8.dp)
                                ) {
                                        Text(text = choice)
                                }
                        }
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (userChoice != null && computerChoice != null) {
                        Text(text = "You chose: $userChoice")
                        Text(text = "Computer chose: $computerChoice")
                        Text(text = result!!, fontSize = 20.sp)
                }
        }
}

@Preview(showBackground = true)
@Composable
fun PreviewGame() {
        RockPaperScissorsGame()
}
