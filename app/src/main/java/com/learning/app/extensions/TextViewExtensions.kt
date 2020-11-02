package com.learning.app.extensions

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView

fun TextView.setTextOrHide(value: String?) {
    if (value.isNullOrBlank()) {
        visibility = GONE
    } else {
        text = value
        visibility = VISIBLE
    }
}