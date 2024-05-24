package com.vegasega.amnews.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.CardItemViewBinding
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.ui.main.home.CenterHome
import com.vegasega.amnews.ui.main.home.CenterHome.Companion.binding
import com.vegasega.amnews.ui.mainActivity.MainActivity

/**
 * Created by rizvan on 12/13/16.
 */
class VerticlePagerAdapter(
    private val listener: OnItemClickListener,
    textToSpeechVoice: TextToSpeech
) : PagerAdapter() {

    private var list : ArrayList<Item> = ArrayList()

    var isActive = false
    var isHide = false
    var counter = 0
    var counterChild = 0

    var textToSpeech: TextToSpeech = textToSpeechVoice

//    var mLayoutInflater: LayoutInflater =
//        MainActivity.context.get()?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === (`object` as LinearLayout)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val itemView = mLayoutInflater.inflate(R.layout.card_item_view, container, false)
        val bindingChild = CardItemViewBinding.inflate(LayoutInflater.from(MainActivity.context.get()), null, false)

        Log.e("TAG", "positionXX "+counter)
        bindingChild.apply {


//            if (position == 0 && counter == 1) {
//                updatePosition(2);
//                notifyDataSetChanged();
//            }
//            if (position == 1 && counter == 2) {
//                updatePosition(3);
//                notifyDataSetChanged();
//            }

            ivCross.setOnClickListener {
                isHide = false
                isActive = false

                notifyDataSetChanged()
                //binding.introViewPager.adapter?.instantiateItem(binding.introViewPager, binding.introViewPager.currentItem + 1)
//                notifyItemChanged(counter)
//                adapter.notifyDataSetChanged()
//                CenterHome.binding.introViewPager.invalidate()
                baseButtons.visibility = View.VISIBLE
                group.visibility = View.GONE
            }

            ivAudio.setOnClickListener {
                isHide = true
                isActive = true
                notifyDataSetChanged()
                //binding.introViewPager.adapter?.instantiateItem(binding.introViewPager, binding.introViewPager.currentItem + 1)
//                notifyItemChanged(counter)
//                adapter.notifyDataSetChanged()
                baseButtons.visibility = View.GONE
                group.visibility = View.VISIBLE
            }


            if (isHide){
                baseButtons.visibility = View.GONE
                group.visibility = View.VISIBLE
            } else {
                baseButtons.visibility = View.VISIBLE
                group.visibility = View.GONE
            }


        }


        container.addView(bindingChild.root)
        return bindingChild.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }



    fun updatePosition(position: Int) {
        counter = position
//        var ddd = CenterHome.viewss
//        if(position == 0){
//            val LinearLayout : VerticalViewPager = CenterHome.viewVertical
//            destroyItem(CenterHome.viewVertical, position, LinearLayout)
//            instantiateItem(CenterHome.viewVertical, position)
//        } else {
//            //notifyDataSetChanged()
//        }
//        instantiateItem(CenterHome.viewss, position)
//        notifyItemChanged(counter)
//        notifyDataSetChanged()
//        adapter.notifyDataSetChanged();
        Log.e("TAG", "updatePosition "+position)
    }

    fun submitData(itemMainArray: ArrayList<Item>) {
        list = itemMainArray
    }

}
