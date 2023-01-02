package com.inflames1986.mytestrobolectric.presenter.details

import com.inflames1986.mytestrobolectric.view.details.ViewDetailsContract

internal class DetailsPresenter internal constructor(
    private var count: Int = 0
) : PresenterDetailsContract {

    private var viewContract: ViewDetailsContract? = null

    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        count++
        viewContract?.setCount(count)
    }

    override fun onDecrement() {
        count--
        viewContract?.setCount(count)
    }

    override fun onAttach(viewContract: ViewDetailsContract) {
        this.viewContract = viewContract
    }

    override fun onDetach() {
        this.viewContract = null
    }
}