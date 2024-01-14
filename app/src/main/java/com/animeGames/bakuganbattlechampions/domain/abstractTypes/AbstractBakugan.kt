package com.animeGames.bakuganbattlechampions.domain.abstractTypes

import com.animeGames.bakuganbattlechampions.domain.entity.Id
import com.animeGames.bakuganbattlechampions.domain.entity.Power

//АТД Бакуган
abstract class AbstractBakugan {
    //команды

    //предусловие: уровень силы бакугана больше 0
    //постусловие: уровень силы бакугана уменьшен на переданную величину, либо равен 0
    abstract fun takeDamage(damage: Power) //успешно; уровень силы равен 0

    //постусловие: уровень силы бакугана увеличен на переданную величину
    abstract fun powerUp(powerBonus: Power)

    //запросы
    abstract fun getActualPower(): Power
    abstract fun id(): Id

    //запросы статусов
    abstract fun getTakeDamageStatus(): Int

    companion object {
        const val TAKE_DAMAGE_NIL = 0 //takeDamage() еще не вызывалась
        const val TAKE_DAMAGE_OK = 1 //последняя takeDamage() отработала успешно
        const val TAKE_DAMAGE_ERR = 2 //уровень силы равен 0
    }

}