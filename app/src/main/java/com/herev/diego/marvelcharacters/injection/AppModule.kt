package com.herev.diego.marvelcharacters.injection

import android.app.Application
import android.content.Context
import com.herev.diego.marvelcharacters.androidConectors.stringResources.IStringResourceProvider
import com.herev.diego.marvelcharacters.androidConectors.stringResources.StringResourceProvider
import com.herev.diego.marvelcharacters.network.ApiFactory
import com.herev.diego.marvelcharacters.network.MarvelApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideContext (app : Application) : Context {
        return app
    }

    @Provides
    @Singleton
    fun provideMarvelApiService () : MarvelApiService {
        return ApiFactory().createMarvelApiService()
    }

    @Provides
    @Singleton
    fun provideStringResourceProvider (context: Context) : IStringResourceProvider {
        return StringResourceProvider(context)
    }
}