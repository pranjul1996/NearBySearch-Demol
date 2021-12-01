package com.softradix.nearbysearch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.softradix.nearbysearch.adapter.ItemSearchAdapter
import com.softradix.nearbysearch.base.BaseFragment
import com.softradix.nearbysearch.data.Businesse
import com.softradix.nearbysearch.databinding.FragmentSearchBinding
import com.softradix.nearbysearch.utils.Constants
import com.softradix.nearbysearch.utils.makeGone
import com.softradix.nearbysearch.utils.makeVisible
import com.softradix.nearbysearch.utils.toast
import com.softradix.nearbysearch.viewModel.SearchViewModel


class SearchFragment : BaseFragment() {
    private lateinit var mViewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private var itemSearchAdapter: ItemSearchAdapter? = null
    private var itemList = ArrayList<Businesse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        attachObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        listeners()

        mViewModel.getSearchResult(
            null,
            "111 Sutter St, San Francisco, xCA"
        )

    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            itemSearchAdapter = ItemSearchAdapter(itemList)
            adapter = itemSearchAdapter
        }
    }

    private fun listeners() {

        binding.searchBtn.setOnClickListener {
            val search = binding.searchEt.text.toString()
            if (search.isEmpty()) {
                toast("Please enter something")
            } else {
                mViewModel.getSearchResult(
                    binding.searchEt.text.toString(),
                    "111 Sutter St, San Francisco, xCA"
                )
            }
        }

//        swipe to refresh Data
        binding.swipeRefresh.setOnRefreshListener {
            mViewModel.getSearchResult(
                binding.searchEt.text.toString(),
                "111 Sutter St, San Francisco, xCA"
            )
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun attachObservers() {
        mViewModel.searchResponse.observe(this) { response ->
            response?.let {
                itemList.clear()
                itemList.addAll(it.businesses)
                itemSearchAdapter?.notifyDataSetChanged()

                if (itemList.isEmpty()) binding.noDataText.makeVisible() else binding.noDataText.makeGone()
            }
            mViewModel.apiError.observe(this) {
                it?.let {
                    toast(it)
                }
            }
            mViewModel.onFailure.observe(this) {
                it?.let {
                    toast(Constants.SOMETHING_WENT_WRONG)
                }

            }
            mViewModel.isLoading.observe(this) {
                it?.let {
                    showLoading(it)
                }
            }
        }
    }

}