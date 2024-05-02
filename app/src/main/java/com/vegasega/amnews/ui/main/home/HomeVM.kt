package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.ItemHomeHorizontalMenusBinding
import com.vegasega.amnews.genericAdapter.GenericAdapter
import com.vegasega.amnews.ui.mainActivity.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor() : ViewModel() {

    var itemMain : ArrayList<Item> = ArrayList()

    init {
        itemMain?.add(Item(MainActivity.context.get()!!.getString(R.string.something_went_wrong),R.drawable.m1,
            arrayListOf(
                ItemList("I can easily put icon"),
                ItemList("but if I set gravity to center") ,
                ItemList("then only text is centered"),
                ItemList("but no icon"),
                ItemList(" my drawableLeft icon"),
            )))
        itemMain?.add(Item(MainActivity.context.get()!!.getString(R.string.something_went_wrong),R.drawable.m1,
            arrayListOf(
                ItemList("I can easily put icon"),
                ItemList("but if I set gravity to center") ,
                ItemList("then only text is centered"),
                ItemList("but no icon"),
                ItemList(" my drawableLeft icon"),
            )))
        itemMain?.add(Item(MainActivity.context.get()!!.getString(R.string.something_went_wrong),R.drawable.m1,
            arrayListOf(
                ItemList("I can easily put icon"),
                ItemList("but if I set gravity to center") ,
                ItemList("then only text is centered"),
                ItemList("but no icon"),
                ItemList(" my drawableLeft icon"),
            )))
        itemMain?.add(Item(MainActivity.context.get()!!.getString(R.string.something_went_wrong),R.drawable.m1,
            arrayListOf(
                ItemList("I can easily put icon"),
                ItemList("but if I set gravity to center") ,
                ItemList("then only text is centered"),
                ItemList("but no icon"),
                ItemList(" my drawableLeft icon"),
            )))
        itemMain?.add(Item(MainActivity.context.get()!!.getString(R.string.something_went_wrong),R.drawable.m1,
            arrayListOf(
                ItemList("I can easily put icon"),
                ItemList("but if I set gravity to center") ,
                ItemList("then only text is centered"),
                ItemList("but no icon"),
                ItemList(" my drawableLeft icon"),
            )))
        itemMain?.add(Item(MainActivity.context.get()!!.getString(R.string.something_went_wrong),R.drawable.m1,
            arrayListOf(
                ItemList("I can easily put icon"),
                ItemList("but if I set gravity to center") ,
                ItemList("then only text is centered"),
                ItemList("but no icon"),
                ItemList(" my drawableLeft icon"),
            )))

    }



    val dashboardAdapter = object : GenericAdapter<ItemHomeHorizontalMenusBinding, Item>() {
        override fun onCreateView(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) = ItemHomeHorizontalMenusBinding.inflate(inflater, parent, false)

        @SuppressLint("SuspiciousIndentation")
        override fun onBindHolder(
            binding: ItemHomeHorizontalMenusBinding,
            dataClass: Item,
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

            }

        }
    }




    data class Item (
        var name: String = "",
        var image: Int = 0,
        var itemList: ArrayList<ItemList> = ArrayList()
    )


    data class ItemList (
        var name: String = ""
    )
}