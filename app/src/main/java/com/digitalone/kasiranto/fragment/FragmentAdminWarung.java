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
import com.digitalone.kasiranto.adapter.AdapterWarung;
import com.digitalone.kasiranto.adapter.AdapterWarung;
import com.digitalone.kasiranto.model.AdminMessage;
import com.digitalone.kasiranto.model.Warung;
import com.digitalone.kasiranto.model.WarungItem;
import com.digitalone.kasiranto.model.WarungItem;
import com.digitalone.kasiranto.service.RestAPIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAdminWarung extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{

    public static final String TITLE = "Warung";
    private View                            view;
    private List<WarungItem> items;
    private AdapterWarung adapterWarung;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private AlertDialog.Builder             dialog;
    private EditText edtNama,edtHarga,edtStok;
    private String                          nama, harga, stok;
    private LayoutInflater                  inflater;
    private View                            dialogView;
    private SwipeRefreshLayout              refreshLayout;
    
    public static FragmentAdminWarung newInstance(){return new FragmentAdminWarung();}

    public FragmentAdminWarung() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_warung, container, false);
        initView();
        return view;
    }

    private void initView() {
        refreshLayout       = view.findViewById(R.id.refreshwarung);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                getWarungs();
            }
        });
        fab                 = view.findViewById(R.id.fabwarung);
        recyclerView        = view.findViewById(R.id.recycleadminwarung);
        fab.setOnClickListener(this);
        items               = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterWarung);
    }

    private void dialogForm(){
        dialog      = new AlertDialog.Builder(getActivity());
        inflater    = getLayoutInflater();
        dialogView  = inflater.inflate(R.layout.form_warung, null);
        edtHarga            = dialogView.findViewById(R.id.warung_form_harga);
        edtNama             = dialogView.findViewById(R.id.warung_form_nama);
        edtStok             = dialogView.findViewById(R.id.warung_form_stok);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Form Item Warung");
        dialog.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nama                = edtNama.getText().toString();
                harga               = edtHarga.getText().toString();
                stok                = edtStok.getText().toString();
                insertWarung(nama,harga,stok);
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

    private void insertWarung(String nama, String harga, String stok){
        retrofit2.Call<AdminMessage> call = RestAPIHelper.ServiceApi(getActivity().getApplication()).insertWarung(nama,harga,stok);
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

    private void getWarungs(){
        retrofit2.Call<Warung> call = RestAPIHelper.ServiceApi(getActivity().getApplication()).getWarung();
        call.enqueue(new Callback<Warung>() {
            @Override
            public void onResponse(Call<Warung> call, Response<Warung> response) {
                if (response.body() != null){
                    items = response.body().getWarung();
                    adapterWarung = new AdapterWarung(items, getContext());
                    recyclerView.setAdapter(adapterWarung);
                }else {
                    Toast.makeText(getContext(), "Error response", Toast.LENGTH_SHORT).show();
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Warung> call, Throwable t) {
                refreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        items.clear();
        adapterWarung.notifyDataSetChanged();
        getWarungs();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabwarung:
                dialogForm();
                break;
        }
    }
}
