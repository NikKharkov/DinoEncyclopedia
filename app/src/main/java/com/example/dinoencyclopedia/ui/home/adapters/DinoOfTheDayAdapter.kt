package com.example.dinoencyclopedia.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.dinoencyclopedia.data.local.DinosaurEntity
import com.example.dinoencyclopedia.databinding.ItemDinosaurOfTheDayBinding
import com.example.dinoencyclopedia.util.ENCYCLOPEDIA_BASE_URL

class DinoOfTheDayAdapter(
    private val onDinoClick: (Int) -> Unit
) : RecyclerView.Adapter<DinoOfTheDayAdapter.DinoOfTheDayViewHolder>() {

    private var dinoOfTheDay: DinosaurEntity? = null

    inner class DinoOfTheDayViewHolder(private val binding: ItemDinosaurOfTheDayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dinosaur: DinosaurEntity) {
            binding.dotdImage.load("$ENCYCLOPEDIA_BASE_URL${dinosaur.imageUrl}")
            binding.dotdName.text = dinosaur.name
            binding.dotdDescription.text = dinosaur.shortDescription

            binding.root.setOnClickListener {
                onDinoClick(dinosaur.id)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DinoOfTheDayViewHolder {
        val binding = ItemDinosaurOfTheDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return DinoOfTheDayViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DinoOfTheDayViewHolder,
        position: Int
    ) {
        dinoOfTheDay?.let { holder.bind(it) }
    }

    override fun getItemCount() = 1

    fun submitDinoOfTheDay(dino: DinosaurEntity?) {
        dinoOfTheDay = dino
        notifyItemChanged(0)
    }
}