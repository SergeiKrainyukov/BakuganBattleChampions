package com.animeGames.bakuganbattlechampions.extension

import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard
import com.animeGames.bakuganbattlechampions.domain.entity.AbilityCard
import com.animeGames.bakuganbattlechampions.domain.entity.GateCard

fun List<AbstractCard>.getAbilityCards(): List<AbilityCard> {
    return filterIsInstance<AbilityCard>().map { it }
}

fun List<AbstractCard>.getGateCards(): List<GateCard> {
    return filterIsInstance<GateCard>().map { it }
}