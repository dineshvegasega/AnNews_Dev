package com.vegasega.amnews.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.CenterHomeBinding
import com.vegasega.amnews.ui.interfaces.OnItemClickListener
import com.vegasega.amnews.ui.main.home.HomeVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingDesignOne : Fragment() , OnItemClickListener {
//    private var onboardingAdapter: OnboardingAdapter? = null
//    private var buttonOnboardingAction: AppCompatButton? = null

    lateinit var textToSpeech: TextToSpeech

    private val viewModel: HomeVM by viewModels()

    @SuppressLint("StaticFieldLeak")
    private var _binding: CenterHomeBinding? = null

    val binding get() = _binding!!


    lateinit var adapter: OnboardingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CenterHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.apply {

//            button111.setOnClickListener(View.OnClickListener {
//                if (introViewPager.currentItem + 1 < adapter!!.itemCount) {
//                    introViewPager.currentItem = introViewPager.currentItem + 1
//                } else {
//                }
//            })
        }


//        textToSpeech = TextToSpeech(
//            requireContext(), { status ->
//                if (status != TextToSpeech.ERROR) {
//                    Log.e("XXX", "Google tts initialized")
//
//                    adapter = OnboardingAdapter(this, textToSpeech)
//
//                    if (Home.consentIntent != null) {
//                        viewModel.itemMain.clear()
//                        Log.e("TAG", "consentIntent "+ Home.consentIntent.toString())
//                        Home.consentIntent?.let {
//                            viewModel.itemMain.add(it)
//                        }
//                        adapter.submitData(viewModel.itemMain)
//                    } else {
//                        Log.e("TAG", "consentIntentNULL ")
//                        adapter.submitData(viewModel.itemMain)
//                    }
//
//                    createVerticalView()
//                } else {
//                    Log.e("XXX", "Internal Google engine init error.")
//                }
//            }, "com.google.android.tts"
//        )
//        textToSpeech.setSpeechRate(0.7f)


//        adapter = OnboardingAdapter(viewModel.itemMain)
////        adapter.submitData(viewModel.itemMain)
//
//        binding.introViewPager.adapter = adapter

//        createVerticalView()

//        adapter = OnboardingAdapter(viewModel.itemMain);
//        val onboardingViewPager: ViewPager2 = view.findViewById<ViewPager2>(R.id.introViewPager)
        binding.introViewPager.adapter = adapter
        binding.introViewPager.offscreenPageLimit = 6

//        onboardingViewPager.adapter = adapter


    }

    private fun createVerticalView() {
        binding.apply {
            introViewPager.adapter = adapter

//            introViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
////                setCurrentOnboardingIndicators(position)
//                }
//            })
        }

    }



    override fun onClickMain() {
        TODO("Not yet implemented")
    }

    override fun onClickItem(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickItemUp(position: Int) {
        TODO("Not yet implemented")
    }
}