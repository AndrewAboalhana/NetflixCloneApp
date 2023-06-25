package com.aa.netflixclone.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aa.netflixclone.domain.models.exceptions.NetworkException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel<T : BaseUiState, EFFECT : BaseUiEffect>(state: T) : ViewModel() {

    protected val _state = MutableStateFlow(state)
    val state = _state.asStateFlow()

    protected val _effect = MutableSharedFlow<EFFECT>()
    val effect = _effect.asSharedFlow()

    fun <V> tryToExecute(
        callee: suspend () -> V,
        onSuccess: (V) -> Unit,
        onError: (ErrorUiState) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = callee()
                onSuccess(result)
            } catch (e: Exception) {
                onError(ErrorUiState.InternalServerError)
            }
        }
    }

}