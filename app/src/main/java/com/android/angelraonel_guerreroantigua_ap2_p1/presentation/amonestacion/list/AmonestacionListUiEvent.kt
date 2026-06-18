package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.list

sealed class AmonestacionListUiEvent{
    object Load: AmonestacionListUiEvent()
    object Refresh: AmonestacionListUiEvent()
    data class ShowMessage(val message: String) : AmonestacionListUiEvent()
    object ClearMessage: AmonestacionListUiEvent()
    object CreateNew: AmonestacionListUiEvent()
    data class Edit(val id: Int): AmonestacionListUiEvent()
    object NavigateToCreate: AmonestacionListUiEvent()
    object NavigateToEdit: AmonestacionListUiEvent()
}
