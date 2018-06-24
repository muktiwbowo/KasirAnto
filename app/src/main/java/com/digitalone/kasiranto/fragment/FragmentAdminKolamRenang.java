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
import com.digitalone.kasiranto.adapter.AdapterKolamIkan;
import com.digitalone.kasiranto.adapter.AdapterKolamRenang;
import com.digitalone.kasiranto.adapter.AdapterKolamRenangTemp;
import com.digitalone.kasiranto.adapter.AdapterTiketMasuk;
import com.digitalone.kasiranto.model.AdminMessage;

import com.digitalone.kasiranto.model.KolamRenang;
import com.digitalone.kasiranto.model.KolamRenangItem;
import com.digitalone.kasiranto.model.TiketMasuk;
import com.digitalone.kasiranto.model.TiketMasukItem;
import com.digitalone.kasiranto.service.RestAPIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAdminKolamRenang extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String              TITLE = "Kolam Renang";
    private View                            view;
    private List<KolamRenangItem> items;
    private AdapterKolamRenang kafe;
    private RecyclerView                    recyclerView;
    private FloatingActionButton            fab;
    private AlertDialog.Builder             dialog;
    private EditText                        edtNama,edtHarga,edtStok;
    private String                          nama, harga, stok;
    private LayoutInflater                  inflater;
    private View                            dialogView;
    private SwipeRefreshLayout              refreshLayout;

    public static FragmentAdminKolamRenang newInstance(){
        return new FragmentAdminKolamRenang();
    }

    public FragmentAdminKolamRenang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_kafe, container, false);
        initView();
        return view;
    }

    private void initView() {
        refreshLayout       = view.findViewById(R.id.refreshkafe);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                getTiketMasuk();
            }
        });
        fab                 = view.findViewById(R.id.fab);
        recyclerView        = view.findViewById(R.id.recycleadminkafe);
        fab.setOnClickListener(this);
        items               = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(kafe);
    }


    private void dialogForm(){
        dialog      = new AlertDialog.Builder(getActivity());
        inflater    = getLayoutInflater();
        dialogView  = inflater.inflate(R.layout.form_dialog, null);
        edtHarga            = dialogView.findViewById(R.id.kafe_form_harga);
        edtNama             = dialogView.findViewById(R.id.kafe_form_nama);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Form Item Tiket Masuk");
        dialog.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nama                = edtNama.getText().toString();
                harga               = edtHarga.getText().toString();
                insertKolamRenang(nama,harga);
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

    private void insertKolamRenang(String nama, String harga){
        retrofit2.Call<AdminMessage> call = RestAPIHelper.ServiceApi(getActivity().getApplication()).insertKolamRenang(nama,harga);
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

    private void getTiketMasuk(){
        retrofit2.Call<KolamRenang> call = RestAPIHelper.ServiceApi(getActivity().getApplication()).getKolamRenang();
        call.enqueue(new Callback<KolamRenang>() {
            @Override
            public void onResponse(Call<KolamRenang> call, Response<KolamRenang> response) {
                if (response.body() != null){
                    items = response.body().getKafe();
                    kafe = new AdapterKolamRenang(items, getContext());
                    recyclerView.setAdapter(kafe);
                }else {
                    Toast.makeText(getContext(), "Error response", Toast.LENGTH_SHORT).show();
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<KolamRenang> call, Throwable t) {
                refreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                dialogForm();
                break;
        }
    }

    @Override
    public void onRefresh() {
        items.clear();
        kafe.notifyDataSetChanged();
        getTiketMasuk();
    }
}
