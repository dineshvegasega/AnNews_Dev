package com.vegasega.amnews.ui.main.home;

import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class NewPagerAdapter extends FragmentPagerAdapter {

//    private static int NUM_ITEMS = 3;
//    private Map<Integer, Fragment> mFragmentTags;
//    private FragmentManager mFragmentManager;

    ArrayList<FirstFragment> fragmentList = new ArrayList<>();

    public NewPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object object = super.instantiateItem(container, position);
        if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            String tag = fragment.getTag();
            //mFragmentTags.put(position, tag);
        }
        return object;
    }

    public FirstFragment getFragment(int position) {
        return fragmentList.get(position);
    }


    public void addFragments(FirstFragment fragment){
        fragmentList.add(fragment);
    }

}
