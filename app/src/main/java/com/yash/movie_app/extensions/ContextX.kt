package com.yash.movie_app.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes

/**
 * Created by Joshi on 01-06-2020.
 */

fun Context.inflate(@LayoutRes layoutRes: Int): View? {
    return LayoutInflater.from(this).inflate(layoutRes, null)
}