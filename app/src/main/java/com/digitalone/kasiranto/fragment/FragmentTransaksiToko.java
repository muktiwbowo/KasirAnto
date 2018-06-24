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
import com.digitalone.kasiranto.adapter.AdapterTransaksiToko;
import com.digitalone.kasiranto.adapter.AdapterTransaksiToko;
import com.digitalone.kasiranto.model.TokoTransaksi;
import com.digitalone.kasiranto.model.TokoTransaksiItem;
import com.digitalone.kasiranto.service.RestAPIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTransaksiToko extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    public static final String TITLE = "Transaksi Toko";

    private SwipeRefreshLayout refreshLayout;
    private List<TokoTransaksiItem> items;
    private AdapterTransaksiToko adapterTransaksiToko;
    private RecyclerView recyclerView;
    private View                        view;
    private TextView msgKosong;
    public static FragmentTransaksiToko newInstance(){
        return new FragmentTransaksiToko();
    }

    public FragmentTransaksiToko() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view    = inflater.inflate(R.layout.fragment_transaksi_toko, container, false);
        initView();
        if (items == null){
            msgKosong.setVisibility(View.VISIBLE);
        }else {
            msgKosong.setVisibility(View.GONE);
            getTransaksiToko();
        }
        return view;
    }

    private void initView() {
        msgKosong           = view.findViewById(R.id.msg_transaksi);
        refreshLayout       = view.findViewById(R.id.refrestokotr);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                getTransaksiToko();
            }
        });
        recyclerView        = view.findViewById(R.id.recycletransaksitoko);
        items               = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTransaksiToko);
    }

    private void getTransaksiToko() {
        retrofit2.Call<TokoTransaksi> call = RestAPIHelper.ServiceApi(getActivity().getApplication()).getTokoTr();
        call.enqueue(new Callback<TokoTransaksi>() {
            @Override
            public void onResponse(Call<TokoTransaksi> call, Response<TokoTransaksi> response) {
                if (response.body() != null){
                    items = response.body().getTokoTransaksi();
                    adapterTransaksiToko    = new AdapterTransaksiToko(items, getContext());
                    recyclerView.setAdapter(adapterTransaksiToko);
                    refreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<TokoTransaksi> call, Throwable t) {
                Toast.makeText(getContext(), "OnFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        items.clear();
        adapterTransaksiToko.notifyDataSetChanged();
        getTransaksiToko();
    }
}
