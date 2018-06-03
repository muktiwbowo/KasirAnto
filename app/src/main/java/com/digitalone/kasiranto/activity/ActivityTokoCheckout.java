package com.digitalone.kasiranto.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.adapter.AdapterKafeTemp;
import com.digitalone.kasiranto.adapter.AdapterTokoTemp;
import com.digitalone.kasiranto.helper.DBHelper;
import com.digitalone.kasiranto.model.KafeTemp;
import com.digitalone.kasiranto.model.TokoTemp;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ActivityTokoCheckout extends AppCompatActivity implements View.OnClickListener{

    private AdapterTokoTemp tokoAdapter;
    private RecyclerView rvTemp;
    private List<TokoTemp> temps;
    private Toolbar toolBar;
    private TextView noItemsView;
    private Button btnCheckout;
    JSONArray arrayItems;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toko_checkout);
        initViews();
        getPesanan();
    }

    public void initViews() {
        toolBar         = findViewById(R.id.toolbartokodetail);
        btnCheckout     = findViewById(R.id.btn_checkout_toko);
        rvTemp          = findViewById(R.id.rv_temp_toko);
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

    public void getPesanan() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvTemp.setLayoutManager(layoutManager);
        tokoAdapter = new AdapterTokoTemp(temps, this);
        rvTemp.setAdapter(tokoAdapter);
        toggleEmptyList();
        temps.addAll(db.getAllTokoTemps());
    }

    private void toggleEmptyList() {
        if (db.getTokoTempsCount() > 0) {
            noItemsView.setVisibility(View.GONE);
        } else {
            noItemsView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
