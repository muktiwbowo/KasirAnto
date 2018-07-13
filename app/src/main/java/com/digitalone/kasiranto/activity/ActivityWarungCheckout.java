package com.digitalone.kasiranto.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.adapter.AdapterTokoTemp;
import com.digitalone.kasiranto.adapter.AdapterWarungTemp;
import com.digitalone.kasiranto.fragment.FragmentKolamIkan;
import com.digitalone.kasiranto.helper.DBHelper;
import com.digitalone.kasiranto.model.AdminMessage;
import com.digitalone.kasiranto.model.WarungTemp;
import com.digitalone.kasiranto.model.TokoTemp;
import com.digitalone.kasiranto.model.WarungTemp;
import com.digitalone.kasiranto.service.RestAPIHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityWarungCheckout extends AppCompatActivity implements View.OnClickListener{

    private AdapterWarungTemp       warungAdapter;
    private RecyclerView            rvTemp;
    private List<WarungTemp>        temps;
    private Toolbar                 toolBar;
    private TextView                noItemsView;
    private Button                  btnCheckout;
    JSONArray                       arrayItems;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warung_checkout);
        initViews();
        getPesanan();
    }

    private void initViews() {
        toolBar         = findViewById(R.id.toolbarwarungdetail);
        btnCheckout     = findViewById(R.id.btn_checkout_warung);
        rvTemp          = findViewById(R.id.rv_temp_warung);
        noItemsView     = findViewById(R.id.empty_notes_view);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Pesanan");
        db              = new DBHelper(this);
        temps           = new ArrayList<>();
        btnCheckout.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
       finish();

        return super.onSupportNavigateUp();
    }

    private void insertPesanan(){
        ArrayList<WarungTemp> items = (ArrayList<WarungTemp>) db.getAllWarungTemps();
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(items, new TypeToken<ArrayList<WarungTemp>>() {}.getType());
        if (! element.isJsonArray()){
            Log.d("tes", "gagal");
        }
        JsonArray jsonArray = element.getAsJsonArray();
        JsonObject object = new JsonObject();
        object.getAsJsonObject(String.valueOf(jsonArray));

        retrofit2.Call<AdminMessage> call = RestAPIHelper.ServiceApi(getApplication()).orderedWarung(jsonArray);
        call.enqueue(new Callback<AdminMessage>() {
            @Override
            public void onResponse(@NonNull Call<AdminMessage> call, @NonNull Response<AdminMessage> response) {
                if (response.body() != null) {
                    boolean error = response.body().getError();
                    String message = response.body().getMsg();
                    if (error == false) {
                        Log.v(ActivityWarungCheckout.class.getSimpleName(), message);
                        Toast.makeText(ActivityWarungCheckout.this, "Transaksi berhasil disimpan", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.v(ActivityWarungCheckout.class.getSimpleName(), message);
                        Toast.makeText(ActivityWarungCheckout.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AdminMessage> call, @NonNull Throwable t) {
                Toast.makeText(ActivityWarungCheckout.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertAllTransaksi(){
        ArrayList<WarungTemp> items = (ArrayList<WarungTemp>) db.getAllWarungTemps();
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(items, new TypeToken<ArrayList<WarungTemp>>() {}.getType());
        if (! element.isJsonArray()){
            Log.d("tes", "gagal");
        }
        JsonArray jsonArray = element.getAsJsonArray();
        JsonObject object = new JsonObject();
        object.getAsJsonObject(String.valueOf(jsonArray));

        retrofit2.Call<AdminMessage> call = RestAPIHelper.ServiceApi(getApplication()).transaksiAllWarung(jsonArray);
        call.enqueue(new Callback<AdminMessage>() {
            @Override
            public void onResponse(@NonNull Call<AdminMessage> call, @NonNull Response<AdminMessage> response) {
                if (response.body() != null) {
                    boolean error = response.body().getError();
                    String message = response.body().getMsg();
                    if (error == false) {
                        Log.v(ActivityWarungCheckout.class.getSimpleName(), message);
                        Toast.makeText(ActivityWarungCheckout.this, "Transaksi berhasil disimpan", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.v(ActivityWarungCheckout.class.getSimpleName(), message);
                        Toast.makeText(ActivityWarungCheckout.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AdminMessage> call, @NonNull Throwable t) {
                Toast.makeText(ActivityWarungCheckout.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPesanan() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvTemp.setLayoutManager(layoutManager);
        warungAdapter = new AdapterWarungTemp(temps, this);
        rvTemp.setAdapter(warungAdapter);
        toggleEmptyList();
        temps.addAll(db.getAllWarungTemps());
    }

    private void toggleEmptyList() {
        if (db.getWarungTempsCount() > 0) {
            noItemsView.setVisibility(View.GONE);
        } else {
            noItemsView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_checkout_warung:
                insertPesanan();
                insertAllTransaksi();
                db.deleteallWarung();
                initViews();
                getPesanan();

                ActivityWarung.fa.finish();
                Intent myIntent = new Intent(ActivityWarungCheckout.this, ActivityWarung.class);
                 startActivity(myIntent);
                    finish();
                //convertToJSONArray();
                break;
        }
    }
    public void refresh(){

        initViews();
        getPesanan();

    }
}
