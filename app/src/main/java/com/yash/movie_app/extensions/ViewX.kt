package com.yash.movie_app.extensions

import android.view.View

/**
 * Created by Joshi on 02-06-2020.
 */

var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }
