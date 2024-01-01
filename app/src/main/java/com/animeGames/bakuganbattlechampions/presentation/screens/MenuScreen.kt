package com.animeGames.bakuganbattlechampions.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.animeGames.bakuganbattlechampions.presentation.theme.BakuganBattleChampionsTheme

@Composable
fun MenuScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Определяем общий модификатор для кнопок
            val buttonModifier = Modifier
                .fillMaxWidth() // Заполняем всю доступную ширину
                .padding(horizontal = 16.dp) // Добавляем горизонтальный отступ

            Button(
                onClick = { navController.navigate("campaign") },
                modifier = buttonModifier
            ) {
                Text("Кампания", fontSize = 22.sp)
            }

            Button(
                onClick = { navController.navigate("collections") },
                modifier = buttonModifier
            ) {
                Text("Коллекции", fontSize = 22.sp)
            }

            Button(
                onClick = { navController.navigate("shop") },
                modifier = buttonModifier
            ) {
                Text("Магазин", fontSize = 22.sp)
            }

            Button(
                onClick = { navController.navigate("settings") },
                modifier = buttonModifier
            ) {
                Text("Настройки", fontSize = 22.sp)
            }

            Button(
                onClick = { navController.navigate("profile") },
                modifier = buttonModifier
            ) {
                Text("Профиль", fontSize = 22.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    BakuganBattleChampionsTheme {
        MenuScreen(navController = rememberNavController())
    }
}