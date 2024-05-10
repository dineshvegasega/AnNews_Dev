package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.HomeBinding
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.utils.parcelable
import com.vegasega.amnews.utils.updatePagerHeightForChild
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Home : Fragment(), OnItemClickListener {
    var isUp: Boolean = false
    private val viewModel: HomeVM by viewModels()
    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!

    lateinit var textToSpeech: TextToSpeech
//    var counter = 0
//    var counterChild = 0
//    var scrollTrue = false


    lateinit var adapter: VerticalViewPagerAdapter
//    val adapter by lazy { VerticalViewPagerAdapter }


    companion object{
        @JvmStatic
        var isOpen: Boolean = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeBinding.inflate(inflater)
        return binding.root
    }

    //    var sentence: String ="दिल्ली न्यूज़ राजनीति"
//    var sentence: String ="Vous devrez également"
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("NotifyDataSetChanged", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // var adapter = HomePagerAdapter(this)


        binding.apply {
//
//            ivUp.setOnClickListener {
//                adapter.updatePosition(0)
//                introViewPager.adapter = adapter
//            }
//
//            ivSearch.setOnClickListener {
//                findNavController().navigate(R.id.action_home_to_search)
//            }


//            val swipe: SwipeLayout = findViewById(R.id.swipe)
//            swipe.addDrag(SwipeLayout.DragEdge.Left, swipe.findViewById(R.id.layoutLeft))
//            swipe.addDrag(SwipeLayout.DragEdge.Right, swipe.findViewById(R.id.layoutRight))

//            swipe.setEnabled(false)


//            swipe.addSwipeListener(object : SwipeLayout.SwipeListener {
//                override fun onStartOpen(layout: SwipeLayout?) {
//                   Log.e("TAG", "onStartOpen")
//                }
//
//                override fun onOpen(layout: SwipeLayout?) {
//                    Log.e("TAG", "onOpen")
//                    isOpen = true
////                    binding.swipe.setSwipeEnabled(true);
//                }
//
//                override fun onStartClose(layout: SwipeLayout?) {
//                    Log.e("TAG", "onStartClose")
//                }
//
//                override fun onClose(layout: SwipeLayout?) {
//                    Log.e("TAG", "onClose")
//                    isOpen = false
////                    binding.swipe.open(false)
////                    binding.swipe.close(false)
////                    binding.swipe.setSwipeEnabled(false);
//                }
//
//                override fun onUpdate(layout: SwipeLayout?, leftOffset: Int, topOffset: Int) {
//                    Log.e("TAG", "onUpdate")
//                }
//
//                override fun onHandRelease(layout: SwipeLayout?, xvel: Float, yvel: Float) {
//                    Log.e("TAG", "onHandRelease")
//                }
//
//            })

            layoutTopic.setOnClickListener {
//                swipe.open(SwipeLayout.DragEdge.Left)
                MainHome.callBackListener!!.onCallBack(0)
            }

            layoutArticle.setOnClickListener {
//                swipe.open(SwipeLayout.DragEdge.Right)
                MainHome.callBackListener!!.onCallBack(2)
            }


        }


//        binding.apply {
//            ivUp.setOnClickListener {
////                MainActivity.activity.get()?.runOnUiThread {
////                    val url =
////                        "https://www.google.com/"
////                    url?.let {
////                        val webIntent = Intent(
////                            Intent.ACTION_VIEW,
////                            Uri.parse(url)
////                        )
////                        try {
////                            requireContext().startActivity(webIntent)
////                        } catch (_: ActivityNotFoundException) {
////                        }
////                    }
////                }
//            }
//
//
////            baseButtons.visibility = View.GONE
////            baseShare.visibility = View.GONE
////            baseButtonsPlay.visibility = View.VISIBLE
////            seekbar.visibility = View.VISIBLE
//
//            ivAudio.setOnClickListener {
//                baseButtons.visibility = View.GONE
//                baseShare.visibility = View.GONE
//                baseButtonsPlay.visibility = View.VISIBLE
//                seekbar.visibility = View.VISIBLE
//            }
//
//            ivSearch.setOnClickListener {
//                findNavController().navigate(R.id.action_home_to_search)
//            }
//
//            ivCross.setOnClickListener {
//                baseButtons.visibility = View.VISIBLE
//                baseShare.visibility = View.GONE
//                baseButtonsPlay.visibility = View.GONE
//                seekbar.visibility = View.GONE
//            }
//
//
//            linearTopics.setOnClickListener {
//                baseButtons.visibility = View.VISIBLE
//                baseShare.visibility = View.GONE
//                baseButtonsPlay.visibility = View.GONE
//                seekbar.visibility = View.GONE
//            }
//
//            linearArticles.setOnClickListener {
//                baseButtons.visibility = View.VISIBLE
//                baseShare.visibility = View.GONE
//                baseButtonsPlay.visibility = View.GONE
//                seekbar.visibility = View.GONE
//            }
//
//
//            textToSpeech = TextToSpeech(requireContext()) {status ->
//                if (status == TextToSpeech.SUCCESS){
//                    Log.e("TextToSpeech", "Initialization Success")
//                }else{
//                    Log.e("TextToSpeech", "Initialization Failed")
//                }
//            }
//////            textToSpeech.language = Locale("hi","IN")
//
////            textToSpeech = TextToSpeech(requireContext(), {status ->
////                Log.e("XXX", "Google tts initialized "+status)
////            }, "com.google.android.tts")
////            en-in-x-ene-network
////            val a = HashSet<String>();
////            a.add("male")
//
////            val v = Voice("hi-IN-Neural2-C", Locale.ITALY, 400, 200, false, HashSet<String>())
////            textToSpeech.setVoice(v)
////            val result: Int = textToSpeech.setVoice(v)
////            if (result == TextToSpeech.LANG_MISSING_DATA
////                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
////                Log.e("TTS", "This Language is not supported");
////            } else {
////                Log.e("TTS", "This Language");
////            }
////            textToSpeech.setGender(TextToSpeech.MALE)
//
////            for (tmpVoice in textToSpeech.getVoices()) {
////                Log.i("XXX", "Itts.getVoices() "+textToSpeech.getVoices())
////            }
//
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

                    adapter = VerticalViewPagerAdapter(this, viewModel.itemMain, textToSpeech)
                    createVerticalView()
                } else {
                    Log.i("XXX", "Internal Google engine init error.")
                }
            }, "com.google.android.tts"
        )
