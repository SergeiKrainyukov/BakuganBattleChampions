package com.animeGames.bakuganbattlechampions.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.animeGames.bakuganbattlechampions.presentation.theme.BakuganBattleChampionsTheme

@Composable
fun BattlesScreen(navController: NavController) {
    val cardItems = listOf(
        CardItem("Бой с Маручо", "Вам предстоит сразиться с противником стихии Аквос", "Уровень 1"),
        CardItem(
            "Бой с Шун Казами",
            "Вам предстоит сразиться с противником стихии Вентус",
            "Уровень 2"
        ),
    )

    CardsList(navController, cardItems)
}

data class CardItem(
    val title: String,
    val description: String,
    val level: String
)

@Composable
fun CardsList(navController: NavController, cardItems: List<CardItem>) {
    LazyColumn {
        items(cardItems) { cardItem ->
            CardItemView(cardItem){
                navController.navigate("battle")
            }
        }
    }
}

@Composable
fun CardItemView(cardItem: CardItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = cardItem.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = cardItem.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = cardItem.level,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BattlesScreenPreview() {
    BakuganBattleChampionsTheme {
        BattlesScreen(rememberNavController())
    }
}
