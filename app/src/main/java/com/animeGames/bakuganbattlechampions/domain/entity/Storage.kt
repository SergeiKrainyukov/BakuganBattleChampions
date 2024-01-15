package com.animeGames.bakuganbattlechampions.domain.entity

import com.animeGames.bakuganbattlechampions.data.database.AppDatabase
import com.animeGames.bakuganbattlechampions.data.repository.BakuganRepositoryImpl
import com.animeGames.bakuganbattlechampions.data.repository.CardRepositoryImpl
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractStorage

class Storage : AbstractStorage() {

    private var upgradeBakuganStatus = UPGRADE_BAKUGAN_NIL

    private val cardRepository = CardRepositoryImpl()
    private val bakuganRepository = BakuganRepositoryImpl()

    override fun upgradeBakugan(bakuganId: Id) {
        AppDatabase.getAllBakugans().find { it.id() == bakuganId }?.getActualPower()
            ?.increase(Power(100))
        upgradeBakuganStatus = UPGRADE_BAKUGAN_OK
    }

    override fun getAllCards(): List<AbstractCard> {
        return cardRepository.getCardsForCurrentPlayer()
    }

    override fun getAllBakugans(): List<AbstractBakugan> {
        return bakuganRepository.getBakugansForCurrentPlayer()
    }

    override fun upgradeBakuganStatus(): Int {
        return upgradeBakuganStatus
    }
}