package com.streetsaarthi.nasvi.screens.onboarding.walkThrough

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.WalkThroughItemBinding
import com.vegasega.amnews.genericAdapter.GenericAdapter
import com.vegasega.amnews.models.ItemWalkThrough
import com.vegasega.amnews.ui.mainActivity.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WalkThroughVM @Inject constructor(): ViewModel() {

    var itemMain : ArrayList<ItemWalkThrough> ?= ArrayList()

    init {
        itemMain?.add(ItemWalkThrough(MainActivity.context.get()!!.getString(R.string.title1), MainActivity.context.get()!!.getString(R.string.desc1),R.drawable.walk1))
        itemMain?.add(ItemWalkThrough(MainActivity.context.get()!!.getString(R.string.title2), MainActivity.context.get()!!.getString(R.string.desc2),R.drawable.walk2))
        itemMain?.add(ItemWalkThrough(MainActivity.context.get()!!.getString(R.string.title3), MainActivity.context.get()!!.getString(R.string.desc3),R.drawable.walk3))
    }

//    val photosAdapter = object : GenericAdapter<WalkThroughItemBinding, ItemWalkThrough>() {
//        override fun onCreateView(
//            inflater: LayoutInflater,
//            parent: ViewGroup,
//            viewType: Int
//        ) = WalkThroughItemBinding.inflate(inflater, parent, false)
//
//        override fun onBindHolder(binding: WalkThroughItemBinding, dataClass: ItemWalkThrough, position: Int) {
////            binding.textHeaderadfdsfTxt3.text = dataClass.name
////            Picasso.get().load(
////                dataClass.image
////            ).into(binding!!.imageLogo)
////            binding.root.singleClick {
////                when(position) {
////                    0 -> it.findNavController().navigate(R.id.action_onboard_to_quickRegistration)
////                    1 -> it.findNavController().navigate(R.id.action_onboard_to_loginPassword)
////                    2 -> it.findNavController().navigate(R.id.action_onboard_to_loginOtp)
////                    3 -> it.findNavController().navigate(R.id.action_onboard_to_completeRegistration)
////                    4 -> it.findNavController().navigate(R.id.action_onboard_to_completeRegistration)
////
////                }
////            }
//        }
//    }

}