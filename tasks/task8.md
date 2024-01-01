Добавлены следующие реализации АТД:

```kotlin

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

    companion object {
        const val TAKE_DAMAGE_NIL = 0 //takeDamage() еще не вызывалась
        const val TAKE_DAMAGE_OK = 1 //последняя takeDamage() отработала успешно
        const val TAKE_DAMAGE_ERR = 2 //уровень силы равен 0
    }

}

//АТД карта
//Его будут наследовать конкретные классы Карты ворот и карты способностей
abstract class Card {
    //команды
    //предусловие: карта еще не была активирована
    //постусловие: карта поменяла статус на активирована
    abstract fun activate(abstractBakugan: AbstractBakugan) //успешно; карта уже была активирована

    //запросы
    abstract fun isActivated(): Boolean
    abstract fun isNotActivated(): Boolean

    //запросы статусов
    abstract fun activateStatus(): Int

    companion object {
        const val ACTIVATE_NIL = 0 //activate() еще не вызывалась
        const val ACTIVATE_OK = 1 //activate() отработала успешно
        const val ACTIVATE_ERR = 2 //карта уже была активирована
    }

}


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

//АТД Хранилище
abstract class Storage {

    //команды
    //предусловие: количество средств на счете игрока >= стоимости улучшения бакугана
    //постусловие: количество средств на счете игрока уменьшилось на величину стоимости улучшения бакугана
    //постусловие: бакуган с данным id имеет новое значение силы в базе данных
    abstract fun upgradeBakugan(bakuganId: Id) // успешно; на счете недостаточно средств

    //запросы
    abstract fun getAllCards(): List<Card>
    abstract fun getAllBakugans(): List<AbstractBakugan>

    //запросы статусов
    abstract fun upgradeBakuganStatus(): Int

    companion object {
        const val UPGRADE_BAKUGAN_NIL = 0 //команда upgradeBakugan() еще не вызывалась
        const val UPGRADE_BAKUGAN_OK = 1 //последняя upgradeBakugan() отработала успешно
        const val UPGRADE_BAKUGAN_ERR = 2 //недостаточно средств на счете
    }
}

//АТД Магазин
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

//АТД репозиторий для бакуганов
interface BakuganRepository {

    //Команды
    //Постусловие: Бакуган с переданным id добавлен в список бакуганов, принадлежащих игроку, в бд
    fun addBakuganForCurrentPlayer(bakuganId: Id)


    //Запросы
    fun getBakugansForCurrentPlayer(): List<AbstractBakugan>
    fun getBakugansForPlayer(playerId: Id): List<AbstractBakugan>
}

//АТД репозиторий для карт
interface CardRepository {
    //Команды
    //Постусловие: Карта с переданным id добавлена в список карт, принадлежащих игроку, в бд
    fun addCardForCurrentPlayer()

    //Запросы
    fun getCardsForCurrentPlayer(): List<Card>
    fun getCardsForPlayer(playerId: Id): List<Card>
}

```