package com.bakaenko.facerecognition.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel<T> : ViewModel() {

    protected val _props = MutableLiveData<BaseProps<T>>()
    val props: LiveData<BaseProps<T>>
        get() = _props


    protected fun launchIO(action: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            action()
        }
    }

    protected suspend fun <T> async(action: suspend () -> T): T {
        return withContext(viewModelScope.coroutineContext + Dispatchers.IO + exceptionHandler) {
            action()
        }
    }

    protected open val exceptionHandler = CoroutineExceptionHandler { _, e ->
        _props.postValue(BaseProps.Error(e.localizedMessage))
    }
}