package com.inflames1986.mytestrobolectric.presenter.search

import com.inflames1986.mytestrobolectric.presenter.PresenterContract
import com.inflames1986.mytestrobolectric.view.search.ViewSearchContract

internal interface PresenterSearchContract : PresenterContract<ViewSearchContract> {
    fun searchGitHub(searchQuery: String)
}