package com.vegasega.amnews.ui.onboarding.onboardLanguage

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.DialogOnboardLanguageBinding
import com.vegasega.amnews.databinding.ItemOnboardLanguageBinding
import com.vegasega.amnews.databinding.OnboardLanguageBinding
import com.vegasega.amnews.genericAdapter.GenericAdapter
import com.vegasega.amnews.ui.mainActivity.MainActivity
import com.vegasega.amnews.utils.singleClick
import dagger.hilt.android.AndroidEntryPoint
import com.vegasega.amnews.networking.OnboardLanguage
import com.vegasega.amnews.ui.mainActivity.MainActivityVM.Companion.locale

@AndroidEntryPoint
class OnboardLanguage : Fragment(){

    private var _binding: OnboardLanguageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnboardLanguageVM by viewModels()

    var languageAlert : BottomSheetDialog?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = OnboardLanguageBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {


            when(locale.language){
                MainActivity.context.get()!!
                    .getString(R.string.frenchVal) -> {
                    linear1.background = ContextCompat.getDrawable(requireContext(), R.drawable.select_lang_radius)
                }

                MainActivity.context.get()!!
                    .getString(R.string.englishVal) -> {
                    linear2.background = ContextCompat.getDrawable(requireContext(), R.drawable.select_lang_radius)
                }

                MainActivity.context.get()!!
                    .getString(R.string.hindiVal) -> {
                    linear3.background = ContextCompat.getDrawable(requireContext(), R.drawable.select_lang_radius)
                }
            }



            linear1.singleClick {
                linear1.background = ContextCompat.getDrawable(requireContext(), R.drawable.select_lang_radius)
                linear2.background = ContextCompat.getDrawable(requireContext(), R.drawable.unselect_lang_radius)
                linear3.background = ContextCompat.getDrawable(requireContext(), R.drawable.unselect_lang_radius)
                linear4.background = ContextCompat.getDrawable(requireContext(), R.drawable.unselect_lang_radius)
                Handler(Looper.getMainLooper()).postDelayed(Thread {
                    MainActivity.mainActivity.get()?.reloadActivity("fr", OnboardLanguage)
                }, 300)

            }
            linear2.singleClick {
                linear1.background = ContextCompat.getDrawable(requireContext(), R.drawable.unselect_lang_radius)
                linear2.background = ContextCompat.getDrawable(requireContext(), R.drawable.select_lang_radius)
                linear3.background = ContextCompat.getDrawable(requireContext(), R.drawable.unselect_lang_radius)
                linear4.background = ContextCompat.getDrawable(requireContext(), R.drawable.unselect_lang_radius)
                Handler(Looper.getMainLooper()).postDelayed(Thread {
                    MainActivity.mainActivity.get()?.reloadActivity("en", OnboardLanguage)
                }, 300)
            }
            linear3.singleClick {
                linear1.background = ContextCompat.getDrawable(requireContext(), R.drawable.unselect_lang_radius)
                linear2.background = ContextCompat.getDrawable(requireContext(), R.drawable.unselect_lang_radius)
                linear3.background = ContextCompat.getDrawable(requireContext(), R.drawable.select_lang_radius)
                linear4.background = ContextCompat.getDrawable(requireContext(), R.drawable.unselect_lang_radius)
                Handler(Looper.getMainLooper()).postDelayed(Thread {
                    MainActivity.mainActivity.get()?.reloadActivity("hi", OnboardLanguage)
                }, 300)
            }
            linear4.singleClick {
                linear1.background = ContextCompat.getDrawable(requireContext(), R.drawable.unselect_lang_radius)
                linear2.background = ContextCompat.getDrawable(requireContext(), R.drawable.unselect_lang_radius)
                linear3.background = ContextCompat.getDrawable(requireContext(), R.drawable.unselect_lang_radius)
                linear4.background = ContextCompat.getDrawable(requireContext(), R.drawable.select_lang_radius)
                Handler(Looper.getMainLooper()).postDelayed(Thread {
                    MainActivity.mainActivity.get()?.reloadActivity("bho", OnboardLanguage)
                }, 300)
            }

//            btLanguage.singleClick {
//                if(languageAlert?.isShowing == true) {
//                    return@singleClick
//                }
//                val dialogBinding = DialogOnboardLanguageBinding.inflate(root.context.getSystemService(
//                    Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//                )
//                val dialog = BottomSheetDialog(root.context)
//                dialog.setContentView(dialogBinding.root)
//                dialog.setOnShowListener { dia ->
//                    languageAlert = dia as BottomSheetDialog
//                    val bottomSheetInternal: FrameLayout =
//                        languageAlert?.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
//                    bottomSheetInternal.setBackgroundResource(R.drawable.bg_top_round_corner)
//                }
//                dialog.show()
//
//                val pastBookingAdapter = object : GenericAdapter<ItemOnboardLanguageBinding, OnboardLanguageVM.Item>() {
//                    override fun onCreateView(
//                        inflater: LayoutInflater,
//                        parent: ViewGroup,
//                        viewType: Int
//                    ) = ItemOnboardLanguageBinding.inflate(inflater, parent, false)
//
//                    override fun updatePosition(position: Int) {
//                    }
//
//                    @SuppressLint("NotifyDataSetChanged")
//                    override fun onBindHolder(
//                        binding: ItemOnboardLanguageBinding,
//                        dataClass: OnboardLanguageVM.Item,
//                        position: Int
//                    ) {
//                        binding.btImage.setImageDrawable(ContextCompat.getDrawable(binding.root.context, if (dataClass.isSelected == true) R.drawable.radio_sec_filled else R.drawable.radio_sec_empty));
//                        binding.btLanguage.text = dataClass.name
//
//                        binding.btLanguage.singleClick {
//                            val list = currentList
//                            list.forEach {
//                                it.isSelected = dataClass == it
//                            }
//                            notifyDataSetChanged()
//                            Handler(Looper.getMainLooper()).postDelayed(Thread {
//                                MainActivity.mainActivity.get()?.runOnUiThread {
//                                    languageAlert?.dismiss()
//                                }
//                            }, 100)
////                            MainActivity.mainActivity.get()?.reloadActivity(dataClass.locale, Start)
//                        }
//
//                        binding.mainContainer.singleClick {
//                            val list = currentList
//                            list.forEach {
//                                it.isSelected = dataClass == it
//                            }
//                            notifyDataSetChanged()
//                            Handler(Looper.getMainLooper()).postDelayed(Thread {
//                                MainActivity.mainActivity.get()?.runOnUiThread {
//                                    languageAlert?.dismiss()
//                                }
//                            }, 100)
////                            MainActivity.mainActivity.get()?.reloadActivity(dataClass.locale, Start)
//                        }
//                    }
//                }
////                val recyclerView = dialogView.findViewById<RecyclerView>(R.id.rvList)
//
//                dialogBinding.apply {
//                    pastBookingAdapter.submitList(viewModel.itemMain)
//                    rvList.adapter = pastBookingAdapter
//                }
//
//            }
        }
    }
}