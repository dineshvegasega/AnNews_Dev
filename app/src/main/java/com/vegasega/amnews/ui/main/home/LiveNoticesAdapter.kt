package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.ItemLoadingBinding
import com.vegasega.amnews.databinding.Lay3Binding
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.ui.mainActivity.MainActivity
import com.vegasega.amnews.BR

class LiveNoticesAdapter(private val listener: OnItemClickListener,
                         textToSpeechVoice: TextToSpeech
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var isActive = false
    var isHide = false
    var counter = 0
    var counterChild = 0

    var textToSpeech: TextToSpeech = textToSpeechVoice

    var itemModels: MutableList<Item> = ArrayList()

    var mp: MediaPlayer

    init {
        mp = MediaPlayer.create(MainActivity.context.get(), R.raw.sound_3)
    }

    private val item: Int = 0
    private val loading: Int = 1

    private var isLoadingAdded: Boolean = false
    private var retryPageLoad: Boolean = false

    private var errorMsg: String? = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  if(viewType == item){
            val binding: Lay3Binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.lay3, parent, false)
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


    class TopMoviesVH(binding: Lay3Binding) : RecyclerView.ViewHolder(binding.root) {
        var itemRowBinding: Lay3Binding = binding
        fun bind(obj: Any?,  position: Int) {
            itemRowBinding.setVariable(BR._all, obj)
            itemRowBinding.executePendingBindings()
            val dataClass = obj as Item
            itemRowBinding.apply {

                if (dataClass.isAdd == true){
                    textTitle0.text = dataClass.name
                } else {
                    textTitle0.text = "Topic "+dataClass.notice_id
                }


//                dataClass.notice_image?.url?.glideImage(itemRowBinding.root.context, ivMap)
//                textTitle.setText(dataClass.name)
//                textDesc.setText(dataClass.description)
//
////                textHeaderTxt4.setText(if (dataClass.status == "Active") root.context.resources.getString(R.string.live) else root.context.resources.getString(R.string.expired))
////                textHeaderTxt4.backgroundTintList = if (dataClass.status == "Active") ContextCompat.getColorStateList(root.context,R.color._138808) else ContextCompat.getColorStateList(root.context,R.color._F02A2A)
//
//
//                root.singleClick {
////                    if (dataClass.user_scheme_status == "applied"){
//                    if(networkFailed) {
//                        viewModel.viewDetail(dataClass, position = position, root, 1)
//                    } else {
//                        root.context.callNetworkDialog()
//                    }
////                    }else{
////                        viewModel.viewDetail(""+dataClass.scheme_id, position = position, root, 2)
////                    }
//                }

            }
        }


    }

    class LoadingVH(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root) {
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
    }



}