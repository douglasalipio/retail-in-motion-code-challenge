package com.douglasalipio.luasforecasts.forecast

import com.douglasalipio.luasforecasts.data.ForecastsResponse
import com.douglasalipio.luasforecasts.di.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ForecastPresenter @Inject constructor(private val interactor: ForecastContract.Interactor) :
    ForecastContract.Presenter {

    private var view: ForecastContract.View? = null

    override fun <T> takeView(view: T) {
        this.view = view as ForecastContract.View
    }

    override fun fetchForecasts() {
        view?.let {
            interactor.requestForecasts(object : ForecastInteractor.GetForecastCallback {
                override fun onForecastLoaded(forecastResponse: ForecastsResponse) {
                    it.showForecasts(forecastResponse)
                }

                override fun onDataNotAvailable(strError: String) {
                    it.showDataError()
                }
            })
        }
    }

    override fun dropView() {
        view = null
    }
}
