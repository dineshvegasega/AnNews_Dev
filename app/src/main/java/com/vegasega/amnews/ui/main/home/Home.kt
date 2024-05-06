package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.gson.reflect.TypeToken
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.HomeBinding
import com.vegasega.amnews.models.BaseResponseDC
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.models.ItemMain
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.ui.mainActivity.MainActivity
import com.vegasega.amnews.utils.parcelable
import com.vegasega.amnews.utils.parcelableArrayList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Home : Fragment(), OnItemClickListener {

    private val viewModel: HomeVM by viewModels()
    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!

    lateinit var textToSpeech: TextToSpeech
    var counter = 0
    var counterChild = 0
    var scrollTrue = false

//    lateinit var adapter : HomePagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeBinding.inflate(inflater)
        return binding.root
    }

    //    var sentence: String ="दिल्ली न्यूज़ राजनीति"
//    var sentence: String ="Vous devrez également"
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("NotifyDataSetChanged", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter = HomePagerAdapter(this)





        binding.apply {
            ivUp.setOnClickListener {
                MainActivity.activity.get()?.runOnUiThread {
                    val url =
                        "https://www.google.com/"
                    url?.let {
                        val webIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(url)
                        )
                        try {
                            requireContext().startActivity(webIntent)
                        } catch (_: ActivityNotFoundException) {
                        }
                    }
                }
            }


//            baseButtons.visibility = View.GONE
//            baseShare.visibility = View.GONE
//            baseButtonsPlay.visibility = View.VISIBLE
//            seekbar.visibility = View.VISIBLE

            ivAudio.setOnClickListener {
                baseButtons.visibility = View.GONE
                baseShare.visibility = View.GONE
                baseButtonsPlay.visibility = View.VISIBLE
                seekbar.visibility = View.VISIBLE
            }

            ivSearch.setOnClickListener {
                findNavController().navigate(R.id.action_home_to_search)
            }

            ivCross.setOnClickListener {
                baseButtons.visibility = View.VISIBLE
                baseShare.visibility = View.GONE
                baseButtonsPlay.visibility = View.GONE
                seekbar.visibility = View.GONE
            }


            linearTopics.setOnClickListener {
                baseButtons.visibility = View.VISIBLE
                baseShare.visibility = View.GONE
                baseButtonsPlay.visibility = View.GONE
                seekbar.visibility = View.GONE
            }

            linearArticles.setOnClickListener {
                baseButtons.visibility = View.VISIBLE
                baseShare.visibility = View.GONE
                baseButtonsPlay.visibility = View.GONE
                seekbar.visibility = View.GONE
            }


//            textToSpeech = TextToSpeech(requireContext()) {status ->
//                if (status == TextToSpeech.SUCCESS){
//                    Log.e("TextToSpeech", "Initialization Success")
//                }else{
//                    Log.e("TextToSpeech", "Initialization Failed")
//                }
//            }
////            textToSpeech.language = Locale("hi","IN")


            textToSpeech = TextToSpeech( requireContext(), { status ->
                    if (status != TextToSpeech.ERROR) {
                        Log.i("XXX", "Google tts initialized")
                    } else {
                        Log.i("XXX", "Internal Google engine init error.")
                    }
                }, "com.google.android.tts"
            )


            ivPlay.setOnClickListener {
                ivPlay.visibility = View.GONE
                ivPause.visibility = View.VISIBLE
                scrollTrue = true
                playSong(viewModel.itemMain[counter])
                Log.i("TAG", "ivPlay "+ counter)
            }




            ivPause.setOnClickListener {
                ivPlay.visibility = View.VISIBLE
                ivPause.visibility = View.GONE
                scrollTrue = false
                counterChild = 0
                if (textToSpeech.isSpeaking) {
                    textToSpeech.stop()
                }
            }


            ivPlayback.setOnClickListener {
//                ivPlay.visibility = View.VISIBLE
//                ivPause.visibility = View.GONE
                counterChild = 0
                if(counter != 0){
                    counter = counter - 1
                }
                introViewPager.setCurrentItem(counter, true)
            }

            ivPlaynext.setOnClickListener {
//                ivPlay.visibility = View.VISIBLE
//                ivPause.visibility = View.GONE
                counterChild = 0
                counter = counter + 1
                introViewPager.setCurrentItem(counter, true)
            }

//                    seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
//                        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
////                            binding.tvTrickleRateValue.text = progress.toString() + " " + getString(R.string.ls)
////                            binding.tvTrickleIndicator.text = progress.toString()
////                            val bounds: Rect = binding.seekbar.thumb.bounds
////                            binding.tvTrickleIndicator.x = (binding.seekbarTrickle.left + bounds.left + marginStart).toFloat()
//                        }
//
//                        override fun onStartTrackingTouch(seekBar: SeekBar?) { }
//
//                        override fun onStopTrackingTouch(seekBar: SeekBar?) { }
//
//                    })


            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = viewModel.dashboardAdapter
            viewModel.dashboardAdapter.submitList(viewModel.itemMain)
            viewModel.dashboardAdapter.notifyDataSetChanged()


            introViewPager.adapter = adapter
            if (arguments != null){
                val positionKey = arguments?.getInt("pos", 0)
                counter = positionKey ?: 0
                Log.e("TAG", "positionKey "+counter)

                val consentIntent = arguments?.parcelable<ItemMain>("key")
                consentIntent?.let {
                    viewModel.itemMain = it.data
                }
                adapter.submitList(viewModel.itemMain)
                adapter.notifyDataSetChanged()

                Handler(Looper.myLooper()!!).postDelayed({
                    introViewPager.setCurrentItem(counter, true)
                }, 300)
            } else {
                adapter.submitList(viewModel.itemMain)
                adapter.notifyDataSetChanged()
            }







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

                    if (arguments != null) {
                        val positionKey = arguments?.getInt("pos", 0)
                        counter = positionKey ?: 0
                        arguments = null
                    } else {
                        counter = position
                    }
                    counterChild = 0

                    if (scrollTrue){
                        MainActivity.activity.get()?.runOnUiThread {
                            baseButtons.visibility = View.GONE
                            baseShare.visibility = View.GONE
                            baseButtonsPlay.visibility = View.VISIBLE
                            seekbar.visibility = View.VISIBLE
                            ivPlay.visibility = View.GONE
                            ivPause.visibility = View.VISIBLE
                            Log.e("TAG", "scrollTrue1111")
                            playSong(viewModel.itemMain[counter])
                        }

                    } else {
                        Log.e("TAG", "scrollTrue22222")
                    }


//                    playSong(viewModel.itemMain[counter])
//                    Log.e("TAG", "positionB" + position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    Log.e("TAG", "positionC" + state)
                   // scrollTrue = if (state == 0) true else false
                }
            })
        }
    }


    private fun playSong(itemMain: Item) {
        binding.apply {
//            if (textToSpeech.isSpeaking) {
//                textToSpeech.stop()
//                ivPlay.setImageResource(R.drawable.play)
//                Log.e("TAG", "ZZZZZZZZZZ")
//                Log.e("MainActivity", "counterAAA " + counter)
//                textPlay.text = "H"
//                textToSpeech.speak(itemMain.name, TextToSpeech.QUEUE_ADD, null, itemMain.name)
//                textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
//                    override fun onDone(utteranceId: String) {
////                        Log.e("MainActivity", "TTS onDone " + utteranceId);
//                        if (itemMain.name == utteranceId) {
//                            if (counter != viewModel.itemMain.size - 1) {
//                                playSongChild(viewModel.itemMain[counter])
////                                Log.e("MainActivity", "counter " + counter);
//                            } else {
//                                MainActivity.activity.get()?.runOnUiThread {
//                                    ivPlay.visibility = View.VISIBLE
//                                    ivPause.visibility = View.GONE
//                                }
//                            }
//
//                        }
//                    }
//
//                    @Deprecated("Deprecated in Java")
//                    override fun onError(utteranceId: String) {
//                    }
//
//                    override fun onStart(utteranceId: String) {
//
//                    }
//
//                    override fun onRangeStart(
//                        utteranceId: String?,
//                        start: Int,
//                        end: Int,
//                        frame: Int
//                    ) {
//                        super.onRangeStart(utteranceId, start, end, frame)
//                    }
//                })
//
//            } else {
//                Log.e("TAG", "XXXXXXXXXXX")
//                Log.e("MainActivity", "counterBBB " + counter)
//                textPlay.text = "H"
//                textToSpeech.speak(itemMain.name, TextToSpeech.QUEUE_ADD, null, itemMain.name)
//                textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
//                    override fun onDone(utteranceId: String) {
////                        Log.e("MainActivity", "TTS onDone " + utteranceId);
//                        if (itemMain.name == utteranceId) {
//                            if (counter != viewModel.itemMain.size - 1) {
////                                Log.e("XXX", "counterChild "+counterChild)
//
//                                playSongChild(viewModel.itemMain[counter])
////                                counter++
////                                counterChild = 0
////                                Log.e("MainActivity", "counter " + counter);
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
//                                    ivPlay.visibility = View.VISIBLE
//                                    ivPause.visibility = View.GONE
//                                }
//                            }
//
//                        }
//                    }
//
//                    @Deprecated("Deprecated in Java")
//                    override fun onError(utteranceId: String) {
//                    }
//
//                    override fun onStart(utteranceId: String) {
//                    }
//
//                    override fun onRangeStart(
//                        utteranceId: String?,
//                        start: Int,
//                        end: Int,
//                        frame: Int
//                    ) {
//                        super.onRangeStart(utteranceId, start, end, frame)
//                    }
//                })
//            }



            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
            }
            Log.e("TAG", "XXXXXXXXXXX")
            Log.e("MainActivity", "counterBBB " + counter)
            textPlay.text = "H"
            seekbar.max = 5
            seekbar.progress = 0
            lifecycleScope.launch {
                delay(300)
            }
            textToSpeech.speak(itemMain.name, TextToSpeech.QUEUE_FLUSH, null, itemMain.name)
            textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onDone(utteranceId: String) {
//                        Log.e("MainActivity", "TTS onDone " + utteranceId);
                    if (itemMain.name == utteranceId) {
//                        if (counter != viewModel.itemMain.size - 1) {
////                                Log.e("XXX", "counterChild "+counterChild)
                            Log.e("MainActivity", "counter111 " + counter)
                            lifecycleScope.launch {
                                playSongChild(viewModel.itemMain[counter])
                            }
//                        } else {
//                            Log.e("MainActivity", "counter222 " + counter)
////                            MainActivity.activity.get()?.runOnUiThread {
////                                ivPlay.visibility = View.VISIBLE
////                                ivPause.visibility = View.GONE
////                            }
//                        }

                    }
                }

                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String) {
                }

                override fun onStart(utteranceId: String) {
                }

                override fun onRangeStart(
                    utteranceId: String?,
                    start: Int,
                    end: Int,
                    frame: Int
                ) {
                    super.onRangeStart(utteranceId, start, end, frame)
                }
            })
        }
    }


    private fun playSongChild(itemMain: Item) {
        binding.apply {
            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
                ivPlay.setImageResource(R.drawable.play)
            } else {
                textPlay.text = "0"+(counterChild+1)
                seekbar.max = 5
                seekbar.progress = counterChild+1
                lifecycleScope.launch {
                   delay(300)
                }
                textToSpeech.speak(itemMain.itemList[counterChild].name, TextToSpeech.QUEUE_FLUSH, null, itemMain.itemList[counterChild].name)
                textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onDone(utteranceId: String) {
//                        Log.e("MainActivity", "TTS onDone " + utteranceId);
                        if (itemMain.itemList[counterChild].name == utteranceId) {
                            if (counterChild != itemMain.itemList.size - 1) {
                                lifecycleScope.launch {
                                    counterChild ++
                                    playSongChild(viewModel.itemMain[counter])
                                }
//                                counter++
//                                counterChild = 0
                                Log.e("MainActivity", "counter " + counter);
//                                lifecycleScope.launch {
//
//                                    delay(100)
//                                    introViewPager.setCurrentItem(counter, true)
//
//                                    delay(100)
//                                    playSong(viewModel.itemMain[counter])
//
//                                }
                            } else {
                                MainActivity.activity.get()?.runOnUiThread {
                                    counterChild = 0
                                    ivPlay.visibility = View.VISIBLE
                                    ivPause.visibility = View.GONE
                                }
                            }

                        }
                    }

                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String) {
                    }

                    override fun onStart(utteranceId: String) {
//                        Log.e("MainActivity", "TTS onStart " + utteranceId);
//                        ivPlayPause.setImageResource(R.drawable.pause)
                    }

                    override fun onRangeStart(
                        utteranceId: String?,
                        start: Int,
                        end: Int,
                        frame: Int
                    ) {
                        super.onRangeStart(utteranceId, start, end, frame)
//                        Log.e("MainActivity", "TTS start" + start)
//                        Log.e("MainActivity", "TTS end" + end)
//                        Log.e("MainActivity", "TTS frame" + frame)
                    }
                })
            }
        }
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
            ivPlay.visibility = View.VISIBLE
            ivPause.visibility = View.GONE
            scrollTrue = false
            counterChild = 0
            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.shutdown()
    }

    var topListCounter = 0
    override fun onClickMain() {
        binding.apply {
            if (topListCounter == 0) {
                topListCounter++
                recyclerView.visibility = View.VISIBLE
            } else {
                topListCounter = 0
                recyclerView.visibility = View.GONE
            }
        }
    }

    override fun onClickItem() {
        binding.apply {
            baseButtons.visibility = View.GONE
            baseShare.visibility = View.VISIBLE
            baseButtonsPlay.visibility = View.GONE
            seekbar.visibility = View.GONE
        }
    }





