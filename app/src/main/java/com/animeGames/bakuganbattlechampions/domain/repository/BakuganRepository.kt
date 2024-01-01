package com.animeGames.bakuganbattlechampions.domain.repository

import com.animeGames.bakuganbattlechampions.domain.entity.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.entity.Id

interface BakuganRepository {

    //Команды
    //Постусловие: Бакуган с переданным id добавлен в список бакуганов, принадлежащих игроку, в бд
    fun addBakuganForCurrentPlayer(bakuganId: Id)


    //Запросы
    fun getBakugansForCurrentPlayer(): List<AbstractBakugan>
    fun getBakugansForPlayer(playerId: Id): List<AbstractBakugan>
}