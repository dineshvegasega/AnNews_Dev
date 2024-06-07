package com.vegasega.amnews.ui.onboarding.onboardLanguage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vegasega.amnews.R
import com.vegasega.amnews.ui.mainActivity.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class OnboardLanguageVM @Inject constructor() : ViewModel() {
    var itemMain : ArrayList<Item> ?= ArrayList()

    var appLanguage = MutableLiveData<String>("")

    companion object {
        @JvmStatic
        var locale: Locale = Locale.getDefault()
    }


    init {
        locale = Locale.getDefault()

        itemMain?.add(Item(MainActivity.context.get()!!.getString(R.string.english), MainActivity.context.get()!!.getString(R.string.englishVal),false))
        itemMain?.add(Item(MainActivity.context.get()!!.getString(R.string.hindi), MainActivity.context.get()!!.getString(R.string.hindiVal),false))

        for (item in itemMain!!.iterator()) {
            if(item.locale == ""+locale){
                item.apply {
                    item.isSelected = true
                }
                appLanguage.value = item.name
            }
        }


    }

    data class Item (
        var name: String = "",
        var locale: String = "",
        var isSelected: Boolean? = false
    )
}