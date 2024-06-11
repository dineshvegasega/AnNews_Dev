package com.vegasega.amnews.ui.onboarding.verificationCode

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.VerificationCodeBinding
import com.vegasega.amnews.utils.singleClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerificationCode : Fragment() {

    private var _binding: VerificationCodeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VerificationCodeVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VerificationCodeBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val key = ""+arguments?.getString("key")

            Log.e("TAG", "onViewCreated: $key")

            imageBack.singleClick {
                findNavController().navigateUp()
            }

            textTimerTxt.text = Html.fromHtml(resources.getString(R.string._23_seconds_left, "12"), Html.FROM_HTML_MODE_COMPACT)
            textResendCodeTxt.text = Html.fromHtml(resources.getString(R.string.didn_t_get_the_code_resend_code_color), Html.FROM_HTML_MODE_COMPACT)

        }

    }
}