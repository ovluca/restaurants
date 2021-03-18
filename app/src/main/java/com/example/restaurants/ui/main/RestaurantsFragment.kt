package com.example.restaurants.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurants.R
import com.example.restaurants.databinding.RestaurantsFragmentBinding
import com.example.restaurants.settings.OrderBy

class RestaurantsFragment : Fragment() {

    companion object {
        fun newInstance() = RestaurantsFragment()
    }

    private var _binding: RestaurantsFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: RestaurantsViewModel

    lateinit var restaurantsAdapter: RestaurantsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RestaurantsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RestaurantsViewModel::class.java)

        setupRecyclerView()
        setupOrderBy()
        setupFilter()
        setupObservers()
    }

    private fun setupRecyclerView() {
        restaurantsAdapter = RestaurantsAdapter(viewModel.adapterList)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = restaurantsAdapter
        }
    }

    private fun setupOrderBy() {
        val adapter =
            ArrayAdapter(
                requireContext(),
                R.layout.row_order_filter,
                OrderBy.values()
            )
        (binding.orderByRatingDropDown as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.orderByRatingDropDown.setOnItemClickListener { _, _, position, _ ->
            run {
                viewModel.order(position)
                restaurantsAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setupFilter() {
        val adapter =
            ArrayAdapter(
                requireContext(),
                R.layout.row_order_filter,
                resources.getStringArray(R.array.filter_by)
            )
        (binding.filterByRatingDropDown as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.filterByRatingDropDown.setOnItemClickListener { _, _, position, _ ->
            run {
                viewModel.filterByRating(position)
                restaurantsAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setupObservers() {
        viewModel.restaurantsLoadedData.observe(viewLifecycleOwner, {
            restaurantsAdapter.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE
            binding.frequentWordsTextView.text = viewModel.topThreeFrequentWords.toString()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}