package com.pak.paisapay.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pak.paisapay.data.model.Currency

@Database(entities = [Currency::class], version = 1)
@TypeConverters(Converter::class)
abstract class CurrencyDataBase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var INSTANCE: CurrencyDataBase? = null

        fun getDatabase(context: Context): CurrencyDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CurrencyDataBase::class.java,
                        "currencyDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}