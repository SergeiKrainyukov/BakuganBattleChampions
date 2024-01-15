package com.animeGames.bakuganbattlechampions.domain.repository

import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.entity.Bakugan
import com.animeGames.bakuganbattlechampions.domain.entity.Id

//АТД репозиторий для бакуганов
interface BakuganRepository {

    //Команды
    //Постусловие: Бакуган с переданным id добавлен в список бакуганов, принадлежащих игроку, в бд
    fun addBakuganForCurrentPlayer(bakuganId: Id)


    //Запросы
    fun getBakugansForCurrentPlayer(): List<AbstractBakugan>
    fun getBakugansForPlayer(playerId: Id): List<AbstractBakugan>
    fun getBakuganById(bakuganId: Id): AbstractBakugan?
}