package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.form

import java.time.LocalDate

class AmonestacionFormUiState {
    val amonestacionId: Int = 0
    val nombre: String= ""
    val razon: String= ""
    val monto: Double = 0.0

    val amonestacionError: String? = null
    val nombreError: String? = null
    val razonError: String?= null
    val montoError: String?=null

    val isSaving: Boolean = false
    val isDeleting: Boolean = false
    val isNew: Boolean = false
    val saved: Boolean = false
    val deleted: Boolean = false
}