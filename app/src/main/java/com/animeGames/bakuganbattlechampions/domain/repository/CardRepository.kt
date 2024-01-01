package com.animeGames.bakuganbattlechampions.domain.repository

import com.animeGames.bakuganbattlechampions.domain.entity.Card
import com.animeGames.bakuganbattlechampions.domain.entity.Id

interface CardRepository {
    //Команды
    //Постусловие: Карта с переданным id добавлена в список карт, принадлежащих игроку, в бд
    fun addCardForCurrentPlayer()

    //Запросы
    fun getCardsForCurrentPlayer(): List<Card>
    fun getCardsForPlayer(playerId: Id): List<Card>
}