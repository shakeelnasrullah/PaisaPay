package com.pak.paisapay.presentation.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pak.paisapay.base.BaseRecyclerViewAdapter
import com.pak.paisapay.data.model.Currency
import com.pak.paisapay.databinding.CurrencyListItemBinding


class CurrencyAdapter : BaseRecyclerViewAdapter<CurrencyAdapter.CurrencyViewHolder, Currency>() {

    class CurrencyViewHolder(private val binding : CurrencyListItemBinding )
        : RecyclerView.ViewHolder(binding.root){
            fun bind(item : Currency) {
                binding.apply {
                    currencyTitleTv.text = item.key
                    amountTv.text = item.value.toString()

                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = CurrencyListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}