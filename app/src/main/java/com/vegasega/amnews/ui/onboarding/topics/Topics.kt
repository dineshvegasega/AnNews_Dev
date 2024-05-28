package com.vegasega.amnews.ui.onboarding.topics

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.TopicsBinding
import com.vegasega.amnews.datastore.db.MusicModel
import com.vegasega.amnews.ui.mainActivity.MainActivity.Companion.db
import com.vegasega.amnews.utils.SpacesItemDecoration
import com.vegasega.amnews.utils.dpToPx
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Topics : Fragment(){

    private var _binding: TopicsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TopicsVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TopicsBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = viewModel.verticalAdapter
            viewModel.verticalAdapter.notifyDataSetChanged()
            viewModel.verticalAdapter.submitList(arrayListOf(
                Item(R.drawable.globe_1, "Mondial"),
                Item(R.drawable.waves, "Région Océan Indien"),
                Item(R.drawable.r_gional, "Régional"),
                Item(R.drawable.politique, "Politique"),
                Item(R.drawable.la_d_fense, "La défense"),
                Item(R.drawable.finance, "Finance"),
                Item(R.drawable.machine_learning_1, "Technologie"),
                Item(R.drawable.lifestyle, "Lifestyle"),
                Item(R.drawable.divertissement, "Divertissement"),
                Item(R.drawable.tourisme, "Tourisme"),
                Item(R.drawable.des_sports, "Des sports"),
                Item(R.drawable.opinion, "Opinion")
                ))
            val spacingInPixels: Int = dpToPx(16f)
            recyclerView.addItemDecoration(SpacesItemDecoration(spacingInPixels, 2))


            val arr1 = ArrayList<String>()
            arr1.add("A")
            arr1.add("B")
            arr1.add("C")
            arr1.add("D")
            arr1.add("E")
            arr1.add("F")
            arr1.add("G")
            arr1.add("H")
            arr1.add("I")
            arr1.add("J")
            arr1.add("K")
            arr1.add("L")
            arr1.add("M")
            arr1.add("N")
            arr1.add("O")
            arr1.add("P")

            val arr2 = ArrayList<String>()
            arr2.add("1")
            arr2.add("2")
            arr2.add("3")
            arr2.add("4")
            arr2.add("5")
            arr2.add("6")
            arr2.add("7")
            arr2.add("8")
            arr2.add("9")
            arr2.add("10")
            arr2.add("11")
            arr2.add("12")
            arr2.add("13")
            arr2.add("14")
            arr2.add("15")
            arr2.add("16")

            val arr3 = ArrayList<String>()

            val addNum = 4
//            val mainLength = arr1.size / addNum
//            Log.e("TAG", "mainLength "+mainLength)

//5 4
//4 6
//3 8

//5 % 1
//  4 % 0
// 3 % 1

//5/3
//4/ 4
//3/5

            //2 - 15 16
            //3 - 8 8
            //4 - 5 6
            //5 - 3 4
            //6 - - 4

            val length = ((arr1.size-1) + 6)


            var counter = -1
            var counter2 = 0
            for (i in 0 .. length){
                if (i % addNum == 0){
                    if(counter == 0){
                        Log.e("TAG", "AAAAAAAAAA "+counter)
                        arr3.add(arr2[counter])
                    } else {
                        if(counter != -1){
                            Log.e("TAG", "BBBBBBBBBB "+counter)
                            arr3.add(arr2[counter])
                        }
                    }
                    counter = counter + 1
                } else {
                    Log.e("TAG", "CCCCCCCC "+counter2)
                    arr3.add(arr1[counter2])
                    counter2 = counter2 + 1
                }
            }
//            arr3.add(arr2[counter])
//            Log.e("TAG", "DDDDDDDD "+counter)

            arr3.forEach {
                Log.e("TAG", "ZZZZZZZZZZZZ "+it)
            }





//
//            lifecycleScope.launch(Dispatchers.IO) {
//                val userDao = db?.musicDao()
//                userDao?.insertAll(MusicModel(12, "dasd" , "fffff"))
//
//                val all = userDao?.getAll()
//                all?.forEach {
//                    Log.e("TAG", "all "+it)
//                }
//            }




//            Log.e("TAG", "arr1 "+(arr1.size))
//
//            val arr3 = arr1.size / 2
//
//            var num = arr1.size - 1
//            var reversed = 0
//
//            while (reversed != num) {
////                val digit = num % 10
////                reversed = reversed * 10 + digit
////                num /= 10
//                if(reversed % 3 == 0){
//                    println("Reversed Number1: $reversed")
//                } else {
//                    println("Reversed Number2: $reversed")
//                }
//
//                reversed++
//            }


//            while (arr1.size != -1){
//                Log.e("TAG", "arr3 "+arr1)
//            }


        }

    }






}