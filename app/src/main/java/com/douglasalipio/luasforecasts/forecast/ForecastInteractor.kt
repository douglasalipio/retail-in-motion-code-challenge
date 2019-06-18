package com.douglasalipio.luasforecasts.forecast


import com.douglasalipio.luasforecasts.data.AppDataSource
import com.douglasalipio.luasforecasts.data.ForecastsResponse
import com.douglasalipio.luasforecasts.util.getStop
import com.douglasalipio.luasforecasts.util.io
import com.douglasalipio.luasforecasts.util.ui
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ForecastInteractor @Inject constructor(private val appRepository: AppDataSource) : ForecastContract.Interactor {

    private val compositeDisposable = CompositeDisposable()

    interface GetForecastCallback {

        fun onForecastLoaded(forecastResponse: ForecastsResponse)

        fun onDataNotAvailable(strError: String)
    }

    override fun requestForecasts(getForecastCallback: GetForecastCallback) {
        compositeDisposable.add(
            appRepository.requestData(getStop())
                .subscribeOn(io())
                .observeOn(ui())
                .doOnError { getForecastCallback.onDataNotAvailable(it.message ?: "") }
                .subscribe { onNext -> getForecastCallback.onForecastLoaded(onNext) }
        )
    }
}

