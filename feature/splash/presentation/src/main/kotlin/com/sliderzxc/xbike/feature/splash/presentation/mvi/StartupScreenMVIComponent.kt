package com.sliderzxc.xbike.feature.splash.presentation.mvi

import com.arkivanov.decompose.ComponentContext
import com.sliderzxc.xbike.feature.splash.presentation.mvi.StartupScreenMVIComponent.Action
import com.sliderzxc.xbike.feature.splash.presentation.mvi.StartupScreenMVIComponent.Intent
import com.sliderzxc.xbike.feature.splash.presentation.mvi.StartupScreenMVIComponent.State
import com.sliderzxc.xbike.foundation.mvi.MVI
import pro.respawn.flowmvi.api.MVIAction
import pro.respawn.flowmvi.api.MVIIntent
import pro.respawn.flowmvi.api.MVIState
import pro.respawn.flowmvi.api.Store
import pro.respawn.flowmvi.dsl.store

class StartupScreenMVIComponent(
    componentContext: ComponentContext,
    //authRepository: AuthRepository,
) : ComponentContext by componentContext, MVI<State, Intent, Action> {

    override val store: Store<State, Intent, Action> = store(initial = State()) {

//        init {
//            if (authRepository.isAuthorized())
//                action(Action.GoToMainScreen)
//            else action(Action.GoToAuthorization)
//        }
    }

    data class State(
        val hasMoreItems: Boolean = true,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
    ) : MVIState

    sealed class Intent : MVIIntent {
        data object Load : Intent()
    }

    sealed class Action : MVIAction {
        data class Failure(val throwable: Throwable) : Action()
    }
}

