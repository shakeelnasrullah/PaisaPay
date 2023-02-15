package com.pak.paisapay.presentation.currency

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.pak.paisapay.base.BaseFragment
import com.pak.paisapay.databinding.FragmentCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CurrencyFragment : BaseFragment<FragmentCurrencyBinding>(FragmentCurrencyBinding::inflate),
    AdapterView.OnItemSelectedListener {

    private val viewModel: CurrencyVM by viewModels()
    private var recyclerAdapter: CurrencyAdapter? = null


    override fun setupView() {
        binding?.calculate?.setOnClickListener {
            closeKeyboard(requireContext(), it)
            viewModel.validateAmount(binding?.amountEt?.text.toString())
        }
        binding?.recyclerview?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.recyclerview?.hasFixedSize()
        recyclerAdapter = CurrencyAdapter()
        binding?.recyclerview?.adapter = recyclerAdapter
    }

    override fun initObservations() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            binding?.progressBar?.visibility = View.GONE
            when (uiState) {
                CurrencyUIStates.CurrencyLoadingState -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                }
                is CurrencyUIStates.CurrenciesSuccessState -> {
                    binding?.loadingLayout?.root?.visibility = View.GONE
                    binding?.calculate?.visibility = View.VISIBLE
                    // set up spinner
                    setUpSpinner(viewModel.getCountriesCode())
                    recyclerAdapter?.replaceList(uiState.currencies)
                }
                is CurrencyUIStates.ErrorState -> {
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(context, "Error Occurred in API", Toast.LENGTH_SHORT).show()
                }
                is CurrencyUIStates.FieldValidationErrorState -> {
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
                }
                is CurrencyUIStates.FieldValidationSuccessState -> {
                    viewModel.convertCurrencies(uiState.amount.toBigDecimal())
                }
                CurrencyUIStates.ConversionLoadingState -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                    binding?.loadingLayout?.root?.visibility = View.VISIBLE
                }
                is CurrencyUIStates.ConversionSuccessState -> {
                    binding?.progressBar?.visibility = View.GONE
                    recyclerAdapter?.replaceList(uiState.convertedList)
                }
            }
        }
    }

    private fun setUpSpinner(currencyNameList: List<String>) {
        binding?.spinner?.onItemSelectedListener = this
        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            requireContext(), android.R.layout.simple_spinner_item, currencyNameList
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        binding?.spinner?.adapter = adapter
        binding?.progressBar?.visibility = View.GONE
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.sourceCurrency = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}