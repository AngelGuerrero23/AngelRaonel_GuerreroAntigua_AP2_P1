package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.list

import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.model.Amonestacion

data class AmonestacionListUiState(
    val isLoading: Boolean = false,
    val amonestaciones: List<Amonestacion> = emptyList(),
    val error: String? = null
)