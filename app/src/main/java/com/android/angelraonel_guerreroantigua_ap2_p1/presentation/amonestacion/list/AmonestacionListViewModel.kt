package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase.ObserveAmonestacionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.compose
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.internal.throwArrayMissingFieldException
import javax.inject.Inject

@HiltViewModel
class AmonestacionListViewModel @Inject constructor(
    private val observeAmonestacionUseCase: ObserveAmonestacionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AmonestacionListUiState())
    val state: StateFlow<AmonestacionListUiState> = _state.asStateFlow()

    init {
        loadAmonestaciones()
    }
    fun loadAmonestaciones() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            observeAmonestacionUseCase().collectLatest { list-> _state.update {
                it.copy(isLoading = false, amonestacion = list, message = null)
            } }
        }

    }

    fun onEvent(event: AmonestacionListUiEvent) {
        when (event) {
            AmonestacionListUiEvent.Load -> loadAmonestaciones()
            AmonestacionListUiEvent.Refresh-> loadAmonestaciones()
            is AmonestacionListUiEvent.ShowMessage-> _state.update { it.copy(message = event.message)}
            AmonestacionListUiEvent.ClearMessage-> _state.update { it.copy(message = null)}
            AmonestacionListUiEvent.CreateNew-> _state.update { it.copy(navigateToCreate = true) }
            is AmonestacionListUiEvent.Edit-> _state.update { it.copy(navigateToEditId = event.id) }
            AmonestacionListUiEvent.NavigateToCreate -> _state.update { it.copy(navigateToCreate = false) }
            AmonestacionListUiEvent.NavigateToEdit -> _state.update { it.copy(navigateToEditId = null) }
        }
    }
}