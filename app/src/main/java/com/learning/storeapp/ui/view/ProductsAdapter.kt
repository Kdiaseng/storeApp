package com.learning.storeapp.ui.view


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.storeapp.databinding.ItemLayoutBinding
import com.learning.storeapp.ui.model.ItemUiState
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private val dataSet: List<ItemUiState>,
    private val onClick: (ItemUiState) -> Unit
) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ItemUiState) {

            itemBinding.apply {
                textTitle.text = item.title
                "R$ ${item.price}".also { textPrice.text = it }
                Picasso.get().load(item.image)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .into(image)
                root.setOnClickListener {
                    onClick(item)
                }
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