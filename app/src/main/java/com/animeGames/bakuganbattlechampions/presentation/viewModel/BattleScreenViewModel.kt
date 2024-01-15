package com.animeGames.bakuganbattlechampions.presentation.viewModel

import android.os.Handler
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
                currentUserFieldBakugans = listOf(),
                opponentFieldBakugans = listOf(),
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
            currentUserFieldBakugans = listOf(bakugan),
            currentUserBakugans = currentPlayer.getActualBakugans()
        )
    }

    private fun onAbilityCardClick() {
        _screenState.value?.let { if (it.fieldGateCards.isEmpty() || it.currentUserFieldBakugans.isEmpty()) return }
        if (currentPlayer.getActualCards().getAbilityCards().isEmpty()) return
        val abilityCard = currentPlayer.getActualCards().getAbilityCards().first()
        currentPlayer.removeCard(abilityCard.id())
        _screenState.value?.let {
            it.currentUserFieldBakugans.forEach { abilityCard.activate(it) }
            _screenState.value = _screenState.value?.copy(
                currentUserAbilityCards = currentPlayer.getActualCards().getGateCards(),
                currentUserFieldBakugans = it.currentUserFieldBakugans
            )
        }
        checkGameState()
    }

    private fun checkGameState() {
        _screenState.value?.let {
            if (it.currentUserBakugans.isEmpty() && it.currentUserGateCards.isEmpty() && it.currentUserAbilityCards.isEmpty()) {
                Handler().postDelayed({
                    val bakugan = currentOpponent!!.getActualBakugans().first()
                    currentOpponent!!.removeBakugan(bakugan.id())
                    _screenState.value = it.copy(
                        opponentBakugans = currentOpponent!!.getActualBakugans(),
                        opponentFieldBakugans = listOf(bakugan)
                    )
                }, 1_000)
            }
        }
        Handler().postDelayed({
            val abilityCard = currentOpponent!!.getActualCards().getAbilityCards().first()
            currentOpponent!!.removeCard(abilityCard.id())
            _screenState.value = _screenState.value?.copy(
                opponentAbilityCards = currentOpponent!!.getActualCards().getAbilityCards()
            )
        }, 2_000)
        Handler().postDelayed({
            calculateBattleResult()
        }, 3_000)
    }

    private fun calculateBattleResult() {
        _screenState.value?.let {
            if (it.opponentFieldBakugans.first().getActualPower()
                    .getActualValue() > it.currentUserFieldBakugans.first().getActualPower()
                    .getActualValue()
            ) {
                _screenState.value = it.copy(isCurrentUserWon = false, isGameOver = true)
                return
            }
            _screenState.value = it.copy(isCurrentUserWon = true, isGameOver = true)
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
    val currentUserFieldBakugans: List<AbstractBakugan>,
    val opponentFieldBakugans: List<AbstractBakugan>,
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
            currentUserFieldBakugans = listOf(),
            opponentFieldBakugans = listOf(),
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