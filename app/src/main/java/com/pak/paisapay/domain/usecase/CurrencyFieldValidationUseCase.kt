package com.pak.paisapay.domain.usecase

import com.pak.paisapay.presentation.currency.CurrencyUIStates

class CurrencyFieldValidationUseCase {

    fun execute(input: String) = when {
        input.isBlank() -> CurrencyUIStates.FieldValidationErrorState(message = "Amount can't be empty")
        input == "0" -> CurrencyUIStates.FieldValidationErrorState(message = "Amount should be greater than 0")
        else -> CurrencyUIStates.FieldValidationSuccessState(input.toDouble())
    }
}