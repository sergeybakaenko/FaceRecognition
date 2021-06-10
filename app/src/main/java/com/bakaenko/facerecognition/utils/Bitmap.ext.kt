package com.bakaenko.facerecognition.utils

import android.graphics.*


fun Bitmap.drawRectangle(rect: Rect): Bitmap? {
    val bitmap = copy(config, true)
    val canvas = Canvas(bitmap)

    Paint().apply {
        alpha = 0xA0
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 10f

        canvas.drawRect(rect, this)
    }

    return bitmap
}