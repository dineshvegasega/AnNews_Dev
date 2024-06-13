package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.streetsaarthi.nasvi.screens.interfaces.CallBackListener
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.CenterHomeBinding
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.ui.mainActivity.MainActivity
import com.vegasega.amnews.utils.autoScroll
import com.vegasega.amnews.utils.getRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import java.io.IOException
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.utils.PaginationScrollListener
import kotlinx.coroutines.delay


@AndroidEntryPoint
class CenterHome : Fragment(), OnItemClickListener, CallBackListener {
    var isUp: Boolean = false
    private val viewModel: HomeVM by viewModels()

    lateinit var textToSpeech: TextToSpeech
    var itemMainFinal: MutableList<Item> = ArrayList()

    companion object {
        @JvmStatic
        var isOpen: Boolean = false

        @SuppressLint("StaticFieldLeak")
        private var _binding: CenterHomeBinding? = null

        val binding get() = _binding!!

        @JvmStatic
        lateinit var adapter: LiveNoticesAdapter
//
//        @JvmStatic
//        lateinit var adapter : QuickRegistrationAdapter

        var callBackListener: CallBackListener? = null

        lateinit var mp: MediaPlayer

    }

    private var LOADER_TIME: Long = 500
    private var pageStart: Int = 1
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private var totalPages: Int = 1
    private var currentPage: Int = pageStart

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
        callBackListener = this
        mp = MediaPlayer.create(MainActivity.context.get(), R.raw.sound_2)
//        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        mp.setAudioAttributes(
//            AudioAttributes.Builder()
//                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                .build()
//        )

        binding.apply {

//            viewVertical = introViewPager

            layoutTopic.setOnClickListener {
                Home.callBackListener!!.onCallBack(0)
            }

            layoutArticle.setOnClickListener {
                Home.callBackListener!!.onCallBack(2)
            }

        }


//        var counter3 = 0
//        for (i in 0..(viewModel.itemMainTopics.size - 1)) {
//            if (viewModel.itemMainTopics.size != viewModel.itemMainAds.size) {
//                if (counter3 == viewModel.itemMainAds.size - 1) {
//                    viewModel.itemMainAds.add(viewModel.itemMainAds[counter3])
//                    counter3 = 0
//                } else {
//                    viewModel.itemMainAds.add(viewModel.itemMainAds[counter3])
//                    counter3++
//                }
//            }
//        }
//
//
//
//
////        val dd = 4
//        var rnds = (5..10).random()
//        var a1 = viewModel.itemMainTopics.size / rnds
//        var a2 = viewModel.itemMainTopics.size % rnds
//
//        try {
//            var counter = -1
//            var counter2 = 0
//            for (i in 0..(viewModel.itemMainTopics.size + 1) + a1 + a2) {
//                rnds = (5..10).random()
//                Log.e("TAG", "rnds " + rnds)
//                a1 = viewModel.itemMainTopics.size / rnds
//                a2 = viewModel.itemMainTopics.size % rnds
//                if (i % rnds == 0) {
//                    if (counter == 0) {
//                        // println("AAAAAAAAAA "+arr2[counter])
//                        viewModel.itemMainFinal.add(viewModel.itemMainAds[counter])
//                    } else {
//                        if (counter != -1) {
//                            // println("BBBBBBBBBB "+arr2[counter])
//                            viewModel.itemMainFinal.add(viewModel.itemMainAds[counter])
//                        }
//                    }
//                    counter = counter + 1
//                } else {
//                    //println(" arr22222 "+arr1[counter2])
//                    viewModel.itemMainFinal.add(viewModel.itemMainTopics[counter2])
//                    counter2 = counter2 + 1
//                }
//            }
//        } catch (_: Exception) {
//
//        }


//        viewModel.itemMainTopics.forEach {
//            Log.e("TAG", "itemMainFinal " + it.name)
//            viewModel.itemMainFinal.add(it)
//        }

//
//        mainThread {
////            viewModel.show()
////            binding.loadingProgressBar.visibility = View.GONE
//        }

//        binding.loadingProgressBar.visibility = View.VISIBLE

