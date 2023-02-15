package com.pak.paisapay.domain.repo

import com.pak.paisapay.data.model.Currency
import org.json.JSONObject
import java.math.BigDecimal

class CurrencyParser {

    fun parse(input: String): List<Currency> {
        val jsonObject: JSONObject = JSONObject(input).getJSONObject(QUOTES_KEY)
        val list: MutableList<Currency> = ArrayList()
        jsonObject.keys().forEach {
            list.add(Currency(key = it.substring(3), value = getValue(jsonObject.get(it))))
        }
        return list
    }


    fun getValue(input: Any) = try {
        input.toString().toBigDecimal()
    } catch (e: Exception) {
        BigDecimal(0.0)
    }

    companion object {
        private const val QUOTES_KEY = "quotes"
    }

}