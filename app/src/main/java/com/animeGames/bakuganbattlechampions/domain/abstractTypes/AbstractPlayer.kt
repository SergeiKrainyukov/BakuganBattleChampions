package com.animeGames.bakuganbattlechampions.domain.abstractTypes

import com.animeGames.bakuganbattlechampions.domain.entity.Id

//АТД игрок
//Его наследует как реальный пользователь, так и ии, против которого он играет.
abstract class AbstractPlayer {

    //Команды
    //Предусловие: карта существует в колоде
    //Постусловие: карта удалена из колоды
    abstract fun removeCard(cardId: Id) // успешно; Карты нет в колоде

    //Предусловие: бакуган существует в колоде
    //Постусловие: бакуган удален из колоды
    abstract fun removeBakugan(bakuganId: Id) // успешно; Бакугана нет в колоде

    //Запросы
    abstract fun getActualCards(): List<AbstractCard>
    abstract fun getActualBakugans(): List<AbstractBakugan>
    abstract fun getName(): String
    abstract fun getLevel(): Int
    abstract fun getId(): Id

    //Запросы статусов:
    abstract fun removeCardStatus(): Int
    abstract fun removeBakuganStatus(): Int

    companion object {
        // Статусы
        const val REMOVE_CARD_NIL = 0  // removeCard() не вызывался
        const val REMOVE_CARD_OK = 1 // Карта успешно удалена из колоды
        const val REMOVE_CARD_ERR = 2  // Карты нет в колоде

        const val REMOVE_BAKUGAN_NIL = 0   // removeBakugan() не вызывался
        const val REMOVE_BAKUGAN_OK = 1   // Бакуган успешно удален из колоды
        const val REMOVE_BAKUGAN_ERR = 2   // Бакугана нет в колоде
    }
}