package com.vegasega.amnews.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vegasega.amnews.databinding.ItemHomePagerVerticleBinding
import com.vegasega.amnews.utils.singleClick

class HomePagerAdapter : ListAdapter<HomeVM.Item, HomePagerAdapter.PremiumPacksViewHolder>(
    DELIVERY_ITEM_COMPARATOR
) {
    inner class PremiumPacksViewHolder(private val binding: ItemHomePagerVerticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.singleClick {
            }
        }

        fun bind(model: HomeVM.Item) {
            binding.apply {
//                textHeaderadfdsfTxt3.text = ""+model.name
//                textHeaderTxt2.text = ""+model.desc
                imageLogo.setImageResource(model.image)

                timeline1.initLine(1)
                timeline2.initLine(0)
                timeline3.initLine(0)
                timeline4.initLine(0)
                timeline5.initLine(2)
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
        private val DELIVERY_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<HomeVM.Item>() {
            override fun areItemsTheSame(
                oldItem: HomeVM.Item,
                newItem: HomeVM.Item
            ): Boolean {
                return false
            }

            override fun areContentsTheSame(
                oldItem: HomeVM.Item,
                newItem: HomeVM.Item
            ): Boolean {
                return false
            }
        }
    }
}