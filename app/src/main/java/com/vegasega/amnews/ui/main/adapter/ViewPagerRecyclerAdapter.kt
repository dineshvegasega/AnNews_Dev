package com.vegasega.amnews.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import com.github.vipulasri.timelineview.TimelineView
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.CardItemViewBinding
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.utils.singleClick

class ViewPagerRecyclerAdapter(private val listener: OnItemClickListener,
                               textToSpeechVoice: TextToSpeech) :
    RecycledPagerAdapter<RecycledPagerAdapter.ViewHolder?>() {

    var isActive = false
    var isHide = false
    var counter = 0
    var counterChild = 0

    var textToSpeech: TextToSpeech = textToSpeechVoice

    var list: ArrayList<Item> = ArrayList()



    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
//        }
        val v = layoutInflater!!.inflate(R.layout.card_item_view, null, false)
        return TextScrollViewHolder(v)

//        val dialogBinding = CardItemViewBinding.inflate(parent.context.getSystemService(
//            Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        )
//        return TextScrollViewHolder(dialogBinding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        (viewHolder as TextScrollViewHolder).apply {
//            itemView.singleClick {
//                Log.e("TAG", "mainLayout")
//                listener.onClickMain()
//            }

            ivCross.setOnClickListener {
                isHide = false
                if (textToSpeech.isSpeaking) {
                    textToSpeech.stop()
                    isActive = false
                }
                notifyDataSetChanged()
                baseButtons.visibility = View.VISIBLE
                group.visibility = View.GONE
            }

            ivAudio.setOnClickListener {
                isHide = true
                isActive = true
                notifyDataSetChanged()
                baseButtons.visibility = View.GONE
                group.visibility = View.VISIBLE
            }

            Log.e("TAG", "isHide " + isHide)
            if (isHide){
                baseButtons.visibility = View.GONE
                group.visibility = View.VISIBLE
            } else {
                baseButtons.visibility = View.VISIBLE
                group.visibility = View.GONE
            }

            Log.e("TAG", "counter " + counter)
        }


    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {

    }

    class TextScrollViewHolder(v: View?) : ViewHolder(v) {
        var textTitle0 : AppCompatTextView
        var textTitle1 : AppCompatTextView
        var textTitle2 : AppCompatTextView
        var textTitle3 : AppCompatTextView
        var textTitle4 : AppCompatTextView
        var textTitle5 : AppCompatTextView

        var imageLogo : AppCompatImageView

        var timeline1 : TimelineView
        var timeline2 : TimelineView
        var timeline3 : TimelineView
        var timeline4 : TimelineView
        var timeline5 : TimelineView

        var ivCross : AppCompatImageView
        var ivPlayback : AppCompatImageView
        var ivPlaynext : AppCompatImageView
        var ivPlayPause : AppCompatImageView
        var textPlay : AppCompatTextView
        var seekbar : SeekBar


        var baseButtons : ConstraintLayout
        var group : Group

        var ivUp : AppCompatImageView
        var ivAudio : AppCompatImageView
        var ivSearch : AppCompatImageView

        init {
            textTitle0 = itemView.findViewById<AppCompatTextView>(com.vegasega.amnews.R.id.textTitle0)
            textTitle1 = itemView.findViewById<AppCompatTextView>(com.vegasega.amnews.R.id.textTitle1)
            textTitle2 = itemView.findViewById<AppCompatTextView>(com.vegasega.amnews.R.id.textTitle2)
            textTitle3 = itemView.findViewById<AppCompatTextView>(com.vegasega.amnews.R.id.textTitle3)
            textTitle4 = itemView.findViewById<AppCompatTextView>(com.vegasega.amnews.R.id.textTitle4)
            textTitle5 = itemView.findViewById<AppCompatTextView>(com.vegasega.amnews.R.id.textTitle5)

            imageLogo = itemView.findViewById<AppCompatImageView>(com.vegasega.amnews.R.id.imageLogo)

            timeline1 = itemView.findViewById<TimelineView>(com.vegasega.amnews.R.id.timeline1)
            timeline2 = itemView.findViewById<TimelineView>(com.vegasega.amnews.R.id.timeline2)
            timeline3 = itemView.findViewById<TimelineView>(com.vegasega.amnews.R.id.timeline3)
            timeline4 = itemView.findViewById<TimelineView>(com.vegasega.amnews.R.id.timeline4)
            timeline5 = itemView.findViewById<TimelineView>(com.vegasega.amnews.R.id.timeline5)

            ivCross = itemView.findViewById<AppCompatImageView>(com.vegasega.amnews.R.id.ivCross)
            ivPlayback = itemView.findViewById<AppCompatImageView>(com.vegasega.amnews.R.id.ivPlayback)
            ivPlaynext = itemView.findViewById<AppCompatImageView>(com.vegasega.amnews.R.id.ivPlaynext)
            ivPlayPause = itemView.findViewById<AppCompatImageView>(com.vegasega.amnews.R.id.ivPlayPause)
            textPlay = itemView.findViewById<AppCompatTextView>(com.vegasega.amnews.R.id.textPlay)
            seekbar = itemView.findViewById<SeekBar>(com.vegasega.amnews.R.id.seekbar)


            baseButtons = itemView.findViewById<ConstraintLayout>(com.vegasega.amnews.R.id.baseButtons)
            group = itemView.findViewById<Group>(com.vegasega.amnews.R.id.group)

            ivUp = itemView.findViewById<AppCompatImageView>(com.vegasega.amnews.R.id.ivUp)
            ivAudio = itemView.findViewById<AppCompatImageView>(com.vegasega.amnews.R.id.ivAudio)
            ivSearch = itemView.findViewById<AppCompatImageView>(com.vegasega.amnews.R.id.ivSearch)
        }
    }



    @SuppressLint("NotifyDataSetChanged")
    fun updatePosition(position: Int) {
        counter = position
       // notifyDataSetChanged()
    }


    fun submitData(itemMainArray: ArrayList<Item>) {
        list = itemMainArray
    }

}
