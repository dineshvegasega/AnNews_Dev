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
import androidx.fragment.app.viewModels
import com.streetsaarthi.nasvi.screens.interfaces.CallBackListener
import com.vegasega.amnews.databinding.HomeBinding
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.utils.parcelable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment(), CallBackListener {
    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeVM by viewModels()

    companion object {
        var callBackListener: CallBackListener? = null

        var consentIntent : Item ?= null

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
            introViewPager.isUserInputEnabled = false
//            introViewPager.setOnClickListener {
//                Log.e("TAG", "introViewPager")
//            }

//            val consentIntent = arguments?.parcelable<Item>("key")
//            Log.e("TAG", "consentIntent222 "+consentIntent.toString())

            consentIntent = arguments?.parcelable<Item>("key")


            var arr1 = ArrayList<String>()
            arr1.add("A")
            arr1.add("B")
            arr1.add("C")
            arr1.add("D")
            arr1.add("E")
            arr1.add("F")
            arr1.add("G")
            arr1.add("H")

            var arr2 = ArrayList<String>()
            arr2.add("1")
            arr2.add("2")
            arr2.add("3")
            arr2.add("4")

            var arr3 = ArrayList<String>()

            var length = (arr1.size + arr2.size) - 1


            var counter = -1
            var counter2 = 0
            for (i in 0 .. length){
                if (i % 3 == 0){
                    if(counter == 0){
//                        Log.e("TAG", "AAAAAAAAAA "+counter)
                        arr3.add(arr2[counter])
                    } else {
                        if(counter != -1){
//                            Log.e("TAG", "BBBBBBBBBB "+counter)
                            arr3.add(arr2[counter])
                        }
                    }
                    counter = counter + 1
                } else {
//                    Log.e("TAG", "CCCCCCCC "+counter2)
                    arr3.add(arr1[counter2])
                    counter2 = counter2 + 1
                }
            }
            arr3.add(arr2[counter])
//            Log.e("TAG", "DDDDDDDD "+counter)

            arr3.forEach {
                Log.e("TAG", "ZZZZZZZZZZZZ "+it)
            }

        }
    }

    override fun onCallBack(pos: Int) {
        binding.introViewPager.setCurrentItem(pos, true)
//        when(pos){
//            0 -> binding.introViewPager.setCurrentItem(pos, false)
//            1 -> binding.introViewPager.setCurrentItem(pos, false)
//        }
    }

    override fun onCallBackHideShow() {
    }
}