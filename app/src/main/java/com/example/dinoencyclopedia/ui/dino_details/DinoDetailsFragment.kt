package com.example.dinoencyclopedia.ui.dino_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil3.load
import com.example.dinoencyclopedia.databinding.FragmentDinoDetailsBinding
import com.example.dinoencyclopedia.util.ENCYCLOPEDIA_BASE_URL
import org.koin.androidx.viewmodel.ext.android.viewModel

class DinoDetailsFragment : Fragment() {

    private var _binding: FragmentDinoDetailsBinding? = null

    private val binding get() = _binding!!

    private val args: DinoDetailsFragmentArgs by navArgs()

    private val dinoDetailsViewModel: DinoDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDinoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dinoDetailsViewModel.loadDinosaur(args.dinoId)
        observeUiState()
    }

    private fun observeUiState() {
        dinoDetailsViewModel.dinosaur.observe(viewLifecycleOwner) {
            binding.toolbar.apply {
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
                title = it.name
            }

            binding.dinoImage.load("$ENCYCLOPEDIA_BASE_URL${it.imageUrl}")

            binding.dinoName.text = it.name

            binding.dinoPeriod.text = it.period
            binding.dinoDiet.text = it.diet
            binding.dinoLength.text = it.length
            binding.dinoWeight.text = it.weight
            binding.dinoLocation.text = it.location

            binding.dinoDescription.text = it.longDescription
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}