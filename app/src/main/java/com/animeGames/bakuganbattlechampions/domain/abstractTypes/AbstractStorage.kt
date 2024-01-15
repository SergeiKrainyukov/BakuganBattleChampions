package com.animeGames.bakuganbattlechampions.domain.abstractTypes

import com.animeGames.bakuganbattlechampions.domain.entity.Id

//АТД Хранилище
abstract class AbstractStorage {

    //команды
    //предусловие: количество средств на счете игрока >= стоимости улучшения бакугана
    //постусловие: количество средств на счете игрока уменьшилось на величину стоимости улучшения бакугана
    //постусловие: бакуган с данным id имеет новое значение силы в базе данных
    abstract fun upgradeBakugan(bakuganId: Id) // успешно; на счете недостаточно средств

    //запросы
    abstract fun getAllCards(): List<AbstractCard>
    abstract fun getAllBakugans(): List<AbstractBakugan>

    //запросы статусов
    abstract fun upgradeBakuganStatus(): Int

    companion object {
        const val UPGRADE_BAKUGAN_NIL = 0 //команда upgradeBakugan() еще не вызывалась
        const val UPGRADE_BAKUGAN_OK = 1 //последняя upgradeBakugan() отработала успешно
        const val UPGRADE_BAKUGAN_ERR = 2 //недостаточно средств на счете
    }
}