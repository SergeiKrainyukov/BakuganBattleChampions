package com.animeGames.bakuganbattlechampions.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.animeGames.bakuganbattlechampions.R
import com.animeGames.bakuganbattlechampions.presentation.theme.BakuganBattleChampionsTheme

@Composable
fun ProfileScreen() {
    val avatarPainter = painterResource(id = R.drawable.ic_user)
    val name = "Elizaveta Petrova"
    val additionalInfoItems = listOf(
        AdditionalInfoItem("Должность", "Разработчик"),
        AdditionalInfoItem("Организация", "Компания X"),
        AdditionalInfoItem("Электронная почта", "email@example.com"),
        AdditionalInfoItem("Город", "Москва")
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (avatar, nameView, redactButton) = createRefs()

            Image(
                painter = avatarPainter,
                contentDescription = "Аватар пользователя",
                modifier = Modifier
                    .size(80.dp)
                    .constrainAs(avatar) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .width(120.dp)
                    .constrainAs(nameView) {
                        start.linkTo(avatar.end, margin = 24.dp)
                        top.linkTo(avatar.top)
                        bottom.linkTo(avatar.bottom)
                    }
            )
        }

        // Дополнительная информация (пример функции ниже)
        additionalInfoItems.forEach { item ->
            ProfileAdditionalInfoItem(item)
        }

        Button(
            onClick = { },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
        ) {
            Text(text = "Редактировать")
        }

    }
}

@Composable
fun ProfileAdditionalInfoItem(item: AdditionalInfoItem) {
    ConstraintLayout(
        modifier = Modifier
            .padding(start = 15.dp)
    ) {
        val (icon, title, description, divider) = createRefs()

        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = "",
            modifier = Modifier
                .size(35.dp)
                .constrainAs(icon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = item.title, // Замените на ваше значение
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(icon.end, margin = 20.dp)
                top.linkTo(icon.top)
            }
        )

        Text(
            text = item.content, // Замените на ваше значение
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.constrainAs(description) {
                start.linkTo(icon.end, margin = 20.dp)
                top.linkTo(title.bottom)
            }
        )

        Box(
            modifier = Modifier
                .height(2.dp)
                .clip(shape = RectangleShape)
                .background(colorResource(id = R.color.black))
                .constrainAs(divider) {
                    start.linkTo(description.start)
                    end.linkTo(parent.end)
                    top.linkTo(description.bottom, margin = 20.dp)
                    width = Dimension.fillToConstraints
                }
        )
    }
}


data class AdditionalInfoItem(val title: String, val content: String)

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    BakuganBattleChampionsTheme {
        ProfileScreen()
    }
}