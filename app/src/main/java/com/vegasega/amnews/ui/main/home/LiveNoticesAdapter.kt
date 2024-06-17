package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vegasega.amnews.BR
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.ItemLoadingBinding
import com.vegasega.amnews.databinding.Lay3Binding
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.ui.mainActivity.MainActivity
import com.vegasega.amnews.utils.mainThread
import com.vegasega.amnews.utils.singleClick
import kotlinx.coroutines.delay


class LiveNoticesAdapter(
    private val listener: OnItemClickListener,
    textToSpeechVoice: TextToSpeech
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var isActive = false
    var isHide = false
    var counter = 0
    var counterChild = 0

    var textToSpeech: TextToSpeech = textToSpeechVoice

    var itemModels: MutableList<Item> = ArrayList()

    var isPlayChild = 0


//    var mp:MediaPlayer = mp1
//
//
//    init {
//        mp = MediaPlayer.create(MainActivity.context.get(), R.raw.sound_22)
//    }


    lateinit var itemRowBinding2: Lay3Binding


    private val item: Int = 0
    private val loading: Int = 1

    private var isLoadingAdded: Boolean = false
    private var retryPageLoad: Boolean = false

    private var errorMsg: String? = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  if(viewType == item){
            val binding: Lay3Binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.lay3, parent, false)
            itemRowBinding2 = binding
            TopMoviesVH(binding)
        }else{
            val binding: ItemLoadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_loading, parent, false)
            LoadingVH(binding)
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = itemModels[position]
        if(getItemViewType(position) == item){

            val myOrderVH: TopMoviesVH = holder as TopMoviesVH
//            myOrderVH.itemRowBinding.movieProgress.visibility = View.VISIBLE
            myOrderVH.bind(model, position)
        }else{
            val loadingVH: LoadingVH = holder as LoadingVH
            if (retryPageLoad) {
                loadingVH.itemRowBinding.loadmoreProgress.visibility = View.GONE
            } else {
                loadingVH.itemRowBinding.loadmoreProgress.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return if (itemModels.size > 0) itemModels.size else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0){
            item
        }else {
            if (position == itemModels.size - 1 && isLoadingAdded) {
                loading
            } else {
                item
            }
        }
    }


    inner class TopMoviesVH(binding: Lay3Binding) : RecyclerView.ViewHolder(binding.root) {
        var itemRowBinding: Lay3Binding = binding

        @SuppressLint("NotifyDataSetChanged", "SetTextI18n", "ClickableViewAccessibility")
        fun bind(obj: Any?, position: Int) {
            itemRowBinding.setVariable(BR._all, obj)
            itemRowBinding.executePendingBindings()
            val model = obj as Item
            itemRowBinding.apply {
                if (isHide) {
                    baseButtons.visibility = View.GONE
                    group.visibility = View.VISIBLE
                } else {
                    baseButtons.visibility = View.VISIBLE
                    group.visibility = View.GONE
                }

                itemView.singleClick {
                    Log.e("TAG", "mainLayout")
                    listener.onClickMain()
                }

                textTitle0.text = "" + model.title
                textTitle1.text = "" + model.itemList[0].name
                textTitle2.text = "" + model.itemList[1].name
                textTitle3.text = "" + model.itemList[2].name
                textTitle4.text = "" + model.itemList[3].name
                textTitle5.text = "" + model.itemList[4].name

                if (model.isAdd == false) {
                    imageLogo.setImageResource(model.image)
                    imageFull.visibility = View.GONE
                    layoutAll.visibility = View.VISIBLE
                } else {
                    if (model.isAddBig == false) {
                        imageLogo.setImageResource(model.image)
                        layoutAll.visibility = View.VISIBLE
                        imageFull.visibility = View.GONE
                    } else {
                        imageFull.setImageResource(model.image)
                        layoutAll.visibility = View.GONE
                        imageFull.visibility = View.VISIBLE
                    }
                }

                seekbar.isFocusableInTouchMode = false


                timeline1.initLine(1)
//        holder.timeline1.initLine(0)
                timeline2.initLine(0)
                timeline3.initLine(0)
                timeline4.initLine(0)
//        holder.timeline5.initLine(0)
                timeline5.initLine(2)

                timeline1.marker = ContextCompat.getDrawable(root.context, R.drawable.ellipse_grey)
                timeline1.setStartLineColor(ContextCompat.getColor(root.context, R.color._C4C4C4), 1)
                timeline1.setEndLineColor(ContextCompat.getColor(root.context, R.color._C4C4C4), 1)

                timeline2.marker = ContextCompat.getDrawable(root.context, R.drawable.ellipse_grey)
                timeline2.setStartLineColor(ContextCompat.getColor(root.context, R.color._C4C4C4), 0)
                timeline2.setEndLineColor(ContextCompat.getColor(root.context, R.color._C4C4C4), 0)

                timeline3.marker = ContextCompat.getDrawable(root.context, R.drawable.ellipse_grey)
                timeline3.setStartLineColor(ContextCompat.getColor(root.context, R.color._C4C4C4), 0)
                timeline3.setEndLineColor(ContextCompat.getColor(root.context, R.color._C4C4C4), 0)

                timeline4.marker = ContextCompat.getDrawable(root.context, R.drawable.ellipse_grey)
                timeline4.setStartLineColor(ContextCompat.getColor(root.context, R.color._C4C4C4), 0)
                timeline4.setEndLineColor(ContextCompat.getColor(root.context, R.color._C4C4C4), 0)

                timeline5.marker = ContextCompat.getDrawable(root.context, R.drawable.ellipse_grey)
                timeline5.setStartLineColor(ContextCompat.getColor(root.context, R.color._C4C4C4), 2)
                timeline5.setEndLineColor(ContextCompat.getColor(root.context, R.color._C4C4C4), 2)


                if (position == counter) {
                    Log.e("TAG", "QQQQQQQ " + position)
                    counterChild = 0
                    Handler(Looper.myLooper()!!).postDelayed({
                        if (isActive == true) {

                            if (model.isAdd == false) {
                                playSong(model, itemRowBinding)
                            } else {
                                if (model.isAddBig == false) {
                                    playSong(model, itemRowBinding)
                                } else {
                                    MainActivity.activity.get()?.runOnUiThread {
                                        counterChild = 0
                                        ivPlayPause.setImageResource(R.drawable.play)
                                        mainThread {
                                            Log.e("MainActivity", "counterChilddelay " + counterChild);
                                            delay(3000)
//                                try {
//                                    mp.start()
//                                } catch (_: IOException) {
//                                }
//                                delay(500)
                                            listener.onClickItem(counter + 1)
                                        }
                                    }
                                }
                            }

                        }
                    }, 100)

                } else {
                    Log.e("TAG", "WWWWWWW " + position)
                    if (textToSpeech.isSpeaking) {
                        textToSpeech.stop()
                        ivPlayPause.setImageResource(R.drawable.play)
                    }
                }


                ivPlayback.setOnClickListener {
                    if (counter != 0) {
                        listener.onClickItem(counter - 1)
                        Log.e("TAG", "ivPlayback " + counter)
                    }
                }

                ivPlaynext.setOnClickListener {
                    if (counter != itemModels.size - 1) {
                        listener.onClickItem(counter + 1)
                        Log.e("TAG", "ivPlaynext " + counter)
                    }
                }


                ivPlayPause.setOnClickListener {
                    if (textToSpeech.isSpeaking) {
                        textToSpeech.stop()
                        isActive = false
                        ivPlayPause.setImageResource(R.drawable.play)
                    } else {
                        isActive = true
                        Handler(Looper.myLooper()!!).postDelayed({
                            if (isPlayChild == 1){
                                playSong(model, itemRowBinding)
                            } else if(isPlayChild == 2){
                                playSongChild(model, itemRowBinding)
                            }
                        }, 100)
                        ivPlayPause.setImageResource(R.drawable.pause)
                    }
                }


                ivCross.setOnClickListener {
                    isHide = false
                    if (textToSpeech.isSpeaking) {
                        textToSpeech.stop()
                    }
                    isActive = false
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





                ivUp.setOnClickListener {
                    isActive = false
                    listener.onClickItemUp(0)
                }

                ivSearch.setOnClickListener {
                    it.findNavController().navigate(R.id.action_home_to_search)
                }


            }
        }


    }

    inner class LoadingVH(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root) {
        var itemRowBinding: ItemLoadingBinding = binding
    }

    fun showRetry(show: Boolean, errorMsg: String) {
        retryPageLoad = show
        notifyItemChanged(itemModels.size - 1)
        this.errorMsg = errorMsg
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAllSearch(movies: MutableList<Item>) {
        itemModels.clear()
        itemModels.addAll(movies)
//        for(movie in movies){
//            add(movie)
//        }
        notifyDataSetChanged()
    }

    fun addAll(movies: MutableList<Item>) {
        for(movie in movies){
            add(movie)
        }
    }

    fun add(moive: Item) {
        itemModels.add(moive)
        notifyItemInserted(itemModels.size - 1)
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
//        add(ItemLiveScheme())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

//        val position: Int =itemModels.size -1
//        val movie: ItemLiveScheme = itemModels[position]
//
//        if(movie != null){
//            itemModels.removeAt(position)
//            notifyItemRemoved(position)
//        }
    }


    fun submitData(itemMainArray: ArrayList<Item>) {
        itemModels = itemMainArray
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updatePosition(position: Int) {
        counter = position
        Log.e("TAG", "updatePosition " + position)
//        itemRowBinding2.apply {
//            if (isHide) {
//                baseButtons.visibility = View.GONE
//                group.visibility = View.VISIBLE
//            } else {
//                baseButtons.visibility = View.VISIBLE
//                group.visibility = View.GONE
//            }
//        }
//        notifyItemChanged(position)

    }







    @SuppressLint("SetTextI18n")
    private fun playSong(model: Item, holder: Lay3Binding) {
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }


        holder.ivPlayPause.setImageResource(R.drawable.pause)
//        Handler(Looper.myLooper()!!).postDelayed({
            holder.seekbar.max = 5
            holder.seekbar.progress = 0
            holder.textPlay.text = "H"
//            when (model.lang){
//                "en" -> textToSpeech.setLanguage(Locale("en","US"))
//                "hi" -> textToSpeech.setLanguage(Locale("hi","IN"))
//            }
            Handler(Looper.myLooper()!!).postDelayed({
                isPlayChild = 1
                textToSpeech.speak(model.title, TextToSpeech.QUEUE_FLUSH, null, model.title)
            }, 600)

            textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onDone(utteranceId: String) {
                    if (model.title == utteranceId) {
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
//        }, 50)
    }


    @SuppressLint("SetTextI18n")
    private fun playSongChild(itemMain: Item, holder: Lay3Binding) {
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }


        holder.textPlay.text = "0" + (counterChild + 1)
        holder.seekbar.max = 5
        holder.seekbar.progress = counterChild + 1

        when(counterChild){
            0 -> {
                holder.timeline1.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
            }
            1 -> {
                holder.timeline1.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline1.setEndLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 1)

                holder.timeline2.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline2.setStartLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)
            }
            2 -> {
                holder.timeline1.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline1.setEndLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 1)

                holder.timeline2.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline2.setStartLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)
                holder.timeline2.setEndLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)

                holder.timeline3.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline3.setStartLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)
            }
            3 -> {
                holder.timeline1.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline1.setEndLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 1)

                holder.timeline2.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline2.setStartLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)
                holder.timeline2.setEndLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)

                holder.timeline3.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline3.setStartLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)
                holder.timeline3.setEndLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)

                holder.timeline4.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline4.setStartLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)
            }
            4 -> {
                holder.timeline1.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline1.setEndLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 1)

                holder.timeline2.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline2.setStartLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)
                holder.timeline2.setEndLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)

                holder.timeline3.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline3.setStartLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)
                holder.timeline3.setEndLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)

                holder.timeline4.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline4.setStartLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)
                holder.timeline4.setEndLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 0)

                holder.timeline5.marker = ContextCompat.getDrawable(holder.root.context, R.drawable.ellipse_black)
                holder.timeline5.setStartLineColor(ContextCompat.getColor(holder.root.context, R.color._138808), 2)
            }
        }

        isPlayChild = 2
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
                            isPlayChild = 0
                            holder.ivPlayPause.setImageResource(R.drawable.play)
                            mainThread {
//                                try {
//                                    mp.start()
//                                } catch (_: IOException) {
//                                }
                                Log.e("MainActivity", "counterChilddelay " + counterChild);
//                                try {
//                                   // val mp = MediaPlayer.create(MainActivity.context.get(), R.raw.sound_22)
//                                    if (mp.isPlaying) {
//                                        mp.stop()
////                                        mp.reset()
////                                        mp.release()
//                                    }
//                                    mp.start()
//                                } catch (_: IOException) {
//                                }
                                delay(100)
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