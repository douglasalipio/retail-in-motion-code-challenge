package com.douglasalipio.luasforecasts.forecast

import com.douglasalipio.luasforecasts.di.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class ForecastModule {
    //@FragmentScoped
    //@ContributesAndroidInjector
    //internal abstract fun tasksFragment(): TasksFragment
    @ActivityScoped
    @Binds
    internal abstract fun forecastPresenter(presenter: ForecastPresenter): ForecastContract.Presenter

    @ActivityScoped
    @Binds
    internal abstract fun forecastInteractor(interactor: ForecastInteractor): ForecastContract.Interactor
}