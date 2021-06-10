package com.bakaenko.facerecognition.base

sealed class BaseProps<T> {
    class Data<T>(val value: T) : BaseProps<T>()
    class Empty<T>() : BaseProps<T>()
    class Loading<T>() : BaseProps<T>()
    class Error<T>(val message: String?) : BaseProps<T>()
}

fun <T> BaseProps<T>.value() = when (this) {
    is BaseProps.Data -> value
    else -> null
}