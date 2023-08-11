package com.learning.storeapp.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.learning.storeapp.databinding.FragmentListProductsBinding
import com.learning.storeapp.ui.viewModels.ProductsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
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

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.progressCircular.isVisible = uiState.isFetchingProducts

                    uiState.userMessages.firstOrNull()?.let {
                        Log.e("MESSAGE", it.message)
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT)
                            .setAction("Action") {}.show()
                        viewModel.userMessageShown(it.id)
                    }
                    for (product in uiState.products) {
                        Log.e("descriptions", product.description)
                    }
                }
            }
        }

        viewModel.fetchProducts()

        binding.buttonNext.setOnClickListener {
            binding.text.text = binding.textField.editText?.text
            viewModel.fetchProducts()


//            val action =
//                ListProductsFragmentDirections.actionListProductsFragmentToDetailsFragment()
//            findNavController().navigate(action);
        }

        binding.texInput.doOnTextChanged { text, start, before, count ->
            Log.e("TExt", text.toString())
            binding.text.text = text.toString()
        }
    }
}