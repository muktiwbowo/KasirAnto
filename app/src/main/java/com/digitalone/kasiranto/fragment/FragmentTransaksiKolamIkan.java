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
import com.digitalone.kasiranto.adapter.AdapterTransaksiKafe;
import com.digitalone.kasiranto.adapter.AdapterTransaksiKolamIkan;
import com.digitalone.kasiranto.model.KafeTransaksi;
import com.digitalone.kasiranto.model.KafeTransaksiItem;
import com.digitalone.kasiranto.model.KolamIkan;
import com.digitalone.kasiranto.model.KolamIkanTransaksiItem;
import com.digitalone.kasiranto.model.KolamTransaksi;
import com.digitalone.kasiranto.service.RestAPIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTransaksiKolamIkan extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    public static final String          TITLE = "Transaksi KolamIkan";

    private SwipeRefreshLayout          refreshLayout;
    private List<KolamIkanTransaksiItem>     items;
    private AdapterTransaksiKolamIkan       adapterTransaksiKafe;
    private RecyclerView                recyclerView;
    private View                        view;
    private TextView                    msgKosong;

    public static FragmentTransaksiKolamIkan newInstance(){
        return new FragmentTransaksiKolamIkan();
    }

    public FragmentTransaksiKolamIkan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view    = inflater.inflate(R.layout.fragment_transaksi_kafe, container, false);
        initView();
        if (items == null){
            msgKosong.setVisibility(View.VISIBLE);
        }else {
            msgKosong.setVisibility(View.GONE);
            getTransaksiKafe();
        }
        return view;
    }

    private void initView() {
        msgKosong           = view.findViewById(R.id.msg_transaksi);
        refreshLayout       = view.findViewById(R.id.refreshkafetr);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                getTransaksiKafe();
            }
        });
        recyclerView        = view.findViewById(R.id.recycletransaksikafe);
        items               = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTransaksiKafe);
    }

    private void getTransaksiKafe() {
        retrofit2.Call<KolamTransaksi> call = RestAPIHelper.ServiceApi(getActivity().getApplication()).getKolamikanTr();
        call.enqueue(new Callback<KolamTransaksi>() {
            @Override
            public void onResponse(Call<KolamTransaksi> call, Response<KolamTransaksi> response) {
                if (response.body() != null){
                    items = response.body().getKafeTransaksi();
                    adapterTransaksiKafe    = new AdapterTransaksiKolamIkan(items, getContext());
                    recyclerView.setAdapter(adapterTransaksiKafe);
                    refreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<KolamTransaksi> call, Throwable t) {
                Toast.makeText(getContext(), "OnFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        items.clear();
        adapterTransaksiKafe.notifyDataSetChanged();
        getTransaksiKafe();
    }
}
