package com.vegasega.amnews.ui

//import com.vegasega.amnews.ui.main.home.CenterHome.Companion.viewss
import android.database.DataSetObservable
import android.database.DataSetObserver
import android.media.AudioManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.CardItemViewBinding
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.ui.mainActivity.MainActivity
import com.vegasega.amnews.utils.mainThread
import kotlinx.coroutines.delay


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

    var views: SparseArray<View> = SparseArray()

    private val mObservable = DataSetObservable()
    private val mViewPagerObserver: DataSetObserver? = null

//    var mLayoutInflater: LayoutInflater? = null
   //var mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    lateinit var containerView : ViewGroup

    override fun getCount(): Int {
        return list.size
    }


    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
        super.restoreState(state, loader)
        Log.e("TAG", "restoreState " + counter)
    }

    override fun startUpdate(container: ViewGroup) {
        super.startUpdate(container)
        Log.e("TAG", "startUpdate " + counter)
    }

    override fun finishUpdate(container: ViewGroup) {
        super.finishUpdate(container)
        Log.e("TAG", "finishUpdate " + counter)
//        val bindingChild = CardItemViewBinding.inflate(LayoutInflater.from(MainActivity.context.get()), container, false)
//        bindingChild.apply {
//            textTitle0.visibility = View.GONE
//        }
       // val bindingChild = CardItemViewBinding.inflate(LayoutInflater.from(MainActivity.context.get()), container, false)


    }

    override fun notifyDataSetChanged() {
//        super.notifyDataSetChanged()
        Log.e("TAG", "notifyDataSetChanged " + counter)
//        instantiateItem(containerView, counter)

    }

    override fun isViewFromObject(view: View, `objecta`: Any): Boolean {
//        return view === `object`
        return view.equals(objecta)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        containerView = container
        val bindingChild = CardItemViewBinding.inflate(LayoutInflater.from(MainActivity.context.get()), container, false)
        Log.e("TAG", "counterAA " + counter)



//        if (mContext is ActivityClass && position === 0) //The page listener in ActivityClass //is trigger only when page is changed so i tried to hack it when the viewPager is //instantiate for the first time
//            return (mContext as ActivityClass).viewPagerHandler(itemView, position)


//        bindingChild.apply {
//            root.singleClick {
//                Log.e("TAG", "mainLayout")
//                listener.onClickMain()
//            }
//
//            val model = list[position]
//            textTitle0.text = "" + model.name
//            textTitle1.text = "" + model.itemList[0].name
//            textTitle2.text = "" + model.itemList[1].name
//            textTitle3.text = "" + model.itemList[2].name
//            textTitle4.text = "" + model.itemList[3].name
//            textTitle5.text = "" + model.itemList[4].name
//
//            imageLogo.setImageResource(model.image)
//
//            timeline1.initLine(1)
//            timeline2.initLine(0)
//            timeline3.initLine(0)
//            timeline4.initLine(0)
//            timeline5.initLine(2)
//
//            Log.e("TAG", "counterAA " + counter)
//
//            if (position == counter) {
////                Log.e("TAG", "QQQQQQQ " + position)
//                counterChild = 0
//                if (isActive == true){
//                    playSong(model, bindingChild)
//                }
//            } else {
////                Log.e("TAG", "WWWWWWW " + position)
//                if (textToSpeech.isSpeaking) {
//                    textToSpeech.stop()
//                    ivPlayPause.setImageResource(R.drawable.play)
//                }
//            }
//
//
//
//            ivPlayback.setOnClickListener {
//                if(counter != 0){
//                    listener.onClickItem(counter - 1)
////                    Log.e("TAG", "ivPlayback "+counter)
//                }
//            }
//
//            ivPlaynext.setOnClickListener {
//                if(counter != list.size - 1){
//                    listener.onClickItem(counter + 1)
////                    Log.e("TAG", "ivPlaynext "+counter)
//                }
//            }
//
//
//            ivPlayPause.setOnClickListener {
//                if (textToSpeech.isSpeaking) {
//                    textToSpeech.stop()
//                    isActive = false
//                    ivPlayPause.setImageResource(R.drawable.play)
//                } else {
//                    isActive = true
//                    playSong(model, bindingChild)
//                    ivPlayPause.setImageResource(R.drawable.pause)
//                }
//            }
//
//
//            ivCross.setOnClickListener {
//                isHide = false
//                if (textToSpeech.isSpeaking) {
//                    textToSpeech.stop()
//                    isActive = false
//                }
////                adapter
////                binding.introViewPager.adapter?.instantiateItem(binding.introViewPager, binding.introViewPager.currentItem)
////                notifyItemChanged(counter)
////                adapter.notifyDataSetChanged()
////                CenterHome.binding.introViewPager.invalidate()
//                baseButtons.visibility = View.VISIBLE
//                group.visibility = View.GONE
//            }
//
//            ivAudio.setOnClickListener {
//                isHide = true
//                isActive = true
////                binding.introViewPager.adapter?.instantiateItem(binding.introViewPager, binding.introViewPager.currentItem)
////                notifyItemChanged(counter)
////                adapter.notifyDataSetChanged()
//                baseButtons.visibility = View.GONE
//                group.visibility = View.VISIBLE
//            }
//
//
//            if (isHide){
//                baseButtons.visibility = View.GONE
//                group.visibility = View.VISIBLE
//            } else {
//                baseButtons.visibility = View.VISIBLE
//                group.visibility = View.GONE
//            }
//
//
//            ivUp.setOnClickListener {
//                isActive = false
//                listener.onClickItemUp(0)
//            }
//
//            ivSearch.setOnClickListener {
//                it.findNavController().navigate(R.id.action_mainHome_to_search)
//            }
//
//        }









        container.addView(bindingChild.root)
        return bindingChild.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as LinearLayout)
        container.removeView(`object` as View)
    }




    private fun playSong(model: Item, binding : CardItemViewBinding) {

        binding.apply {
            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
            }

            ivPlayPause.setImageResource(R.drawable.pause)
            Handler(Looper.myLooper()!!).postDelayed({
                seekbar.max = 5
                seekbar.progress = 0
                textPlay.text = "H"
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

    }


    private fun playSongChild(itemMain: Item, binding : CardItemViewBinding) {
        binding.apply {
            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
            }


            val bundle = Bundle()
            bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM,3)
            textPlay.text = "0"+(counterChild+1)
            seekbar.max = 5
            seekbar.progress = counterChild+1
            textToSpeech.speak(itemMain.itemList[counterChild].name, TextToSpeech.QUEUE_FLUSH, null, itemMain.itemList[counterChild].name)
            textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onDone(utteranceId: String) {
//                        Log.e("MainActivity", "TTS onDone " + utteranceId);
                    if (itemMain.itemList[counterChild].name == utteranceId) {
                        if (counterChild != itemMain.itemList.size - 1) {
                            counterChild ++
                            playSongChild(itemMain, binding)
                        } else {
                            MainActivity.activity.get()?.runOnUiThread {
                                counterChild = 0
                                binding.ivPlayPause.setImageResource(R.drawable.play)
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



    fun updatePosition(position: Int) {
        counter = position
//        instantiateItem(viewss, position)
//        notifyItemChanged(counter)
        notifyDataSetChanged()
//        adapter.notifyDataSetChanged();
        Log.e("TAG", "updatePosition "+position)
//        registerDataSetObserver()
    }

    fun submitData(itemMainArray: ArrayList<Item>) {
        list = itemMainArray
    }

}
