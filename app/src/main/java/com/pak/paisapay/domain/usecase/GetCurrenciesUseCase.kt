package com.pak.paisapay.domain.usecase

import com.pak.paisapay.data.DataResource
import com.pak.paisapay.data.local.CurrencyDao
import com.pak.paisapay.data.model.Currency
import com.pak.paisapay.domain.repo.CurrencyRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow


class GetCurrenciesUseCase(
    private val repository: CurrencyRepositoryImpl,
    private val dispatcher: CoroutineDispatcher,
    private val dao: CurrencyDao
) : FlowUseCase<Unit, DataResource<List<Currency>>>(dispatcher) {
    override fun execute(parameters: Unit): Flow<DataResource<List<Currency>>> {
        return repository.getCurrencyRates()

    }
}
