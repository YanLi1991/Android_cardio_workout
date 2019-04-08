package com.yan.cardioworkout.Other;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yan.cardioworkout.SplashFrag1;
import com.yan.cardioworkout.SplashFrag2;

public class SplashScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 2;

    public SplashScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new SplashFrag1();
        }else{
            return new SplashFrag2();
        }

    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
