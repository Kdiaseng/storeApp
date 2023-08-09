package com.learning.storeapp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.learning.storeapp.databinding.FragmentListProductsBinding
import com.learning.storeapp.ui.viewModels.ProductsViewModel
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

        binding.buttonNext.setOnClickListener {
            binding.text.text = binding.textField.editText?.text
            val action =
                ListProductsFragmentDirections.actionListProductsFragmentToDetailsFragment()
            findNavController().navigate(action);
        }

        binding.texInput.doOnTextChanged { text, start, before, count ->
            Log.e("TExt", text.toString())
            binding.text.text = text.toString()
        }
    }
}