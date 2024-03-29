package com.animeGames.bakuganbattlechampions.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.animeGames.bakuganbattlechampions.R
import com.animeGames.bakuganbattlechampions.presentation.theme.BakuganBattleChampionsTheme
import com.animeGames.bakuganbattlechampions.presentation.viewModel.BattleScreenState
import com.animeGames.bakuganbattlechampions.presentation.viewModel.BattleScreenViewModel
import com.animeGames.bakuganbattlechampions.presentation.viewModel.UIEvent

@Composable
fun IconTextRow(imageId: Int, text: String, onClick: (() -> Unit)? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                if (onClick != null) {
                    onClick()
                }
            }
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier.size(82.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}


@Composable
fun BattleScreen(viewModel: BattleScreenViewModel, navController: NavController) {
    val state by viewModel.screenState().observeAsState(BattleScreenState.initial())
    if (state.isGameOver && state.isCurrentUserWon) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = "Вы выиграли") },
            text = { Text(text = "") },
            confirmButton = {
                Button(onClick = {
                    navController.popBackStack()
                }) {
                    Text("ОК")
                }
            },
        )
    }

    if (state.isGameOver && !state.isCurrentUserWon) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = "Вы проиграли") },
            text = { Text(text = "") },
            confirmButton = {
                Button(onClick = {
                    navController.popBackStack()
                }) {
                    Text("ОК")
                }
            },
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Противник
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconTextRow(
                imageId = R.drawable.baku_icon,
                text = "x${state.opponentBakugans.size}"
            )
            IconTextRow(
                imageId = R.drawable.baku_gate_card,
                text = "x${state.opponentGateCards.size}"
            )
            IconTextRow(
                imageId = R.drawable.baku_card,
                text = "x${state.opponentAbilityCards.size}"
            )
        }

        //Поле игры
        ImageGrid(
            cards = state.fieldGateCards.map { R.drawable.baku_gate_card },
            bakugans = state.currentUserFieldBakugans.map { R.drawable.baku_icon }
                .plus(state.opponentFieldBakugans.map { R.drawable.baku_icon })
        )


        // Игрок
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconTextRow(
                imageId = R.drawable.baku_icon,
                text = "x${state.currentUserBakugans.size}"
            ) {
                viewModel.onClickEvent(UIEvent.BakuganClick)
            }
            IconTextRow(
                imageId = R.drawable.baku_gate_card,
                text = "x${state.currentUserGateCards.size}"
            ) {
                viewModel.onClickEvent(UIEvent.GateCardClick)
            }
            IconTextRow(
                imageId = R.drawable.baku_card,
                text = "x${state.currentUserAbilityCards.size}"
            ) {
                viewModel.onClickEvent(UIEvent.AbilityCardClick)
            }
        }
    }
}

@Composable
fun ImageGrid(cards: List<Int>, bakugans: List<Int>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        for (i in cards.indices step 2) {
            Row(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                ImageItem(
                    imageId = cards[i],
                    subImages = bakugans
                )
                Spacer(modifier = Modifier.width(8.dp))
                if (i + 1 < cards.size) {
                    ImageItem(imageId = cards[i + 1])
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
    }
}
