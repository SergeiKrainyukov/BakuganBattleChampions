package com.animeGames.bakuganbattlechampions.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animeGames.bakuganbattlechampions.R
import com.animeGames.bakuganbattlechampions.data.database.AppDatabase
import com.animeGames.bakuganbattlechampions.domain.entity.Storage
import com.animeGames.bakuganbattlechampions.presentation.theme.BakuganBattleChampionsTheme

@Composable
fun UpgradeScreen(
    imagePainter: Painter,
    title: String,
    description: String,
    onUpgradeClicked: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = imagePainter,
                contentDescription = null, // Provide a proper content description for accessibility
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp), // You can adjust the size as needed
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onUpgradeClicked,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Upgrade")
            }
        }
    }
}

@Composable
fun CardDescriptionScreen() {
    val imagePainter =
        painterResource(id = R.drawable.img_dragonoid) // Replace with your image resource ID
    val title = "Драго"
    val description = "Драго - бакуган Пайруса в виде дракона. Его напарником является Дэн."

    UpgradeScreen(
        imagePainter = imagePainter,
        title = title,
        description = description,
        onUpgradeClicked = {
            Storage().upgradeBakugan(AppDatabase.currentPlayer.getActualBakugans().first().id())
        }
    )
}

@Preview(showBackground = true)
@Composable
fun UpgradeScreenPreview() {
    BakuganBattleChampionsTheme {
        CardDescriptionScreen()
    }
}
