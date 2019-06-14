package com.douglasalipio.luasforecasts.feature

import androidx.lifecycle.ViewModel
import com.douglasalipio.luasforecasts.di.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class FeatureModule {
    //@FragmentScoped
    //@ContributesAndroidInjector
    //internal abstract fun tasksFragment(): TasksFragment
    @ActivityScoped
    @Binds
    internal abstract fun featureViewModel(featureViewModel: FeatureViewModel): ViewModel
}