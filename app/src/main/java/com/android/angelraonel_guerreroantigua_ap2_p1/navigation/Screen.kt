package com.android.angelraonel_guerreroantigua_ap2_p1.navigation

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object BorrameList: Screen(){
    }

    @Serializable
    data class BorrameForm(val borrameId: Int = 0): Screen(){
    }
}