package com.animeGames.bakuganbattlechampions.data.database

import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractPlayer
import com.animeGames.bakuganbattlechampions.domain.entity.AbilityCard
import com.animeGames.bakuganbattlechampions.domain.entity.Bakugan
import com.animeGames.bakuganbattlechampions.domain.entity.BakuganBonus
import com.animeGames.bakuganbattlechampions.domain.entity.GateCard
import com.animeGames.bakuganbattlechampions.domain.entity.Id
import com.animeGames.bakuganbattlechampions.domain.entity.Player
import com.animeGames.bakuganbattlechampions.domain.entity.Power

object AppDatabase {

    private const val FIRST_BAKUGAN_ID = 1
    private const val SECOND_BAKUGAN_ID = 2
    private const val THIRD_BAKUGAN_ID = 3

    val currentPlayer = Player(
        id = Id(0),
        name = "",
        level = 0,
        cards = listOf(
            AbilityCard(
                id = Id(1),
                bakuganBonus = BakuganBonus(Id(THIRD_BAKUGAN_ID), Power(100))
            ),
            GateCard(Id(3), BakuganBonus(Id(THIRD_BAKUGAN_ID), Power(90)))
        ),
        bakugans = listOf(
            Bakugan(id = Id(THIRD_BAKUGAN_ID), name = "Pyrus Dragonoid", power = Power(450)),
        )
    )
    val players: List<AbstractPlayer> = listOf(
        Player(
            id = Id(1),
            name = "Маручо",
            level = 1,
            cards = listOf(
                AbilityCard(
                    id = Id(1),
                    bakuganBonus = BakuganBonus(Id(FIRST_BAKUGAN_ID), Power(100))
                ),
                GateCard(Id(1), BakuganBonus(Id(FIRST_BAKUGAN_ID), Power(90)))
            ),
            bakugans = listOf(
                Bakugan(id = Id(FIRST_BAKUGAN_ID), name = "Subterra Mantris", power = Power(350)),
            )
        ),
        Player(
            id = Id(2),
            name = "Шун Казами",
            level = 2,
            cards = listOf(
                AbilityCard(
                    id = Id(2),
                    bakuganBonus = BakuganBonus(Id(SECOND_BAKUGAN_ID), Power(120))
                ), GateCard(Id(2), BakuganBonus(Id(SECOND_BAKUGAN_ID), Power(70)))
            ),
            bakugans = listOf(
                Bakugan(id = Id(SECOND_BAKUGAN_ID), name = "Ventus Ingram", power = Power(400)),
            )
        ),
    )

    private var _currentOpponentId = Id(-1)
    val currentOpponentId: Id
        get() = _currentOpponentId

    fun updateCurrentOpponentLiveData(id: Id) {
        _currentOpponentId = id
    }

    fun getAllBakugans(): List<Bakugan> {
        val bakugans = mutableListOf<Bakugan>()
        bakugans.apply {
            add(Bakugan(id = Id(FIRST_BAKUGAN_ID), name = "Subterra Mantris", power = Power(350)))
            add(Bakugan(id = Id(SECOND_BAKUGAN_ID), name = "Ventus Ingram", power = Power(400)))
        }
        return bakugans
    }

    fun getAllCards(): List<AbstractCard> {
        val cards = mutableListOf<AbstractCard>()
        cards.apply {
            add(
                AbilityCard(
                    id = Id(1),
                    bakuganBonus = BakuganBonus(Id(THIRD_BAKUGAN_ID), Power(100))
                )
            )
            add(GateCard(Id(3), BakuganBonus(Id(THIRD_BAKUGAN_ID), Power(90))))
            add(
                AbilityCard(
                    id = Id(1),
                    bakuganBonus = BakuganBonus(Id(FIRST_BAKUGAN_ID), Power(100))
                )
            )
            add(GateCard(Id(1), BakuganBonus(Id(FIRST_BAKUGAN_ID), Power(90))))
        }
        return cards
    }
}