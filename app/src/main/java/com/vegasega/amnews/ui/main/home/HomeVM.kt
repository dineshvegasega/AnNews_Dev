package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.CardItemViewBinding
import com.vegasega.amnews.databinding.ItemHomeHorizontalMenusBinding
import com.vegasega.amnews.genericAdapter.GenericAdapter
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.models.ItemList
import com.vegasega.amnews.models.ItemMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import java.security.AccessController.getContext
import javax.inject.Inject


@HiltViewModel
class HomeVM @Inject constructor() : ViewModel() {

//    val adapter by lazy { VerticalViewPagerAdapter(this) }


//    var itemSongs = MutableLiveData<Int>()

    var itemMenusArray : ArrayList<ItemMenu> = ArrayList()

    var itemMain : ArrayList<Item> = ArrayList()

    init {
        itemMenusArray.add(ItemMenu("Latest", R.drawable.icon_star, false))
        itemMenusArray.add(ItemMenu("Trending", R.drawable.icon_trending, false))
        itemMenusArray.add(ItemMenu("Your feed", R.drawable.icon_feed, false))
        itemMenusArray.add(ItemMenu("Featured", R.drawable.icon_featured, false))
        itemMenusArray.add(ItemMenu("Saved", R.drawable.icon_saved, false))

        itemMain?.add(Item("समाचार प्रस्तुतकर्ता, अर्थव्यवस्था, राजनीति और खेल से संबंधित नवीनतम समाचारों और घटनाक्रमों की जानकारी पेश करते हैं।",R.drawable.m1, false,
//        itemMain?.add(Item("then only text is centered",R.drawable.m1,
        arrayListOf(
                ItemList("I can easily put icon"),
                ItemList("but if I set gravity to center") ,
                ItemList("then only text is centered"),
                ItemList("but no icon"),
                ItemList(" my drawableLeft icon"),
            )))
        itemMain?.add(Item("B",R.drawable.m1, false,
            arrayListOf(
                ItemList("1"),
                ItemList("2") ,
                ItemList("3"),
                ItemList("4"),
                ItemList("5"),
            )))
        itemMain?.add(Item("C",R.drawable.m1, false,
            arrayListOf(
                ItemList("1C"),
                ItemList("2C") ,
                ItemList("3C"),
                ItemList("4C"),
                ItemList("5C"),
            )))
        itemMain?.add(Item("D",R.drawable.m1, false,
            arrayListOf(
                ItemList("put icon"),
                ItemList("set gravity") ,
                ItemList("only text"),
                ItemList("no icon"),
                ItemList("icon"),
            )))
        itemMain?.add(Item("E",R.drawable.m1, false,
            arrayListOf(
                ItemList("I can icon"),
                ItemList("but gravity to center") ,
                ItemList("then centered"),
                ItemList("but icon"),
                ItemList("my icon"),
            )))
        itemMain?.add(Item("F",R.drawable.m1, false,
            arrayListOf(
                ItemList("I put icon"),
                ItemList("I set gravity center") ,
                ItemList("then centered"),
                ItemList("icon icon"),
                ItemList("my icon"),
            )))

    }



    val dashboardAdapter = object : GenericAdapter<ItemHomeHorizontalMenusBinding, ItemMenu>() {
        override fun onCreateView(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) = ItemHomeHorizontalMenusBinding.inflate(inflater, parent, false)

        override fun updatePosition(position: Int) {
        }

        @SuppressLint("SuspiciousIndentation")
        override fun onBindHolder(
            binding: ItemHomeHorizontalMenusBinding,
            dataClass: ItemMenu,
            position: Int
        ) {
            binding.apply {
//                if(dataClass.isNew == true){
//                    animationView.visibility = View.VISIBLE
//                    textDotTxt.visibility = View.VISIBLE
//                    layoutBottomRed.visibility = View.VISIBLE
//                } else {
//                    animationView.visibility = View.GONE
//                    textDotTxt.visibility = View.GONE
//                    layoutBottomRed.visibility = View.GONE
//                }

                binding.ivIconCenter.setBackgroundResource(dataClass.image)
//                binding.ivIcon.setColorFilter(ContextCompat.getColor(binding.root.context, R.color._E70932), android.graphics.PorterDuff.Mode.MULTIPLY);

                binding.ivIcon.setColorFilter(
                    ContextCompat.getColor(
                        binding.root.context,
                        if (dataClass.isSelected) R.color._E70932 else R.color._8998A3
                    )
                )

              //  binding.ivIcon.setBackgroundResource(if (dataClass.isSelected == true) R.drawable.fade_golden_fill else R.drawable.gray_fill_round)
                root.setOnClickListener {
                    val list = currentList
                    list.forEach {
                        it.isSelected = dataClass == it
                    }
                    notifyDataSetChanged()
                }

            }

        }
    }



    val verticalAdapter = object : GenericAdapter<CardItemViewBinding, Item>() {
//        private val listener: OnItemClickListener


        init {
            Log.e("TAG", "init2")

        }
        var positionInt = 0
        override fun onCreateView(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) = CardItemViewBinding.inflate(inflater, parent, false)

        @SuppressLint("SuspiciousIndentation")
        override fun onBindHolder(
            binding: CardItemViewBinding,
            model: Item,
            position: Int
        ) {
            binding.apply {
//                if(dataClass.isNew == true){
//                    animationView.visibility = View.VISIBLE
//                    textDotTxt.visibility = View.VISIBLE
//                    layoutBottomRed.visibility = View.VISIBLE
//                } else {
//                    animationView.visibility = View.GONE
//                    textDotTxt.visibility = View.GONE
//                    layoutBottomRed.visibility = View.GONE
//                }

                textTitle0.text = ""+model.name
                textTitle1.text = ""+model.itemList[0].name
                textTitle2.text = ""+model.itemList[1].name
                textTitle3.text = ""+model.itemList[2].name
                textTitle4.text = ""+model.itemList[3].name
                textTitle5.text = ""+model.itemList[4].name

                imageLogo.setImageResource(model.image)

                timeline1.initLine(1)
                timeline2.initLine(0)
                timeline3.initLine(0)
                timeline4.initLine(0)
                timeline5.initLine(2)

                ivPlayback.setOnClickListener {
//                    listener.onClickItem()
                }
                ivPlaynext.setOnClickListener {

                }

                ivPlayPause.setOnClickListener {
                }

                if(position == positionInt){
                    Log.e("TAG", "QQQQQQQ "+position)

                } else {
                    Log.e("TAG", "WWWWWWW "+position)
                }

            }

        }


        @SuppressLint("NotifyDataSetChanged")
        override fun updatePosition(position: Int) {
            positionInt = position
           // Log.e("TAG", "BBBBBBB "+positionInt)


        }
    }



}