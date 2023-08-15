package com.learning.storeapp.ui.view

import android.os.Bundle
import android.util.Log
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

        binding.searchView.editText.setOnEditorActionListener { _, _, _ ->
            Log.e("VALUE", binding.searchBar.text.toString())
            binding.searchBar.text = binding.searchView.text
            binding.searchView.hide()
            viewModel.searchProduct(binding.searchBar.text.toString())
            return@setOnEditorActionListener false
        }

        binding.searchView.editText.addTextChangedListener {
            viewModel.searchProduct(it.toString())
        }

        viewModel.fetchProducts()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->

                    Log.e("UPDATE", "UPDATE")
                    binding.progressCircular.isVisible = uiState.isFetchingProducts

                    binding.recyclerView.adapter = ProductsAdapter(uiState.products) {
                        val bundle = Bundle()
                        bundle.putParcelable("itemUiState", it)
                        findNavController().navigate(
                            R.id.action_listProductsFragment_to_detailsFragment,
                            bundle
                        )
                    }
                    binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

                    binding.recyclerViewSearch.adapter = SearchProductAdapter(uiState.products) {
                        val bundle = Bundle()
                        bundle.putParcelable("itemUiState", it)
                        findNavController().navigate(
                            R.id.action_listProductsFragment_to_detailsFragment,
                            bundle
                        )
                    }
                    binding.recyclerViewSearch.layoutManager = GridLayoutManager(context, 1)

                    uiState.userMessages.firstOrNull()?.let {
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT)
                            .setAction("Action") {}.show()
                        viewModel.userMessageShown(it.id)
                    }

                }
            }
        }
    }


}