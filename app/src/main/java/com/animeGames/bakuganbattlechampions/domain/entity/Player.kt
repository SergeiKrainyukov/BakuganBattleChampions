package com.animeGames.bakuganbattlechampions.domain.entity

//АТД игрок
//Его наследует как реальный пользователь, так и ии, против которого он играет.
abstract class Player {

    //Команды
    //Предусловие: карта существует в колоде
    //Постусловие: карта удалена из колоды
    abstract fun removeCard(cardId: Id) // успешно; Карты нет в колоде

    //Предусловие: бакуган существует в колоде
    //Постусловие: бакуган удален из колоды
    abstract fun removeBakugan(abstractBakugan: AbstractBakugan) // успешно; Бакугана нет в колоде

    //Запросы
    abstract fun getActualCards(): List<Card>
    abstract fun getActualBakugans(): List<Card>

    //Запросы статусов:
    abstract fun removeCardStatus(): Int
    abstract fun removeBakuganStatus(): Int

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