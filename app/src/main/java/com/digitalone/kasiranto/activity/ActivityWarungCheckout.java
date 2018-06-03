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
import com.digitalone.kasiranto.adapter.AdapterTokoTemp;
import com.digitalone.kasiranto.adapter.AdapterWarungTemp;
import com.digitalone.kasiranto.helper.DBHelper;
import com.digitalone.kasiranto.model.TokoTemp;
import com.digitalone.kasiranto.model.WarungTemp;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

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

    }
}
