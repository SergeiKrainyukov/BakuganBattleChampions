package com.animeGames.bakuganbattlechampions.domain.entity

import com.animeGames.bakuganbattlechampions.data.database.AppDatabase
import com.animeGames.bakuganbattlechampions.data.repository.BakuganRepositoryImpl
import com.animeGames.bakuganbattlechampions.data.repository.CardRepositoryImpl
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractStore
import com.animeGames.bakuganbattlechampions.domain.repository.BakuganRepository
import com.animeGames.bakuganbattlechampions.domain.repository.CardRepository

class Store: AbstractStore() {

    private var buyBakuganStatus = BUY_BAKUGAN_NIL
    private var buyCardStatus = BUY_CARD_NIL

    private val cardRepository = CardRepositoryImpl()
    private val bakuganRepository = BakuganRepositoryImpl()

    override fun buyBakugan(bakuganId: Id) {
        bakuganRepository.addBakuganForCurrentPlayer(bakuganId)
        buyBakuganStatus = BUY_BAKUGAN_OK
    }

    override fun buyCard(cardId: Id) {
        cardRepository.addCardForCurrentPlayer(cardId)
        buyCardStatus = BUY_CARD_OK
    }

    override fun getAllCards(): List<AbstractCard> {
        return AppDatabase.getAllCards()
    }

    override fun getAllBakugans(): List<AbstractBakugan> {
        return AppDatabase.getAllBakugans()
    }

    override fun buyBakuganStatus(): Int {
        return buyBakuganStatus
    }

    override fun buyCardStatus(): Int {
        return buyCardStatus
    }
}