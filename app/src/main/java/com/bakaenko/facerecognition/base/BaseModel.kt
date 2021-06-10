package com.bakaenko.facerecognition.base

class BaseModel {

    sealed class BaseState<T> {
        class Data<T>(val value: T) : BaseState<T>()
        class Empty<T>() : BaseState<T>()
        class Loading<T>() : BaseState<T>()
        class Error<T>(val message: String?) : BaseState<T>()
    }

    fun <T> BaseState<T>.value() = when (this) {
        is BaseState.Data -> value
        else -> null
    }

}