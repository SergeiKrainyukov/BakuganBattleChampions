package com.animeGames.bakuganbattlechampions.domain.entity

import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard

class GateCard(
    private val id: Id,
    private val bakuganBonus: BakuganBonus
) : AbstractCard() {

    private var activateStatus = ACTIVATE_NIL
    private var isActivated = false

    override fun activate(abstractBakugan: AbstractBakugan) {
        isActivated = true
        if (abstractBakugan.id() == bakuganBonus.bakuganId) {
            abstractBakugan.powerUp(bakuganBonus.powerBonus)
            activateStatus = ACTIVATE_OK
            return
        }
        activateStatus = ACTIVATE_ERR
    }

    override fun isActivated() = isActivated

    override fun isNotActivated() = !isActivated

    override fun id() = id

    override fun activateStatus() = activateStatus

}