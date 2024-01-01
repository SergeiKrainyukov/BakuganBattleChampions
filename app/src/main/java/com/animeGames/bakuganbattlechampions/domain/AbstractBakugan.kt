package com.animeGames.bakuganbattlechampions.domain

//АТД Бакуган
abstract class AbstractBakugan {
    //команды
    abstract fun takeDamage(damage: Int)

    abstract fun powerUp(powerBonus: Int)

    //запросы
    abstract fun getActualPower(): Int

}