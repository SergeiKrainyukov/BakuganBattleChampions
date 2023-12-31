package com.animeGames.bakuganbattlechampions.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.animeGames.bakuganbattlechampions.presentation.screens.BattlesScreen
import com.animeGames.bakuganbattlechampions.presentation.screens.CardDescriptionScreen
import com.animeGames.bakuganbattlechampions.presentation.screens.CollectionsScreen
import com.animeGames.bakuganbattlechampions.presentation.screens.MenuScreen
import com.animeGames.bakuganbattlechampions.presentation.screens.ProfileScreen
import com.animeGames.bakuganbattlechampions.presentation.screens.SettingsScreen
import com.animeGames.bakuganbattlechampions.presentation.theme.BakuganBattleChampionsTheme
import com.animeGames.bakuganbattlechampions.presentation.theme.BattleScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BakuganBattleChampionsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MenuScreen(navController) }
        composable("campaign") { BattlesScreen(navController) }
        composable("collections") { CollectionsScreen(navController) }
        composable("shop") {  }
        composable("settings") { SettingsScreen() }
        composable("profile") { ProfileScreen() }
        composable("battle") { BattleScreen() }
        composable("card_description") { CardDescriptionScreen() }
    }
}


