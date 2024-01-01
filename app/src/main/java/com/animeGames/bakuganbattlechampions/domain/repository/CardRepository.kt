package com.animeGames.bakuganbattlechampions.domain.repository

import com.animeGames.bakuganbattlechampions.domain.entity.Card
import com.animeGames.bakuganbattlechampions.domain.entity.Id

interface CardRepository {
    //Команды
    fun addCardForCurrentPlayer()

    //Запросы
    fun getCardsForCurrentPlayer(): List<Card>
    fun getCardsForPlayer(playerId: Id): List<Card>
}