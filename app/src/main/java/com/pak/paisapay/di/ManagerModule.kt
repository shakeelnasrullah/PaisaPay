package com.pak.paisapay.di

import android.content.Context
import com.pak.paisapay.domain.repo.CurrencyParser
import com.pak.paisapay.domain.usecase.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class ManagerModule {

    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context) : PreferenceManager {
        return PreferenceManager(context)
    }

    @Singleton
    @Provides
    fun provideCurrencyParser() : CurrencyParser {
        return CurrencyParser()
    }


}