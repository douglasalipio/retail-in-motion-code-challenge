package com.douglasalipio.luasforecasts.foundation

interface BasePresenter {
    /**
     * Binds presenter with a view when resumed. The Presenter will perform initialization here.
     *
     * @param view the view associated with this presenter
     */
    fun <T> takeView(view: T)

    /**
     * Drops the reference to the view when destroyed
     */
    fun dropView()
}