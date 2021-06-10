package com.bakaenko.facerecognition.utils

import android.view.View


fun View.visibleIf(isVisible: Boolean) {
    val wasVisibleEarlier = visibility == View.VISIBLE
    if (wasVisibleEarlier != isVisible) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}