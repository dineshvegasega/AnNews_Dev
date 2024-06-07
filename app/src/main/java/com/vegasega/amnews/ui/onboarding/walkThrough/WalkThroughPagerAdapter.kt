package com.streetsaarthi.nasvi.screens.onboarding.walkThrough

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vegasega.amnews.databinding.WalkThroughItemBinding
import com.vegasega.amnews.models.ItemWalkThrough
import com.vegasega.amnews.utils.singleClick

class WalkThroughPagerAdapter : ListAdapter<ItemWalkThrough, WalkThroughPagerAdapter.PremiumPacksViewHolder>(
        DELIVERY_ITEM_COMPARATOR
    ) {
    inner class PremiumPacksViewHolder(private val binding: WalkThroughItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.singleClick {
            }
        }

        fun bind(model: ItemWalkThrough) {
            binding.apply {
                textHeaderadfdsfTxt3.text = ""+model.name
                textHeaderTxt2.text = ""+model.desc
                imageLogo.setImageResource(model.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): PremiumPacksViewHolder {
        val binding =
            WalkThroughItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PremiumPacksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PremiumPacksViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val DELIVERY_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<ItemWalkThrough>() {
            override fun areItemsTheSame(
                oldItem: ItemWalkThrough,
                newItem: ItemWalkThrough
            ): Boolean {
                return false
            }

            override fun areContentsTheSame(
                oldItem: ItemWalkThrough,
                newItem: ItemWalkThrough
            ): Boolean {
                return false
            }
        }
    }
}