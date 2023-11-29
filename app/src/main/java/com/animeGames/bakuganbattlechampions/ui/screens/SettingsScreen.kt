package com.animeGames.bakuganbattlechampions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.animeGames.bakuganbattlechampions.ui.theme.BakuganBattleChampionsTheme

@Composable
fun SettingsScreen() {
    var difficultyLevel by remember { mutableStateOf("средний") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DifficultyLevelRow(difficultyLevel) { newLevel ->
            difficultyLevel = newLevel
        }
        ResetButton {
            difficultyLevel = "средний"
        }
    }
}

@Composable
fun DifficultyLevelRow(difficultyLevel: String, onDifficultyChange: (String) -> Unit) {
    val difficultyLevels = listOf("легкий", "средний", "сложный")

    fun increaseDifficulty() {
        val currentIndex = difficultyLevels.indexOf(difficultyLevel)
        if (currentIndex < difficultyLevels.lastIndex) {
            onDifficultyChange(difficultyLevels[currentIndex + 1])
        }
    }

    fun decreaseDifficulty() {
        val currentIndex = difficultyLevels.indexOf(difficultyLevel)
        if (currentIndex > 0) {
            onDifficultyChange(difficultyLevels[currentIndex - 1])
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Уровень сложности:",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = difficultyLevel)
        Spacer(modifier = Modifier.width(16.dp))
        IconButton(onClick = { increaseDifficulty() }) {
            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Увеличить сложность")
        }
        IconButton(onClick = { decreaseDifficulty() }) {
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Уменьшить сложность")
        }
    }
}

@Composable
fun ResetButton(onReset: () -> Unit) {
    Button(onClick = onReset) {
        Text(text = "Сбросить")
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    BakuganBattleChampionsTheme {
        SettingsScreen()
    }
}
