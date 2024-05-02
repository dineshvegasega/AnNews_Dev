package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.HomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class Home : Fragment() {

    private val viewModel: HomeVM by viewModels()
    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!

    lateinit var textToSpeech: TextToSpeech


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeBinding.inflate(inflater)
        return binding.root
    }

    var sentence: String ="दिल्ली न्यूज़ राजनीति"
//    var sentence: String ="Vous devrez également"
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

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

            var counter = 0
            viewModel.itemMain.forEach {
                var counterItem = 0
                Log.e("XXX", "itemMain "+counter)
                viewModel.itemMain[counter].itemList.forEach {
                    Log.e("XXX", "itemList "+counterItem)
                    Thread.sleep(1000)
                    counterItem++
                }
                Thread.sleep(1000)
                counter++
            }


            ivPlayPause.setOnClickListener {
//                val map = HashMap<String, String>()
//                map[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "uniqueId()"
//                textToSpeech.speak(map.get(""), TextToSpeech.QUEUE_ADD, map)

//                val onInitListener = TextToSpeech.OnInitListener {
//                    textToSpeech.setOnUtteranceProgressListener(progressListener)
//                    textToSpeech.voice = textToSpeech.voices.find { it.name == "en-us-x-iog-local" } ?: textToSpeech.defaultVoice
//                    textToSpeech.speak("यूपी के कैसरगंज से बृजभूषण", TextToSpeech.QUEUE_ADD, Bundle(), "utteranceId")
//
//                }
//                textToSpeech = TextToSpeech(requireContext(), onInitListener)


//                mediaPlayer?.setDataSource(sentence);
//                mediaPlayer?.prepare()
//                mediaPlayer?.start()

                if (textToSpeech.isSpeaking){
                    textToSpeech.stop()
                    ivPlayPause.setImageResource(R.drawable.play)
                }else{
                    textToSpeech.speak(sentence, TextToSpeech.QUEUE_FLUSH, null, "unique_id")
//                    textToSpeech.setLanguage(Locale.forLanguageTag("hin-IND"));
//                    textToSpeech.language = Locale.FRANCE
//                    textToSpeech.setLanguage(Locale("hi", "IND", "IND"))
//                    textToSpeech.voice = textToSpeech.voices.find { it.name == "en-us-x-iog-local" } ?: textToSpeech.defaultVoice

                    textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                        override fun onDone(utteranceId: String) {
                             Log.e("MainActivity", "TTS onDone "+utteranceId);
                            ivPlayPause.setImageResource(R.drawable.play)
                        }

                        @Deprecated("Deprecated in Java")
                        override fun onError(utteranceId: String) {
                        }

                        override fun onStart(utteranceId: String) {
                            Log.e("MainActivity", "TTS onStart "+utteranceId);
                            ivPlayPause.setImageResource(R.drawable.pause)
                        }

                        override fun onRangeStart(
                            utteranceId: String?,
                            start: Int,
                            end: Int,
                            frame: Int
                        ) {
                            super.onRangeStart(utteranceId, start, end, frame)
                            Log.e("MainActivity", "TTS start"+start)
                            Log.e("MainActivity", "TTS end"+end)
                            Log.e("MainActivity", "TTS frame"+frame)

//                            requireActivity().runOnUiThread(Runnable {
//                                val textWithHighlights: Spannable = SpannableString(sentence)
//                                textWithHighlights.setSpan(
//                                    ForegroundColorSpan(Color.YELLOW),
//                                    start,
//                                    end,
//                                    Spanned.SPAN_INCLUSIVE_INCLUSIVE
//                                )
//                                textTitle111.setText(textWithHighlights)
//                            })

                        }
                    })
                    seekbar.progress = 4
                    seekbar.max = sentence.length
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

            val adapter= HomePagerAdapter()
            adapter.submitList(viewModel.itemMain)
            adapter.notifyDataSetChanged()
            introViewPager.adapter=adapter
            TabLayoutMediator(tabLayout, introViewPager) { tab, position ->
                Log.e("TAG", "positionD" +position)
            }.attach()

            introViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    Log.e("TAG", "positionA" +position)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    //tabPosition = position
                    Log.e("TAG", "positionB" +position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    Log.e("TAG", "positionC" +state)
                }
            })
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


    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.shutdown()
    }
}