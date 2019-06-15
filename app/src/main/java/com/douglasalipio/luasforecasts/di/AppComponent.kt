package com.douglasalipio.luasforecasts.di

import com.douglasalipio.luasforecasts.LuasApplication
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
interface AppComponent : AndroidInjector<LuasApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: LuasApplication): Builder

        fun build(): AppComponent
    }
}

