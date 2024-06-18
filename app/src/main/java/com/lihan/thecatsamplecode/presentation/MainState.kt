package com.lihan.thecatsamplecode.presentation

import com.lihan.thecatsamplecode.domain.model.Cat

data class MainState(
    val isLoading: Boolean = false,
    val items: List<Cat> = emptyList()
)
