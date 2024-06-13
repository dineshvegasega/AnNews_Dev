package com.vegasega.amnews.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.vegasega.amnews.R

class ABC : AppCompatActivity() {

    var itemMainTopics: ArrayList<String> = ArrayList()

    var itemMainAds: ArrayList<String> = ArrayList()

    var itemMainFinal: ArrayList<String> = ArrayList()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.abc)


        val btSignIn = findViewById<MaterialButton>(R.id.btSignIn)
        val btSignIn2 = findViewById<MaterialButton>(R.id.btSignIn2)

//        val pullToRefresh = findViewById<SwipeRefreshLayout>(R.id.pullToRefresh)
//
//        pullToRefresh.setOnRefreshListener {
//            //   refreshData() // your code
//            pullToRefresh.isRefreshing = false
//
//            Log.e("ABC", "onCreate: ")
//        }

        itemMainTopics.add("A")
        itemMainTopics.add("B")
        itemMainTopics.add("C")
        itemMainTopics.add("D")
        itemMainTopics.add("E")
        itemMainTopics.add("F")
        itemMainTopics.add("G")
        itemMainTopics.add("H")
        itemMainTopics.add("I")
        itemMainTopics.add("J")
        itemMainTopics.add("K")
        itemMainTopics.add("L")
        itemMainTopics.add("M")
        itemMainTopics.add("N")
        itemMainTopics.add("O")


        itemMainAds.add("ADD1")
        itemMainAds.add("ADD2")
        itemMainAds.add("ADD3")
        itemMainAds.add("ADD4")
        itemMainAds.add("ADD5")


        var counter3 = 0
        for (i in 0..(itemMainTopics.size - 1)) {
            if (itemMainTopics.size != itemMainAds.size) {
                if (counter3 == itemMainAds.size - 1) {
                    itemMainAds.add(itemMainAds[counter3])
                    counter3 = 0
                } else {
                    itemMainAds.add(itemMainAds[counter3])
                    counter3++
                }
            }
        }

        itemMainAds.forEach {
            Log.e("TAG", "itemMainAds " + it)
        }


        //        val dd = 4


