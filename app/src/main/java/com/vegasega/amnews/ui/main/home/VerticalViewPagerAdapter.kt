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
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.vegasega.amnews.R
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.ui.mainActivity.MainActivity
import com.vegasega.amnews.utils.mainThread
import com.vegasega.amnews.utils.singleClick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VerticalViewPagerAdapter(
    private val listener: OnItemClickListener,
    var list: ArrayList<Item>,
    textToSpeechVoice: TextToSpeech
) : RecyclerView.Adapter<VerticalViewPagerAdapter.PagerViewHolder>() {
    var isActive = false
    var isHide = false
    var counter = 0
    var counterChild = 0

    var textToSpeech: TextToSpeech = textToSpeechVoice

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.card_item_view, parent, false)) {
        val mainLayout = itemView.findViewById<CardView>(R.id.mainLayout)

        val textTitle0 = itemView.findViewById<AppCompatTextView>(R.id.textTitle0)
        val textTitle1 = itemView.findViewById<AppCompatTextView>(R.id.textTitle1)
        val textTitle2 = itemView.findViewById<AppCompatTextView>(R.id.textTitle2)
        val textTitle3 = itemView.findViewById<AppCompatTextView>(R.id.textTitle3)
        val textTitle4 = itemView.findViewById<AppCompatTextView>(R.id.textTitle4)
        val textTitle5 = itemView.findViewById<AppCompatTextView>(R.id.textTitle5)

        val imageLogo = itemView.findViewById<AppCompatImageView>(R.id.imageLogo)

        val timeline1 = itemView.findViewById<TimelineView>(R.id.timeline1)
        val timeline2 = itemView.findViewById<TimelineView>(R.id.timeline2)
        val timeline3 = itemView.findViewById<TimelineView>(R.id.timeline3)
        val timeline4 = itemView.findViewById<TimelineView>(R.id.timeline4)
        val timeline5 = itemView.findViewById<TimelineView>(R.id.timeline5)

        val ivCross = itemView.findViewById<AppCompatImageView>(R.id.ivCross)
        val ivPlayback = itemView.findViewById<AppCompatImageView>(R.id.ivPlayback)
        val ivPlaynext = itemView.findViewById<AppCompatImageView>(R.id.ivPlaynext)
        val ivPlayPause = itemView.findViewById<AppCompatImageView>(R.id.ivPlayPause)
        val textPlay = itemView.findViewById<AppCompatTextView>(R.id.textPlay)
        val seekbar = itemView.findViewById<SeekBar>(R.id.seekbar)


        val baseButtons = itemView.findViewById<ConstraintLayout>(R.id.baseButtons)
        val group = itemView.findViewById<Group>(R.id.group)

        val ivUp = itemView.findViewById<AppCompatImageView>(R.id.ivUp)
        val ivAudio = itemView.findViewById<AppCompatImageView>(R.id.ivAudio)
        val ivSearch = itemView.findViewById<AppCompatImageView>(R.id.ivSearch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(parent)

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.itemView.singleClick {
            Log.e("TAG", "mainLayout")
            listener.onClickMain()
        }

        val model = list[position]
        holder.textTitle0.text = "" + model.name
        holder.textTitle1.text = "" + model.itemList[0].name
        holder.textTitle2.text = "" + model.itemList[1].name
        holder.textTitle3.text = "" + model.itemList[2].name
        holder.textTitle4.text = "" + model.itemList[3].name
        holder.textTitle5.text = "" + model.itemList[4].name

        holder.imageLogo.setImageResource(model.image)

        holder.timeline1.initLine(1)
        holder.timeline2.initLine(0)
        holder.timeline3.initLine(0)
        holder.timeline4.initLine(0)
        holder.timeline5.initLine(2)


        if (position == counter) {
            Log.e("TAG", "QQQQQQQ " + position)
            counterChild = 0
            if (isActive == true){
                playSong(model, holder)
            }
        } else {
            Log.e("TAG", "WWWWWWW " + position)
            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
                holder.ivPlayPause.setImageResource(R.drawable.play)
            }
        }


        holder.ivPlayback.setOnClickListener {
            if(counter != 0){
                listener.onClickItem(counter - 1)
                Log.e("TAG", "ivPlayback "+counter)
            }
        }

        holder.ivPlaynext.setOnClickListener {
            if(counter != list.size - 1){
                listener.onClickItem(counter + 1)
                Log.e("TAG", "ivPlaynext "+counter)
            }
        }


        holder.ivPlayPause.setOnClickListener {
            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
                isActive = false
                holder.ivPlayPause.setImageResource(R.drawable.play)
            } else {
                isActive = true
                playSong(model, holder)
                holder.ivPlayPause.setImageResource(R.drawable.pause)
            }
        }


        holder.ivCross.setOnClickListener {
            isHide = false
            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
                isActive = false
            }
            notifyItemChanged(counter)
            holder.baseButtons.visibility = View.VISIBLE
            holder.group.visibility = View.GONE
        }

        holder.ivAudio.setOnClickListener {
            isHide = true
            isActive = true
            notifyItemChanged(counter)
            holder.baseButtons.visibility = View.GONE
            holder.group.visibility = View.VISIBLE
        }


        if (isHide){
            holder.baseButtons.visibility = View.GONE
            holder.group.visibility = View.VISIBLE
        } else {
            holder.baseButtons.visibility = View.VISIBLE
            holder.group.visibility = View.GONE
        }


        holder.ivUp.setOnClickListener {
            isActive = false
            listener.onClickItemUp(0)
        }

        holder.ivSearch.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainHome_to_search)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePosition(position: Int) {
        counter = position
        notifyItemChanged(counter)
    }


    private fun playSong(model: Item, holder: PagerViewHolder) {
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
                        playSongChild(model, holder)
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


    private fun playSongChild(itemMain: Item, holder: PagerViewHolder) {
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }

        val bundle = Bundle()
        bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM,3)
        holder.textPlay.text = "0"+(counterChild+1)
        holder.seekbar.max = 5
        holder.seekbar.progress = counterChild+1
        textToSpeech.speak(itemMain.itemList[counterChild].name, TextToSpeech.QUEUE_FLUSH, null, itemMain.itemList[counterChild].name)
        textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onDone(utteranceId: String) {
//                        Log.e("MainActivity", "TTS onDone " + utteranceId);
                if (itemMain.itemList[counterChild].name == utteranceId) {
                    if (counterChild != itemMain.itemList.size - 1) {
                            counterChild ++
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
                                listener.onClickItem(counter + 1)
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