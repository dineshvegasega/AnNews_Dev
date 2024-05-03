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
        itemMain?.add(Item("A",R.drawable.m1,
            arrayListOf(
                ItemList("I can easily put icon"),
                ItemList("but if I set gravity to center") ,
                ItemList("then only text is centered"),
                ItemList("but no icon"),
                ItemList(" my drawableLeft icon"),
            )))
        itemMain?.add(Item("B",R.drawable.m1,
            arrayListOf(
                ItemList("1"),
                ItemList("2") ,
                ItemList("3"),
                ItemList("4"),
                ItemList("5"),
            )))
        itemMain?.add(Item("C",R.drawable.m1,
            arrayListOf(
                ItemList("1C"),
                ItemList("2C") ,
                ItemList("3C"),
                ItemList("4C"),
                ItemList("5C"),
            )))
        itemMain?.add(Item("D",R.drawable.m1,
            arrayListOf(
                ItemList("put icon"),
                ItemList("set gravity") ,
                ItemList("only text"),
                ItemList("no icon"),
                ItemList("icon"),
            )))
        itemMain?.add(Item("E",R.drawable.m1,
            arrayListOf(
                ItemList("I can icon"),
                ItemList("but gravity to center") ,
                ItemList("then centered"),
                ItemList("but icon"),
                ItemList("my icon"),
            )))
        itemMain?.add(Item("F",R.drawable.m1,
            arrayListOf(
                ItemList("I put icon"),
                ItemList("I set gravity center") ,
                ItemList("then centered"),
                ItemList("icon icon"),
                ItemList("my icon"),
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