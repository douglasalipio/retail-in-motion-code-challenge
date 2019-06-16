package com.douglasalipio.luasforecasts

import com.douglasalipio.luasforecasts.data.LuasDataSource
import com.nhaarman.mockitokotlin2.capture
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*


class FeatureResponsePresenterTest {
//    @Mock
//    private lateinit var luasRepository: LuasDataSource
//    @Mock
//    private lateinit var view: FeatureContract.View
//    @Mock
//    private lateinit var interactor: FeatureContract.Interactor
//    @Captor
//    private lateinit var getFeatureCallbackCaptor: ArgumentCaptor<FeatureInteractor.GetFeatureCallback>
//    private lateinit var presenter: FeaturePresenter
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//        presenter = FeaturePresenter(interactor)
//        presenter.takeView(view)
//    }
//
//    @Test
//    fun `should return a list of features`() {
//        val features = listOfFeature()
//        presenter.loadData()
//        //`when`(luasRepository.requestForecasts()).thenReturn(Flowable.just(features))
//        //verify(luasRepository).requestForecasts().subscribe()
//        verify(interactor).requestForecasts(capture(getFeatureCallbackCaptor))
//        getFeatureCallbackCaptor.value.onFeatureLoaded(features)
//        verify(view).showData(features)
//    }
//
//    @Test
//    fun `should show a error message`() {
//        presenter.loadData()
//        verify(interactor).requestForecasts(capture(getFeatureCallbackCaptor))
//        getFeatureCallbackCaptor.value.onDataNotAvailable("data not available.")
//        verify(view).showDataError()
//    }
}