package com.pak.paisapay.presentation.currency

import com.pak.paisapay.data.model.Currency

sealed class CurrencyUIStates {

    class CurrenciesSuccessState( val currencies : List<Currency>) : CurrencyUIStates()
    object CurrencyLoadingState : CurrencyUIStates()
    object ConversionLoadingState : CurrencyUIStates()
    class ConversionSuccessState(val convertedList : List<Currency>) : CurrencyUIStates()
    class FieldValidationErrorState(val message: String) : CurrencyUIStates()
    class FieldValidationSuccessState(val amount : Double) : CurrencyUIStates()
    class ErrorState(val message: String?) : CurrencyUIStates()

}
