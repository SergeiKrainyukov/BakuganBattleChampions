package com.animeGames.bakuganbattlechampions.domain.repository

import com.animeGames.bakuganbattlechampions.domain.entity.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.entity.Player

interface BakuganRepository {

    //Команды
    fun addBakuganForCurrentPlayer(bakugan: AbstractBakugan)


    //Запросы
    fun getBakugansForCurrentPlayer(): List<AbstractBakugan>
    fun getBakugansForPlayer(player: Player): List<AbstractBakugan>
}