        textToSpeech = TextToSpeech(
            requireContext(), { status ->
                if (status != TextToSpeech.ERROR) {
                    Log.e("XXX", "Google tts initialized")
//                    textToSpeech.setLanguage(Locale("hi","IN"))
//                    textToSpeech.setLanguage(Locale("en","US"))
                    textToSpeech.setSpeechRate(0.7f)
//                    val a: HashSet<String> = HashSet<String>()
//                    a.add("male")
//                    val v = Voice("en-us-x-sfg#male_1-local", Locale("en", "US"), 400, 200, true, a)
//                    textToSpeech.setVoice(v)

                    adapter = LiveNoticesAdapter(this, textToSpeech)
                    if (Home.consentIntent != null) {
                        viewModel.itemMainFinal.clear()
                        Log.e("TAG", "consentIntent " + Home.consentIntent.toString())
                        Home.consentIntent?.let {
                            viewModel.itemMainFinal.add(it)
                        }
                        adapter.submitData(viewModel.itemMainFinal)
                    } else {
                        Log.e("TAG", "consentIntentNULL ")
                        adapter.submitData(viewModel.itemMainFinal)
                    }

                    loadFirstPage()
                    createVerticalView()
                } else {
                    Log.e("XXX", "Internal Google engine init error.")
                }
            }, "com.google.android.tts"
        )
//        val a: HashSet<String> = HashSet<String>()
//        a.add("male")
//        val v = Voice("en-us-x-sfg#male_1-local", Locale("en", "US"), 400, 200, true, a)
//        textToSpeech.setVoice(v)
//        textToSpeech.setLanguage(Locale("hin", "IND", "variant"))
//        val locale = Locale("hi","IN")
//        textToSpeech.setLanguage(locale)
//        textToSpeech.setSpeechRate(0.7f)


        observerDataRequest()

