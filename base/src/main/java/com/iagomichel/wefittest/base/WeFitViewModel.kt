package com.iagomichel.wefittest.base

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class WeFitViewModel: ViewModel() {

    private val _loading = mutableStateOf(true)
    val loading: State<Boolean> = _loading

    private val _error = mutableStateOf<Throwable?>(null)
    val error: State<Throwable?> = _error

    protected fun launchWeFitCoroutine(
        block: suspend CoroutineScope.() -> Unit
    ) =
        viewModelScope.launch {
                _loading.value = true
            runCatching {
                block()
            }.onSuccess {
                _loading.value = false
            }.onFailure { errorCoroutine ->
                _loading.value = false
               _error.value = errorCoroutine
            }
        }
}
