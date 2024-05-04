package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.HomeBinding
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Home : Fragment(), OnItemClickListener {

    private val viewModel: HomeVM by viewModels()
    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!

    lateinit var textToSpeech: TextToSpeech
    var counter = 0

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
    @SuppressLint("NotifyDataSetChanged", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomePagerAdapter(this)

        binding.apply {

            ivUp.setOnClickListener {
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


            textToSpeech = TextToSpeech(
                requireContext(),
                OnInitListener { status ->
                    if (status != TextToSpeech.ERROR) {
                        Log.i("XXX", "Google tts initialized")
                        //onTTSInitialized()
                    } else {
                        Log.i("XXX", "Internal Google engine init error.")
                    }
                }, "com.google.android.tts"
            )


//            val tts = TextToSpeech(context, object : OnInitListener {
//                private var mCallCount = 0 // trying to investigate potential infinite loops
//
//                override fun onInit(status: Int) {
//                    if ((mCallCount % 100) == 1) {
//                        // report this
//                    }
//                    mCallCount++
//                }
//            })


            var imageCounter = 0
            ivPlay.setOnClickListener {
                ivPlay.visibility = View.GONE
                ivPause.visibility = View.VISIBLE
                playSong(viewModel.itemMain[counter])
//                if(ivPlayPause.getDrawable().getConstantState()!!.equals(getResources().getDrawable(R.drawable.play).getConstantState()))
//                {
//                    Toast.makeText(requireContext(), "work", Toast.LENGTH_SHORT).show();
//                    ivPlayPause.setImageResource(R.drawable.pause)
//                }
//                else
//                {
//                    Toast.makeText(requireContext(), "not work", Toast.LENGTH_SHORT).show();
//                    ivPlayPause.setImageResource(R.drawable.play)
//                }
//                val drawableCompat = ContextCompat.getDrawable(requireContext(), R.drawable.play)

//                val ff = ivPlayPause.resources.getResourceName(R.drawable.play)
//                val gg = ivPlayPause.resources.getResourceName(R.drawable.pause)
//                Log.e("TAG", "AAAAAA "+ff)
//                Log.e("TAG", "AAAAAA "+gg)
//                val hh = ivPlayPause.resources.getResourceName(R.drawable.play)
//                Log.e("TAG", "AAAAAA "+hh)
//
////                val ff = ivPlayPause.resources.getResourceName(R.drawable.play)
////                val gg = ivPlayPause.resources.getResourceName(R.drawable.pause)
////                Log.e("TAG", "AAAAAA "+ff)
//                if(ff == "com.vegasega.amnews:drawable/play"){
//                    ivPlayPause.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.pause));
//                } else {
//                    ivPlayPause.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.play));
//                }

//                if(ivPlayPause.getDrawable()==getResources().getDrawable(R.drawable.play)){
//                    ivPlayPause.setImageResource(R.drawable.pause)
//                } else {
//                    ivPlayPause.setImageResource(R.drawable.play)
//                }



//
//                if(drawableCompat == R.drawable.play){
//
//                }
//                if (ivPlayPause.resources.getResourceName(R.drawable.play)) {
//                    Log.e("TAG", "AAAAAA")
//                   // ivPlayPause.setImageResource(R.drawable.pause)
//                    ivPlayPause.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.pause));
//
//                } else {
//                    Log.e("TAG", "BBBBBB")
////                    ivPlayPause.setImageResource(R.drawable.play)
//                    ivPlayPause.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.play));
//                }

//                if (imageCounter == 0) {
//                    imageCounter++
//                    ivPlayPause.setImageResource(R.drawable.pause)
//                    playSong(viewModel.itemMain[counter])
//                } else {
//                    imageCounter = 0
//                    ivPlayPause.setImageResource(R.drawable.play)
//                    if (textToSpeech.isSpeaking) {
//                        textToSpeech.stop()
//                    }
//                }

//                if (ivPlayPause.drawable == ContextCompat.getDrawable(requireContext(), R.drawable.pause)){
//                    ivPlayPause.setImageResource(R.drawable.play)
////                    playSong(viewModel.itemMain[counter])
//                }
//
//                if (ivPlayPause.drawable == ContextCompat.getDrawable(requireContext(), R.drawable.play)){
//                    ivPlayPause.setImageResource(R.drawable.pause)
////                    playSong(viewModel.itemMain[counter])
//                }

//                else {
//                    ivPlayPause.setImageResource(R.drawable.pause)
//                }


//                lifecycleScope.launch(Dispatchers.IO) {
//                    async {
//                        viewModel.itemMain.forEach {
//                            var counter = 0
//
//                            runBlocking {
//                                var counterItem = 0
//                                Log.e("XXX", "itemMain " + counter)
//                                if (textToSpeech.isSpeaking) {
//                                    textToSpeech.stop()
//                                    ivPlayPause.setImageResource(R.drawable.play)
//                                } else {
//                                    textToSpeech.speak(
//                                        viewModel.itemMain[counter].name,
//                                        TextToSpeech.QUEUE_FLUSH,
//                                        null,
//                                        viewModel.itemMain[counter].name
//                                    )
//                                    textToSpeech.setOnUtteranceProgressListener(object :
//                                        UtteranceProgressListener() {
//                                        override fun onDone(utteranceId: String) {
//                                            Log.e("MainActivity", "TTS onDone " + utteranceId);
//                                            ivPlayPause.setImageResource(R.drawable.play)
//                                            if (viewModel.itemMain[counter].name == utteranceId) {
//                                                counter++
//                                                    await()
//                                            }
//
//                                        }
//
//                                        @Deprecated("Deprecated in Java")
//                                        override fun onError(utteranceId: String) {
//                                        }
//
//                                        override fun onStart(utteranceId: String) {
//                                            Log.e("MainActivity", "TTS onStart " + utteranceId);
//                                            ivPlayPause.setImageResource(R.drawable.pause)
//                                        }
//
//                                        override fun onRangeStart(
//                                            utteranceId: String?,
//                                            start: Int,
//                                            end: Int,
//                                            frame: Int
//                                        ) {
//                                            super.onRangeStart(utteranceId, start, end, frame)
//                                            Log.e("MainActivity", "TTS start" + start)
//                                            Log.e("MainActivity", "TTS end" + end)
//                                            Log.e("MainActivity", "TTS frame" + frame)
//
//                                        }
//                                    })
//                                }
//                            }
//
//                                // playSong(viewModel.itemMain[counter].name)
////                    viewModel.itemMain[counter].itemList.forEach {
////                        Log.e("XXX", "itemList "+counterItem)
////                        playSong(viewModel.itemMain[counter].itemList[counterItem].name)
////                        counterItem++
////                    }
//
//                        }
//                    }
//
//
//                }


            }

            ivPause.setOnClickListener {
                ivPlay.visibility = View.VISIBLE
                ivPause.visibility = View.GONE
                if (textToSpeech.isSpeaking) {
                        textToSpeech.stop()
                    }
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

            adapter.submitList(viewModel.itemMain)
            adapter.notifyDataSetChanged()
            introViewPager.adapter = adapter
            TabLayoutMediator(tabLayout, introViewPager) { tab, position ->
                Log.e("TAG", "positionD" + position)
            }.attach()

            introViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    Log.e("TAG", "positionA" + position)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    //tabPosition = position
                    Log.e("TAG", "positionB" + position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    Log.e("TAG", "positionC" + state)
                }
            })
        }

    }


    private fun playSong(itemMain: HomeVM.Item) {
        binding.apply {
            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
                ivPlay.setImageResource(R.drawable.play)
            } else {
                textToSpeech.speak(itemMain.name, TextToSpeech.QUEUE_FLUSH, null, itemMain.name)
                textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onDone(utteranceId: String) {
                        Log.e("MainActivity", "TTS onDone " + utteranceId);
//                        ivPlayPause.setImageResource(R.drawable.play)
                        if (itemMain.name == utteranceId) {
                            if(counter != viewModel.itemMain.size - 1){
                                counter++
                                playSong(viewModel.itemMain[counter])
                            } else {
                                ivPlay.visibility = View.VISIBLE
                                ivPause.visibility = View.GONE
                            }
                            Log.e("MainActivity", "counter " + counter);
                        }
                    }

                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String) {
                    }

                    override fun onStart(utteranceId: String) {
                        Log.e("MainActivity", "TTS onStart " + utteranceId);
//                        ivPlayPause.setImageResource(R.drawable.pause)
                    }

                    override fun onRangeStart(
                        utteranceId: String?,
                        start: Int,
                        end: Int,
                        frame: Int
                    ) {
                        super.onRangeStart(utteranceId, start, end, frame)
                        Log.e("MainActivity", "TTS start" + start)
                        Log.e("MainActivity", "TTS end" + end)
                        Log.e("MainActivity", "TTS frame" + frame)
                    }
                })
            }
        }

    }


    private val progressListener: UtteranceProgressListener = object : UtteranceProgressListener() {
        override fun onStart(utteranceId: String) {
            Log.e("TAG", "Started utterance $utteranceId")
        }

        override fun onDone(utteranceId: String) {
            Log.e("TAG", "Done with utterance $utteranceId")
        }

        override fun onError(utteranceId: String?) {}
    }

    fun View.isUserInteractionEnabled(enabled: Boolean) {
        isEnabled = enabled
        if (this is ViewGroup && this.childCount > 0) {
            this.children.forEach {
                it.isUserInteractionEnabled(enabled)
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
}