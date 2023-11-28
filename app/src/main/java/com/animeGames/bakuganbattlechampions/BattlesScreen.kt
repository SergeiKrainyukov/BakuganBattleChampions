package com.animeGames.bakuganbattlechampions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.animeGames.bakuganbattlechampions.ui.theme.BakuganBattleChampionsTheme

data class CardItem(
    val title: String,
    val description: String,
    val level: String
)

@Composable
fun CardsScreen(cardItems: List<CardItem>) {
    LazyColumn {
        items(cardItems) { cardItem ->
            CardItemView(cardItem)
        }
    }
}

@Composable
fun CardItemView(cardItem: CardItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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

@Composable
fun BattlesScreen() {
    val cardItems = listOf(
        CardItem("Бой с Маручо", "Вам предстоит сразиться с противником стихии Аквос", "Уровень 1"),
        CardItem("Бой с Шун Казами", "Вам предстоит сразиться с противником стихии Вентус", "Уровень 2"),
    )

    CardsScreen(cardItems)
}

@Preview(showBackground = true)
@Composable
fun BattlesScreenPreview() {
    BakuganBattleChampionsTheme {
        BattlesScreen()
    }
}
