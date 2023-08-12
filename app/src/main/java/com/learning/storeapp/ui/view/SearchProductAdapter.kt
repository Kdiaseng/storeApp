package com.learning.storeapp.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.storeapp.databinding.ItemSearchLayoutBinding
import com.learning.storeapp.ui.model.ItemUiState

class SearchProductAdapter(
    private val dataSet: List<ItemUiState>,
    private val onClickAction: (ItemUiState) -> Unit
) :
    RecyclerView.Adapter<SearchProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSearchLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemUiState = this.dataSet[position]
        holder.bind(itemUiState)
    }

    inner class ViewHolder(private val binding: ItemSearchLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemUiState) {
            binding.apply {
                textProduct.text = item.title
                root.setOnClickListener {
                    onClickAction(item)
                }
            }
        }
    }
}