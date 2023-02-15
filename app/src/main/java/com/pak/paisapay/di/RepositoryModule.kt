package com.pak.paisapay.di

import com.pak.paisapay.data.local.CurrencyDao
import com.pak.paisapay.data.remote.APIService
import com.pak.paisapay.domain.repo.CurrencyParser
import com.pak.paisapay.domain.repo.CurrencyRepositoryImpl
import com.pak.paisapay.domain.usecase.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module

class RepositoryModule {

    @Singleton
    @Provides
    fun provideCurrencyRepository(
        apiService: APIService,
        dao: CurrencyDao,
        preferenceManager: PreferenceManager,
        currencyParser: CurrencyParser
    ): CurrencyRepositoryImpl {
        return CurrencyRepositoryImpl(
            apiService, dao, Dispatchers.IO, preferenceManager, currencyParser
        )
    }
}