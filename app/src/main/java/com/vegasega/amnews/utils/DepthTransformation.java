package com.vegasega.amnews.utils;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

public class DepthTransformation implements ViewPager2.PageTransformer{
    @Override
    public void transformPage(View page, float position) {
        if (position < -1.0f) {
            page.setAlpha(0.0f);
        } else if (position <= 0.0f) {
            page.setAlpha(1.0f);
            page.setTranslationX(0.0f);
            page.setScaleX(1.0f);
            page.setScaleY(1.0f);
        } else if (position <= 1.0f) {
            page.setTranslationX((-position) * ((float) page.getWidth()));
            page.setAlpha(0.7f - Math.abs(position));
            page.setScaleX(1.0f - Math.abs(position));
            page.setScaleY(1.0f - Math.abs(position));
        } else {
            page.setAlpha(0.0f);
        }
    }

}
