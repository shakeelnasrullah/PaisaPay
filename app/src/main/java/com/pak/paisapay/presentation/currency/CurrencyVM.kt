package com.pak.paisapay.presentation.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pak.paisapay.data.model.Currency
import com.pak.paisapay.data.onError
import com.pak.paisapay.data.onLoading
import com.pak.paisapay.data.onSuccess
import com.pak.paisapay.domain.usecase.ConvertCurrenciesUseCase
import com.pak.paisapay.domain.usecase.CurrencyFieldValidationUseCase
import com.pak.paisapay.domain.usecase.GetCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject


@HiltViewModel
class CurrencyVM @Inject constructor(
    val useCase: GetCurrenciesUseCase,
    private val currencyFieldValidationUseCase: CurrencyFieldValidationUseCase,
    private val convertCurrenciesUseCase: ConvertCurrenciesUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<CurrencyUIStates>()
    val uiState: LiveData<CurrencyUIStates> = _uiState

    var sourceCurrency: String = ""
    var currencies: List<Currency> = listOf()

    init {
        getCurrencyRates()
    }

    private fun getCurrencyRates() {
        viewModelScope.launch {
            useCase.invoke(Unit).collect() { dataSource ->
                dataSource.onLoading {
                    _uiState.value = CurrencyUIStates.CurrencyLoadingState
                }
                dataSource.onError {
                    _uiState.value = CurrencyUIStates.ErrorState(this.exception.message)
                }
                dataSource.onSuccess {
                    currencies = this.data
                    _uiState.value = CurrencyUIStates.CurrenciesSuccessState(currencies)
                }
            }
        }
    }

    fun convertCurrencies(amount: BigDecimal) {
        _uiState.value = CurrencyUIStates.ConversionLoadingState
        _uiState.value = convertCurrenciesUseCase.execute(sourceCurrency, currencies, amount)
    }

    fun validateAmount(amount: String) {
        _uiState.value = currencyFieldValidationUseCase.execute(amount)
    }

    fun getCountriesCode(): List<String> {
        val countries = mutableListOf<String>()
        currencies.forEach {
            countries.add(it.key)
        }
        return countries
    }
}