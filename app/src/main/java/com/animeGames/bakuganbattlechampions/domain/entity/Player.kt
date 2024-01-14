package com.animeGames.bakuganbattlechampions.domain.entity

import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractPlayer
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard

class Player(
    abstractCards: List<AbstractCard>,
    bakugans: List<AbstractBakugan>
) : AbstractPlayer() {

    private val cards: MutableList<AbstractCard>
    private val bakugans: MutableList<AbstractBakugan>

    private var removeCardStatus = REMOVE_CARD_NIL
    private var removeBakuganStatus = REMOVE_BAKUGAN_NIL

    init {
        this.cards = abstractCards.toMutableList()
        this.bakugans = bakugans.toMutableList()
    }

    override fun removeCard(cardId: Id) {
        val previousSize = cards.size
        cards.removeIf { it.id() == cardId }
        removeCardStatus = if (cards.size < previousSize) REMOVE_CARD_OK else REMOVE_CARD_ERR
    }

    override fun removeBakugan(bakuganId: Id) {
        val previousSize = bakugans.size
        bakugans.removeIf { it.id() == bakuganId }
        removeBakuganStatus = if (bakugans.size < previousSize) REMOVE_CARD_OK else REMOVE_CARD_ERR
    }

    override fun getActualCards() = cards

    override fun getActualBakugans() = bakugans

    override fun removeCardStatus() = removeCardStatus

    override fun removeBakuganStatus() = removeBakuganStatus
}