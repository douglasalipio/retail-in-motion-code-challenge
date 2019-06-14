package com.douglasalipio.luasforecasts.di

import android.app.Application
import android.content.Context
import com.douglasalipio.luasforecasts.data.AppDataSource
import com.douglasalipio.luasforecasts.data.AppRepository
import com.douglasalipio.luasforecasts.data.remote.ApiHelper
import com.douglasalipio.luasforecasts.data.remote.RemoteDataSource
import com.douglasalipio.luasforecasts.data.remote.ServiceAppFactory
import com.douglasalipio.luasforecasts.feature.FeatureActivity
import com.douglasalipio.luasforecasts.feature.FeatureModule
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [FeatureModule::class])
    abstract fun featureAcitivity(): FeatureActivity
}

@Module
class AppModule {

    @Provides
    @Reusable
    internal fun provideContext(application: Application): Context = application
}

@Module
class RepositoryModule {

    @Provides
    @Reusable
    internal fun provideAppRepository(remoteDataSource: RemoteDataSource): AppDataSource =
        AppRepository(remoteDataSource)
}

@Module
class NetworkModule {

    @Provides
    @Reusable
    internal fun provideRemoteRepository(apiHelper: ApiHelper): RemoteDataSource =
        RemoteDataSource(apiHelper)

    @Provides
    @Reusable
    internal fun providePostApi() = ServiceAppFactory.create(true)
}
