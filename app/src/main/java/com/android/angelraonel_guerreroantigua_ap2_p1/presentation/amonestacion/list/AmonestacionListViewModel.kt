package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmonestacionListViewModel @Inject constructor(
    private val repository: AmonestacionRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AmonestacionListUiState())
    val state: StateFlow<AmonestacionListUiState> = _state.asStateFlow()

    init {
        loadAmonestaciones()
    }

    fun onEvent(event: AmonestacionListUiEvent) {
        when (event) {
            is AmonestacionListUiEvent.Delete -> {
                viewModelScope.launch {
                    repository.deleteAmonestacion(event.id)
                }
            }

            else -> {}
        }
    }

    private fun loadAmonestaciones() {
        repository.observeAmonestacion()
            .onEach { list ->
                _state.update { it.copy(amonestaciones = list) }
            }
            .launchIn(viewModelScope)
    }
}