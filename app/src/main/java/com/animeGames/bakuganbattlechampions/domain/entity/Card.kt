package com.animeGames.bakuganbattlechampions.domain.entity

//АТД карта
//Его будут наследовать конкретные классы Карты ворот и карты способностей
abstract class Card {
    //команды
    abstract fun activate(abstractBakugan: AbstractBakugan)

    //запросы
    abstract fun isActivated(): Boolean

}