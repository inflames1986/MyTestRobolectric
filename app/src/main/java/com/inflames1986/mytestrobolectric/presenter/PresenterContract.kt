package com.inflames1986.mytestrobolectric.presenter

interface PresenterContract<ViewContract> {
    fun onAttach(viewContract: ViewContract)
    fun onDetach()
}