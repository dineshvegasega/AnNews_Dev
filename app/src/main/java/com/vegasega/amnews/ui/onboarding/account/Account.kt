package com.vegasega.amnews.ui.onboarding.account

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
import com.vegasega.amnews.databinding.AccountBinding
import com.vegasega.amnews.ui.mainActivity.MainActivity.Companion.navHostFragment
import com.vegasega.amnews.utils.singleClick
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Account : Fragment() {

    private var _binding: AccountBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AccountVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AccountBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            val key = ""+arguments?.getString("key")

            Log.e("TAG", "onViewCreated: $key")

            if(key != "null"){
                if(key == "1") {
                    textHeaderLoginTxt.text = Html.fromHtml(resources.getString(R.string.create_account), Html.FROM_HTML_MODE_COMPACT)
                    textDiscLoginTxt.text = Html.fromHtml(resources.getString(R.string.choose_how_you_want_to_create_your_account), Html.FROM_HTML_MODE_COMPACT)
                    btGoogle.text = Html.fromHtml(resources.getString(R.string.signup_with_google_account), Html.FROM_HTML_MODE_COMPACT)
                    btMobile.text = Html.fromHtml(resources.getString(R.string.signup_with_mobile_number), Html.FROM_HTML_MODE_COMPACT)
                    btGuest.visibility = View.VISIBLE
                    textHeaderTxt2.text = Html.fromHtml(resources.getString(R.string.already_a_user_login_here), Html.FROM_HTML_MODE_COMPACT)
                } else if(key == "2") {
                    textHeaderLoginTxt.text = Html.fromHtml(resources.getString(R.string.login_here), Html.FROM_HTML_MODE_COMPACT)
                    textDiscLoginTxt.text = Html.fromHtml(resources.getString(R.string.choose_how_you_want_to_login_in_the_app), Html.FROM_HTML_MODE_COMPACT)
                    btGoogle.text = Html.fromHtml(resources.getString(R.string.login_with_google_account), Html.FROM_HTML_MODE_COMPACT)
                    btMobile.text = Html.fromHtml(resources.getString(R.string.login_with_mobile_number), Html.FROM_HTML_MODE_LEGACY)
                    btGuest.visibility = View.GONE
                    textHeaderTxt2.text = Html.fromHtml(resources.getString(R.string.first_time_user_signup_here), Html.FROM_HTML_MODE_COMPACT)
                }
            } else {
                textHeaderLoginTxt.text = Html.fromHtml(resources.getString(R.string.login_here), Html.FROM_HTML_MODE_COMPACT)
                textDiscLoginTxt.text = Html.fromHtml(resources.getString(R.string.choose_how_you_want_to_login_in_the_app), Html.FROM_HTML_MODE_COMPACT)
                btGoogle.text = Html.fromHtml(resources.getString(R.string.login_with_google_account), Html.FROM_HTML_MODE_COMPACT)
                btMobile.text = Html.fromHtml(resources.getString(R.string.login_with_mobile_number), Html.FROM_HTML_MODE_LEGACY)
                btGuest.visibility = View.GONE
                textHeaderTxt2.text = Html.fromHtml(resources.getString(R.string.first_time_user_signup_here), Html.FROM_HTML_MODE_COMPACT)
            }


            btMobile.singleClick {
                findNavController().navigate(R.id.action_account_to_loginSignup, Bundle().apply {
                    putString("key", key)
                })
            }

            textHeaderTxt2.singleClick {
                findNavController().navigate(R.id.action_account_to_account, Bundle().apply {
                    putString("key", if(key == "1") "2" else "1")
                })
            }
        }
    }
}