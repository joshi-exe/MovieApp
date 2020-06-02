package com.yash.movie_app.utils

import android.widget.SearchView

/**
 * Created by Joshi on 02-06-2020.
 */

open class SearchViewUtil : SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean = false
}