package com.vegasega.amnews.ui.main.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonElement
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.CardItemViewBinding
import com.vegasega.amnews.databinding.ItemHomeHorizontalMenusBinding
import com.vegasega.amnews.databinding.LoaderBinding
import com.vegasega.amnews.genericAdapter.GenericAdapter
import com.vegasega.amnews.models.BaseResponseDC
import com.vegasega.amnews.models.Item
import com.vegasega.amnews.models.ItemList
import com.vegasega.amnews.models.ItemMenu
import com.vegasega.amnews.networking.ApiInterface
import com.vegasega.amnews.networking.CallHandler
import com.vegasega.amnews.networking.Repository
import com.vegasega.amnews.networking.getJsonRequestBody
import com.vegasega.amnews.ui.mainActivity.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import java.security.AccessController.getContext
import javax.inject.Inject


@HiltViewModel
class HomeVM @Inject constructor(private val repository: Repository) : ViewModel() {

//    val adapter by lazy { VerticalViewPagerAdapter(this) }


//    var itemSongs = MutableLiveData<Int>()

    var alertDialog: AlertDialog? = null
    init {
        val alert = AlertDialog.Builder(MainActivity.activity.get())
        val binding =
            LoaderBinding.inflate(LayoutInflater.from(MainActivity.activity.get()), null, false)
        alert.setView(binding.root)
        alert.setCancelable(false)
        alertDialog = alert.create()
        alertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun show() {
        viewModelScope.launch {
            if (alertDialog != null) {
                alertDialog?.dismiss()
                alertDialog?.show()
            }
        }
    }

    fun hide() {
        viewModelScope.launch {
            if (alertDialog != null) {
                alertDialog?.dismiss()
            }
        }
    }


    var itemMenusArray : ArrayList<ItemMenu> = ArrayList()

    var itemMainTopics : ArrayList<Item> = ArrayList()

    var itemMainAds : ArrayList<Item> = ArrayList()

    var itemMainFinal : ArrayList<Item> = ArrayList()

    init {
        itemMenusArray.add(ItemMenu("Latest", R.drawable.icon_star, false))
        itemMenusArray.add(ItemMenu("Trending", R.drawable.icon_trending, false))
        itemMenusArray.add(ItemMenu("Your feed", R.drawable.icon_feed, false))
        itemMenusArray.add(ItemMenu("Featured", R.drawable.icon_featured, false))
        itemMenusArray.add(ItemMenu("Saved", R.drawable.icon_saved, false))



        itemMainTopics?.add(Item("A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any",
            R.drawable.m1,
            false,
            false,
            false,
            "en",
            "",
            arrayListOf(
                ItemList("A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any"),
                ItemList("A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any"),
                ItemList("A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any"),
                ItemList("A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any"),
                ItemList("A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any"),
            )))
        itemMainTopics?.add(Item("समाचार प्रस्तुतकर्ता, अर्थव्यवस्था, राजनीति और खेल से संबंधित नवीनतम समाचारों और घटनाक्रमों की जानकारी पेश करते करते है",R.drawable.m1,
            false,
            false,
            false,
            "hi",
            "",
            arrayListOf(
                ItemList("समाचार प्रस्तुतकर्ता, अर्थव्यवस्था, राजनीति और खेल से संबंधित नवीनतम समाचारों और घटनाक्रमों की जानकारी पेश करते करते हैं"),
                ItemList("समाचार प्रस्तुतकर्ता, अर्थव्यवस्था, राजनीति और खेल से संबंधित नवीनतम समाचारों और घटनाक्रमों की जानकारी पेश करते करते हैं"),
                ItemList("समाचार प्रस्तुतकर्ता, अर्थव्यवस्था, राजनीति और खेल से संबंधित नवीनतम समाचारों और घटनाक्रमों की जानकारी पेश करते करते हैं"),
                ItemList("समाचार प्रस्तुतकर्ता, अर्थव्यवस्था, राजनीति और खेल से संबंधित नवीनतम समाचारों और घटनाक्रमों की जानकारी पेश करते करते हैं"),
                ItemList("समाचार प्रस्तुतकर्ता, अर्थव्यवस्था, राजनीति और खेल से संबंधित नवीनतम समाचारों और घटनाक्रमों की जानकारी पेश करते करते हैं"),
            )))
        itemMainTopics?.add(Item("कुछ समय पहिले हमरा ई गिने के जरूरत रहे कि ईमेल टेम्पलेट में एगो पाठ के टुकड़ा में कतना अक्षर होला (कवनो पास ना होखे खातिर",R.drawable.m1,
            false,
            false,
            false,
            "bho",
            "",
            arrayListOf(
                ItemList("कुछ समय पहिले हमरा ई गिने के जरूरत रहे कि ईमेल टेम्पलेट में एगो पाठ के टुकड़ा में कतना अक्षर होला (कवनो पास ना होखे खातिर"),
                ItemList("कुछ समय पहिले हमरा ई गिने के जरूरत रहे कि ईमेल टेम्पलेट में एगो पाठ के टुकड़ा में कतना अक्षर होला (कवनो पास ना होखे खातिर") ,
                ItemList("कुछ समय पहिले हमरा ई गिने के जरूरत रहे कि ईमेल टेम्पलेट में एगो पाठ के टुकड़ा में कतना अक्षर होला (कवनो पास ना होखे खातिर"),
                ItemList("कुछ समय पहिले हमरा ई गिने के जरूरत रहे कि ईमेल टेम्पलेट में एगो पाठ के टुकड़ा में कतना अक्षर होला (कवनो पास ना होखे खातिर"),
                ItemList("कुछ समय पहिले हमरा ई गिने के जरूरत रहे कि ईमेल टेम्पलेट में एगो पाठ के टुकड़ा में कतना अक्षर होला (कवनो पास ना होखे खातिर"),
            )))
        itemMainTopics?.add(Item("D",R.drawable.m1,
            false,
            false,
            false,
            "en",
            "",
            arrayListOf(
                ItemList("put icon"),
                ItemList("set gravity") ,
                ItemList("only text"),
                ItemList("no icon"),
                ItemList("icon"),
            )))
        itemMainTopics?.add(Item("E",R.drawable.m1,
            false,
            false,
            false,
            "en",
            "",
            arrayListOf(
                ItemList("I can icon"),
                ItemList("but gravity to center") ,
                ItemList("then centered"),
                ItemList("but icon"),
                ItemList("my icon"),
            )))
        itemMainTopics?.add(Item("F",R.drawable.m1,
            false,
            false,
            false,
            "en",
            "",
            arrayListOf(
                ItemList("I put icon"),
                ItemList("I set gravity center") ,
                ItemList("then centered"),
                ItemList("icon icon"),
                ItemList("my icon"),
            )))


        itemMainTopics?.add(Item("M",R.drawable.m1,
            false,
            false,
            false,
            "en",
            "",
            arrayListOf(
                ItemList("M1"),
                ItemList("M2") ,
                ItemList("M3"),
                ItemList("M4"),
                ItemList("M5"),
            )))

        itemMainTopics?.add(Item("N",R.drawable.m1,
            false,
            false,
            false,
            "en",
            "",
            arrayListOf(
                ItemList("N1"),
                ItemList("N2") ,
                ItemList("N3"),
                ItemList("N4"),
                ItemList("N5"),
            )))


        itemMainTopics?.add(Item("O",R.drawable.m1,
            false,
            false,
            false,
            "en",
            "",
            arrayListOf(
                ItemList("O1"),
                ItemList("O2") ,
                ItemList("O3"),
                ItemList("O4"),
                ItemList("O5"),
            )))


        itemMainTopics?.add(Item("P",R.drawable.m1,
            false,
            false,
            false,
            "en",
            "",
            arrayListOf(
                ItemList("P1"),
                ItemList("P2") ,
                ItemList("P3"),
                ItemList("P4"),
                ItemList("P5"),
            )))





        itemMainAds?.add(Item("Add A",R.drawable.add1,
            false,
            true,
            false,
            "en",
            "",
            arrayListOf(
                ItemList("AA11"),
                ItemList("AA22") ,
                ItemList("AA33"),
                ItemList("AA44"),
                ItemList("AA55"),
            )))


        itemMainAds?.add(Item("Add B",R.drawable.add2,
            false,
            true,
            true,
            "en",
            "",
            arrayListOf(
                ItemList("BB11"),
                ItemList("BB22") ,
                ItemList("BB33"),
                ItemList("BB44"),
                ItemList("BB55"),
            )))


        itemMainAds?.add(Item("Add C",R.drawable.add1,
            false,
            true,
            false,
            "en",
            "",
            arrayListOf(
                ItemList("CC11"),
                ItemList("CC22") ,
                ItemList("CC33"),
                ItemList("CC44"),
                ItemList("CC55"),
            )))

        itemMainAds?.add(Item("Add D",R.drawable.add1,
            false,
            true,
            false,
            "en",
            "",
            arrayListOf(
                ItemList("CC11"),
                ItemList("CC22") ,
                ItemList("CC33"),
                ItemList("CC44"),
                ItemList("CC55"),
            )))

        itemMainAds?.add(Item("Add E",R.drawable.add1,
            false,
            true,
            false,
            "en",
            "",
            arrayListOf(
                ItemList("CC11"),
                ItemList("CC22") ,
                ItemList("CC33"),
                ItemList("CC44"),
                ItemList("CC55"),
            )))
    }



    val dashboardAdapter = object : GenericAdapter<ItemHomeHorizontalMenusBinding, ItemMenu>() {
        override fun onCreateView(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) = ItemHomeHorizontalMenusBinding.inflate(inflater, parent, false)

        override fun updatePosition(position: Int) {
        }

        @SuppressLint("SuspiciousIndentation")
        override fun onBindHolder(
            binding: ItemHomeHorizontalMenusBinding,
            dataClass: ItemMenu,
            position: Int
        ) {
            binding.apply {
//                if(dataClass.isNew == true){
//                    animationView.visibility = View.VISIBLE
//                    textDotTxt.visibility = View.VISIBLE
//                    layoutBottomRed.visibility = View.VISIBLE
//                } else {
//                    animationView.visibility = View.GONE
//                    textDotTxt.visibility = View.GONE
//                    layoutBottomRed.visibility = View.GONE
//                }

                binding.ivIconCenter.setBackgroundResource(dataClass.image)
//                binding.ivIcon.setColorFilter(ContextCompat.getColor(binding.root.context, R.color._E70932), android.graphics.PorterDuff.Mode.MULTIPLY);

                binding.ivIcon.setColorFilter(
                    ContextCompat.getColor(
                        binding.root.context,
                        if (dataClass.isSelected) R.color._E70932 else R.color._8998A3
                    )
                )

                //  binding.ivIcon.setBackgroundResource(if (dataClass.isSelected == true) R.drawable.fade_golden_fill else R.drawable.gray_fill_round)
                root.setOnClickListener {
                    val list = currentList
                    list.forEach {
                        it.isSelected = dataClass == it
                    }
                    notifyDataSetChanged()
                }

            }

        }
    }



    val verticalAdapter = object : GenericAdapter<CardItemViewBinding, Item>() {
//        private val listener: OnItemClickListener


        init {
            Log.e("TAG", "init2")

        }
        var positionInt = 0
        override fun onCreateView(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) = CardItemViewBinding.inflate(inflater, parent, false)

        @SuppressLint("SuspiciousIndentation")
        override fun onBindHolder(
            binding: CardItemViewBinding,
            model: Item,
            position: Int
        ) {
            binding.apply {
//                if(dataClass.isNew == true){
//                    animationView.visibility = View.VISIBLE
//                    textDotTxt.visibility = View.VISIBLE
//                    layoutBottomRed.visibility = View.VISIBLE
//                } else {
//                    animationView.visibility = View.GONE
//                    textDotTxt.visibility = View.GONE
//                    layoutBottomRed.visibility = View.GONE
//                }

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

                ivPlayback.setOnClickListener {
//                    listener.onClickItem()
                }
                ivPlaynext.setOnClickListener {

                }

                ivPlayPause.setOnClickListener {
                }

                if(position == positionInt){
                    Log.e("TAG", "QQQQQQQ "+position)

                } else {
                    Log.e("TAG", "WWWWWWW "+position)
                }

            }

        }


        @SuppressLint("NotifyDataSetChanged")
        override fun updatePosition(position: Int) {
            positionInt = position
            // Log.e("TAG", "BBBBBBB "+positionInt)


        }
    }




    private var itemLiveNoticeResult = MutableLiveData<BaseResponseDC<Any>>()
    val itemLiveNotice : LiveData<BaseResponseDC<Any>> get() = itemLiveNoticeResult
    fun liveNotice(jsonObject: JSONObject) = viewModelScope.launch {
        repository.callApi(
            callHandler = object : CallHandler<Response<BaseResponseDC<JsonElement>>> {
                override suspend fun sendRequest(apiInterface: ApiInterface) =
                    apiInterface.allNoticeList(requestBody = jsonObject.getJsonRequestBody())
                override fun success(response: Response<BaseResponseDC<JsonElement>>) {
                    if (response.isSuccessful){
                        itemLiveNoticeResult.value = response.body() as BaseResponseDC<Any>
                    }
                }

                override fun error(message: String) {
                    super.error(message)
//                    showSnackBar(message)
                }

                override fun loading() {
                    super.loading()
                }
            }
        )
    }





    private var itemLiveNoticeResultSecond = MutableLiveData<BaseResponseDC<Any>>()
    val itemLiveNoticeSecond : LiveData<BaseResponseDC<Any>> get() = itemLiveNoticeResultSecond
    fun liveNoticeSecond(jsonObject: JSONObject) = viewModelScope.launch {
        repository.callApi(
            callHandler = object : CallHandler<Response<BaseResponseDC<JsonElement>>> {
                override suspend fun sendRequest(apiInterface: ApiInterface) =
                    apiInterface.allNoticeList(requestBody = jsonObject.getJsonRequestBody())
                override fun success(response: Response<BaseResponseDC<JsonElement>>) {
                    if (response.isSuccessful){
                        itemLiveNoticeResultSecond.value =  response.body() as BaseResponseDC<Any>
                    }
                }

                override fun error(message: String) {
                    super.error(message)
//                    showSnackBar(message)
                }

                override fun loading() {
                    super.loading()
                }
            }
        )
    }


}