package com.animeGames.bakuganbattlechampions.data.database

import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractPlayer
import com.animeGames.bakuganbattlechampions.domain.entity.Player

object AppDatabase {
    val currentPlayer = Player(
        listOf(),
        listOf()
    )
    val abstractPlayers: List<AbstractPlayer> = listOf()
    val bakugans: List<AbstractBakugan> = listOf()
    val abstractCards: List<AbstractCard> = listOf()
}