package com.douglasalipio.luasforecasts.forecast

import com.douglasalipio.luasforecasts.foundation.BasePresenter
import com.douglasalipio.luasforecasts.foundation.BaseView
import com.douglasalipio.luasforecasts.data.ForecastsResponse
import com.douglasalipio.luasforecasts.foundation.BaseInteractor

interface ForecastContract {

    interface View : BaseView<Presenter> {

        fun showForecasts(forecast: ForecastsResponse)
        fun showDataError()
    }

    interface Presenter : BasePresenter {
        fun fetchForecasts()
    }

    interface Interactor : BaseInteractor {

        fun requestForecasts(getForecastCallback: ForecastInteractor.GetForecastCallback)
    }
}