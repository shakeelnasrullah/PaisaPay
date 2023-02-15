package com.pak.paisapay.domain.repo

import com.pak.paisapay.data.DataResource
import com.pak.paisapay.data.model.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencyRates(): Flow<DataResource<List<Currency>>>
}