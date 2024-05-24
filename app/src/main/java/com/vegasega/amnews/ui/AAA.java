package com.vegasega.amnews.ui;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class AAA extends AppCompatActivity {


//    ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ViewPager viewPager = new ViewPager(this){
        @Override
        public boolean onInterceptHoverEvent(MotionEvent event) {
            return super.onInterceptHoverEvent(event);
        }
    };
}
