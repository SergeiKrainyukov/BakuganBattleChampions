package com.animeGames.bakuganbattlechampions.domain.entity

import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractStore

class Store: AbstractStore() {
    override fun buyBakugan(bakuganId: Id) {
        TODO("Not yet implemented")
    }

    override fun buyCard(cardId: Id) {
        TODO("Not yet implemented")
    }

    override fun getAllCards(): List<AbstractCard> {
        TODO("Not yet implemented")
    }

    override fun getAllBakugans(): List<AbstractBakugan> {
        TODO("Not yet implemented")
    }

    override fun buyBakuganStatus(): Int {
        TODO("Not yet implemented")
    }

    override fun buyCardStatus(): Int {
        TODO("Not yet implemented")
    }
}