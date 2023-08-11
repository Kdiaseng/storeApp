package com.learning.storeapp.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.storeapp.databinding.ItemLayoutBinding
import com.learning.storeapp.ui.model.ItemUiState

class ProductsAdapter(private val dataSet: MutableList<ItemUiState>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(private val itemBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ItemUiState) {
            itemBinding.apply {
                textTitle.text = item.title
                textPrice.text = item.price.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount() =
        dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemUiState = this.dataSet[position]
        holder.bind(itemUiState)
    }

}