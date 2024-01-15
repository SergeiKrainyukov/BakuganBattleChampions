package com.animeGames.bakuganbattlechampions.data.repository

import com.animeGames.bakuganbattlechampions.data.database.AppDatabase
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard
import com.animeGames.bakuganbattlechampions.domain.entity.Id
import com.animeGames.bakuganbattlechampions.domain.repository.CardRepository

class CardRepositoryImpl : CardRepository {
    override fun addCardForCurrentPlayer(id: Id) {
        AppDatabase.getAllCards().find { it.id() == id }?.let {
            AppDatabase.currentPlayer.getActualCards()
                .add(it)
        }
    }

    override fun getCardsForCurrentPlayer(): List<AbstractCard> {
        return AppDatabase.currentPlayer.getActualCards()
    }

    override fun getCardsForPlayer(playerId: Id): List<AbstractCard> {
        return AppDatabase.players.find { it.getId() == playerId }?.getActualCards() ?: listOf()
    }
}