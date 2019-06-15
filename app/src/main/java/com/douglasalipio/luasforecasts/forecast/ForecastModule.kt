package com.douglasalipio.luasforecasts.forecast

import androidx.lifecycle.ViewModel
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
    internal abstract fun featureViewModel(forecastViewModel: ForecastViewModel): ViewModel
}