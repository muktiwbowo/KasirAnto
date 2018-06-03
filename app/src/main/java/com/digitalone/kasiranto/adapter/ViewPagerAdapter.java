package com.digitalone.kasiranto.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.digitalone.kasiranto.fragment.FragmentAdminKafe;
import com.digitalone.kasiranto.fragment.FragmentAdminToko;
import com.digitalone.kasiranto.fragment.FragmentAdminWarung;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static int TAB_COUNT = 3;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FragmentAdminKafe.newInstance();
            case 1:
                return FragmentAdminToko.newInstance();
            case 2:
                return FragmentAdminWarung.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return FragmentAdminKafe.TITLE;
            case 1:
                return FragmentAdminToko.TITLE;
            case 2:
                return FragmentAdminWarung.TITLE;
        }
        return super.getPageTitle(position);
    }
}
