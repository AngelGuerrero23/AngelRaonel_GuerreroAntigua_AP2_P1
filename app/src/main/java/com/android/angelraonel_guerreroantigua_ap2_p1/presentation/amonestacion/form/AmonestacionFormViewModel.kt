package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.room.util.copy
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.model.Amonestacion
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.DeleteAmonestacionUseCase
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.GetAmonestacionUseCase
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.UpsertAmonestacionesUseCase
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.validateMonto
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.validateNombre
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
    private var repository: AmonestacionRepository,
    private val upsertAmonestacionesUseCase: UpsertAmonestacionesUseCase,
    private val getAmonestacionUseCase: GetAmonestacionUseCase,
    private val deleteAmonestacionUseCase: DeleteAmonestacionUseCase,
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
                            nombre = amonestacion.nombre ?: "",
                            razon = amonestacion.razon ?: "",
                            monto = amonestacion.monto.toString()
                        )
                    }
                }
            }
        }
    }

    private fun onSaved(){
        val amonestacionActual = state.value
        val validateNombre = validateNombre(amonestacionActual.nombre)
        val validateRazon = validateRazon(amonestacionActual.razon)
        val validateMonto = validateMonto(amonestacionActual.monto.toString())

        if(!validateMonto.isValid || !validateRazon.isValid || !validateNombre.isValid)
        {
            _state.update {
                it.copy(
                    nombreError = validateNombre.error,
                    razonError = validateRazon.error,
                    montoError = validateMonto.error,
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }

            if (state.value.isNew) {
                if (repository.exists(amonestacionActual.amonestacionId)) {
                    _state.update {
                        it.copy(
                            isSaving = false,
                            amonestacionError = "Esta amonestacion ya existe"
                        )
                    }
                    return@launch
                }
            }

            val amonestacion = Amonestacion(
                amonestacionId = amonestacionActual.amonestacionId,
                nombre = amonestacionActual.nombre,
                razon = amonestacionActual.razon,
                monto = amonestacionActual.monto.0.0
            )

            val result = upsertAmonestacionesUseCase(amonestacion)
            result.onSuccess { newId ->
                _state.update {
                    it.copy(
                        isSaving = false,
                        saved = true,
                        amonestacionId = newId,
                        isNew = false
                    )
                }
            }.onFailure {
                _state.update { it.copy(isSaving = false) }
            }
        }
    }

    private fun onDelete(){
        val id = state.value.amonestacionId
        if(id == 0) return
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            deleteAmonestacionUseCase(id)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }

    fun onEvent(event: AmonestacionFormUiEvent){
        when(event){
            is AmonestacionFormUiEvent.AmonestacionIdChanged -> _state.update {it.copy(amonestacionId = event.value.toIntOrNull() ?: 0, amonestacionError = null) }
            is AmonestacionFormUiEvent.NombreChanged -> _state.update { it.copy(nombre = event.value, nombreError = null) }
            is AmonestacionFormUiEvent.RazonChanged -> _state.update { it.copy(razon = event.value, razonError = null) }
            is AmonestacionFormUiEvent.MontoChanged -> _state.update { it.copy(monto = event.value, montoError = null) }
            AmonestacionFormUiEvent.Save -> onSaved()
            AmonestacionFormUiEvent.Delete -> onDelete()
        }
    }
}