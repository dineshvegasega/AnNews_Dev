package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OVER_SCROLL_NEVER
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.CenterHomeBinding
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.ui.VerticlePagerAdapter
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.utils.parcelable
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CenterHome : Fragment(), OnItemClickListener {
    var isUp: Boolean = false
    private val viewModel: HomeVM by viewModels()
    private var _binding: CenterHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var textToSpeech: TextToSpeech

    lateinit var adapter: VerticlePagerAdapter


    companion object{
        @JvmStatic
        var isOpen: Boolean = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CenterHomeBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("NotifyDataSetChanged", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            layoutTopic.setOnClickListener {
                Home.callBackListener!!.onCallBack(0)
            }

            layoutArticle.setOnClickListener {
                Home.callBackListener!!.onCallBack(2)
            }

        }


        textToSpeech = TextToSpeech(
            requireContext(), { status ->
                if (status != TextToSpeech.ERROR) {
                    Log.i("XXX", "Google tts initialized")
                    if (arguments != null){
                        val consentIntent = arguments?.parcelable<Item>("key")
                        viewModel.itemMain.clear()
                        consentIntent?.let {
                            viewModel.itemMain.add(it)
                        }
                    }

                    adapter = VerticlePagerAdapter(requireContext(), viewModel.itemMain, textToSpeech)
                    createVerticalView()
                } else {
                    Log.i("XXX", "Internal Google engine init error.")
                }
            }, "com.google.android.tts"
        )

    }



    fun View.isUserInteractionEnabled(enabled: Boolean) {
        isEnabled = enabled
        if (this is ViewGroup && this.childCount > 0) {
            this.children.forEach {
                it.isUserInteractionEnabled(enabled)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.apply {
            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.shutdown()
    }


    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0f,  // fromXDelta
            0f,  // toXDelta
            view.height.toFloat(),  // fromYDelta
            0f
        ) // toYDelta
        animate.duration = 400
        animate.fillAfter = true
        view.startAnimation(animate)
        binding.layoutTopic.setClickable(true)
        binding.layoutArticle.setClickable(true)
    }

    fun slideDown(view: View) {
        val animate = TranslateAnimation(
            0f,
            0f,
            0f,
            view.height.toFloat()
        ) // toYDelta
        animate.duration = 400
        animate.fillAfter = true
        view.startAnimation(animate)
        binding.layoutTopic.setClickable(false)
        binding.layoutArticle.setClickable(false)
    }

    fun slideUp2(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0f,
            0f,
            -view.height.toFloat(),
            0f
        ) // toYDelta
        animate.duration = 400
        animate.fillAfter = true
        view.startAnimation(animate)
    }



    fun slideDown2(view: View) {
        val animate = TranslateAnimation(
            0f,  // fromXDelta
            0f,  // toXDelta
            0f,  // fromYDelta
            -view.height.toFloat()
        ) // toYDelta
        animate.duration = 400
        animate.fillAfter = true
        view.startAnimation(animate)
    }



    //    var topListCounter = 0
    override fun onClickMain() {
        Log.e("TAG", "onClickMain")
        binding.apply {
            if (isUp) {
                slideDown(baseShare);
                slideDown2(recyclerView);
            } else {
                slideUp(baseShare);
                slideUp2(recyclerView);
            }
            isUp = !isUp;
        }
    }



    override fun onClickItem(position: Int) {
        binding.apply {
            introViewPager.setCurrentItem(position, true)
        }
    }

    override fun onClickItemUp(position: Int) {
        adapter.updatePosition(position)
        binding.introViewPager.adapter = adapter
    }




    @SuppressLint("NotifyDataSetChanged")
    private fun createVerticalView() {
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = viewModel.dashboardAdapter
            viewModel.dashboardAdapter.submitList(viewModel.itemMenusArray)
            viewModel.dashboardAdapter.notifyDataSetChanged()


            introViewPager.adapter = adapter
//
//            introViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
//            introViewPager.adapter = adapter
//            with(introViewPager) {
//                clipToPadding = true
//                clipChildren = true
//                offscreenPageLimit = 2
//            }
//            introViewPager.setPageTransformer(SwipeTransformer())
//            introViewPager.setOverScrollMode(OVER_SCROLL_NEVER);
//
//            introViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//                override fun onPageScrolled(
//                    position: Int,
//                    positionOffset: Float,
//                    positionOffsetPixels: Int
//                ) {
//                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
////                    Log.e("TAG", "positionA" + position)
//                }
//
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
//                    adapter.updatePosition(position)
//                }
//
//                override fun onPageScrollStateChanged(state: Int) {
//                    super.onPageScrollStateChanged(state)
//                }
//            })
        }

    }

    inner class SwipeTransformer : ViewPager2.PageTransformer {
//        private val screenHeight = resources.displayMetrics.heightPixels
//        private val pageMarginPy = resources.getDimensionPixelOffset(R.dimen.pageMargin)
//        private val offsetPy = resources.getDimensionPixelOffset(R.dimen.offset)
//        private val pageHeight = screenHeight - pageMarginPy - offsetPy
//
//        private val MIN_SCALE = 0.75f
//        private val MIN_ALPHA = 0.75f

        private val MIN_SCALE = 0.75f


//        val MIN_SCALE = 0.75f

//        override fun transformPage(page: View, position: Float) {
//            val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
//            page.apply {
//                if (position < -1) { // [-Infinity,-1)
//                    scaleX = scaleFactor
//                    scaleY = scaleFactor
//                    alpha = 0f
//                } else if (position <= 0) { // [-1,0]
//                    translationY = pageHeight * -position
//
//                    scaleX = scaleFactor
//                    scaleY = scaleFactor
//                    alpha =
//                        (MIN_ALPHA + (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
//                } else if (position <= 1) { // (0,1]
//                    alpha = 1f
//                    scaleX = 1f
//                    scaleY = scaleFactor
//
//                    val viewPager = page.parent.parent as ViewPager2
//                    val offset = position * -(2 * offsetPy + pageMarginPy)
//
//                    if (viewPager.orientation == ViewPager2.ORIENTATION_VERTICAL) {
//                        page.translationY = offset
//                    } else {
//                        page.translationX = offset
//                    }
//
//                } else { // (1,+Infinity]
//                    alpha = 0f
//                    scaleX = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
//                    scaleY = 1f
//                }
//            }
//
//
////
////            page.apply {
////                if (position < -1){
////                    page.setAlpha(0f);
////                }
////                else if (position <= 0){
////                    page.setAlpha(1f)
////                    page.translationX = page.width * -position
////                    val yPosition = position * page.height
////                    page.translationY = yPosition
////                    page.setScaleX(1f)
////                    page.setScaleY(1f)
////                }
////                else if (position <= 1){
////                    page.setTranslationX(page.getWidth() * -position);
////                    val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
////                    page.setScaleX(scaleFactor);
////                    page.setScaleY(scaleFactor);
////                }
////                else {
////                    page.setAlpha(0f);
////                }
////
////            }
//
//        }



        override fun transformPage(page: View, position: Float) {
//            val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
            page.apply {
                if (position < -1) {
                    alpha = 0f
                } else if (position <= 0) {
//                    translationY = pageHeight * -position
//
//                    scaleX = scaleFactor
//                    scaleY = scaleFactor
//                    alpha =
//                        (MIN_ALPHA + (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    alpha = 1f
                    page.setTranslationX(page.getWidth() * -position);
                    val yPosition = position * page!!.height
                    page!!.translationY = yPosition
                    page!!.setScaleX(1f)
                    page!!.setScaleY(1f)
                } else if (position <= 1) {
//                    alpha = 1f
//                    scaleX = 1f
//                    scaleY = scaleFactor
//
//                    val viewPager = page.parent.parent as ViewPager2
//                    val offset = position * -(2 * offsetPy + pageMarginPy)
//
//                    if (viewPager.orientation == ViewPager2.ORIENTATION_VERTICAL) {
//                        page.translationY = offset
//                    } else {
//                        page.translationX = offset
//                    }

                    alpha = 1f
                    page!!.translationX = page!!.width * -position

                    val scaleFactor = (MIN_SCALE
                            + (1 - MIN_SCALE) * (1 - Math.abs(position)))
                    page!!.scaleX = scaleFactor
                    page!!.scaleY = scaleFactor
                } else {
                    alpha = 0f

//                    alpha = 0f
//                    scaleX = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
//                    scaleY = 1f
                }
            }


//
//            page.apply {
//                if (position < -1){
//                    page.setAlpha(0f);
//                }
//                else if (position <= 0){
//                    page.setAlpha(1f)
//                    page.translationX = page.width * -position
//                    val yPosition = position * page.height
//                    page.translationY = yPosition
//                    page.setScaleX(1f)
//                    page.setScaleY(1f)
//                }
//                else if (position <= 1){
//                    page.setTranslationX(page.getWidth() * -position);
//                    val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
//                    page.setScaleX(scaleFactor);
//                    page.setScaleY(scaleFactor);
//                }
//                else {
//                    page.setAlpha(0f);
//                }
//
//            }

        }
    }

    //mock-up data
    private fun getCardViewList(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.m1,
            R.drawable.m1,
            R.drawable.m1,
            R.drawable.m1
        )
    }

}


