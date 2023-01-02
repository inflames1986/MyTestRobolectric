package com.inflames1986.mytestrobolectric.presenter.details

import com.inflames1986.mytestrobolectric.presenter.PresenterContract
import com.inflames1986.mytestrobolectric.view.details.ViewDetailsContract

internal interface PresenterDetailsContract : PresenterContract<ViewDetailsContract> {
    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()
}