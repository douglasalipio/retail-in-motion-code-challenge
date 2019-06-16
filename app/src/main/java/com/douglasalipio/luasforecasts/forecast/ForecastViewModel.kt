package com.douglasalipio.luasforecasts.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglasalipio.luasforecasts.data.LuasRepository
import com.douglasalipio.luasforecasts.data.ForecastsResult
import com.douglasalipio.luasforecasts.util.io
import com.douglasalipio.luasforecasts.util.ui
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ForecastViewModel @Inject constructor(private val repository: LuasRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var forecastsLiveData: MutableLiveData<ForecastsResult> = MutableLiveData()

    fun fetchData() {
        compositeDisposable.add(
            repository.requestData()
                .subscribeOn(io())
                .observeOn(ui())
                .doOnSubscribe { ForecastsResult.Loading }
                .doOnError { ForecastsResult.Error }
                .subscribe {
                    forecastsLiveData.postValue(ForecastsResult.ForecastsData(it))
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}