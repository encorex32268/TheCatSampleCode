package com.lihan.thecatsamplecode.presentation

sealed class UiEvent {

    data object Success: UiEvent()
    data object Failed: UiEvent()

}