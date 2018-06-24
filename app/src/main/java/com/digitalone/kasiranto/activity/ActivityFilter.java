package com.digitalone.kasiranto.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.adapter.AdapterKafeDate;
import com.digitalone.kasiranto.adapter.AdapterKolamIkanDate;
import com.digitalone.kasiranto.adapter.AdapterKolamRenangDate;
import com.digitalone.kasiranto.adapter.AdapterTiketMasukDate;
import com.digitalone.kasiranto.adapter.AdapterTokoDate;
import com.digitalone.kasiranto.adapter.AdapterWarungDate;
import com.digitalone.kasiranto.model.KafeDate;
import com.digitalone.kasiranto.model.KafeDateItem;
import com.digitalone.kasiranto.model.KolamIkan;
import com.digitalone.kasiranto.model.KolamIkanDate;
import com.digitalone.kasiranto.model.KolamIkanDateItem;
import com.digitalone.kasiranto.model.KolamRenang;
import com.digitalone.kasiranto.model.KolamRenangDate;
import com.digitalone.kasiranto.model.KolamrenangDateItem;
import com.digitalone.kasiranto.model.TiketMasukDate;
import com.digitalone.kasiranto.model.TiketMasukDateItem;
import com.digitalone.kasiranto.model.TokoDate;
import com.digitalone.kasiranto.model.TokoDateItem;
import com.digitalone.kasiranto.model.WarungDate;
import com.digitalone.kasiranto.model.WarungDateItem;
import com.digitalone.kasiranto.service.RestAPIHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityFilter extends AppCompatActivity implements View.OnClickListener{
    private TextView            txtDari, txtKe, totalFilter;
    private Button              btnCariKafe, btnCariToko, btnCariWarung,btncarikolamikan, btnkolamrenang,btntiketmasuk;
    private LinearLayout        btnDari, btnKe;
    private DatePickerDialog dateFrom, dateTo;
    private SimpleDateFormat dateFormatter, dateToFormatter;
    private SwipeRefreshLayout  refreshLayout;
    private List<KafeDateItem>  items;
    private List<KolamrenangDateItem> itemskolamrenang;
    private List<TokoDateItem>  tokoDateItems;
    private List<KolamIkanDateItem> kolamIkanDateItems;
    private List<WarungDateItem> warungDateItems;
    private List<TiketMasukDateItem>    tiketMasukDateItems;
    private AdapterTiketMasukDate       adapterTiketMasukDate;
    private AdapterKafeDate     adapterKafeDate;
    private AdapterTokoDate     adapterTokoDate;
    private AdapterKolamRenangDate adapterKolamDate;
    private AdapterWarungDate   adapterWarungDate;
    private AdapterKolamIkanDate    adapterKolamIkanDate;
    private RecyclerView        recyclerView;
    private Toolbar             toolBar;
    private String              dari, ke;
    private int                 total, totaltoko, totalwarung,totalkolamikan,totalkolamrenang, totaltiketmasuk;
    private String              totalfilter, totalfiltertoko, totalfilterwarung, totalfilterkolamikan,totalfilterkolamrenang, totalfiltertiketmasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        initView();
    }

    private void initView(){
        total       = 0;
        totaltoko   = 0;
        totalkolamikan = 0;
        totalwarung = 0;
        toolBar     = findViewById(R.id.toolbarfilter);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Filter Transaksi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnCariKafe     = findViewById(R.id.btn_cari_kafe);
        btnCariToko     = findViewById(R.id.btn_cari_toko);
        btnCariWarung   = findViewById(R.id.btn_cari_warung);
        txtDari     = findViewById(R.id.fromkafe);
        txtKe       = findViewById(R.id.tokafe);
        totalFilter = findViewById(R.id.totalfilter);
        btnDari     = findViewById(R.id.btn_from_kafe);
        btnKe       = findViewById(R.id.btn_to_kafe);
        refreshLayout   = findViewById(R.id.refreshfilter);
        btncarikolamikan = findViewById(R.id.btn_cari_kolamikan);
        btntiketmasuk   = findViewById(R.id.btn_cari_tiketMasuk);
        recyclerView    = findViewById(R.id.recyclefilter);
        btnkolamrenang = findViewById(R.id.btn_cari_KolamRenang);
        items           = new ArrayList<>();
        tiketMasukDateItems = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterKafeDate);

        btnDari.setOnClickListener(this);
        btnCariToko.setOnClickListener(this);
        btnCariWarung.setOnClickListener(this);
        btnKe.setOnClickListener(this);
        btnCariKafe.setOnClickListener(this);
        btncarikolamikan.setOnClickListener(this);
        btnkolamrenang.setOnClickListener(this);
        btntiketmasuk.setOnClickListener(this);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateToFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }

    private void showDateFrom(){
        Calendar newCalendar = Calendar.getInstance();
        dateFrom = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txtDari.setText(""+dateFormatter.format(newDate.getTime()));
                dari = txtDari.getText().toString();
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dateFrom.show();
    }

    private void showDateTo(){
        Calendar newCalendar = Calendar.getInstance();
        dateTo= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                txtKe.setText(""+dateToFormatter.format(newDate.getTime()));
                ke = txtKe.getText().toString();
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dateTo.show();
    }

    private void getFilterKafe(String from, String to){
        retrofit2.Call<KafeDate> call = RestAPIHelper.ServiceApi(getApplication()).filterKafeDate(from, to);
        call.enqueue(new Callback<KafeDate>() {
            @Override
            public void onResponse(Call<KafeDate> call, Response<KafeDate> response) {
                if (response.body() != null){
                    boolean error = response.body().getError();
                    if (!error) {
                        items = response.body().getDateKafe();
                        for (int i=0; i<items.size(); i++){
                            //totali.add(Integer.valueOf(items.get(i).getKtTotal()));
                            totalfilter = items.get(i).getKtTotal();
                            total       = total + Integer.parseInt(totalfilter);
                            //Toast.makeText(ActivityFilter.this, ""+total, Toast.LENGTH_SHORT).show();
                        }

                        totalFilter.setText(String.valueOf(total));
                        adapterKafeDate = new AdapterKafeDate(items, getApplicationContext());
                        recyclerView.setAdapter(adapterKafeDate);
                        Log.d("response", String.valueOf(items));
                    }
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<KafeDate> call, Throwable t) {
                Log.e(ActivityFilter.class.getSimpleName(),t.getMessage());
                Toast.makeText(ActivityFilter.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getFilterKolamIkan(String from, String to){
        retrofit2.Call<KolamIkanDate> call = RestAPIHelper.ServiceApi(getApplication()).filterKolamDate(from, to);
        call.enqueue(new Callback<KolamIkanDate>() {
            @Override
            public void onResponse(Call<KolamIkanDate> call, Response<KolamIkanDate> response) {
                if (response.body() != null){
                    boolean error = response.body().getError();
                    if (!error) {
                        kolamIkanDateItems = response.body().getDateKafe();
                        for (int i=0; i<kolamIkanDateItems.size(); i++){
                            //totali.add(Integer.valueOf(items.get(i).getKtTotal()));
                            totalfilterkolamikan = kolamIkanDateItems.get(i).getKtTotal();
                            totalkolamikan       = totalkolamikan + Integer.parseInt(totalfilterkolamikan);
                            //Toast.makeText(ActivityFilter.this, ""+total, Toast.LENGTH_SHORT).show();
                        }
                        totalFilter.setText(String.valueOf(totalkolamikan));
                        adapterKolamIkanDate = new AdapterKolamIkanDate(kolamIkanDateItems, getApplicationContext());
                        recyclerView.setAdapter(adapterKolamIkanDate);
                        Log.d("response", String.valueOf(warungDateItems));
                    }
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<KolamIkanDate> call, Throwable t) {
                Log.e(ActivityFilter.class.getSimpleName(),t.getMessage());
                Toast.makeText(ActivityFilter.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFilterKolamrenang(String from, String to){
        retrofit2.Call<KolamRenangDate> call = RestAPIHelper.ServiceApi(getApplication()).filterKolamRenangDate(from, to);
        call.enqueue(new Callback<KolamRenangDate>() {
            @Override
            public void onResponse(Call<KolamRenangDate> call, Response<KolamRenangDate> response) {
                if (response.body() != null){
                    boolean error = response.body().getError();
                    if (!error) {
                        itemskolamrenang = response.body().getDateKafe();
                        for (int i = 0; i < itemskolamrenang.size(); i++) {
                            //totali.add(Integer.valueOf(items.get(i).getKtTotal()));
                            totalfilterkolamrenang = itemskolamrenang.get(i).getKtTotal();
                            totalkolamrenang = totalkolamrenang + Integer.parseInt(totalfilterkolamrenang);
                            //Toast.makeText(ActivityFilter.this, ""+total, Toast.LENGTH_SHORT).show();
                        }
                        totalFilter.setText(String.valueOf(totalkolamrenang));
                        adapterKolamDate = new AdapterKolamRenangDate(itemskolamrenang, getApplicationContext());
                        recyclerView.setAdapter(adapterKolamDate);
                    }
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<KolamRenangDate> call, Throwable t) {
                Log.e(ActivityFilter.class.getSimpleName(),t.getMessage());
                Toast.makeText(ActivityFilter.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFilterTiketMasuk(String from, String to){
        retrofit2.Call<TiketMasukDate> call = RestAPIHelper.ServiceApi(getApplication()).filterTiketMasukDate(from, to);
        call.enqueue(new Callback<TiketMasukDate>() {
            @Override
            public void onResponse(Call<TiketMasukDate> call, Response<TiketMasukDate> response) {
                if (response.body() != null){
                    boolean error = response.body().getError();
                    if (!error) {
                        tiketMasukDateItems = response.body().getTiketmasukDate();
                        for (int i = 0; i < tiketMasukDateItems.size(); i++) {
                            //totali.add(Integer.valueOf(items.get(i).getKtTotal()));
                            totalfiltertiketmasuk = tiketMasukDateItems.get(i).getTmtTotal();
                            totaltiketmasuk = totaltiketmasuk + Integer.parseInt(totalfiltertiketmasuk);
                            //Toast.makeText(ActivityFilter.this, ""+total, Toast.LENGTH_SHORT).show();
                        }
                        totalFilter.setText(String.valueOf(totaltiketmasuk));
                        adapterTiketMasukDate = new AdapterTiketMasukDate(tiketMasukDateItems, getApplicationContext());
                        recyclerView.setAdapter(adapterTiketMasukDate);
                    }
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<TiketMasukDate> call, Throwable t) {
                Log.e(ActivityFilter.class.getSimpleName(),t.getMessage());
                Toast.makeText(ActivityFilter.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getFilterToko(String from, String to){
        retrofit2.Call<TokoDate> call = RestAPIHelper.ServiceApi(getApplication()).filterTokoDate(from, to);
        call.enqueue(new Callback<TokoDate>() {
            @Override
            public void onResponse(Call<TokoDate> call, Response<TokoDate> response) {
                if (response.body() != null){
                    boolean error = response.body().getError();
                    if (!error) {
                        tokoDateItems = response.body().getDateToko();
                        for (int i=0; i<tokoDateItems.size(); i++){
                            //totali.add(Integer.valueOf(items.get(i).getKtTotal()));
                            totalfiltertoko = tokoDateItems.get(i).getTtTotal();
                            totaltoko       = totaltoko + Integer.parseInt(totalfiltertoko);
                            //Toast.makeText(ActivityFilter.this, ""+total, Toast.LENGTH_SHORT).show();
                        }
                        totalFilter.setText(String.valueOf(totaltoko));
                        adapterTokoDate = new AdapterTokoDate(tokoDateItems, getApplicationContext());
                        recyclerView.setAdapter(adapterTokoDate);
                        Log.d("response", String.valueOf(items));
                    }
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<TokoDate> call, Throwable t) {
                Log.e(ActivityFilter.class.getSimpleName(),t.getMessage());
                Toast.makeText(ActivityFilter.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getFilterWarung(String from, String to){
        retrofit2.Call<WarungDate> call = RestAPIHelper.ServiceApi(getApplication()).filterWarungDate(from, to);
        call.enqueue(new Callback<WarungDate>() {
            @Override
            public void onResponse(Call<WarungDate> call, Response<WarungDate> response) {
                if (response.body() != null){
                    boolean error = response.body().getError();
                    if (!error) {
                        warungDateItems = response.body().getDateWarung();
                        for (int i=0; i<warungDateItems.size(); i++){
                            //totali.add(Integer.valueOf(items.get(i).getKtTotal()));
                            totalfilterwarung = warungDateItems.get(i).getWtTotal();
                            totalwarung      = totalwarung + Integer.parseInt(totalfilterwarung);
                            //Toast.makeText(ActivityFilter.this, ""+total, Toast.LENGTH_SHORT).show();
                        }
                        totalFilter.setText(String.valueOf(totalwarung));
                        adapterWarungDate = new AdapterWarungDate(warungDateItems, getApplicationContext());
                        recyclerView.setAdapter(adapterWarungDate);
                        Log.d("response", String.valueOf(items));
                    }
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<WarungDate> call, Throwable t) {
                Log.e(ActivityFilter.class.getSimpleName(),t.getMessage());
                Toast.makeText(ActivityFilter.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_from_kafe:
                showDateFrom();
                break;
            case R.id.btn_to_kafe:
                showDateTo();
                break;
            case R.id.btn_cari_kafe:
                refreshLayout.setRefreshing(true);
                getFilterKafe(dari, ke);
                total = 0;
                totalFilter.setText(String.valueOf(total));
                break;
            case R.id.btn_cari_toko:
                refreshLayout.setRefreshing(true);
                getFilterToko(dari, ke);
                totaltoko = 0;
                totalFilter.setText(String.valueOf(totaltoko));
                break;
            case R.id.btn_cari_warung:
                refreshLayout.setRefreshing(true);
                getFilterWarung(dari, ke);
                totalwarung = 0;
                totalFilter.setText(String.valueOf(totalwarung));
                break;
            case R.id.btn_cari_kolamikan:

                refreshLayout.setRefreshing(true);
                getFilterKolamIkan(dari, ke);
                totalkolamikan = 0 ;
                totalFilter.setText(String.valueOf(totalkolamikan));
                break;
            case R.id.btn_cari_KolamRenang :
                refreshLayout.setRefreshing(true);
                getFilterKolamrenang(dari,ke);
                totalkolamrenang = 0;
                totalFilter.setText(String.valueOf(totalkolamrenang));
            case R.id.btn_cari_tiketMasuk:
                refreshLayout.setRefreshing(true);
                getFilterTiketMasuk(dari,ke);
                totaltiketmasuk = 0;
                totalFilter.setText(String.valueOf(totaltiketmasuk));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
