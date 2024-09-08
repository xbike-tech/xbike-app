package com.sliderzxc.xbike.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object Startup : Screen()
}