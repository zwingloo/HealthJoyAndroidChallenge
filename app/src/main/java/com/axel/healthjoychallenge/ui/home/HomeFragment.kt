package com.axel.healthjoychallenge.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.axel.healthjoychallenge.R
import com.axel.healthjoychallenge.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private var apiKey = ""

    private lateinit var postAdapter: PostPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiKey = arguments?.getString(API_KEY) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        subscribe()
    }

    private fun subscribe() {
        homeViewModel.getPost(apiKey = apiKey).observe(viewLifecycleOwner) {
            postAdapter.submitData(lifecycle, it)
        }
    }

    private fun setupRecyclerView() {
        postAdapter = PostPagingAdapter()
        binding.rvPost.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = postAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupToolbar() {
        with(binding.toolbar) {
            inflateMenu(R.menu.menu_top_app_bar)
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            val menuSearch = menu.findItem(R.id.action_search)
            val searchView = menuSearch.actionView as SearchView
            searchView.queryHint = getString(R.string.enter_search_phrase)

            val searchViewTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    homeViewModel.searchPost(query ?: "")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            }
            searchView.setOnQueryTextListener(searchViewTextListener)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val API_KEY = "API_KEY"
    }
}