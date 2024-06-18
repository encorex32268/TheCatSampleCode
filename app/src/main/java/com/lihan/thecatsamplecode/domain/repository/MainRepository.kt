package com.lihan.thecatsamplecode.domain.repository

import com.lihan.thecatsamplecode.domain.model.Cat
import com.lihan.thecatsamplecode.domain.util.ApiError
import com.lihan.thecatsamplecode.domain.util.Result

interface MainRepository {
    suspend fun getCats(): Result<List<Cat> , ApiError>
}