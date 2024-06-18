package com.lihan.thecatsamplecode.domain.mapper

import com.lihan.thecatsamplecode.data.model.CatDto
import com.lihan.thecatsamplecode.domain.model.Cat

fun CatDto.toCat(): Cat {
    return Cat(
        id = id,
        url = url,
        width = width,
        height = height
    )
}