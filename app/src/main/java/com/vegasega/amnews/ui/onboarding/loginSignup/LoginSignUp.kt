package com.vegasega.amnews.ui.onboarding.loginSignup

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
import com.vegasega.amnews.databinding.LoginSignupBinding
import com.vegasega.amnews.utils.singleClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginSignUp : Fragment() {

    private var _binding: LoginSignupBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginSignUpVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginSignupBinding.inflate(inflater)
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

            if(key == "1") {
                textHeaderLoginTxt.text = Html.fromHtml(resources.getString(R.string.create_account), Html.FROM_HTML_MODE_COMPACT)
                textDiscLoginTxt.text = Html.fromHtml(resources.getString(R.string.choose_how_you_want_to_create_your_account), Html.FROM_HTML_MODE_COMPACT)
            } else if(key == "2") {
                textHeaderLoginTxt.text = Html.fromHtml(resources.getString(R.string.login_here), Html.FROM_HTML_MODE_COMPACT)
                textDiscLoginTxt.text = Html.fromHtml(resources.getString(R.string.choose_how_you_want_to_login_in_the_app), Html.FROM_HTML_MODE_COMPACT)
            }



            btSignIn.singleClick {
                findNavController().navigate(R.id.action_loginSignup_to_verificationCode, Bundle().apply {
                    putString("key", key)
                })
            }
        }
    }
}