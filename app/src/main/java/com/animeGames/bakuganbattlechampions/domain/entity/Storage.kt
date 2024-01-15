package com.animeGames.bakuganbattlechampions.domain.entity

import com.animeGames.bakuganbattlechampions.data.database.AppDatabase
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractStorage
import com.animeGames.bakuganbattlechampions.domain.repository.BakuganRepository

class Storage: AbstractStorage() {
    override fun upgradeBakugan(bakuganId: Id) {

    }

    override fun getAllCards(): List<AbstractCard> {
        return AppDatabase.getAllCards()
    }

    override fun getAllBakugans(): List<AbstractBakugan> {
        return AppDatabase.getAllBakugans()
    }

    override fun upgradeBakuganStatus(): Int {
        return UPGRADE_BAKUGAN_NIL
    }
}