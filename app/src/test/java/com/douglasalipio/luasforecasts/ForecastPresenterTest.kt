package com.baseproject.interview

import com.douglasalipio.luasforecasts.forecast.ForecastContract
import com.douglasalipio.luasforecasts.forecast.ForecastInteractor
import com.douglasalipio.luasforecasts.forecast.ForecastPresenter
import com.nhaarman.mockitokotlin2.capture
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*


class ForecastPresenterTest {

    @Mock
    private lateinit var view: ForecastContract.View
    @Mock
    private lateinit var interactor: ForecastContract.Interactor
    @Captor
    private lateinit var getForecastCallbackCaptor: ArgumentCaptor<ForecastInteractor.GetForecastCallback>
    private lateinit var presenter: ForecastPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ForecastPresenter(interactor)
        presenter.takeView(view)
    }

    @Test
    fun `should return a list of features`() {
        val forecastsResponse = forecast()
        presenter.fetchForecasts()
        verify(interactor).requestForecasts(capture(getForecastCallbackCaptor))
        getForecastCallbackCaptor.value.onForecastLoaded(forecastsResponse)
        verify(view).showForecasts(forecastsResponse)
    }

    @Test
    fun `should show a error message`() {
        presenter.fetchForecasts()
        verify(interactor).requestForecasts(capture(getForecastCallbackCaptor))
        getForecastCallbackCaptor.value.onDataNotAvailable("data not available.")
        verify(view).showDataError()
    }
}