package com.vegasega.amnews.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.viewpager.widget.ViewPager
import com.vegasega.amnews.ui.mainActivity.MainActivity
import kotlin.math.abs


/**
 * Created by jrizvan on 12/13/16.
 */
class VerticalViewPager : ViewPager {

    companion object{
        private const val MIN_SCALE = 0.90f
    }
    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    private fun init() {
        // The majority of the magic happens here
        setPageTransformer(true, VerticalPageTransformer())
        // The easiest way to get rid of the overscroll drawing that happens on the left and right
        overScrollMode = OVER_SCROLL_NEVER
    }

    private inner class VerticalPageTransformer : PageTransformer {
        override fun transformPage(view: View, position: Float) {
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.alpha = 0f
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.alpha = 1f
                // Counteract the default slide transition
                view.translationX = view.width * -position

                //set Y position to swipe in from top
                val yPosition = position * view.height
                view.translationY = yPosition
                view.scaleX = 1f
                view.scaleY = 1f
            } else if (position <= 1) { // [0,1]
                view.alpha = 1f

                // Counteract the default slide transition
                view.translationX = view.width * -position


                // Scale the page down (between MIN_SCALE and 1)
                val scaleFactor = (Companion.MIN_SCALE
                        + (1 - Companion.MIN_SCALE) * (1 - abs(
                    position.toDouble()
                ))).toFloat()
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.alpha = 0f
            }
        }


    }

    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private fun swapXY(ev: MotionEvent): MotionEvent {
        val width = width.toFloat()
        val height = height.toFloat()

        val newX = (ev.y / height) * width
        val newY = (ev.x / width) * height

        ev.setLocation(newX, newY)

        return ev
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val intercepted = super.onInterceptTouchEvent(swapXY(ev))
        swapXY(ev)
        return intercepted
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return super.onTouchEvent(swapXY(ev))
    }

}
