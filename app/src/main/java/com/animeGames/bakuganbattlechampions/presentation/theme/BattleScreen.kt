package com.animeGames.bakuganbattlechampions.presentation.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animeGames.bakuganbattlechampions.R

@Composable
fun IconTextRow(imageId: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null, // Decorative image, no description needed
            modifier = Modifier.size(82.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}


@Composable
fun BattleScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Верхняя часть экрана
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconTextRow(imageId = R.drawable.baku_icon, text = "x3")
            IconTextRow(imageId = R.drawable.baku_gate_card, text = "x1")
            IconTextRow(imageId = R.drawable.baku_card, text = "x3")
        }

        // Центральная часть экрана (пока пустая)
        ImageGrid(
            images = listOf(
                R.drawable.baku_gate_card,
                R.drawable.baku_gate_card,
                R.drawable.baku_gate_card,
                R.drawable.baku_gate_card
            )
        )

        // Нижняя часть экрана
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconTextRow(imageId = R.drawable.baku_icon, text = "x3")
            IconTextRow(imageId = R.drawable.baku_gate_card, text = "x1")
            IconTextRow(imageId = R.drawable.baku_card, text = "x3")
        }
    }
}

@Composable
fun ImageGrid(images: List<Int>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        for (i in images.indices step 2) {
            Row(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                ImageItem(imageId = images[i], subImages = if (i == 0) listOf(
                    R.drawable.baku_icon,
                    R.drawable.baku_icon
                ) else null)
                Spacer(modifier = Modifier.width(8.dp))
                if (i + 1 < images.size) {
                    ImageItem(imageId = images[i + 1])
                }
            }
        }
    }
}

@Composable
fun ImageItem(imageId: Int, subImages: List<Int>? = null) {
    Box {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null, // Decorative image, no description needed
            modifier = Modifier.size(200.dp)
        )
        // Если есть дополнительные изображения, отобразить их в левом верхнем углу
        if (subImages != null) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                subImages.forEach { subImageId ->
                    Image(
                        painter = painterResource(id = subImageId),
                        contentDescription = null, // Decorative image, no description needed
                        modifier = Modifier
                            .size(70.dp)
                            .padding(top = 4.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BattleScreenPreview() {
    BakuganBattleChampionsTheme {
        BattleScreen()
    }
}