//        private fun playSong(itemMain: HomeVM.Item) {
//        binding.apply {
//            if (textToSpeech.isSpeaking) {
//                textToSpeech.stop()
//                ivPlay.setImageResource(R.drawable.play)
//            } else {
//                textToSpeech.speak(itemMain.name, TextToSpeech.QUEUE_FLUSH, null, itemMain.name)
//                textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
//                    override fun onDone(utteranceId: String) {
//                        Log.e("MainActivity", "TTS onDone " + utteranceId);
//                        if (itemMain.name == utteranceId) {
//                            if (counter != viewModel.itemMain.size - 1) {
//                                counter++
//                                lifecycleScope.launch {
////                                    delay(100)
////                                    introViewPager.setCurrentItem(counter, true)
////
////                                    delay(100)
////                                    playSong(viewModel.itemMain[counter])
//
//                                viewModel.itemSongs.value = counter
//
//                                }
//                            } else {
//                                MainActivity.activity.get()?.runOnUiThread {
//                                    ivPlay.visibility = View.VISIBLE
//                                    ivPause.visibility = View.GONE
//                                }
//                            }
//                            Log.e("MainActivity", "counter " + counter);
//                        }
//                    }
//
//                    @Deprecated("Deprecated in Java")
//                    override fun onError(utteranceId: String) {
//                    }
//
//                    override fun onStart(utteranceId: String) {
//                        Log.e("MainActivity", "TTS onStart " + utteranceId);
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
//                        Log.e("MainActivity", "TTS start" + start)
//                        Log.e("MainActivity", "TTS end" + end)
//                        Log.e("MainActivity", "TTS frame" + frame)
//                    }
//                })
//            }
//        }
//    }

}



//fun ViewPager2.setCurrentItem(
//    item: Int,
//    duration: Long,
//    interpolator: ObjectAnimator = AccelerateDecelerateInterpolator(),
//    pagePxWidth: Int = width // Default value taken from getWidth() from ViewPager2 view
//) {
//    val pxToDrag: Int = pagePxWidth * (item - currentItem)
//    val animator = ValueAnimator.ofInt(0, pxToDrag)
//    var previousValue = 0
//    animator.addUpdateListener { valueAnimator ->
//        val currentValue = valueAnimator.animatedValue as Int
//        val currentPxToDrag = (currentValue - previousValue).toFloat()
//        fakeDragBy(-currentPxToDrag)
//        previousValue = currentValue
//    }
//    animator.addListener(object : Animator.AnimatorListener {
//        override fun onAnimationStart(animation: Animator) { beginFakeDrag() }
//        override fun onAnimationEnd(animation: Animator) { endFakeDrag() }
//        override fun onAnimationCancel(animation: Animator) { /* Ignored */ }
//        override fun onAnimationRepeat(animation: Animator) { /* Ignored */ }
//    })
//    animator.interpolator = interpolator
//    animator.duration = duration
//    animator.start()
//}
