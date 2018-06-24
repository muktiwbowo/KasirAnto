package com.digitalone.kasiranto.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.digitalone.kasiranto.fragment.FragmentAdminKafe;
import com.digitalone.kasiranto.fragment.FragmentAdminKolamIkan;
import com.digitalone.kasiranto.fragment.FragmentAdminKolamRenang;
import com.digitalone.kasiranto.fragment.FragmentAdminTiketMasuk;
import com.digitalone.kasiranto.fragment.FragmentAdminToko;
import com.digitalone.kasiranto.fragment.FragmentAdminWarung;
import com.digitalone.kasiranto.fragment.FragmentKolamRenang;
import com.digitalone.kasiranto.fragment.FragmentWarung;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static int TAB_COUNT = 6;
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
            case 3 :
                return FragmentAdminKolamIkan.newInstance();
            case 4 :
                return FragmentAdminTiketMasuk.newInstance();
            case  5 :
                return FragmentAdminKolamRenang.newInstance();
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
            case 3 :
                return FragmentAdminKolamIkan.TITLE;
            case 4 :
                return FragmentAdminTiketMasuk.TITLE;
            case 5 :
                return FragmentAdminKolamRenang.TITLE;
        }
        return super.getPageTitle(position);
    }
}
