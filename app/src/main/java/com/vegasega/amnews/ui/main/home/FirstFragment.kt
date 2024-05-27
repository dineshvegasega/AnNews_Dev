package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.media.AudioManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.CardItemViewBinding
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.ui.interfaces.RefreshFragment
import com.vegasega.amnews.ui.main.home.CenterHome2.Companion.counter
import com.vegasega.amnews.ui.main.home.CenterHome2.Companion.counterChild
import com.vegasega.amnews.ui.main.home.CenterHome2.Companion.isActive
import com.vegasega.amnews.ui.main.home.CenterHome2.Companion.isHide
import com.vegasega.amnews.ui.main.home.CenterHome2.Companion.isPageChanged
//import com.vegasega.amnews.ui.main.home.CenterHome2.Companion.isPageChanged
//import com.vegasega.amnews.ui.main.home.CenterHome2.Companion.isPageSwiped
import com.vegasega.amnews.ui.main.home.CenterHome2.Companion.textToSpeech
import com.vegasega.amnews.ui.mainActivity.MainActivity
import com.vegasega.amnews.utils.mainThread
import com.vegasega.amnews.utils.singleClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class FirstFragment(item: Item) : Fragment(), RefreshFragment {
    init {
        Log.e("TAG", "FirstFragment")
    }

    var model = item

    @SuppressLint("StaticFieldLeak")
    private var _binding: CardItemViewBinding? = null

    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CardItemViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("TAG", "onViewCreated")
        binding.apply {

//            isPageChanged.value = true

//            isPageSwiped = true
//
////            onHideShow()
//
//            if(isPageSwiped == true){
//                onHideShow()
//            }


            textTitle0.text = "" + model.name
            textTitle1.text = "" + model.itemList[0].name
            textTitle2.text = "" + model.itemList[1].name
            textTitle3.text = "" + model.itemList[2].name
            textTitle4.text = "" + model.itemList[3].name
            textTitle5.text = "" + model.itemList[4].name

            imageLogo.setImageResource(model.image)

            timeline1.initLine(1)
            timeline2.initLine(0)
            timeline3.initLine(0)
            timeline4.initLine(0)
            timeline5.initLine(2)




            requireActivity().runOnUiThread {
                if (isHide) {
                    baseButtons.visibility = View.GONE
                    group.visibility = View.VISIBLE
                } else {
                    baseButtons.visibility = View.VISIBLE
                    group.visibility = View.GONE
                }
            }


        }


    }

    override fun onStart() {
        super.onStart()
        Log.e("TAG", "onStart")
//        if(isPageSwiped == true){
//            onHideShow()
//        }
//        onHideShow()


    }


    override fun onResume() {
        super.onResume()
        Log.e("TAG", "onResume")
//        if(isPageSwiped == true){
//            onHideShow()
//        }
        //onHideShow()

        binding.apply {
            requireActivity().runOnUiThread {
                if (isHide) {
                    baseButtons.visibility = View.GONE
                    group.visibility = View.VISIBLE
                } else {
                    baseButtons.visibility = View.VISIBLE
                    group.visibility = View.GONE
                }
            }
        }
    }


    override fun onPause() {
        super.onPause()
//        if(isPageSwiped == true){
//            onHideShow()
//        }

//        binding.apply {
//            if (isHide){
//                baseButtons.visibility = View.GONE
//                group.visibility = View.VISIBLE
//            } else {
//                baseButtons.visibility = View.VISIBLE
//                group.visibility = View.GONE
//            }
//        }
    }

    override fun onStop() {
        super.onStop()
        Log.e("TAG", "onStop")
    }

    @SuppressLint("SuspiciousIndentation")
    override fun refresh(position: Int) {
        Log.e("TAG", "refresh")

        counter = position

//        isPageSwiped = true
//        if(isPageSwiped == true){
//            onHideShow()
//        }

//        isPageChanged.observeForever {
//            if (it){
        onHideShow()
//            }
//        }

    }


    @SuppressLint("ClickableViewAccessibility")
    fun onHideShow() {
        Log.e("TAG", "onHideShow")
        binding.apply {

//            root.setOnTouchListener { view, motionEvent ->
//                CenterHome2.callBackListener?.onCallBackHideShow()
//                false
//            }

            layoutRoot.singleClick {
                Log.e("TAG", "mainLayout")
//                listener.onClickMain()
                CenterHome2.callBackListener?.onCallBackHideShow()
            }

            ivCross.setOnClickListener {
                isHide = false
                if (textToSpeech.isSpeaking) {
                    textToSpeech.stop()
                }
                isActive = false
                baseButtons.visibility = View.VISIBLE
                group.visibility = View.GONE
            }

            ivAudio.setOnClickListener {
                isHide = true
                isActive = true
                baseButtons.visibility = View.GONE
                group.visibility = View.VISIBLE

                if (textToSpeech.isSpeaking) {
                    textToSpeech.stop()
                    ivPlayPause.setImageResource(R.drawable.play)
                }
                if (isActive == true) {
                    playSong(model, binding)
                }
            }

            if (isHide) {
                baseButtons.visibility = View.GONE
                group.visibility = View.VISIBLE
            } else {
                baseButtons.visibility = View.VISIBLE
                group.visibility = View.GONE
            }





            ivPlayPause.setOnClickListener {
                if (textToSpeech.isSpeaking) {
                    textToSpeech.stop()
                    isActive = false
                    ivPlayPause.setImageResource(R.drawable.play)
                } else {
                    isActive = true
                    playSong(model, binding)
                    ivPlayPause.setImageResource(R.drawable.pause)
                }
            }


            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
                ivPlayPause.setImageResource(R.drawable.play)
            }
            if (isActive == true) {
                playSong(model, binding)
            }




            ivUp.setOnClickListener {
                isActive = false
                // listener.onClickItemUp(0)
            }

            ivSearch.setOnClickListener {
                it.findNavController().navigate(R.id.action_home_to_search)
            }

        }
    }


    private fun playSong(model: Item, holder: CardItemViewBinding) {
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }


        holder.ivPlayPause.setImageResource(R.drawable.pause)
        Handler(Looper.myLooper()!!).postDelayed({
            holder.seekbar.max = 5
            holder.seekbar.progress = 0
            holder.textPlay.text = "H"
            textToSpeech.speak(model.name, TextToSpeech.QUEUE_FLUSH, null, model.name)
            val bundle = Bundle()
            bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_MUSIC)
            textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onDone(utteranceId: String) {
                    if (model.name == utteranceId) {
                        Log.e("MainActivity", "counter111 " + counter)
                        playSongChild(model, binding)
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
        }, 50)
    }


    private fun playSongChild(itemMain: Item, holder: CardItemViewBinding) {
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }

        val bundle = Bundle()
        bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM, 3)
        holder.textPlay.text = "0" + (counterChild + 1)
        holder.seekbar.max = 5
        holder.seekbar.progress = counterChild + 1
        textToSpeech.speak(
            itemMain.itemList[counterChild].name,
            TextToSpeech.QUEUE_FLUSH,
            null,
            itemMain.itemList[counterChild].name
        )
        textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onDone(utteranceId: String) {
//                        Log.e("MainActivity", "TTS onDone " + utteranceId);
                if (itemMain.itemList[counterChild].name == utteranceId) {
                    if (counterChild != itemMain.itemList.size - 1) {
                        counterChild++
                        playSongChild(itemMain, holder)
//                        MainActivity.activity.get()?.runOnUiThread {
//                            ivPlayPause.setImageResource(R.drawable.play)
//                        }
//                                counter++
//                                counterChild = 0
                        Log.e("MainActivity", "counterChild " + counterChild);
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
//                        MainActivity.activity.get()?.runOnUiThread {
//                            counterChild = 0
////                                    ivPlay.visibility = View.VISIBLE
////                                    ivPause.visibility = View.GONE
//
//                        }

                        MainActivity.activity.get()?.runOnUiThread {
                            counterChild = 0
                            holder.ivPlayPause.setImageResource(R.drawable.play)
                            mainThread {
                                delay(500)
                                //listener.onClickItem(counter + 1)
                                val ll = counter + 1
                                Log.e("TAG", "llXX " + ll)
                                CenterHome2.callBackListener!!.onCallBack(ll)

                            }
                        }
                    }
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
