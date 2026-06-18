package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.model.Amonestacion
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.DeleteAmonestacionUseCase
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.GetAmonestacionUseCase
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.ObserveAmonestacionUseCase
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.UpsertAmonestacionesUseCase
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.validateAmonestacion
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.validateMonto
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.validateNombres
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.validateRazon
import com.android.angelraonel_guerreroantigua_ap2_p1.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmonestacionFormViewModel @Inject constructor(
    private val upsertAmonestacionesUseCase: UpsertAmonestacionesUseCase,
    private val getAmonestacionUseCase: GetAmonestacionUseCase,
    private val deleteAmonestacionUseCase: DeleteAmonestacionUseCase,
    private val observeAmonestacionUseCase: ObserveAmonestacionUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val amonestacionId = savedStateHandle.toRoute<Screen.AmonestacionForm>().amonestacionId

    private val _state = MutableStateFlow(AmonestacionFormUiState())
    val state : StateFlow<AmonestacionFormUiState> = _state.asStateFlow()

    init {
        loadAmonestacion(amonestacionId)
    }

    private fun loadAmonestacion(id: Int?) {
        if (id == null || id == 0) {
            _state.update { it.copy(isNew = true, amonestacionId = 0) }
        } else {
            viewModelScope.launch {
                getAmonestacionUseCase(id)?.let { amonestacion ->
                    _state.update {
                        it.copy(
                            isNew = false,
                            amonestacionId = amonestacion.amonestacionId,
                            nombres = amonestacion.nombre ?: "",
                            razon = amonestacion.razon ?: "",
                            monto = amonestacion.monto.toString()
                        )
                    }
                }
            }
        }
    }
    fun onEvent(event: AmonestacionFormUiEvent){
        when(event){
            is AmonestacionFormUiEvent.AmonestacionIdChanged -> _state.update {it.copy(amonestacionId = event.value.toIntOrNull() ?: 0, amonstacionError = null) }
            is AmonestacionFormUiEvent.NombreChanged -> _state.update { it.copy(nombres = event.value, nombreError = null) }
            is AmonestacionFormUiEvent.RazonChanged -> _state.update { it.copy(razon = event.value, razonError = null) }
            is AmonestacionFormUiEvent.MontoChanged -> _state.update { it.copy(monto = event.value, montoError = null) }
            AmonestacionFormUiEvent.Save -> onSaved()
            AmonestacionFormUiEvent.Delete -> onDelete()
            else -> {}
        }
    }
    private fun onSaved(){
        val amonestacionId = state.value.amonestacionId
        val nombres = state.value.nombres
        val razon = state.value.razon
        val monto = state.value.monto

        val validateAmonestacion = validateAmonestacion(amonestacionId)
        val validateNombres = validateNombres(nombres)
        val validateRazon = validateRazon(razon)
        val validateMonto = validateMonto(monto)


        if(!validateMonto.isValid || !validateRazon.isValid || !validateNombres.isValid)
        {
            _state.update {
                it.copy(
                    amonstacionError = validateAmonestacion.error,
                    nombreError = validateNombres.error,
                    razonError = validateRazon.error,
                    montoError = validateMonto.error,

                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }

            val amonestacion = Amonestacion(
                amonestacionId = state.value.amonestacionId?:0,
                nombre = nombres,
                razon = razon,
                monto = monto.toDoubleOrNull() ?: 0.0
            )

            val result = upsertAmonestacionesUseCase(amonestacion)
            result.onSuccess { newId ->
                _state.update {
                    it.copy(
                        isSaving = false,
                        saved = true,
                        amonestacionId = newId.toInt(),
                        isNew = false
                    )
                }
            }.onFailure {
                _state.update { it.copy(isSaving = false) }
            }
        }
    }

    private fun onDelete(){
        val id = state.value.amonestacionId?: return
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            deleteAmonestacionUseCase(id)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }
}