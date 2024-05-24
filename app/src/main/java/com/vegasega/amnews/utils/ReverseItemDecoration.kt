package com.vegasega.amnews.utils

import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView

class ReverseItemDecoration : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // Draw the pages in reverse order
//        for (i in parent.childCount - 1 downTo 0) {
//            val child = parent.getChildAt(i)
//            parent.drawChild(c, child, 100)
//        }

        val childCount = parent.childCount
        for (i in childCount - 1 downTo 0) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            parent.drawChild(c, child, state.get(position))
        }
    }
}