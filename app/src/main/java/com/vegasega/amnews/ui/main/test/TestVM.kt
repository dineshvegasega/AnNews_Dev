package com.vegasega.amnews.ui.main.test

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.ItemHomeHorizontalMenusBinding
import com.vegasega.amnews.databinding.ItemLeftListBinding
import com.vegasega.amnews.genericAdapter.GenericAdapter
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.models.ItemList
import com.vegasega.amnews.models.ItemMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestVM @Inject constructor() : ViewModel() {
    var itemMenusArray : ArrayList<ItemMenu> = ArrayList()

    var itemMain : ArrayList<Item> = ArrayList()

    init {
        itemMenusArray.add(ItemMenu("Latest", R.drawable.icon_star, false))
        itemMenusArray.add(ItemMenu("Trending", R.drawable.icon_trending, false))
        itemMenusArray.add(ItemMenu("Your feed", R.drawable.icon_feed, false))
        itemMenusArray.add(ItemMenu("Featured", R.drawable.icon_featured, false))
        itemMenusArray.add(ItemMenu("Saved", R.drawable.icon_saved, false))

        itemMain?.add(
            Item("समाचार प्रस्तुतकर्ता, अर्थव्यवस्था, राजनीति और खेल से संबंधित नवीनतम समाचारों और घटनाक्रमों की जानकारी पेश करते हैं।",
                R.drawable.m1, true,
                false,
                false,
                "en",
                arrayListOf(
                    ItemList("I can easily put icon"),
                    ItemList("but if I set gravity to center") ,
                    ItemList("then only text is centered"),
                    ItemList("but no icon"),
                    ItemList(" my drawableLeft icon"),
                ))
        )
        itemMain?.add(
            Item("B", R.drawable.m1, false,
                false,
                false,
                "en",
                arrayListOf(
                    ItemList("1"),
                    ItemList("2") ,
                    ItemList("3"),
                    ItemList("4"),
                    ItemList("5"),
                ))
        )
        itemMain?.add(
            Item("C", R.drawable.m1, false,
                false,
                false,
                "en",
                arrayListOf(
                    ItemList("1C"),
                    ItemList("2C") ,
                    ItemList("3C"),
                    ItemList("4C"),
                    ItemList("5C"),
                ))
        )
        itemMain?.add(
            Item("D", R.drawable.m1, false,
                false,
                false,
                "en",
                arrayListOf(
                    ItemList("put icon"),
                    ItemList("set gravity") ,
                    ItemList("only text"),
                    ItemList("no icon"),
                    ItemList("icon"),
                ))
        )
        itemMain?.add(
            Item("E", R.drawable.m1, false,
                false,
                false,
                "en",
                arrayListOf(
                    ItemList("I can icon"),
                    ItemList("but gravity to center") ,
                    ItemList("then centered"),
                    ItemList("but icon"),
                    ItemList("my icon"),
                ))
        )
        itemMain?.add(
            Item("F", R.drawable.m1, false,
                false,
                false,
                "en",
                arrayListOf(
                    ItemList("I put icon"),
                    ItemList("I set gravity center") ,
                    ItemList("then centered"),
                    ItemList("icon icon"),
                    ItemList("my icon"),
                ))
        )

        itemMain?.add(
            Item("G", R.drawable.m1, false,
                false,
                false,
                "en",
                arrayListOf(
                    ItemList("I put icon"),
                    ItemList("I set gravity center") ,
                    ItemList("then centered"),
                    ItemList("icon icon"),
                    ItemList("my icon"),
                ))
        )


        itemMain?.add(
            Item("H", R.drawable.m1, false,
                false,
                false,
                "en",
                arrayListOf(
                    ItemList("I put icon"),
                    ItemList("I set gravity center") ,
                    ItemList("then centered"),
                    ItemList("icon icon"),
                    ItemList("my icon"),
                ))
        )

    }



    val dashboardAdapter = object : GenericAdapter<ItemLeftListBinding, Item>() {
        override fun onCreateView(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) = ItemLeftListBinding.inflate(inflater, parent, false)

        override fun updatePosition(position: Int) {
        }

        @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
        override fun onBindHolder(
            binding: ItemLeftListBinding,
            dataClass: Item,
            position: Int
        ) {
            binding.apply {

//                binding.layout.setBackgroundTintList(binding.root.context.getResources().getColorStateList(R.color.your_xml_name));
//                binding.layout.setSupportButtonTintList(ContextCompat.getColorStateList(Activity.this, R.color.colorPrimary));
                // binding.layout.background.setTint(ContextCompat.getColor(binding.root.context,  if (dataClass.isSelected) R.color._E70932 else R.color._C4C4C4))
                binding.layout.setBackgroundResource(if (dataClass.isSelected) R.drawable.right_corner_selected else R.drawable.right_corner)

//                binding.layout.backgroundTintList(
//                    ContextCompat.getColor(
//                        binding.root.context,
//                        if (dataClass.isSelected) R.color._E70932 else R.color._8998A3
//                    )
//                )

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




}