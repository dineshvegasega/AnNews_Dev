package com.vegasega.amnews.ui.onboarding.topics

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.ItemTopicsBinding
import com.vegasega.amnews.genericAdapter.GenericAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicsVM @Inject constructor() : ViewModel() {


    val verticalAdapter = object : GenericAdapter<ItemTopicsBinding, Item>() {
        init {
            Log.e("TAG", "init2")
        }

        var positionInt = 0
        override fun onCreateView(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) = ItemTopicsBinding.inflate(inflater, parent, false)

        @SuppressLint("SuspiciousIndentation")
        override fun onBindHolder(
            binding: ItemTopicsBinding,
            model: Item,
            position: Int
        ) {
            binding.apply {
                ivLogo.setImageResource(model.id)
                textHeaderTxt.text = model.name
            }

        }


        @SuppressLint("NotifyDataSetChanged")
        override fun updatePosition(position: Int) {
            positionInt = position
            // Log.e("TAG", "BBBBBBB "+positionInt)

        }
    }


}


data class Item(val id: Int, val name: String)