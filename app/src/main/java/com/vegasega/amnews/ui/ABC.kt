package com.vegasega.amnews.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.vegasega.amnews.R

class ABC : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.abc)

        val pullToRefresh = findViewById<SwipeRefreshLayout>(R.id.pullToRefresh)

        pullToRefresh.setOnRefreshListener {
            //   refreshData() // your code
            pullToRefresh.isRefreshing = false

            Log.e("ABC", "onCreate: ")
        }




    }


}