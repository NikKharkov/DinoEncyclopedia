package com.example.dinoencyclopedia.ui.encyclopedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.dinoencyclopedia.R
import com.example.dinoencyclopedia.data.local.DinosaurEntity
import com.example.dinoencyclopedia.databinding.ItemEncyclopediaCardBinding
import com.example.dinoencyclopedia.util.ENCYCLOPEDIA_BASE_URL

class EncyclopediaItemAdapter(
    private val onStarClick: (Int, Boolean) -> Unit,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<EncyclopediaItemAdapter.EncyclopediaItemViewHolder>() {

    private var dinosaurs = emptyList<DinosaurEntity>()

    inner class EncyclopediaItemViewHolder(private val binding: ItemEncyclopediaCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dinosaur: DinosaurEntity) {
            binding.encDinoImage.load("$ENCYCLOPEDIA_BASE_URL${dinosaur.imageUrl}")

            binding.encDinoName.text = dinosaur.name
            binding.encDinoPeriod.text = dinosaur.period
            binding.encDinoDiet.text = dinosaur.diet

            binding.saveToFavourites.setImageResource(
                if (dinosaur.isFavorite) {
                    R.drawable.ic_star_filled
                } else {
                    R.drawable.ic_star_unfilled
                }
            )

            binding.saveToFavourites.setOnClickListener {
                onStarClick(dinosaur.id, dinosaur.isFavorite)
            }

            binding.root.setOnClickListener {
                onItemClick(dinosaur.id)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EncyclopediaItemViewHolder {
        val binding = ItemEncyclopediaCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return EncyclopediaItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: EncyclopediaItemViewHolder,
        position: Int
    ) {
        holder.bind(dinosaurs[position])
    }

    override fun getItemCount() = dinosaurs.size

    fun submitList(submittedDinosaurs: List<DinosaurEntity>) {
        dinosaurs = submittedDinosaurs
        notifyDataSetChanged()
    }
}