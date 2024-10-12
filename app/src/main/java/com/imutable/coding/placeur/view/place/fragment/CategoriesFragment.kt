package com.imutable.coding.placeur.view.place.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.imutable.coding.placeur.R
import com.imutable.coding.placeur.databinding.FragmentCategoriesBinding
import com.imutable.coding.placeur.view.place.fragment.adapters.CategoriesAdapter
import com.imutable.coding.placeur.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private val categoriesViewModel by viewModels<CategoriesViewModel>()
    private var _binding: FragmentCategoriesBinding? = null
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var adapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        val view = binding
        adapter = CategoriesAdapter()
        setupAdapter()
        return view.root
    }

    private fun setupAdapter() {
        val recyclerViewTopFeed = binding.categoriesRecyclerView
        recyclerViewTopFeed.adapter = adapter
        recyclerViewTopFeed.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        categoriesViewModel.getAllCategories()
        categoriesViewModel.allCategoriesList.observe(viewLifecycleOwner, { categories ->
            adapter.setData(categories)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}