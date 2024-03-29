package com.animeGames.bakuganbattlechampions.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.animeGames.bakuganbattlechampions.R
import com.animeGames.bakuganbattlechampions.data.database.AppDatabase
import com.animeGames.bakuganbattlechampions.domain.entity.Storage
import com.animeGames.bakuganbattlechampions.presentation.theme.BakuganBattleChampionsTheme

@Composable
fun FilterBar(filters: List<String>) {
    var selectedFilter by remember { mutableStateOf("Бакуганы") }

    Row(modifier = Modifier.padding(10.dp)) {
        for (filter in filters) {
            val isSelected = filter == selectedFilter
            Button(
                onClick = { selectedFilter = filter },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray,
                    contentColor = Color.White
                ),
            ) {
                Text(filter)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


@Composable
fun CardItem(imageId: Int, title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp)) // Обрезаем изображение по углам
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.headlineSmall)
                Text(text = description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


@Composable
fun CollectionsScreen(navController: NavController) {

    val allBakugans = Storage().getAllBakugans()
    val cardItems = mutableListOf<Pair<Int, Pair<String, String>>>()

    allBakugans.forEach {
        cardItems.add(Pair(R.drawable.baku_icon, it.name() to "Very Strong Character"),)
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
fun CollectionsScreenPreview() {
    BakuganBattleChampionsTheme {
        CollectionsScreen(rememberNavController())
    }
}