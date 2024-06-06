package com.vegasega.amnews.ui

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.util.Log
import android.view.WindowManager
import android.view.WindowMetrics
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
//import com.daimajia.swipe.SwipeLayout
import com.vegasega.amnews.R
import com.vegasega.amnews.utils.SwipeRevealLayout
import java.util.Locale


class AA : AppCompatActivity() {

    lateinit var btn_new_activity : AppCompatButton


    lateinit var textToSpeech: TextToSpeech

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aa)

        val button: AppCompatButton = findViewById(R.id.button)


//        val swipe: SwipeLayout = findViewById(R.id.swipe)
//        swipe.addDrag(SwipeLayout.DragEdge.Left, swipe.findViewById(R.id.left));
//        swipe.addDrag(SwipeLayout.DragEdge.Right, swipe.findViewById(R.id.center));

//
//        val navView: NavigationView = findViewById(R.id.nav_view)
//
//        val params: DrawerLayout.LayoutParams =
//            drawer_layout.getLayoutParams() as DrawerLayout.LayoutParams
//        params.width = 1000
//        navView.setLayoutParams(params)


        //val widthOfNav = (Screen.width) * 0.9

//        val drawer_layout: ConstraintLayout = findViewById(R.id.lay)
//        val linearLayout: LinearLayout = findViewById(R.id.lay1)
//        linearLayout.layoutParams = param
//       var width = drawer_layout.getLayoutParams().height = ConstraintLayout.LayoutParams.MATCH_PARENT;

//        Log.e("TAG", "drawer_layout "+width)
//        val param = LinearLayout.LayoutParams( /*width*/
//            ViewGroup.LayoutParams.MATCH_PARENT,  /*height*/
//            ViewGroup.LayoutParams.MATCH_PARENT,  /*weight*/
//            1.0f
//        )
//        Log.e("TAG", "drawer_layout "+param.width)
//        val layoutHeight = AtomicInteger()
//
//        layoutHeight.set(300)
//
//        val rootParams = linearLayout.getLayoutParams()
//        rootParams.height = layoutHeight.get()
//        drawer_layout.setLayoutParams(rootParams)


//        val metrics: WindowMetrics = getSystemService(WindowManager::class.java).currentWindowMetrics

//        val display = windowManager.defaultDisplay
//        val size = Point()
//        try {
//            display.getRealSize(size)
//        } catch (err: NoSuchMethodError) {
//            display.getSize(size)
//        }
//
//        var width = Resources.getSystem().displayMetrics.widthPixels
//          var height = Resources.getSystem().displayMetrics.heightPixels

//        val width = metrics.x
//        val height = size.y

//        val lp = LinearLayout.LayoutParams(
//            (resources.displayMetrics.widthPixels),
//            resources.displayMetrics.heightPixels
//        )
//        linearLayout.setLayoutParams(lp);

        window.decorView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            window.decorView.getWindowVisibleDisplayFrame(r)
            val height = window.decorView.height
            val width = window.decorView.width
            Log.e("TAG", "widthwidth "+width)



//            if (height - r.bottom > height * 0.1399) {
//                isOpen = WeakReference(true)
//            } else {
//                isOpen = WeakReference(false)
//            }
        }




        // btn_new_activity = findViewById(R.id.button) as Button

        val ss = "समाचार प्रस्तुतकर्ता, अर्थव्यवस्था, राजनीति और खेल से संबंधित नवीनतम समाचारों और घटनाक्रमों की जानकारी पेश करते हैं।"
//        val ss = "I can easily put icon"


        textToSpeech = TextToSpeech(this, TextToSpeech.OnInitListener {
            if(it == TextToSpeech.SUCCESS){
//                Log.e("TAG", "AAAAAAAA ")

//                for (tmpVoice in textToSpeech.getVoices()) {
//                    Log.e("TAG", "AAAAAAAA "+tmpVoice.name)
//                }

                val locale = Locale("hi","IN")
                textToSpeech.setLanguage(locale)

            } else {
                Log.e("TAG", "BBBBBBBB ")
            }
        }, "com.google.android.tts")


//        var zz = HashSet<String>()
//        zz.add("male")
////        zz.add("en-in-x-end-network")
////        zz.add("hi-in-x-hie-local")
//
////        textToSpeech.voice = Voice("en-us-x-sfg#male_2-local", Locale("en","US"), 400, 200, true, zz)
//
//        textToSpeech.setLanguage(Locale.forLanguageTag("en"));
//        textToSpeech.voice = Voice("en-gb-x-fis-network", Locale("en", "UK"), 400, 200, true, zz)
//        textToSpeech.setLanguage(Locale("hin", "IND", "variant"))

        button.setOnClickListener {
            textToSpeech.speak(
                ss,
                TextToSpeech.QUEUE_FLUSH,
                null,
                ss
            )
        }






    }
}