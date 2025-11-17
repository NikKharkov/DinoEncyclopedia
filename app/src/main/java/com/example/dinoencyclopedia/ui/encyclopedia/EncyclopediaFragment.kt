package com.example.dinoencyclopedia.ui.encyclopedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dinoencyclopedia.R
import com.example.dinoencyclopedia.databinding.FragmentEncyclopediaBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EncyclopediaFragment : Fragment() {

    private var _binding: FragmentEncyclopediaBinding? = null
    private val binding get() = _binding!!

    private val encyclopediaItemAdapter by lazy {
        EncyclopediaItemAdapter(
            onStarClick = { id, favouriteStatus ->
                encyclopediaViewModel.updateFavoriteStatus(id, !favouriteStatus)
            },
            onItemClick = { dinoId ->
                openEncyclopediaItem(dinoId)
            }
        )
    }

    private val encyclopediaViewModel: EncyclopediaViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEncyclopediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchView()
        observeUiState()
        setupSwipeOnRefresh()
    }

    private fun setupRecyclerView() {
        binding.encyclopediaGrid.apply {
            adapter = encyclopediaItemAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun setupSearchView() {
        binding.searchEditText.addTextChangedListener { text ->
            val query = text.toString()
            encyclopediaViewModel.searchDinosaurs(query)
        }
    }

    private fun observeUiState() {
        encyclopediaViewModel.encyclopediaUiState.observe(viewLifecycleOwner) { state ->
            binding.loadingEncyclopediaIndicator.isVisible = state.isLoading
            binding.swipeRefresh.isRefreshing = state.isLoading

            encyclopediaItemAdapter.submitList(state.dinosaurs)

            val showFavouritesIcon = if (state.showFavourites) {
                R.drawable.ic_star_filled
            } else {
                R.drawable.ic_star_unfilled
            }

            binding.showFavourites.setImageResource(showFavouritesIcon)
            binding.showFavourites.setOnClickListener {
                encyclopediaViewModel.toggleFavourites()
            }
        }
    }

    private fun setupSwipeOnRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            encyclopediaViewModel.getAllDinosaurs(true)
        }
    }

    private fun openEncyclopediaItem(dinoId: Int) {
        val action = EncyclopediaFragmentDirections.encyclopediaToDinoDetails(dinoId)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}