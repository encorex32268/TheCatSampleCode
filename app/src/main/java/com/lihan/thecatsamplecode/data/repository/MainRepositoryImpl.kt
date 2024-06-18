package com.lihan.thecatsamplecode.data.repository

import android.accounts.NetworkErrorException
import com.lihan.thecatsamplecode.data.remote.CatAPI
import com.lihan.thecatsamplecode.domain.mapper.toCat
import com.lihan.thecatsamplecode.domain.model.Cat
import com.lihan.thecatsamplecode.domain.repository.MainRepository
import com.lihan.thecatsamplecode.domain.util.ApiError
import com.lihan.thecatsamplecode.domain.util.Result
import okio.IOException

class MainRepositoryImpl(
    private val api: CatAPI
): MainRepository {

    override suspend fun getCats(): Result<List<Cat>, ApiError> {
        return try {
            val result = api.getCats()
            if (result.isSuccessful){
                Result.Success(
                    data = result.body()?.let {
                        it.map {
                            it.toCat()
                        }
                    }?: emptyList()
                )
            }else{
                Result.Error(error = ApiError.BAD_REQUEST)
            }
        }catch (e: IOException){
            Result.Error(error = ApiError.IOEXCEPTION)
        }catch (e: Exception){
            Result.Error(error = ApiError.UNKNOWN_ERROR)
        }catch (e: NetworkErrorException){
            Result.Error(error = ApiError.NETWORK_ERROR)
        }
    }
}