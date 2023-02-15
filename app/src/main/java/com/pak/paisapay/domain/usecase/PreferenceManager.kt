package com.pak.paisapay.domain.usecase

import android.content.Context
import com.pak.paisapay.common.Constants.Companion.TIME_EXTRAS
import java.util.*

class PreferenceManager(val context: Context) {

    companion object {
        const val preference_name = "preference_manager"
    }

    fun save(date: Date) {
        val sharedPref =
            context.getSharedPreferences(preference_name, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putLong(TIME_EXTRAS, date.time)
            apply()
        }
    }

    fun getTime(): Long {
        val sharedPref =
            context.getSharedPreferences(preference_name, Context.MODE_PRIVATE) ?: return 0
        return sharedPref.getLong(TIME_EXTRAS, 0)
    }
}