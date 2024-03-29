package com.animeGames.bakuganbattlechampions.domain.abstractTypes

import com.animeGames.bakuganbattlechampions.domain.entity.Id

//АТД карта
//Его будут наследовать конкретные классы Карты ворот и карты способностей
abstract class AbstractCard {
    //команды
    //предусловие: карта еще не была активирована
    //постусловие: карта поменяла статус на активирована
    abstract fun activate(abstractBakugan: AbstractBakugan) //успешно; карта уже была активирована

    //запросы
    abstract fun isActivated(): Boolean
    abstract fun isNotActivated(): Boolean
    abstract fun id(): Id

    //запросы статусов
    abstract fun activateStatus(): Int

    companion object {
        const val ACTIVATE_NIL = 0 //activate() еще не вызывалась
        const val ACTIVATE_OK = 1 //activate() отработала успешно
        const val ACTIVATE_ERR = 2 //карта уже была активирована
    }

}