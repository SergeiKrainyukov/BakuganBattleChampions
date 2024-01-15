package com.animeGames.bakuganbattlechampions.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.animeGames.bakuganbattlechampions.data.database.AppDatabase
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractPlayer
import com.animeGames.bakuganbattlechampions.extension.getAbilityCards
import com.animeGames.bakuganbattlechampions.extension.getGateCards

class BattleScreenViewModel : ViewModel() {
    private var currentOpponent: AbstractPlayer? = null
    private val currentPlayer = AppDatabase.currentPlayer

    private val _screenState = MutableLiveData<BattleScreenState>()
    fun screenState(): LiveData<BattleScreenState> = _screenState

    fun initData() {
        currentOpponent = AppDatabase.players.find { it.getId() == AppDatabase.currentOpponentId }
        currentOpponent?.let {
            _screenState.value = BattleScreenState(
                opponentBakugans = it.getActualBakugans(),
                opponentGateCards = it.getActualCards().getGateCards(),
                opponentAbilityCards = it.getActualCards().getAbilityCards(),
                currentUserBakugans = it.getActualBakugans(),
                currentUserGateCards = it.getActualCards().getGateCards(),
                currentUserAbilityCards = it.getActualCards().getAbilityCards(),
                fieldGateCards = listOf(),
                fieldBakugans = listOf(),
                isGameOver = false,
                isCurrentUserWon = false
            )
        }

    }

    fun onClickEvent(uiEvent: UIEvent) {
        when (uiEvent) {
            UIEvent.BakuganClick -> onClickBakugan()
            UIEvent.GateCardClick -> onClickGateCard()
            UIEvent.AbilityCardClick -> onAbilityCardClick()
        }
    }

    private fun onClickGateCard() {
        if (currentPlayer.getActualCards().getGateCards().isEmpty()) return
        val gateCard = currentPlayer.getActualCards().getGateCards().first()
        currentPlayer.removeCard(gateCard.id())
        _screenState.value = _screenState.value?.copy(
            fieldGateCards = listOf(gateCard),
            currentUserGateCards = currentPlayer.getActualCards().getGateCards()
        )
    }

    private fun onClickBakugan() {
        _screenState.value?.let { if (it.fieldGateCards.isEmpty()) return }
        if (currentPlayer.getActualBakugans().isEmpty()) return
        val bakugan = currentPlayer.getActualBakugans().first()
        currentPlayer.removeBakugan(bakugan.id())
        _screenState.value = _screenState.value?.copy(
            fieldBakugans = listOf(bakugan),
            currentUserBakugans = currentPlayer.getActualBakugans()
        )
    }

    private fun onAbilityCardClick() {
        _screenState.value?.let { if (it.fieldGateCards.isEmpty() || it.fieldBakugans.isEmpty()) return }
        if (currentPlayer.getActualCards().getAbilityCards().isEmpty()) return
        val abilityCard = currentPlayer.getActualCards().getAbilityCards().first()
        currentPlayer.removeCard(abilityCard.id())
        _screenState.value?.let {
            it.fieldBakugans.forEach { abilityCard.activate(it) }
            _screenState.value = _screenState.value?.copy(
                currentUserAbilityCards = currentPlayer.getActualCards().getGateCards(),
                fieldBakugans = it.fieldBakugans
            )
        }
    }
}

data class BattleScreenState(
    val opponentBakugans: List<AbstractBakugan>,
    val opponentGateCards: List<AbstractCard>,
    val opponentAbilityCards: List<AbstractCard>,
    val currentUserBakugans: List<AbstractBakugan>,
    val currentUserGateCards: List<AbstractCard>,
    val currentUserAbilityCards: List<AbstractCard>,
    val fieldGateCards: List<AbstractCard>,
    val fieldBakugans: List<AbstractBakugan>,
    val isGameOver: Boolean,
    val isCurrentUserWon: Boolean
) {
    companion object {
        fun initial() = BattleScreenState(
            opponentBakugans = listOf(),
            opponentGateCards = listOf(),
            opponentAbilityCards = listOf(),
            currentUserBakugans = listOf(),
            currentUserGateCards = listOf(),
            currentUserAbilityCards = listOf(),
            fieldGateCards = listOf(),
            fieldBakugans = listOf(),
            isGameOver = false,
            isCurrentUserWon = false
        )
    }
}

sealed class UIEvent {
    object BakuganClick : UIEvent()
    object GateCardClick : UIEvent()
    object AbilityCardClick : UIEvent()
}