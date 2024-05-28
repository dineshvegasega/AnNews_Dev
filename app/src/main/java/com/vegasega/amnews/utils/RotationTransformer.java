package com.vegasega.amnews.utils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class RotationTransformer implements ViewPager2.PageTransformer {

    private static final float MAX_ROTATION = 90f;
    private static final float MIN_SCALE = 0.9f;

    @Override
    public void transformPage(@NonNull View view, float position) {
        int width = view.getWidth();
        int height = view.getHeight();

        view.setTranslationX(-position * width * 0.8f);
        view.setPivotY(height / 2f);

        if (position < -1) {
            view.setRotationY(-MAX_ROTATION);
            view.setPivotX(0f);
        } else if (position <= 1) {
            if (position < 0) {
                view.setRotationY(position * position * MAX_ROTATION);
                view.setPivotX(0f);
                float scale = MIN_SCALE + 4f * (1f - MIN_SCALE) *
                        (position + 0.5f) * (position + 0.5f);
                view.setScaleX(scale);
                view.setScaleY(scale);
            } else {
                view.setRotationY(-position * position * MAX_ROTATION);
                view.setPivotX(width);
                float scale = MIN_SCALE + 4f * (1f - MIN_SCALE) *
                        (position - 0.5f) * (position - 0.5f);
                view.setScaleX(scale);
                view.setScaleY(scale);
            }
        } else {
            view.setRotationY(MAX_ROTATION);
            view.setPivotX(view.getWidth());
        }
    }
}