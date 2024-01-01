package com.animeGames.bakuganbattlechampions.domain.entity

//АТД игрок
//Его наследует как реальный пользователь, так и ии, против которого он играет.
abstract class Player {

    //Команды
    abstract fun removeCard(card: Card)

    abstract fun removeBakugan(abstractBakugan: AbstractBakugan)

    //Запросы
    abstract fun getActualCards(): List<Card>

    abstract fun getActualBakugans(): List<Card>

    //Запросы статусов:
    abstract fun getRemoveCardStatus(): Int

    abstract fun getRemoveBakuganStatus(): Int

    companion object {
        // Статусы
        private const val REMOVE_CARD_NIL = 0  // removeCard() не вызывался
        private const val REMOVE_CARD_OK = 1 // Карта успешно удалена из колоды
        private const val REMOVE_CARD_ERR = 2  // Карты нет в колоде

        private const val REMOVE_BAKUGAN_NIL = 0   // removeBakugan() не вызывался
        private const val REMOVE_BAKUGAN_OK = 1   // Бакуган успешно удален из колоды
        private const val REMOVE_BAKUGAN_ERR = 2   // Бакугана нет в колоде
    }
}