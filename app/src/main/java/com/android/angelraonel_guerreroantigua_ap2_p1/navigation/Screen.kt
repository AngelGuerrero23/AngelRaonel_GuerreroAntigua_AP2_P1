package com.android.angelraonel_guerreroantigua_ap2_p1.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object AmonestacionList: Screen(){
    }

    @Serializable
    data class AmonestacionForm(val amonestacionId: Int = 0): Screen(){
    }
}