package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.list


sealed interface AmonestacionListUiEvent {
    data class Delete(val id: Int) : AmonestacionListUiEvent
    object Refresh : AmonestacionListUiEvent
}