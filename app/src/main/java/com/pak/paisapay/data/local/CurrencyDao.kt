package com.pak.paisapay.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pak.paisapay.data.model.Currency

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<Currency>)

    @Query("select * from currency")
    suspend fun getCurrencies() : List<Currency>

    @Query("Delete from currency")
    suspend fun deleteAll()
}