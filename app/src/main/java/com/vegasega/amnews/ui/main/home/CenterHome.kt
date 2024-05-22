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
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.CenterHomeBinding
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.ui.VerticlePagerAdapter
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.utils.getRecyclerView
import com.vegasega.amnews.utils.parcelable
import com.vegasega.amnews.utils.setCurrentItem
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class CenterHome : Fragment(), OnItemClickListener {
    var isUp: Boolean = false
    private val viewModel: HomeVM by viewModels()


    lateinit var textToSpeech: TextToSpeech


    companion object {
        @JvmStatic
        var isOpen: Boolean = false

        @SuppressLint("StaticFieldLeak")
        private var _binding: CenterHomeBinding? = null

        val binding get() = _binding!!

        @JvmStatic
        lateinit var adapter: VerticalViewPagerAdapter
//
//        @JvmStatic
//        lateinit var adapter : QuickRegistrationAdapter

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

//            viewVertical = introViewPager

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
                    Log.e("XXX", "Google tts initialized")

                    adapter = VerticalViewPagerAdapter(this, textToSpeech)

//                    Home.consentIntent
//                    Log.e("TAG", "consentIntent "+Home.consentIntent)

                    if (Home.consentIntent != null) {
                        viewModel.itemMain.clear()
                        Log.e("TAG", "consentIntent "+Home.consentIntent.toString())
                        Home.consentIntent?.let {
                            viewModel.itemMain.add(it)
                        }
                        adapter.submitData(viewModel.itemMain)
                    } else {
                        Log.e("TAG", "consentIntentNULL ")
                        adapter.submitData(viewModel.itemMain)
                    }

                    createVerticalView()
                } else {
                    Log.e("XXX", "Internal Google engine init error.")
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


    @SuppressLint("NotifyDataSetChanged", "ClickableViewAccessibility")
    private fun createVerticalView() {
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = viewModel.dashboardAdapter
            viewModel.dashboardAdapter.submitList(viewModel.itemMenusArray)
            viewModel.dashboardAdapter.notifyDataSetChanged()

//            introViewPager.adapter = adapter

//            var arraylist = ArrayList<String>()
//            //adding String elements in the list
//            arraylist.add("Geeks")
//            arraylist.add("Geeks")
//            introViewPager.adapter  = adapter


           // introViewPager.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


//            with(introViewPager) {
//                clipToPadding = true
//                clipChildren = true
//                offscreenPageLimit = 0
//            }




//            introViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//                override fun onPageScrollStateChanged(state: Int) {
//                    Log.e("TAG", "addOnPageChangeListener " + state)
//                }
//
//                override fun onPageScrolled(
//                    position: Int,
//                    positionOffset: Float,
//                    positionOffsetPixels: Int
//                ) {
////                    Log.e("TAG", "onPageScrolled " + position)
//                }
//
//                override fun onPageSelected(position: Int) {
//                    introViewPager.setCurrentItem(position)
//                    adapter.updatePosition(position)
//                    introViewPager.invalidate()
////                    adapter.notifyDataSetChanged()
////                    introViewPager.invalidate()
//                    Log.e("TAG", "onPageSelected " + position)
////                    viewPagerHandler()
//                }
//            })


            introViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
//            introViewPager.getRecyclerView().setOnTouchListener { view, motionEvent ->
//                false
//            }
            introViewPager.adapter = adapter
//            with(introViewPager) {
//                clipToPadding = true
//                clipChildren = true
////                offscreenPageLimit = 2
//
//            }

            introViewPager.setPageTransformer(SwipeTransformer())
//            introViewPager.isUserInputEnabled = false
            introViewPager.overScrollMode = OVER_SCROLL_NEVER
//            PagerSnapHelper().attachToRecyclerView(introViewPager.getRecyclerView())


            //val recyclerView = introViewPager.getChildAt(0)

//            introViewPager.getRecyclerView().setChildDrawingOrderCallback  { childCount, i ->
//
//                when (i) {
//                    0 -> 3
//                    1 -> 4
//                    2 -> 3
//                    3 -> 2
//                    4 -> 1
////                    5 -> 0
//                    else -> throw IllegalStateException("Expected 3 items")
//                }
//            }

            introViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//                    Log.e("TAG", "positionA" + position)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    adapter.updatePosition(position)
                    introViewPager.setCurrentItem(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })


        }

    }

    fun viewPagerHandler(view: View, position: Int): View {
        //ALL LOGIC BUTTONS ACTIONS ETC.
        view.invalidate()
        return view
    }


    inner class SwipeTransformer : ViewPager2.PageTransformer {
        private val screenHeight = resources.displayMetrics.heightPixels
        private val pageMarginPy = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        private val offsetPy = resources.getDimensionPixelOffset(R.dimen.offset)
        private val pageHeight = screenHeight - pageMarginPy - offsetPy

        private val MIN_SCALE = 0.5f
        private val MIN_ALPHA = 0.5f


//        val MIN_SCALE = 0.90f

        override fun transformPage(page: View, position: Float) {
            val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
            page.apply {
                if (position < -1) { // [-Infinity,-1)
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    alpha = 0f
                } else if (position <= 0) { // [-1,0]
                    translationY = pageHeight * -position

                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    alpha =
                        (MIN_ALPHA + (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                } else if (position <= 1) { // (0,1]
                    alpha = 1f
                    scaleX = 1f
                    scaleY = scaleFactor

                    val viewPager = page.parent.parent as ViewPager2
                    val offset = position * -(2 * offsetPy + pageMarginPy)

                    if (viewPager.orientation == ViewPager2.ORIENTATION_VERTICAL) {
                        page.translationY = offset
                    } else {
                        page.translationX = offset
                    }

                } else { // (1,+Infinity]
                    alpha = 0f
                    scaleX = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
                    scaleY = 1f
                }
            }

        }





    }



//    class SliderTransformer(private val offscreenPageLimit: Int) : ViewPager2.PageTransformer {
//
//        companion object {
//
//            private const val DEFAULT_TRANSLATION_X = .0f
//            private const val DEFAULT_TRANSLATION_FACTOR = 1.2f
//
//            private const val SCALE_FACTOR = .14f
//            private const val DEFAULT_SCALE = 1f
//
//            private const val ALPHA_FACTOR = .3f
//            private const val DEFAULT_ALPHA = 1f
//
//        }
//
//        override fun transformPage(page: View, position: Float) {
//
//            page.apply {
//
//                ViewCompat.setElevation(page, -abs(position))
//
//                val scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE
//                val alphaFactor = -ALPHA_FACTOR * position + DEFAULT_ALPHA
//
//                when {
//                    position <= 0f -> {
//                        translationX = DEFAULT_TRANSLATION_X
//                        scaleX = DEFAULT_SCALE
//                        scaleY = DEFAULT_SCALE
//                        alpha = DEFAULT_ALPHA + position
//                    }
//                    position <= offscreenPageLimit - 1 -> {
////                        scaleX = scaleFactor
////                        scaleY = scaleFactor
////                        translationX = -(width / DEFAULT_TRANSLATION_FACTOR) * position
////                        alpha = alphaFactor
//                    }
//                    else -> {
//                        translationX = DEFAULT_TRANSLATION_X
//                        scaleX = DEFAULT_SCALE
//                        scaleY = DEFAULT_SCALE
//                        alpha = DEFAULT_ALPHA
//                    }
//                }
//            }
//        }
//    }

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






