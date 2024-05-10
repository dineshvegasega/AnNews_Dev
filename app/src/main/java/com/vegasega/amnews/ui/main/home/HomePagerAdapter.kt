package com.vegasega.amnews.ui.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vegasega.amnews.databinding.ItemHomePagerVerticleBinding
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.utils.singleClick

class HomePagerAdapter(private val listener: OnItemClickListener) : ListAdapter<Item, HomePagerAdapter.PremiumPacksViewHolder>(
    DELIVERY_ITEM_COMPARATOR
) {
    inner class PremiumPacksViewHolder(private val binding: ItemHomePagerVerticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.singleClick {
                Log.e("TAG", "mainLayout")
                listener.onClickMain()
            }
        }

        fun bind(model: Item) {
            binding.apply {
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

//                textTitle0.setOnClickListener {
//                    listener.onClickItem()
//                }
//                textTitle1.setOnClickListener {
//                    listener.onClickItem()
//                }
//                textTitle2.setOnClickListener {
//                    listener.onClickItem()
//                }
//                textTitle3.setOnClickListener {
//                    listener.onClickItem()
//                }
//                textTitle4.setOnClickListener {
//                    listener.onClickItem()
//                }
//                textTitle5.setOnClickListener {
//                    listener.onClickItem()
//                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PremiumPacksViewHolder {
        val binding =
            ItemHomePagerVerticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PremiumPacksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PremiumPacksViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val DELIVERY_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(
                oldItem: Item,
                newItem: Item
            ): Boolean {
                return false
            }

            override fun areContentsTheSame(
                oldItem: Item,
                newItem: Item
            ): Boolean {
                return false
            }
        }
    }
}