//        try {
//            var counter = -1
//            var counter2 = 0
//            var rnds = 3
//            var a1 = itemMainTopics.size / rnds
//            var a2 = itemMainTopics.size % rnds
//            //rnds = (5..10).random()
//            for (j in 0..100) {
//                for (i in 0..(itemMainTopics.size + 1) + a1 + a2) {
//                    Log.e("TAG", "rnds " + rnds)
//                    Log.e("TAG", "a1 " + a1)
//                    Log.e("TAG", "a2 " + a2)
//                    rnds = (5..10).random()
//                    a1 = itemMainTopics.size / rnds
//                    a2 = itemMainTopics.size % rnds
//                    if (i % rnds == 0) {
//                        if (counter == 0) {
//                            // println("AAAAAAAAAA "+arr2[counter])
//                            itemMainFinal.add(itemMainAds[counter])
//                        } else {
//                            if (counter != -1) {
//                                // println("BBBBBBBBBB "+arr2[counter])
//                                itemMainFinal.add(itemMainAds[counter])
//                            }
//                        }
//                        counter = counter + 1
//                    } else {
//                        //println(" arr22222 "+arr1[counter2])
//                        itemMainFinal.add(itemMainTopics[counter2])
//                        counter2 = counter2 + 1
//                    }
//
//
//                }
//
//
//
//
//            }
//        } catch (_: Exception) {
//
//        }
//
//        var counter5 = 0
//        var counter4 = 0
//
//        var aaa = 0
//        var rand = (4..8).random()
//        var zzz = rand
//        try {
//            for (i in 0..100) {
//                for (j in aaa..rand) {
//                    System.out.println(itemMainTopics[j])
//                    itemMainFinal.add(itemMainTopics[j])
//                    aaa++
//                }
//                var cc = zzz + 5
//                var dd = itemMainTopics.size - 1
//                var ee = dd - cc
//                Log.e("TAG", "cc " + cc)
//                Log.e("TAG", "dd " + dd)
//                Log.e("TAG", "ee " + ee)
//                rand = (1..ee).random()
//                rand += aaa
//                Log.e("TAG", "rand " + rand)
//                Log.e("TAG", "aaa " + aaa)
////                    if(rand == i){
//                        itemMainFinal.add(itemMainAds[i])
//                        System.out.println(itemMainAds[i])
//                        Log.e("TAG", "--------------------------------------------- 22" )
////                        counter5++
////                    }
////                counter4++
//            }
//        } catch (_: Exception) {
//
//        }
//
//        itemMainFinal.forEach {
////            Log.e("TAG", "itemMainFinal " + it)
//        }




        var count1 = 0
        var count2 = 0

        var rand11 = (5..8).random()
        var rand22 = 0
        var rand13 = 0
        for (i in 0..(itemMainTopics.size - 1) + 1) {
            if (rand11 == i) {
//                Log.e("TAG", "Add1 " + count2)
                itemMainFinal.add(itemMainAds[count2])
                rand22 = rand11 + 5
//                Log.e("TAG", "rand22 " + rand22)
                rand13 = (rand22..15).random()
//                Log.e("TAG", "rand13 " + rand13)
                count2++
            } else {
//                Log.e("TAG", "itemMainTopics11 " + itemMainTopics[count1])
//                    Log.e("TAG", "itemMainTopics11 " + count1)
                itemMainFinal.add(itemMainTopics[count1])
                count1++
            }


            if (rand13 == i && rand13 != 0) {
//                Log.e("TAG", "Add2 " + count2)
                itemMainFinal.add(itemMainAds[count2])
                count2++
            }
        }


        itemMainFinal.forEach {
            Log.e("TAG", "itemMainFinal2 " + it)
        }



        btSignIn.setOnClickListener {
            itemMainTopics.add("P")
            itemMainTopics.add("Q")
            itemMainTopics.add("R")
            itemMainTopics.add("S")
            itemMainTopics.add("T")
            itemMainTopics.add("U")
            itemMainTopics.add("V")
            itemMainTopics.add("W")
            itemMainTopics.add("X")
            itemMainTopics.add("Y")
            itemMainTopics.add("Z")
            itemMainTopics.add("A1")
            itemMainTopics.add("A2")
            itemMainTopics.add("A3")
            itemMainTopics.add("A4")




            var counter3 = 0
            for (i in 0..(itemMainTopics.size - 1)) {
                if (itemMainTopics.size != itemMainAds.size) {
                    if (counter3 == itemMainAds.size - 1) {
                        itemMainAds.add(itemMainAds[counter3])
                        counter3 = 0
                    } else {
                        itemMainAds.add(itemMainAds[counter3])
                        counter3++
                    }
                }
            }

//            Log.e("TAG", "itemMainTopicssize " + itemMainTopics.size)
            for (i in 0..(itemMainTopics.size - 1) + 1) {
                if (rand11 == i) {
//                    Log.e("TAG", "Add1 " + count2)
                    itemMainFinal.add(itemMainAds[count2])
                    rand22 = rand11 + 5
//                    Log.e("TAG", "rand22 " + rand22)
                    rand13 = (rand22..15).random()
//                    Log.e("TAG", "rand13 " + rand13)
                    count2++
                } else {
//                    Log.e("TAG", "itemMainTopics11 " + itemMainTopics[count1])

//                    itemMainFinal.add(itemMainTopics[count1])
                    if (count1 <= itemMainTopics.size - 1){
                        itemMainFinal.add(itemMainTopics[count1])
//                        Log.e("TAG", "itemMainTopics11 " + count1)
                        count1++
                    }


                }


                if (rand13 == i && rand13 != 0) {
//                    Log.e("TAG", "Add2 " + count2)
                    itemMainFinal.add(itemMainAds[count2])
                    count2++
                }
            }


            itemMainFinal.forEach {
                Log.e("TAG", "itemMainFinal2 " + it)
            }



        }



        btSignIn2.setOnClickListener {

            itemMainTopics.add("P1")
            itemMainTopics.add("Q1")
            itemMainTopics.add("R1")
            itemMainTopics.add("S1")
            itemMainTopics.add("T1")
            itemMainTopics.add("U1")
            itemMainTopics.add("V1")
            itemMainTopics.add("W1")
            itemMainTopics.add("X1")
            itemMainTopics.add("Y1")
            itemMainTopics.add("Z1")
            itemMainTopics.add("A11")
            itemMainTopics.add("A21")
            itemMainTopics.add("A31")
            itemMainTopics.add("A41")


            var counter3 = 0
            for (i in 0..(itemMainTopics.size - 1)) {
                if (itemMainTopics.size != itemMainAds.size) {
                    if (counter3 == itemMainAds.size - 1) {
                        itemMainAds.add(itemMainAds[counter3])
                        counter3 = 0
                    } else {
                        itemMainAds.add(itemMainAds[counter3])
                        counter3++
                    }
                }
            }

//            Log.e("TAG", "itemMainTopicssize " + itemMainTopics.size)
            for (i in 0..(itemMainTopics.size - 1) + 1) {
                if (rand11 == i) {
//                    Log.e("TAG", "Add1 " + count2)
                    itemMainFinal.add(itemMainAds[count2])
                    rand22 = rand11 + 5
//                    Log.e("TAG", "rand22 " + rand22)
                    rand13 = (rand22..15).random()
//                    Log.e("TAG", "rand13 " + rand13)
                    count2++
                } else {
//                    Log.e("TAG", "itemMainTopics11 " + itemMainTopics[count1])

//                    itemMainFinal.add(itemMainTopics[count1])
                    if (count1 <= itemMainTopics.size - 1){
                        itemMainFinal.add(itemMainTopics[count1])
//                        Log.e("TAG", "itemMainTopics11 " + count1)
                        count1++
                    }


                }


                if (rand13 == i && rand13 != 0) {
//                    Log.e("TAG", "Add2 " + count2)
                    itemMainFinal.add(itemMainAds[count2])
                    count2++
                }
            }


            itemMainFinal.forEach {
                Log.e("TAG", "itemMainFinal2 " + it)
            }



        }


    }


}