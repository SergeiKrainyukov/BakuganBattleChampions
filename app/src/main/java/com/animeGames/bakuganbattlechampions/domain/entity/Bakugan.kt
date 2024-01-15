package com.animeGames.bakuganbattlechampions.domain.entity

import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan

class Bakugan(
    private val id: Id,
    private val name: String,
    private val power: Power
) : AbstractBakugan() {

    private var takeDamageStatus = TAKE_DAMAGE_NIL

    override fun takeDamage(damage: Power) {
        if (power.getActualValue() == Power.MINIMUM_POWER) {
            takeDamageStatus = TAKE_DAMAGE_ERR
            return
        }
        power.decrease(damage)
        takeDamageStatus = TAKE_DAMAGE_OK
    }

    override fun powerUp(powerBonus: Power) {
        power.increase(powerBonus)
    }

    override fun getActualPower() = power

    override fun id() = id

    override fun name() = name

    override fun getTakeDamageStatus() = takeDamageStatus
}