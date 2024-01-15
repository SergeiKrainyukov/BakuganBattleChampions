package com.animeGames.bakuganbattlechampions.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.animeGames.bakuganbattlechampions.R
import com.animeGames.bakuganbattlechampions.domain.entity.Store
import com.animeGames.bakuganbattlechampions.presentation.theme.BakuganBattleChampionsTheme

@Composable
fun StoreScreen(navController: NavController) {

    val allBakugans = Store().getAllBakugans()
    val cardItems = mutableListOf<Pair<Int, Pair<String, String>>>()

    allBakugans.forEach {
        cardItems.add(Pair(R.drawable.baku_icon, it.name() to "Very Strong Character"))
    }

    Column {
        FilterBar(
            listOf(
                "Бакуганы",
                "Карты вор.",
                "Карты сп.",
            )
        )

        LazyColumn {
            items(cardItems) { cardItem ->
                CardItem(
                    imageId = cardItem.first,
                    title = cardItem.second.first,
                    description = cardItem.second.second
                ) {
                    navController.navigate("card_description")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoreScreenPreview() {
    BakuganBattleChampionsTheme {
        CollectionsScreen(rememberNavController())
    }
}