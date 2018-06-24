package com.digitalone.kasiranto.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.activity.ActivityFilter;
import com.digitalone.kasiranto.adapter.AdapterViewPagerTransaksi;
import com.digitalone.kasiranto.adapter.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTransaksi extends Fragment implements View.OnClickListener{

    private Toolbar                     toolbar;
    private View                        view;
    private AdapterViewPagerTransaksi   adapter;
    private TabLayout                   tabLayout;
    private ViewPager                   viewPager;
    private FloatingActionButton        fab;


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
        fab     = view.findViewById(R.id.fabfilter);
        toolbar = view.findViewById(R.id.toolbar);
        fab.setOnClickListener(this);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Transaksi");
        viewPager = view.findViewById(R.id.pagertransaksi);
        tabLayout = view.findViewById(R.id.tabtransaksi);
        adapter = new AdapterViewPagerTransaksi(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabfilter:
                startActivity(new Intent(getActivity(), ActivityFilter.class));
                break;
        }
    }
}
