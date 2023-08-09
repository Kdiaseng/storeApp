package com.learning.storeapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.learning.storeapp.databinding.FragmentListProductsBinding

class ListProductsFragment : Fragment() {

    private  var _binding: FragmentListProductsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonNext.setOnClickListener {
            binding.text.text = binding.textField.editText?.text
            val action = ListProductsFragmentDirections.actionListProductsFragmentToDetailsFragment()
            findNavController().navigate(action);
        }

        binding.texInput.doOnTextChanged { text, start, before, count ->
            Log.e("TExt", text.toString())
            binding.text.text = text.toString()
        }
    }
}