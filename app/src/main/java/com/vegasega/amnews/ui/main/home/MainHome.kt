package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.streetsaarthi.nasvi.screens.interfaces.CallBackListener
import com.vegasega.amnews.databinding.MainHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHome : Fragment(), CallBackListener {
    private var _binding: MainHomeBinding? = null
    private val binding get() = _binding!!


    companion object {
        var callBackListener: CallBackListener? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainHomeBinding.inflate(inflater)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callBackListener = this
        binding.apply {
            val adapter = MainHomeAdapter(requireActivity())
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