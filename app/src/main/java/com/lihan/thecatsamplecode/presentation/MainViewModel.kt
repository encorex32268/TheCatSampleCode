package com.lihan.thecatsamplecode.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lihan.thecatsamplecode.domain.repository.MainRepository
import com.lihan.thecatsamplecode.domain.util.ApiError
import com.lihan.thecatsamplecode.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getData()
    }

    fun onEvent(event: MainEvent){
        when(event){
            MainEvent.DoOtherThings -> Unit
            MainEvent.GetData -> {
                getData()
            }
        }
    }

    private fun getData() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            when(val result = repository.getCats()){
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                    _uiEvent.send(
                        UiEvent.Failed
                    )
                    when(result.error){
                        ApiError.NETWORK_ERROR -> TODO()
                        ApiError.TIMEOUT -> TODO()
                        ApiError.UNAUTHORIZED -> TODO()
                        ApiError.FORBIDDEN -> TODO()
                        ApiError.NOT_FOUND -> TODO()
                        ApiError.INTERNAL_SERVER_ERROR -> TODO()
                        ApiError.BAD_REQUEST -> TODO()
                        ApiError.UNKNOWN_ERROR -> TODO()
                        ApiError.IOEXCEPTION -> TODO()
                    }
                }
                is Result.Success -> {
                    Log.d("TAG", "getData: ${result.data}")
                    _state.update {
                        it.copy(
                            isLoading = false,
                            items = result.data
                        )
                    }
                }
            }
        }
    }

}