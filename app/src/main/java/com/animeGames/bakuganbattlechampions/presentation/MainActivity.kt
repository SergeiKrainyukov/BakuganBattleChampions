package com.animeGames.bakuganbattlechampions.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.animeGames.bakuganbattlechampions.data.database.AppDatabase
import com.animeGames.bakuganbattlechampions.presentation.screens.BattlesScreen
import com.animeGames.bakuganbattlechampions.presentation.screens.CardDescriptionScreen
import com.animeGames.bakuganbattlechampions.presentation.screens.CollectionsScreen
import com.animeGames.bakuganbattlechampions.presentation.screens.MenuScreen
import com.animeGames.bakuganbattlechampions.presentation.screens.ProfileScreen
import com.animeGames.bakuganbattlechampions.presentation.screens.SettingsScreen
import com.animeGames.bakuganbattlechampions.presentation.theme.BakuganBattleChampionsTheme
import com.animeGames.bakuganbattlechampions.presentation.screens.BattleScreen
import com.animeGames.bakuganbattlechampions.presentation.screens.StoreScreen
import com.animeGames.bakuganbattlechampions.presentation.viewModel.BattleScreenViewModel

class MainActivity : ComponentActivity() {

    private lateinit var battleScreenViewModel: BattleScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        battleScreenViewModel = ViewModelProvider(
            this,
            ViewModelFactory()
        )[BattleScreenViewModel::class.java]
        setContent {
            BakuganBattleChampionsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(battleScreenViewModel)
                }
            }
        }
    }
}

@Composable
fun App(
    battleScreenViewModel: BattleScreenViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MenuScreen(navController) }
        composable("campaign") { BattlesScreen(navController) }
        composable("collections") { CollectionsScreen(navController) }
        composable("shop") { StoreScreen(navController = navController)}
        composable("settings") { SettingsScreen() }
        composable("profile") { ProfileScreen() }
        composable("battle") {
            battleScreenViewModel.initData()
            BattleScreen(battleScreenViewModel, navController)
        }
        composable("card_description") { CardDescriptionScreen() }
    }
}

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BattleScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BattleScreenViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


