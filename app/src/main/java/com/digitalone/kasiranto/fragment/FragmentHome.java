package com.digitalone.kasiranto.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
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
public class FragmentHome extends Fragment {

    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    private Toolbar toolbar;

    public static FragmentHome newInstance(){
        return new FragmentHome();
    }
    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        return view;
    }

    private void initView() {
        toolbar = view.findViewById(R.id.toolbarsuper);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Beranda");
        viewPager = view.findViewById(R.id.pager);
        tabLayout = view.findViewById(R.id.tab);
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
