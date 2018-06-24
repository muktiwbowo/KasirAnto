package com.digitalone.kasiranto.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.adapter.AdapterTransaksiWarung;
import com.digitalone.kasiranto.adapter.AdapterTransaksiWarung;
import com.digitalone.kasiranto.model.WarungTransaksi;
import com.digitalone.kasiranto.model.WarungTransaksiItem;
import com.digitalone.kasiranto.service.RestAPIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTransaksiWarung extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    public static final String TITLE = "Transaksi Warung";

    private SwipeRefreshLayout refreshLayout;
    private List<WarungTransaksiItem> items;
    private AdapterTransaksiWarung adapterTransaksiWarung;
    private RecyclerView recyclerView;
    private View                        view;
    private TextView msgKosong;
    public static FragmentTransaksiWarung newInstance(){
        return new FragmentTransaksiWarung();
    }

    public FragmentTransaksiWarung() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view    = inflater.inflate(R.layout.fragment_transaksi_warung, container, false);
        initView();
        if (items == null){
            msgKosong.setVisibility(View.VISIBLE);
        }else {
            msgKosong.setVisibility(View.GONE);
            getTransaksiWarung();
        }
        return view;
    }

    private void initView() {
        msgKosong           = view.findViewById(R.id.msg_transaksi);
        refreshLayout       = view.findViewById(R.id.refreshwarungtr);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                getTransaksiWarung();
            }
        });
        recyclerView        = view.findViewById(R.id.recycletransaksiwarung);
        items               = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTransaksiWarung);
    }

    private void getTransaksiWarung() {
        retrofit2.Call<WarungTransaksi> call = RestAPIHelper.ServiceApi(getActivity().getApplication()).getWarungTr();
        call.enqueue(new Callback<WarungTransaksi>() {
            @Override
            public void onResponse(Call<WarungTransaksi> call, Response<WarungTransaksi> response) {
                if (response.body() != null){
                    items = response.body().getWarungTransaksi();
                    adapterTransaksiWarung    = new AdapterTransaksiWarung(items, getContext());
                    recyclerView.setAdapter(adapterTransaksiWarung);
                    refreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<WarungTransaksi> call, Throwable t) {
                Toast.makeText(getContext(), "OnFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        items.clear();
        adapterTransaksiWarung.notifyDataSetChanged();
        getTransaksiWarung();
    }
}
