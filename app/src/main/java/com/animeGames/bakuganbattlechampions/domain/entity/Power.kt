package com.animeGames.bakuganbattlechampions.domain.entity

data class Power(private var value: Int) {
    fun increase(power: Power) {
        value += power.value
    }

    fun decrease(power: Power) {
        if (power.value >= value) {
            value = MINIMUM_POWER
            return
        }
        value -= power.getActualValue()
    }

    fun getActualValue() = value

    companion object {
        const val MINIMUM_POWER = 0
    }
}
