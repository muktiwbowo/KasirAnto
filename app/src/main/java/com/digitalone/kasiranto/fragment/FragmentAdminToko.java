package com.digitalone.kasiranto.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.adapter.AdapterKafe;
import com.digitalone.kasiranto.adapter.AdapterToko;
import com.digitalone.kasiranto.model.AdminMessage;
import com.digitalone.kasiranto.model.Kafe;
import com.digitalone.kasiranto.model.Toko;
import com.digitalone.kasiranto.model.TokoItem;
import com.digitalone.kasiranto.service.RestAPIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAdminToko extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{
    public static final String TITLE = "Toko";
    private View                            view;
    private List<TokoItem>                  items;
    private AdapterToko                     adapterToko;
    private RecyclerView                    recyclerView;
    private FloatingActionButton            fab;
    private AlertDialog.Builder             dialog;
    private EditText                        edtNama,edtHarga,edtStok;
    private String                          nama, harga, stok;
    private LayoutInflater                  inflater;
    private View                            dialogView;
    private SwipeRefreshLayout              refreshLayout;


    public static FragmentAdminToko newInstance(){
        return new FragmentAdminToko();
    }

    public FragmentAdminToko() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_toko, container, false);
        initView();
        return view;
    }
    private void initView() {
        refreshLayout       = view.findViewById(R.id.refreshtoko);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                getTokos();
            }
        });
        fab                 = view.findViewById(R.id.fabtoko);
        recyclerView        = view.findViewById(R.id.recycleadmintoko);
        fab.setOnClickListener(this);
        items               = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterToko);
    }

    private void dialogForm(){
        dialog      = new AlertDialog.Builder(getActivity());
        inflater    = getLayoutInflater();
        dialogView  = inflater.inflate(R.layout.form_toko, null);
        edtHarga            = dialogView.findViewById(R.id.toko_form_harga);
        edtNama             = dialogView.findViewById(R.id.toko_form_nama);
        edtStok             = dialogView.findViewById(R.id.toko_form_stok);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Form Item Toko");
        dialog.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nama                = edtNama.getText().toString();
                harga               = edtHarga.getText().toString();
                stok                = edtStok.getText().toString();
                insertToko(nama,harga,stok);
            }
        });

        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void insertToko(String nama, String harga, String stok){
        retrofit2.Call<AdminMessage> call = RestAPIHelper.ServiceApi(getActivity().getApplication()).insertToko(nama,harga,stok);
        call.enqueue(new Callback<AdminMessage>() {
            @Override
            public void onResponse(Call<AdminMessage> call, Response<AdminMessage> response) {
                if (response.body() != null){
                    boolean error   = response.body().getError();
                    String msg      = response.body().getMsg();
                    if (!error){
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                    onRefresh();
                }

            }

            @Override
            public void onFailure(Call<AdminMessage> call, Throwable t) {
                Toast.makeText(getContext(), "Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTokos(){
        retrofit2.Call<Toko> call = RestAPIHelper.ServiceApi(getActivity().getApplication()).getToko();
        call.enqueue(new Callback<Toko>() {
            @Override
            public void onResponse(Call<Toko> call, Response<Toko> response) {
                if (response.body() != null){
                    items = response.body().getToko();
                    adapterToko = new AdapterToko(items, getContext());
                    recyclerView.setAdapter(adapterToko);
                }else {
                    Toast.makeText(getContext(), "Error response", Toast.LENGTH_SHORT).show();
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Toko> call, Throwable t) {
                refreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabtoko:
                dialogForm();
                break;
        }
    }

    @Override
    public void onRefresh() {
        items.clear();
        adapterToko.notifyDataSetChanged();
        getTokos();
    }
}
