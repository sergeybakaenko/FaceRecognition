package com.bakaenko.facerecognition.utils

fun Int?.zeroIfNull(): Int {
    return this ?: 0
}

fun Int.getPercentageFrom(number: Int): Int {
    return this.toDouble().div(number.toDouble()).times(100.0).toInt()
}