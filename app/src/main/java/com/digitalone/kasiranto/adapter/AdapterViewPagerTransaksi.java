package com.digitalone.kasiranto.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.digitalone.kasiranto.fragment.FragmentAdminKolamRenang;
import com.digitalone.kasiranto.fragment.FragmentTransaksiKafe;
import com.digitalone.kasiranto.fragment.FragmentTransaksiKolamIkan;
import com.digitalone.kasiranto.fragment.FragmentTransaksiToko;
import com.digitalone.kasiranto.fragment.FragmentTransaksiWarung;

public class AdapterViewPagerTransaksi extends FragmentStatePagerAdapter {
    private static int TAB_COUNT = 5;

    public AdapterViewPagerTransaksi(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FragmentTransaksiKafe.newInstance();
            case 1:
                return FragmentTransaksiToko.newInstance();
            case 2:
                return FragmentTransaksiWarung.newInstance();
            case 3 :
                return FragmentTransaksiKolamIkan.newInstance();
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
        switch (position){
            case 0:
                return FragmentTransaksiKafe.TITLE;
            case 1:
                return FragmentTransaksiToko.TITLE;
            case 2:
                return FragmentTransaksiWarung.TITLE;
            case 3 :
                return FragmentTransaksiKolamIkan.TITLE;
        }
        return super.getPageTitle(position);
    }
}