        recyclerViewScroll()

    }


    //    var topListCounter = 0
    override fun onClickMain() {
        Log.e("TAG", "onClickMain")
        binding.apply {
            if (isUp) {
                slideDown(baseShare);
                slideDown2(recyclerView);
                introViewPager.isUserInputEnabled = true
            } else {
                slideUp(baseShare);
                slideUp2(recyclerView);
                introViewPager.isUserInputEnabled = false
            }
            isUp = !isUp;
        }
    }


    override fun onClickItem(position: Int) {
        binding.apply {
//            try {
//                if (mp.isPlaying) {
//                    mp.stop()
//                    mp.prepare();
//                }
//
//                mp.start()
//            } catch (_: IOException) {
//            }
//            mainThread {
//                delay(500)
//            }
//            Thread.sleep(500)
            introViewPager.setCurrentItem(position, true)
        }
    }

    override fun onClickItemUp(position: Int) {
        Log.e("TAG", "onClickItemUp11")
//        viewModel.show()
//        binding.introViewPager.offscreenPageLimit = viewModel.itemMainFinal.size
//        binding.introViewPager.offscreenPageLimit = 1


//        requireActivity().runOnUiThread {
////            viewModel.show()
//            binding.loadingProgressBar.visibility = View.VISIBLE
//        }

//        defaultThread {
//            binding.loadingProgressBar.visibility = View.VISIBLE
//        }

        binding.introViewPager.adapter = adapter
        adapter.updatePosition(position)
        Log.e("TAG", "onClickItemUp22")
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            viewModel.hide()
//        }, 200)

//        binding.introViewPager.offscreenPageLimit = viewModel.itemMainFinal.size
    }


    @SuppressLint("NotifyDataSetChanged", "ClickableViewAccessibility")
    private fun createVerticalView() {
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = viewModel.dashboardAdapter
            viewModel.dashboardAdapter.submitList(viewModel.itemMenusArray)
            viewModel.dashboardAdapter.notifyDataSetChanged()


            (introViewPager.getRecyclerView()
                .getItemAnimator() as SimpleItemAnimator).supportsChangeAnimations =
                false


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
//                offscreenPageLimit = viewModel.itemMain.size
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


//            introViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
//            introViewPager.getRecyclerView().setReverseLayout(boolean)
//            introViewPager.getRecyclerView()
//            binding.introViewPager.addItemDecoration(ReverseItemDecoration())
//            introViewPager.getRecyclerView().setOnTouchListener { view, motionEvent ->
//                false
//            }

            introViewPager.offscreenPageLimit = 1
            introViewPager.overScrollMode = OVER_SCROLL_NEVER
            introViewPager.adapter = adapter

//            val animator: DefaultItemAnimator = object : DefaultItemAnimator() {
//                override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean {
//                    return true
//                }
//            }
//            introViewPager.getRecyclerView().setItemAnimator(animator)

//            introViewPager.invalidate()

//            introViewPager.setOnTouchListener(OnTouchListener { v, event ->
//                pullToRefresh.setEnabled(false)
//                when (event.action) {
//                    MotionEvent.ACTION_UP -> pullToRefresh.setEnabled(true)
//                }
//                false
//            })


//            introViewPager.setOnTouchListener(OnTouchListener { v, event ->
//                when (event.action) {
//                    MotionEvent.ACTION_MOVE -> pullToRefresh.setEnabled(false)
//                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> pullToRefresh.setEnabled(
//                        true
//                    )
//                }
//                false
//            })

//            viewModel.hide()

//            with(introViewPager) {
//                clipToPadding = true
//                clipChildren = true
////                offscreenPageLimit = 2
//
//            }


//            val layoutManager = LinearLayoutManager(requireContext())
////            layoutManager.reverseLayout = true
//            layoutManager.stackFromEnd = true
//            introViewPager.getRecyclerView().setHasFixedSize(true)
//            introViewPager.getRecyclerView().layoutManager = layoutManager

//            pager.setOnPageChangeListener(object : OnPageChangeListener {
//                override fun onPageScrolled(
//                    position: Int,
//                    positionOffset: Float,
//                    positionOffsetPixels: Int
//                ) {
//                }
//
//                override fun onPageSelected(position: Int) {
//                    pager.postDelayed(Runnable {
//                        pagerAdapter.getItem(position).getView().bringToFront()
//                    }, 300)
//                }
//
//                override fun onPageScrollStateChanged(state: Int) {}
//            })


//            introViewPager.setPageTransformer(DepthPageTransformer())
//            introViewPager.isUserInputEnabled = false
//            introViewPager.overScrollMode = OVER_SCROLL_NEVER
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

            var pageChangeValue = -0
            introViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    if (pageChangeValue != position) {
                        Log.e("TAG", "positionA" + position)
//                        adapter.updatePosition(position)
//                        adapter.notifyItemChanged(position)
//                        onClickItem(pageChangeValue)
                    }
//                    try {
//                        if (mp.isPlaying) {
//                            mp.stop()
//                            mp.prepare();
//                        }
//                        mp.start()
//                    } catch (_: IOException) {
//                    }
                    pageChangeValue = position
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    adapter.updatePosition(position)
//                    introViewPager.setCurrentItem(position, true)
//                    lifecycleScope.launch {
//                        delay(500)
//                    }
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    Log.e("TAG", "state" + state)
                    if (state == 0) {
                        onClickItem(pageChangeValue)
                        try {
                            if (mp.isPlaying) {
                                mp.stop()
                                mp.prepare();
                            }

                            mp.start()
                        } catch (_: IOException) {
                        }
                        adapter.notifyItemChanged(adapter.counter)
                    }
//                    viewModel.hide()
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

    override fun onCallBack(pos: Int) {
//        binding.introViewPager.adapter = adapter
//        adapter.updatePosition(pos)
        binding.introViewPager.autoScroll(binding.introViewPager.currentItem)
        itemMainFinal.clear()

        loadFirstPage()

    }

    override fun onCallBackHideShow() {
    }


    private fun loadFirstPage() {
        pageStart = 1
        isLoading = false
        isLastPage = false
        totalPages = 1
        currentPage = pageStart
        itemMainFinal.clear()
        val obj: JSONObject = JSONObject().apply {
            put("page", currentPage)
            put("user_id", "1044")
        }
        viewModel.liveNotice(obj)
    }

    fun loadNextPage() {
        val obj: JSONObject = JSONObject().apply {
            put("page", currentPage)
            put("user_id", "1044")
        }
        viewModel.liveNoticeSecond(obj)
    }


    private fun recyclerViewScroll() {
        binding.apply {
            introViewPager.getRecyclerView().addOnScrollListener(object :
                PaginationScrollListener(introViewPager.getRecyclerView().layoutManager as LinearLayoutManager) {
                override fun loadMoreItems() {
                    isLoading = true
                    currentPage += 1
                    if (totalPages >= currentPage) {
                        Handler(Looper.myLooper()!!).postDelayed({
                            loadNextPage()
                        }, LOADER_TIME)
                    }
                }

                override fun getTotalPageCount(): Int {
                    return totalPages
                }

                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }
            })
        }
    }

    var count2 = 0

    @SuppressLint("NotifyDataSetChanged")
    private fun observerDataRequest() {
        viewModel.itemLiveNotice.observe(viewLifecycleOwner, Observer {
            val typeToken = object : TypeToken<List<Item>>() {}.type
            val changeValue =
                Gson().fromJson<List<Item>>(Gson().toJson(it.data), typeToken)


            var counter3 = 0
            for (i in 0..(changeValue.size - 1)) {
                if (changeValue.size != viewModel.itemMainAds.size) {
                    if (counter3 == viewModel.itemMainAds.size - 1) {
                        viewModel.itemMainAds.add(viewModel.itemMainAds[counter3])
                        counter3 = 0
                    } else {
                        viewModel.itemMainAds.add(viewModel.itemMainAds[counter3])
                        counter3++
                    }
                }
            }


            var count1 = 0

            var rand11 = (3..5).random()
            var rand22 = 0
            var rand13 = 0
            for (i in 0..(changeValue.size - 1) + 1) {
                if (rand11 == i) {
//                Log.e("TAG", "Add1 " + count2)
                    itemMainFinal.add(viewModel.itemMainAds[count2])
                    rand22 = rand11 + 3
//                Log.e("TAG", "rand22 " + rand22)
                    rand13 = (rand22..10).random()
//                Log.e("TAG", "rand13 " + rand13)
                    count2++
                } else {
//                Log.e("TAG", "itemMainTopics11 " + itemMainTopics[count1])
//                    Log.e("TAG", "itemMainTopics11 " + count1)
                    itemMainFinal.add(changeValue[count1])
                    count1++
                }


                if (rand13 == i && rand13 != 0) {
//                Log.e("TAG", "Add2 " + count2)
                    itemMainFinal.add(viewModel.itemMainAds[count2])
                    count2++
                }
            }


//            val itemStateTemp = changeValue
//            itemMainFinal.addAll(itemStateTemp)
            adapter.addAllSearch(itemMainFinal)
            totalPages = it.meta?.total_pages!!
            if (currentPage == totalPages) {
                adapter.removeLoadingFooter()
            } else if (currentPage <= totalPages) {
                adapter.addLoadingFooter()
                isLastPage = false
            } else {
                isLastPage = true
            }
        })


        viewModel.itemLiveNoticeSecond.observe(viewLifecycleOwner, Observer {
            val typeToken = object : TypeToken<List<Item>>() {}.type
            val changeValue =
                Gson().fromJson<List<Item>>(Gson().toJson(it.data), typeToken)


            var counter3 = 0
            for (i in 0..(changeValue.size - 1)) {
                if (changeValue.size != viewModel.itemMainAds.size) {
                    if (counter3 == viewModel.itemMainAds.size - 1) {
                        viewModel.itemMainAds.add(viewModel.itemMainAds[counter3])
                        counter3 = 0
                    } else {
                        viewModel.itemMainAds.add(viewModel.itemMainAds[counter3])
                        counter3++
                    }
                }
            }


            var count1 = 0

            var rand11 = (3..5).random()
            var rand22 = 0
            var rand13 = 0
            for (i in 0..(changeValue.size - 1) + 1) {
                if (rand11 == i) {
//                Log.e("TAG", "Add1 " + count2)
                    itemMainFinal.add(viewModel.itemMainAds[count2])
                    rand22 = rand11 + 3
//                Log.e("TAG", "rand22 " + rand22)
                    rand13 = (rand22..10).random()
//                Log.e("TAG", "rand13 " + rand13)
                    count2++
                } else {
//                Log.e("TAG", "itemMainTopics11 " + itemMainTopics[count1])
//                    Log.e("TAG", "itemMainTopics11 " + count1)
                    itemMainFinal.add(changeValue[count1])
                    count1++
                }


                if (rand13 == i && rand13 != 0) {
//                Log.e("TAG", "Add2 " + count2)
                    itemMainFinal.add(viewModel.itemMainAds[count2])
                    count2++
                }
            }



//            val itemStateTemp = changeValue
//            itemMainFinal.addAll(itemStateTemp)
            adapter.addAllSearch(itemMainFinal)
            adapter.removeLoadingFooter()
            isLoading = false
            adapter.addAllSearch(itemMainFinal)
            if (currentPage != totalPages) adapter.addLoadingFooter()
            else isLastPage = true
        })


    }


}


