package com.yash.movie_app.utils

import android.content.Context
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Joshi on 01-06-2020.
 */
object AppUtils {

    fun showToast(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun checkNullAndSetText(textView: TextView, string: String?, defaultString: String) {
        if (string != null && string.isNotEmpty() && string != "null") {
            textView.text = string
        } else {
            textView.text = defaultString
        }
    }
}