//
//
//
//            Log.e("TAG", "ivPlaytmpVoice "+ textToSpeech.getVoices())
//
////            for (tmpVoice in textToSpeech.getVoices()) {
////                Log.e("TAG", "ivPlaytmpVoice "+ tmpVoice.name)
////                //if (tmpVoice.name == _voiceName) {
//////                    return tmpVoice
//////                    break
////               // }
////            }
//
//
//            ivPlay.setOnClickListener {
//                ivPlay.visibility = View.GONE
//                ivPause.visibility = View.VISIBLE
//                scrollTrue = true
//                playSong(viewModel.itemMain[counter])
//                Log.i("TAG", "ivPlay "+ counter)
//            }
//
//
//
//
//            ivPause.setOnClickListener {
//                ivPlay.visibility = View.VISIBLE
//                ivPause.visibility = View.GONE
//                scrollTrue = false
//                counterChild = 0
//                if (textToSpeech.isSpeaking) {
//                    textToSpeech.stop()
//                }
//            }
//
//
//            ivPlayback.setOnClickListener {
////                ivPlay.visibility = View.VISIBLE
////                ivPause.visibility = View.GONE
//                counterChild = 0
//                if(counter != 0){
//                    counter = counter - 1
//                }
//                introViewPager.setCurrentItem(counter, true)
//            }
//
//            ivPlaynext.setOnClickListener {
////                ivPlay.visibility = View.VISIBLE
////                ivPause.visibility = View.GONE
//                counterChild = 0
//                counter = counter + 1
//                introViewPager.setCurrentItem(counter, true)
//            }
//
////                    seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
////                        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//////                            binding.tvTrickleRateValue.text = progress.toString() + " " + getString(R.string.ls)
//////                            binding.tvTrickleIndicator.text = progress.toString()
//////                            val bounds: Rect = binding.seekbar.thumb.bounds
//////                            binding.tvTrickleIndicator.x = (binding.seekbarTrickle.left + bounds.left + marginStart).toFloat()
////                        }
////
////                        override fun onStartTrackingTouch(seekBar: SeekBar?) { }
////
////                        override fun onStopTrackingTouch(seekBar: SeekBar?) { }
////
////                    })
//
//
//            recyclerView.setHasFixedSize(true)
//            recyclerView.adapter = viewModel.dashboardAdapter
//            viewModel.dashboardAdapter.submitList(viewModel.itemMain)
//            viewModel.dashboardAdapter.notifyDataSetChanged()
//
//
//            introViewPager.adapter = adapter
//            if (arguments != null){
//                val positionKey = arguments?.getInt("pos", 0)
//                counter = positionKey ?: 0
//                Log.e("TAG", "positionKey "+counter)
//
//                val consentIntent = arguments?.parcelable<ItemMain>("key")
//                consentIntent?.let {
//                    viewModel.itemMain = it.data
//                }
//                adapter.submitList(viewModel.itemMain)
//                adapter.notifyDataSetChanged()
//
//                Handler(Looper.myLooper()!!).postDelayed({
//                    introViewPager.setCurrentItem(counter, true)
//                }, 300)
//            } else {
//                adapter.submitList(viewModel.itemMain)
//                adapter.notifyDataSetChanged()
//            }
//
//
//
//
//
//
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
//
//                    if (arguments != null) {
//                        val positionKey = arguments?.getInt("pos", 0)
//                        counter = positionKey ?: 0
//                        arguments = null
//                    } else {
//                        counter = position
//                    }
//                    counterChild = 0
//
//                    if (scrollTrue){
//                        MainActivity.activity.get()?.runOnUiThread {
//                            baseButtons.visibility = View.GONE
//                            baseShare.visibility = View.GONE
//                            baseButtonsPlay.visibility = View.VISIBLE
//                            seekbar.visibility = View.VISIBLE
//                            ivPlay.visibility = View.GONE
//                            ivPause.visibility = View.VISIBLE
//                            Log.e("TAG", "scrollTrue1111")
//                            playSong(viewModel.itemMain[counter])
//                        }
//
//                    } else {
//                        Log.e("TAG", "scrollTrue22222")
//                    }
//
//
////                    playSong(viewModel.itemMain[counter])
////                    Log.e("TAG", "positionB" + position)
//                }
//
//                override fun onPageScrollStateChanged(state: Int) {
//                    super.onPageScrollStateChanged(state)
//                    Log.e("TAG", "positionC" + state)
//                   // scrollTrue = if (state == 0) true else false
//                }
//            })
//        }


    }

