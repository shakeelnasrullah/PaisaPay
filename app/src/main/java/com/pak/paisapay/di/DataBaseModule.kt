package com.pak.paisapay.di

import android.content.Context
import com.pak.paisapay.data.local.CurrencyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context) = CurrencyDataBase.getDatabase(context)


    @Singleton
    @Provides
    fun provideCurrencyDao(dataBase: CurrencyDataBase) = dataBase.currencyDao()
}