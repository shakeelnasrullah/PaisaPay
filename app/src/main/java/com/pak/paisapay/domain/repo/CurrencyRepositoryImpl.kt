package com.pak.paisapay.domain.repo

import com.pak.paisapay.data.DataResource
import com.pak.paisapay.data.callApi
import com.pak.paisapay.data.data
import com.pak.paisapay.data.local.CurrencyDao
import com.pak.paisapay.data.model.Currency
import com.pak.paisapay.data.remote.APIService
import com.pak.paisapay.data.succeeded
import com.pak.paisapay.domain.usecase.PreferenceManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import java.util.concurrent.TimeUnit


class CurrencyRepositoryImpl(
    private val apiService: APIService,
    val dao: CurrencyDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val preferenceManager: PreferenceManager,
    private val currencyParser: CurrencyParser
) : CurrencyRepository {
    override fun getCurrencyRates(): Flow<DataResource<List<Currency>>> = flow {
        // show loading
        emit(DataResource.Loading)
        val previousTime = preferenceManager.getTime()
        if (needToLoadDataFromServer(previousTime)) {
            val result = callApi { apiService.getCurrencyRates() }
            if (result.succeeded()) {
                val parseList = currencyParser.parse(result.data!!.string())
                dao.deleteAll()
                dao.insertCurrencies(parseList)
                preferenceManager.save(Date())
                emit(DataResource.Success(parseList))
            } else {
                emit(DataResource.Error<Throwable>(RuntimeException("Not Found")))
            }
        } else {
            emit(DataResource.Success(dao.getCurrencies()))
        }
    }.flowOn(dispatcher)


    private fun getTimeDifference(previousTime: Long, currentTime: Long): Long {
        val diffInMilliseconds = currentTime - previousTime
        return TimeUnit.MILLISECONDS.toMinutes(diffInMilliseconds)
    }


    private fun needToLoadDataFromServer(lastSyncTime: Long): Boolean {
        if (lastSyncTime == 0L) {
            return true
        } else {
            val difference = getTimeDifference(lastSyncTime, System.currentTimeMillis())
            if (difference >= 30) {
                return true
            }
            return false
        }
    }
}