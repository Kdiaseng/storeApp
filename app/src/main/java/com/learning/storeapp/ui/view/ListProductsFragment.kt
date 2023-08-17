package com.learning.storeapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.learning.storeapp.R
import com.learning.storeapp.databinding.FragmentListProductsBinding
import com.learning.storeapp.ui.model.ItemUiState
import com.learning.storeapp.ui.viewModels.ProductsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListProductsFragment : Fragment() {
    private var _binding: FragmentListProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchProducts()
        setupListener()
        setupObserver()
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.apply {
                        progressCircular.isVisible = uiState.isFetchingProducts
                        recyclerViewSearch.adapter =
                            SearchProductAdapter(uiState.products, ::goToDetailsProduct)
                        recyclerViewSearch.layoutManager = GridLayoutManager(context, 1)
                    }

                    if (uiState.isListModeView) {
                        showModeList(uiState.products)
                    } else {
                        showModeGrid(uiState.products)
                    }
                    uiState.userMessages.firstOrNull()?.let {
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT)
                            .setAction("Action") {}.show()
                        viewModel.userMessageShown(it.id)
                    }
                }
            }
        }
    }

    private fun setupListener() {
        binding.apply {
            searchView.editText.also {
                it.setOnEditorActionListener { _, _, _ ->
                    this.searchBar.text = this.searchView.text
                    this.searchView.hide()
                    return@setOnEditorActionListener false
                }
                it.addTextChangedListener { text ->
                    viewModel.searchProduct(text.toString())
                }

            }
            searchBar.also {
                it.inflateMenu(R.menu.searchbar_menu)
                it.setOnMenuItemClickListener { itemMenu ->
                    when (itemMenu.itemId) {
                        R.id.show_mode -> {
                            itemMenu.isChecked = !itemMenu.isChecked
                            viewModel.changeModeView()
                            itemMenu.setIcon(
                                if (itemMenu.isChecked) MODE_VIEW_LIST else MODE_VIEW_GRID
                            )
                        }

                        R.id.favorite -> {

                        }
                    }
                    return@setOnMenuItemClickListener false
                }
            }
        }
    }

    private fun showModeGrid(products: List<ItemUiState>) {
        binding.apply {
            val adapter = ProductsGridAdapter(products, ::goToDetailsProduct)
            recyclerView.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(context, 2)
            }
        }
    }

    private fun showModeList(products: List<ItemUiState>) {
        binding.apply {
            val adapter = ProductsListAdapter(products, ::goToDetailsProduct)
            recyclerView.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(context, 1)
            }
        }
    }

    private fun goToDetailsProduct(item: ItemUiState) {
        val bundle = Bundle()
        bundle.putParcelable("itemUiState", item)
        findNavController().navigate(
            R.id.action_listProductsFragment_to_detailsFragment,
            bundle
        )
    }


    companion object {
        val MODE_VIEW_LIST: Int = R.drawable.outline_list
        val MODE_VIEW_GRID: Int = R.drawable.outline_grid
    }

}