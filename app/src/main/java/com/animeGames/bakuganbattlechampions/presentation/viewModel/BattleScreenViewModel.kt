package com.animeGames.bakuganbattlechampions.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.animeGames.bakuganbattlechampions.data.database.AppDatabase
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractBakugan
import com.animeGames.bakuganbattlechampions.domain.abstractTypes.AbstractCard

class BattleScreenViewModel: ViewModel() {
//    val currentOpponent = AppDatabase.players.find { it.getId() == AppDatabase.currentOpponent!! }!!
//    val currentPlayer = AppDatabase.currentPlayer

    private val _screenState = MutableLiveData<BattleScreenState>()
    fun screenState(): LiveData<BattleScreenState> = MutableLiveData()
}

data class BattleScreenState(
    val opponentBakugans: List<AbstractBakugan>,
    val opponentGateCards: List<AbstractCard>,
    val opponentAbilityCards: List<AbstractCard>,
    val currentUserBakugans: List<AbstractBakugan>,
    val currentUserGateCards: List<AbstractCard>,
    val currentUserAbilityCards: List<AbstractCard>,
    val fieldGateCards: List<AbstractCard>,
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
            isGameOver = false,
            isCurrentUserWon = false
        )
    }
}