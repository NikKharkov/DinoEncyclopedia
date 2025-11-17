package com.example.dinoencyclopedia.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinoencyclopedia.databinding.FragmentHomeBinding
import com.example.dinoencyclopedia.domain.news.NewsArticle
import com.example.dinoencyclopedia.domain.news.NewsCategory
import com.example.dinoencyclopedia.ui.home.adapters.DinoOfTheDayAdapter
import com.example.dinoencyclopedia.ui.home.adapters.NewsArticleAdapter
import com.example.dinoencyclopedia.ui.home.adapters.NewsFiltersAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val dinoOfTheDayAdapter by lazy {
        DinoOfTheDayAdapter(
            onDinoClick = { dinoId ->
                openDinosaurOfTheDay(dinoId)
            }
        )
    }

    private val filterAdapters by lazy {
        NewsFiltersAdapter(
            onFilterSelected = { category ->
                homeViewModel.filterByCategory(category)
            }
        )
    }

    private val newsAdapter by lazy {
        NewsArticleAdapter(
            onArticleClick = { article -> openArticle(article) },
            onShareClick = { article -> shareArticle(article) }
        )
    }

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeUiState()
    }

    private fun setupRecyclerView() {
        val concatAdapter = ConcatAdapter(dinoOfTheDayAdapter, filterAdapters, newsAdapter)

        binding.newsList.apply {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeUiState() {
        homeViewModel.homeUiState.observe(viewLifecycleOwner) { state ->
            binding.loadingNewsIndicator.isVisible = state.isLoading

            dinoOfTheDayAdapter.submitDinoOfTheDay(state.dinosaurOfTheDay)
            filterAdapters.updateSelected(state.selectedCategory)

            val filtered = if (state.selectedCategory == NewsCategory.ALL) {
                state.news
            } else {
                state.news.filter { it.category == state.selectedCategory }
            }

            newsAdapter.submitList(filtered)
        }
    }

    private fun shareArticle(newsArticle: NewsArticle) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, newsArticle.link)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(intent, "Check this article\uD83E\uDD96"))
    }

    private fun openArticle(article: NewsArticle) {
        val action = HomeFragmentDirections.homeToWebview(articleUrl = article.link ?: "")
        findNavController().navigate(action)
    }

    private fun openDinosaurOfTheDay(dinoId: Int) {
        val action = HomeFragmentDirections.homeToDotd(dinoId = dinoId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}