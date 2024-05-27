package com.vegasega.amnews.ui.onboarding.topics

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.TopicsBinding
import com.vegasega.amnews.utils.SpacesItemDecoration
import com.vegasega.amnews.utils.dpToPx
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Topics : Fragment(){

    private var _binding: TopicsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TopicsVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TopicsBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = viewModel.verticalAdapter
            viewModel.verticalAdapter.notifyDataSetChanged()
            viewModel.verticalAdapter.submitList(arrayListOf(
                Item(R.drawable.globe_1, "Mondial"),
                Item(R.drawable.waves, "Région Océan Indien"),
                Item(R.drawable.r_gional, "Régional"),
                Item(R.drawable.politique, "Politique"),
                Item(R.drawable.la_d_fense, "La défense"),
                Item(R.drawable.finance, "Finance"),
                Item(R.drawable.machine_learning_1, "Technologie"),
                Item(R.drawable.lifestyle, "Lifestyle"),
                Item(R.drawable.divertissement, "Divertissement"),
                Item(R.drawable.tourisme, "Tourisme"),
                Item(R.drawable.des_sports, "Des sports"),
                Item(R.drawable.opinion, "Opinion")
                ))
            val spacingInPixels: Int = dpToPx(16f)
            recyclerView.addItemDecoration(SpacesItemDecoration(spacingInPixels, 2))
        }

    }






}