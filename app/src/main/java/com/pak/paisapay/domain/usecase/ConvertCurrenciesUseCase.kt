package com.pak.paisapay.domain.usecase

import com.pak.paisapay.data.model.Currency
import com.pak.paisapay.presentation.currency.CurrencyUIStates
import java.math.BigDecimal

class ConvertCurrenciesUseCase {

    fun execute(
        sourceCurrency: String,
        currencies: List<Currency>,
        amount: BigDecimal
    ): CurrencyUIStates {
        val convertedList = arrayListOf<Currency>()
        val currency: Currency? = currencies.find { it.key == sourceCurrency }
        currency?.let {
            currencies.forEach { mCurrency ->
                val result = (mCurrency.value / it.value) * amount
                convertedList.add(Currency(mCurrency.id, mCurrency.key, twoDecimal(result)))
            }
        } ?: CurrencyUIStates.ErrorState(message = "Found Error While conversion")
        return CurrencyUIStates.ConversionSuccessState(convertedList)
    }

    private fun twoDecimal(amount: BigDecimal): BigDecimal {
        return amount.setScale(2, BigDecimal.ROUND_UP)
    }
}