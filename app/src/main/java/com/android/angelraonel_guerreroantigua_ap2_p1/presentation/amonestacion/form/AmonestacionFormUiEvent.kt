package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.form

sealed interface AmonestacionFormUiEvent{
    data class Load(val id: Int?): AmonestacionFormUiEvent
    data class AmonestacionIdChanged(val value: String): AmonestacionFormUiEvent
    data class NombreChanged(val value: String): AmonestacionFormUiEvent
    data class RazonChanged(val value: String): AmonestacionFormUiEvent
    data class MontoChanged(val value: String): AmonestacionFormUiEvent
    data object Save: AmonestacionFormUiEvent
    data object Delete: AmonestacionFormUiEvent
}