//
//    private fun playSong(itemMain: Item) {
//        binding.apply {
////            if (textToSpeech.isSpeaking) {
////                textToSpeech.stop()
////                ivPlay.setImageResource(R.drawable.play)
////                Log.e("TAG", "ZZZZZZZZZZ")
////                Log.e("MainActivity", "counterAAA " + counter)
////                textPlay.text = "H"
////                textToSpeech.speak(itemMain.name, TextToSpeech.QUEUE_ADD, null, itemMain.name)
////                textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
////                    override fun onDone(utteranceId: String) {
//////                        Log.e("MainActivity", "TTS onDone " + utteranceId);
////                        if (itemMain.name == utteranceId) {
////                            if (counter != viewModel.itemMain.size - 1) {
////                                playSongChild(viewModel.itemMain[counter])
//////                                Log.e("MainActivity", "counter " + counter);
////                            } else {
////                                MainActivity.activity.get()?.runOnUiThread {
////                                    ivPlay.visibility = View.VISIBLE
////                                    ivPause.visibility = View.GONE
////                                }
////                            }
////
////                        }
////                    }
////
////                    @Deprecated("Deprecated in Java")
////                    override fun onError(utteranceId: String) {
////                    }
////
////                    override fun onStart(utteranceId: String) {
////
////                    }
////
////                    override fun onRangeStart(
////                        utteranceId: String?,
////                        start: Int,
////                        end: Int,
////                        frame: Int
////                    ) {
////                        super.onRangeStart(utteranceId, start, end, frame)
////                    }
////                })
////
////            } else {
////                Log.e("TAG", "XXXXXXXXXXX")
////                Log.e("MainActivity", "counterBBB " + counter)
////                textPlay.text = "H"
////                textToSpeech.speak(itemMain.name, TextToSpeech.QUEUE_ADD, null, itemMain.name)
////                textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
////                    override fun onDone(utteranceId: String) {
//////                        Log.e("MainActivity", "TTS onDone " + utteranceId);
////                        if (itemMain.name == utteranceId) {
////                            if (counter != viewModel.itemMain.size - 1) {
//////                                Log.e("XXX", "counterChild "+counterChild)
////
////                                playSongChild(viewModel.itemMain[counter])
//////                                counter++
//////                                counterChild = 0
//////                                Log.e("MainActivity", "counter " + counter);
//////                                lifecycleScope.launch {
//////
//////                                    delay(100)
//////                                    introViewPager.setCurrentItem(counter, true)
//////
//////                                    delay(100)
//////                                    playSong(viewModel.itemMain[counter])
//////
//////                                }
////                            } else {
////                                MainActivity.activity.get()?.runOnUiThread {
////                                    ivPlay.visibility = View.VISIBLE
////                                    ivPause.visibility = View.GONE
////                                }
////                            }
////
////                        }
////                    }
////
////                    @Deprecated("Deprecated in Java")
////                    override fun onError(utteranceId: String) {
////                    }
////
////                    override fun onStart(utteranceId: String) {
////                    }
////
////                    override fun onRangeStart(
////                        utteranceId: String?,
////                        start: Int,
////                        end: Int,
////                        frame: Int
////                    ) {
////                        super.onRangeStart(utteranceId, start, end, frame)
////                    }
////                })
////            }
//
//
//
//            if (textToSpeech.isSpeaking) {
//                textToSpeech.stop()
//            }
//            Log.e("TAG", "XXXXXXXXXXX")
//            Log.e("MainActivity", "counterBBB " + counter)
////            textPlay.text = "H"
////            seekbar.max = 5
////            seekbar.progress = 0
//            lifecycleScope.launch {
//                delay(300)
//            }
//            val bundle = Bundle()
//            bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_MUSIC)
//            textToSpeech.speak(itemMain.name, TextToSpeech.QUEUE_FLUSH, null, itemMain.name)
//            textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
//                override fun onDone(utteranceId: String) {
////                        Log.e("MainActivity", "TTS onDone " + utteranceId);
//                    if (itemMain.name == utteranceId) {
////                        if (counter != viewModel.itemMain.size - 1) {
//////                                Log.e("XXX", "counterChild "+counterChild)
//                            Log.e("MainActivity", "counter111 " + counter)
//                            lifecycleScope.launch {
//                                playSongChild(viewModel.itemMain[counter])
//                            }
////                        } else {
////                            Log.e("MainActivity", "counter222 " + counter)
//////                            MainActivity.activity.get()?.runOnUiThread {
//////                                ivPlay.visibility = View.VISIBLE
//////                                ivPause.visibility = View.GONE
//////                            }
////                        }
//
//                    }
//                }
//
//                @Deprecated("Deprecated in Java")
//                override fun onError(utteranceId: String) {
//                }
//
//                override fun onStart(utteranceId: String) {
//                }
//
//                override fun onRangeStart(
//                    utteranceId: String?,
//                    start: Int,
//                    end: Int,
//                    frame: Int
//                ) {
//                    super.onRangeStart(utteranceId, start, end, frame)
//                }
//            })
//
//        }
//    }
//
//
//    private fun playSongChild(itemMain: Item) {
//        binding.apply {
//            if (textToSpeech.isSpeaking) {
//                textToSpeech.stop()
////                ivPlay.setImageResource(R.drawable.play)
//            } else {
////                textPlay.text = "0"+(counterChild+1)
////                seekbar.max = 5
////                seekbar.progress = counterChild+1
//                lifecycleScope.launch {
//                   delay(300)
//                }
//                val bundle = Bundle()
//                bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_MUSIC)
//                textToSpeech.speak(itemMain.itemList[counterChild].name, TextToSpeech.QUEUE_FLUSH, null, itemMain.itemList[counterChild].name)
//                textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
//                    override fun onDone(utteranceId: String) {
////                        Log.e("MainActivity", "TTS onDone " + utteranceId);
//                        if (itemMain.itemList[counterChild].name == utteranceId) {
//                            if (counterChild != itemMain.itemList.size - 1) {
//                                lifecycleScope.launch {
//                                    counterChild ++
//                                    playSongChild(viewModel.itemMain[counter])
//                                }
////                                counter++
////                                counterChild = 0
//                                Log.e("MainActivity", "counter " + counter);
////                                lifecycleScope.launch {
////
////                                    delay(100)
////                                    introViewPager.setCurrentItem(counter, true)
////
////                                    delay(100)
////                                    playSong(viewModel.itemMain[counter])
////
////                                }
//                            } else {
//                                MainActivity.activity.get()?.runOnUiThread {
//                                    counterChild = 0
////                                    ivPlay.visibility = View.VISIBLE
////                                    ivPause.visibility = View.GONE
//                                }
//                            }
//                        }
//                    }
//
//                    @Deprecated("Deprecated in Java")
//                    override fun onError(utteranceId: String) {
//                    }
//
//                    override fun onStart(utteranceId: String) {
////                        Log.e("MainActivity", "TTS onStart " + utteranceId);
////                        ivPlayPause.setImageResource(R.drawable.pause)
//                    }
//
//                    override fun onRangeStart(
//                        utteranceId: String?,
//                        start: Int,
//                        end: Int,
//                        frame: Int
//                    ) {
//                        super.onRangeStart(utteranceId, start, end, frame)
////                        Log.e("MainActivity", "TTS start" + start)
////                        Log.e("MainActivity", "TTS end" + end)
////                        Log.e("MainActivity", "TTS frame" + frame)
//                    }
//                })
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
//        binding.swipe.close()
//        onClickOpen()
        binding.apply {
            //baseButtons.visibility = View.GONE
//            baseShare.visibility = View.VISIBLE
//            baseButtonsPlay.visibility = View.GONE
//            seekbar.visibility = View.GONE

            introViewPager.setCurrentItem(position, true)
//            introViewPager.getChildAt(position).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        }
    }

    override fun onClickItemUp(position: Int) {
//        binding.swipe.close()
//        onClickOpen()
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


            introViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
            introViewPager.adapter = adapter
//            introViewPager.adapter = viewModel.verticalAdapter
//            viewModel.verticalAdapter.submitList(viewModel.itemMain)
            with(introViewPager) {
                clipToPadding = true
                clipChildren = true
                offscreenPageLimit = 2
            }
            introViewPager.setPageTransformer(SwipeTransformer())
//            introViewPager.setPageTransformer { page, position ->
//                introViewPager.updatePagerHeightForChild(page)
//            }



//            introViewPager.setOnItemClickListener(object : OnItemClickListener() {
//                fun onItemClick(
//                    parent: AdapterView<*>?, view: View?,
//                    position: Int, id: Long
//                ) {
//                    val i: Intent = Intent(
//                        this@MainActivity,
//                        SingleItemView::class.java
//                    )
//                    i.putExtra("flag", flag)
//                    i.putExtra("position", position)
//                    startActivity(i)
//                }
//            })

//        });


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
//                    Log.e("TAG", "position "+position)
//                    viewModel.verticalAdapter.updatePosition(position)
//                    viewModel.verticalAdapter.notifyItemChanged(position)
                    adapter.updatePosition(position)
//                    if (arguments != null) {
//                        val positionKey = arguments?.getInt("pos", 0)
//                        counter = positionKey ?: 0
//                        arguments = null
//                    } else {
//                        counter = position
//                    }
//                    counterChild = 0
//
//                    if (scrollTrue){
//                        MainActivity.activity.get()?.runOnUiThread {
//                            Log.e("TAG", "scrollTrue1111")
//                            playSong(viewModel.itemMain[counter])
//                        }
//
//                    } else {
//                        Log.e("TAG", "scrollTrue22222")
//                    }

                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
//                    Log.e("TAG", "positionC" + state)
                    // scrollTrue = if (state == 0) true else false
                }
            })
        }


    }

    inner class SwipeTransformer : ViewPager2.PageTransformer {
        private val screenHeight = resources.displayMetrics.heightPixels
        private val pageMarginPy = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        private val offsetPy = resources.getDimensionPixelOffset(R.dimen.offset)
        private val pageHeight = screenHeight - pageMarginPy - offsetPy

        private val MIN_SCALE = 0.6f
        private val MIN_ALPHA = 0.5f
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


