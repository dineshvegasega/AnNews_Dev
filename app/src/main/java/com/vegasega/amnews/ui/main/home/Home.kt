package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.streetsaarthi.nasvi.screens.interfaces.CallBackListener
import com.vegasega.amnews.databinding.HomeBinding
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.utils.parcelable
import com.vegasega.amnews.utils.reduceDragSensitivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

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
    @SuppressLint("NotifyDataSetChanged", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callBackListener = this
        binding.apply {
            val adapter = HomePagerAdapter(requireActivity())
            introViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
            introViewPager.offscreenPageLimit = 2

            introViewPager.adapter = adapter


            pullToRefresh.setOnRefreshListener {
                //   refreshData() // your code
                CenterHome.callBackListener!!.onCallBack(0)
                pullToRefresh.isRefreshing = false
            }


//            introViewPager.reduceDragSensitivity()


//            introViewPager.setOnTouchListener(View.OnTouchListener { v, event ->
//                Log.e("TAG", "setOnTouchListener")
//                var rawX = 0f
//                val mTouchSlop: Int = ViewConfiguration.get(
//                    requireContext()
//                ).getScaledTouchSlop()
//
//                when (event.actionMasked) {
//                    MotionEvent.ACTION_DOWN -> {
//                        v.parent.requestDisallowInterceptTouchEvent(true)
//                        rawX = event.rawX
//                    }
//
//                    MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
//                        v.parent.requestDisallowInterceptTouchEvent(false)
//                        rawX = 0f
//                    }
//
//                    MotionEvent.ACTION_MOVE -> if (abs((rawX - event.rawX).toDouble()) > mTouchSlop) v.parent.requestDisallowInterceptTouchEvent(
//                        true
//                    )
//                }
//
//                false
//            })


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