package com.digitalone.kasiranto.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.adapter.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTransaksi extends Fragment {

    private Toolbar toolbar;
    private View    view;

    public static FragmentTransaksi newInstance(){
        return new FragmentTransaksi();
    }

    public FragmentTransaksi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view    = inflater.inflate(R.layout.fragment_transaksi, container, false);
        initView();
        return view;
    }

    private void initView() {
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Transaksi");
    }

}
