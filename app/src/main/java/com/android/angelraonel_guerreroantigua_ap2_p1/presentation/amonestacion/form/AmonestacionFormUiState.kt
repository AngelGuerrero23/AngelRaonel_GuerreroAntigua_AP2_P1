package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.form

data class AmonestacionFormUiState(
    val amonestacionId: Int?=null,
    val nombres: String= "",
    val razon: String= "",
    val monto: String= "",

    val amonstacionError: String?=null,
    val nombreError: String?=null,
    val razonError: String?=null,
    val montoError: String?=null,

    val isSaving: Boolean=false,
    val isDeleting: Boolean=false,
    val isNew: Boolean=false,
    val saved: Boolean=false,
    val deleted: Boolean=false

)
