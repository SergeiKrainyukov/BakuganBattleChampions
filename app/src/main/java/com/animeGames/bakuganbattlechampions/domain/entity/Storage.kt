package com.animeGames.bakuganbattlechampions.domain.entity

import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractStorage
import com.animeGames.bakuganbattlechampions.domain.repository.BakuganRepository

class Storage(
    private val bakuganRepository: BakuganRepository
): AbstractStorage() {
    override fun upgradeBakugan(bakuganId: Id) {
        TODO("Not yet implemented")
    }

    override fun getAllCards(): List<AbstractCard> {
        TODO("Not yet implemented")
    }

    override fun getAllBakugans(): List<AbstractBakugan> {
        TODO("Not yet implemented")
    }

    override fun upgradeBakuganStatus(): Int {
        TODO("Not yet implemented")
    }
}