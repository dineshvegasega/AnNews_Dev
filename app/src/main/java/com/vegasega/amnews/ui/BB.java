package com.vegasega.amnews.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.vegasega.amnews.R;
import com.vegasega.amnews.utils.VerticalViewPager;

public class BB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        initSwipePager();
    }

    private void initSwipePager(){
        VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.introViewPager);
//        verticalViewPager.setAdapter(new VerticlePagerAdapter(this));
    }
}