package com.inflames1986.mytestrobolectric.view.search

import com.inflames1986.mytestrobolectric.model.SearchResult
import com.inflames1986.mytestrobolectric.view.ViewContract

internal interface ViewSearchContract : ViewContract {
    fun displaySearchResults(
        searchResults: List<SearchResult>,
        totalCount: Int
    )

    fun displayError()
    fun displayError(error: String)
    fun displayLoading(show: Boolean)
}