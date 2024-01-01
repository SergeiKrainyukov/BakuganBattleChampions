package com.animeGames.bakuganbattlechampions.domain.entity

abstract class Store {

    //запросы
    abstract fun buyBakugan(bakuganId: Id)
    abstract fun buyCard(cardId: Id)

    //команды
    abstract fun getAllCards(): List<Card>
    abstract fun getAllBakugans(): List<AbstractBakugan>

    companion object {
        const val BUY_BAKUGAN_NIL = 0 //buyBakugan() еще не вызывалась
        const val BUY_BAKUGAN_OK = 1 //последняя buyBakugan() отработала успешно
        const val BUY_BAKUGAN_ERR = 2 //недостаточно средств для покупки бакугана

        const val BUY_CARD_NIL = 0 //buyCard() еще не вызывалась
        const val BUY_CARD_OK = 1 //последняя buyCard() отработала успешно
        const val BUY_CARD_ERR = 2 //недостаточно средств для покупки карты
    }
}