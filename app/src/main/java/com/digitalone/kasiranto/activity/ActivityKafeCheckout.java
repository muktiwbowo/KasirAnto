package com.digitalone.kasiranto.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.helper.DBHelper;
import com.digitalone.kasiranto.adapter.AdapterKafeTemp;
import com.digitalone.kasiranto.model.AdminMessage;
import com.digitalone.kasiranto.model.KafeTemp;
import com.digitalone.kasiranto.service.RestAPIHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityKafeCheckout extends AppCompatActivity implements View.OnClickListener {


    private AdapterKafeTemp tempAdapter;
    private RecyclerView rvTemp;
    private List<KafeTemp> temps;
    private Toolbar toolBar;
    private TextView noItemsView;
    private Button btnCheckout;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kafe_checkout);
        initViews();
        getPesanan();
        //convertToJSONArray();
    }

    public void initViews() {
        toolBar         = findViewById(R.id.toolbardetail);
        btnCheckout     = findViewById(R.id.btn_checkout);
        rvTemp          = findViewById(R.id.rv_temp);
        noItemsView     = findViewById(R.id.empty_notes_view);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Pesanan");
        db              = new DBHelper(getApplicationContext());
        temps           = new ArrayList<>();
        btnCheckout.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void getPesanan() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvTemp.setLayoutManager(layoutManager);
        tempAdapter = new AdapterKafeTemp(temps, this);
        rvTemp.setAdapter(tempAdapter);
        toggleEmptyList();
        temps.addAll(db.getAllKafeTemps());
    }

    private void insertPesanan(){
        ArrayList<KafeTemp> items = (ArrayList<KafeTemp>) db.getAllKafeTemps();
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(items, new TypeToken<ArrayList<KafeTemp>>() {}.getType());
        if (! element.isJsonArray()){
            Log.d("tes", "gagal");
        }
        JsonArray jsonArray = element.getAsJsonArray();
        JsonObject object = new JsonObject();
        object.getAsJsonObject(String.valueOf(jsonArray));

        retrofit2.Call<AdminMessage> call = RestAPIHelper.ServiceApi(getApplication()).ordered(jsonArray);
        call.enqueue(new Callback<AdminMessage>() {
            @Override
            public void onResponse(@NonNull Call<AdminMessage> call, @NonNull Response<AdminMessage> response) {
                if (response.body() != null) {
                      boolean error = response.body().getError();
                    String message = response.body().getMsg();
                    if (error == false) {
                        Log.v(ActivityKafeCheckout.class.getSimpleName(), message);
                        Toast.makeText(ActivityKafeCheckout.this, "Transaksi berhasil disimpan", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.v(ActivityKafeCheckout.class.getSimpleName(), message);
                        Toast.makeText(ActivityKafeCheckout.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AdminMessage> call, @NonNull Throwable t) {
                Toast.makeText(ActivityKafeCheckout.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toggleEmptyList() {
        if (db.getKafeTempsCount() > 0) {
            noItemsView.setVisibility(View.GONE);
        } else {
            noItemsView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_checkout:
                insertPesanan();
                db.deleteAllKafe();
                initViews();
                getPesanan();
                ActivityKafeRenang.fa.finish();
                Intent myintent = new Intent(this,ActivityKafeRenang.class);
                startActivity(myintent);
                finish();
                //convertToJSONArray();
                break;
        }
    }
    public  void refresh(){
        initViews();
        getPesanan();
    }
}
