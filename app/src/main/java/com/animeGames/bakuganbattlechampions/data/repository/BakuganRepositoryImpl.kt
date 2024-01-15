package com.animeGames.bakuganbattlechampions.data.repository

import com.animeGames.bakuganbattlechampions.data.database.AppDatabase
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.entity.Id
import com.animeGames.bakuganbattlechampions.domain.repository.BakuganRepository

class BakuganRepositoryImpl : BakuganRepository {
    override fun addBakuganForCurrentPlayer(bakuganId: Id) {
        AppDatabase.getAllBakugans().find { it.id() == bakuganId }
            ?.let { AppDatabase.currentPlayer.getActualBakugans().add(it) }
    }

    override fun getBakugansForCurrentPlayer(): List<AbstractBakugan> {
        return AppDatabase.currentPlayer.getActualBakugans()
    }

    override fun getBakugansForPlayer(playerId: Id): List<AbstractBakugan> {
        return AppDatabase.players.find { it.getId() == playerId }?.getActualBakugans() ?: listOf()
    }

    override fun getBakuganById(bakuganId: Id): AbstractBakugan? {
        return AppDatabase.getAllBakugans().find { it.id() == bakuganId }
    }
}