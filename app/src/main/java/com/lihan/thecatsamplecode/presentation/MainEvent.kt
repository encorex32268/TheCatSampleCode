package com.lihan.thecatsamplecode.presentation

sealed class MainEvent {

    data object GetData: MainEvent()

    data object DoOtherThings: MainEvent()
}