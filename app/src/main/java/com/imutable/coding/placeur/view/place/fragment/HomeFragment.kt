package com.imutable.coding.placeur.view.place.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.imutable.coding.placeur.R
import com.imutable.coding.placeur.databinding.FragmentHomeBinding
import com.imutable.coding.placeur.util.NetworkResult
import com.imutable.coding.placeur.view.place.fragment.adapters.MainFeedAdapter
import com.imutable.coding.placeur.view.place.fragment.adapters.TopFeedAdapter
import com.imutable.coding.placeur.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterMainFeed: MainFeedAdapter
    private lateinit var adapterTopFeed: TopFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val view = binding
        adapterMainFeed = MainFeedAdapter()
        adapterTopFeed = TopFeedAdapter()

        setupTopFeed()
        setupMainFeed()
        return view.root
    }

    private fun setupMainFeed() {
        //Recycler View Main Feed
        val recyclerViewMainFeed = binding.mainFeedRecyclerView
        recyclerViewMainFeed.adapter = adapterMainFeed
        recyclerViewMainFeed.layoutManager =
            GridLayoutManager(requireContext(), 2)

        //Get Feed Data
        setupMainFeedObserver()
    }

    private fun setupMainFeedObserver() {

        binding.homeProgressBar.visibility = View.VISIBLE
        homeViewModel.fetchAllPlaces()
        homeViewModel.allPlaces.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.homeProgressBar.visibility = View.GONE
                    response.data?.let {
                        adapterMainFeed.setData(it)
                    }
                }
                is NetworkResult.Error -> {
                    binding.homeProgressBar.visibility = View.GONE
                    response.message.let {
                        if (it != null) {
                            Snackbar.make(binding.root, it, 100).show()
                        }
                    }

                }

                is NetworkResult.Loading -> {
                    binding.homeProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupTopFeedObserver() {

        binding.homeProgressBar.visibility = View.VISIBLE
        homeViewModel.fetchAllCategories()
        homeViewModel.allCategories.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.homeProgressBar.visibility = View.GONE
                    response.data?.let {
                        adapterTopFeed.setData(it)
                    }
                }
                is NetworkResult.Error -> {
                    binding.homeProgressBar.visibility = View.GONE
                    response.message.let {
                        if (it != null) {
                            Snackbar.make(binding.root, it, 100).show()
                        }
                    }
                }

                is NetworkResult.Loading -> {
                    binding.homeProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupTopFeed() {
        //Recycler View Top Feed
        val recyclerViewTopFeed = binding.topFeedRecyclerView
        recyclerViewTopFeed.adapter = adapterTopFeed
        recyclerViewTopFeed.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        //Get Categories Data
        setupTopFeedObserver()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}