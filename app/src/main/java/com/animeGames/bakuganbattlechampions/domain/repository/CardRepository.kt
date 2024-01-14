package com.animeGames.bakuganbattlechampions.domain.repository

import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard
import com.animeGames.bakuganbattlechampions.domain.entity.Id

//АТД репозиторий для карт
interface CardRepository {
    //Команды
    //Постусловие: Карта с переданным id добавлена в список карт, принадлежащих игроку, в бд
    fun addCardForCurrentPlayer()

    //Запросы
    fun getCardsForCurrentPlayer(): List<AbstractCard>
    fun getCardsForPlayer(playerId: Id): List<AbstractCard>
}