package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.streetsaarthi.nasvi.screens.interfaces.CallBackListener
import com.vegasega.amnews.databinding.HomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment(), CallBackListener {
    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeVM by viewModels()

    companion object {
        var callBackListener: CallBackListener? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeBinding.inflate(inflater)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callBackListener = this
        binding.apply {
            val adapter = HomePagerAdapter(requireActivity())
            introViewPager.adapter = adapter
            adapter.notifyDataSetChanged()
            introViewPager.setCurrentItem(adapter.itemCount-2, false)

//            introViewPager.setOnClickListener {
//                Log.e("TAG", "introViewPager")
//            }

        }
    }

    override fun onCallBack(pos: Int) {
        binding.introViewPager.setCurrentItem(pos, true)
//        when(pos){
//            0 -> binding.introViewPager.setCurrentItem(pos, false)
//            1 -> binding.introViewPager.setCurrentItem(pos, false)
//        }
    }
}