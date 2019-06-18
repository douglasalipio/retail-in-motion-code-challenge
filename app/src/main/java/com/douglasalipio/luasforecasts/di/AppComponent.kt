package com.douglasalipio.luasforecasts.di

import com.douglasalipio.luasforecasts.ForecastApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        NetworkModule::class,
        RepositoryModule::class]
)
interface AppComponent : AndroidInjector<ForecastApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: ForecastApplication): Builder

        fun build(): AppComponent
    }
}

