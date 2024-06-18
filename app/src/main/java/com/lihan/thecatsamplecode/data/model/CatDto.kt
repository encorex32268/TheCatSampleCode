package com.lihan.thecatsamplecode.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CatDto(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)
