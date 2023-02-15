package com.pak.paisapay.di

import  com.pak.paisapay.data.local.CurrencyDao
import com.pak.paisapay.domain.repo.CurrencyRepositoryImpl
import com.pak.paisapay.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers

@InstallIn(ViewModelComponent::class)
@Module
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideCurrencyUseCase(repository: CurrencyRepositoryImpl, dao: CurrencyDao): GetCurrenciesUseCase {
        return GetCurrenciesUseCase(repository, Dispatchers.IO, dao)
    }

    @ViewModelScoped
    @Provides
    fun provideCurrencyFieldValidationUseCase() = CurrencyFieldValidationUseCase()


    @ViewModelScoped
    @Provides
    fun provideConvertCurrenciesUseCase() = ConvertCurrenciesUseCase()

}