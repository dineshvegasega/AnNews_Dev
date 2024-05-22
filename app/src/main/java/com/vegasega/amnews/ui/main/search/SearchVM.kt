package com.vegasega.amnews.ui.main.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.ItemSearchBinding
import com.vegasega.amnews.genericAdapter.GenericAdapter
import com.vegasega.amnews.models.BaseResponseDC
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.models.ItemList
import com.vegasega.amnews.models.ItemMain
import com.vegasega.amnews.ui.main.home.HomeVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchVM @Inject constructor() : ViewModel() {

    //    val adapter by lazy { SearchAdapter(this) }
    var itemMain: ArrayList<Item> = ArrayList()

    init {
        itemMain?.add(
            Item(
                "I", R.drawable.m1,false,
                arrayListOf(
                    ItemList("I can easily put icon"),
                    ItemList("but if I set gravity to center"),
                    ItemList("then only text is centered"),
                    ItemList("but no icon"),
                    ItemList(" my drawableLeft icon"),
                )
            )
        )
        itemMain?.add(
            Item(
                "J", R.drawable.m1,false,
                arrayListOf(
                    ItemList("1"),
                    ItemList("2"),
                    ItemList("3"),
                    ItemList("4"),
                    ItemList("5"),
                )
            )
        )
        itemMain?.add(
            Item(
                "K", R.drawable.m1,false,
                arrayListOf(
                    ItemList("1C"),
                    ItemList("2C"),
                    ItemList("3C"),
                    ItemList("4C"),
                    ItemList("5C"),
                )
            )
        )
    }


    val searchAdapter = object : GenericAdapter<ItemSearchBinding, Item>() {
        override fun onCreateView(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) = ItemSearchBinding.inflate(inflater, parent, false)

        override fun updatePosition(position: Int) {
            
        }

        @SuppressLint("SuspiciousIndentation")
        override fun onBindHolder(
            binding: ItemSearchBinding,
            dataClass: Item,
            position: Int
        ) {
            binding.apply {
                root.setOnClickListener {
                    root.findNavController().navigate(R.id.action_search_to_home, Bundle().apply {
                        putParcelable("key", dataClass)
                    })
                }

            }
        }
    }


}