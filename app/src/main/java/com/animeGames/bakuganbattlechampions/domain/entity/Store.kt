package com.animeGames.bakuganbattlechampions.domain.entity

abstract class Store {

    //команды
    //предусловие: количество средств на счете игрока >= стоимости бакугана
    //постусловие: бакуган с данным id находится в списке бакуганов, принадлежащих игроку в базе данных
    abstract fun buyBakugan(bakuganId: Id) // успешно; на счете недостаточно средств

    //предусловие: количество средств на счете игрока >= стоимости карты
    //постусловие: карта с данным id находится в списке карт, принадлежащих игроку в базе данных
    abstract fun buyCard(cardId: Id) // успешно; на счете недостаточно средств

    //запросы
    abstract fun getAllCards(): List<Card>
    abstract fun getAllBakugans(): List<AbstractBakugan>

    //запросы статусов
    abstract fun buyBakuganStatus(): Int
    abstract fun buyCardStatus(): Int

    companion object {
        const val BUY_BAKUGAN_NIL = 0 //buyBakugan() еще не вызывалась
        const val BUY_BAKUGAN_OK = 1 //последняя buyBakugan() отработала успешно
        const val BUY_BAKUGAN_ERR = 2 //недостаточно средств для покупки бакугана

        const val BUY_CARD_NIL = 0 //buyCard() еще не вызывалась
        const val BUY_CARD_OK = 1 //последняя buyCard() отработала успешно
        const val BUY_CARD_ERR = 2 //недостаточно средств для покупки карты
    }
}