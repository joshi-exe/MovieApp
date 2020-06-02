package com.yash.movie_app.di.module

import android.os.Build
import androidx.multidex.BuildConfig

/**
 * Created by Joshi on 31-05-2020.
 */

data class AppInfo(
    val version: String = BuildConfig.VERSION_NAME,
    val flavor: String = BuildConfig.FLAVOR,
    val deviceModel: String = Build.MODEL
)