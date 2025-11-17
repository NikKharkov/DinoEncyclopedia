package com.example.dinoencyclopedia.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinoencyclopedia.databinding.ChipFilterBinding
import com.example.dinoencyclopedia.databinding.ItemFilterRowBinding
import com.example.dinoencyclopedia.domain.news.NewsCategory

class NewsFiltersAdapter(
    private val onFilterSelected: (NewsCategory) -> Unit
) : RecyclerView.Adapter<NewsFiltersAdapter.FiltersViewHolder>() {

    private var selectedCategory: NewsCategory = NewsCategory.ALL

    inner class FiltersViewHolder(val binding: ItemFilterRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersViewHolder {
        val binding = ItemFilterRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FiltersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FiltersViewHolder, position: Int) {
        val group = holder.binding.filterChipsGroup
        val inflater = LayoutInflater.from(holder.itemView.context)

        group.removeAllViews()

        NewsCategory.entries.forEach { category ->
            val chipBinding = ChipFilterBinding.inflate(inflater, group, false)

            chipBinding.articleFilterChip.text = category.displayName
            chipBinding.articleFilterChip.isChecked = category == selectedCategory

            chipBinding.articleFilterChip.setOnClickListener {
                selectedCategory = category
                onFilterSelected(category)
                notifyItemChanged(0)
            }

            group.addView(chipBinding.articleFilterChip)
        }
    }

    override fun getItemCount() = 1

    fun updateSelected(category: NewsCategory) {
        selectedCategory = category
        notifyItemChanged(0)
    }
}