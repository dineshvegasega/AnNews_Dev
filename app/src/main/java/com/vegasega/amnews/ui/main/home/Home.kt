package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vegasega.amnews.databinding.HomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {

    private val viewModel: HomeVM by viewModels()
    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeBinding.inflate(inflater)
        return binding.root
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val adapter= HomePagerAdapter()
            adapter.submitList(viewModel.itemMain)
            adapter.notifyDataSetChanged()
            introViewPager.adapter=adapter
            TabLayoutMediator(tabLayout, introViewPager) { tab, position ->
                Log.e("TAG", "positionD" +position)
            }.attach()

            introViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    Log.e("TAG", "positionA" +position)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    //tabPosition = position
                    Log.e("TAG", "positionB" +position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    Log.e("TAG", "positionC" +state)
                }
            })
        }

    }


}