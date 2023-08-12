package com.learning.storeapp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.learning.storeapp.R
import com.learning.storeapp.databinding.FragmentDetailsBinding
import com.learning.storeapp.databinding.FragmentListProductsBinding
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailsFragmentArgs.fromBundle(requireArguments())
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val itemUiState = args.itemUiState
        itemUiState?.let { item ->
            binding.apply {
                titleText.text = item.title
                "R$ ${item.price}".also { priceText.text = it }
                descriptionText.text = item.description
                ratingBar.rating = item.rating.rate.toFloat()
                "${item.rating.rate}   |   ${item.rating.count}".also { rationText.text = it }
                Picasso.get().load(item.image).into(imageProduct)
            }
        }

    }


}