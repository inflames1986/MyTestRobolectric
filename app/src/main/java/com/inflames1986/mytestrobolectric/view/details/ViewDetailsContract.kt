package com.inflames1986.mytestrobolectric.view.details

import com.inflames1986.mytestrobolectric.view.ViewContract

internal interface ViewDetailsContract : ViewContract {
    fun setCount(count: Int)
}