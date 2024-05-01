package com.vegasega.amnews.ui.main.home

import androidx.lifecycle.ViewModel
import com.vegasega.amnews.R
import com.vegasega.amnews.ui.mainActivity.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor() : ViewModel() {

    var itemMain : ArrayList<Item> ?= ArrayList()

    init {
        itemMain?.add(Item(MainActivity.context.get()!!.getString(R.string.something_went_wrong), MainActivity.context.get()!!.getString(R.string.something_went_wrong),R.drawable.m1))
        itemMain?.add(Item(MainActivity.context.get()!!.getString(R.string.something_went_wrong), MainActivity.context.get()!!.getString(R.string.something_went_wrong),R.drawable.m1))
        itemMain?.add(Item(MainActivity.context.get()!!.getString(R.string.something_went_wrong), MainActivity.context.get()!!.getString(R.string.something_went_wrong),R.drawable.m1))
    }

    data class Item (
        var name: String = "",
        var desc: String = "",
        var image: Int = 0
    )
}