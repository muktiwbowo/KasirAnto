package com.digitalone.kasiranto.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.helper.DBHelper;
import com.digitalone.kasiranto.model.Warung;
import com.digitalone.kasiranto.model.WarungItem;
import com.digitalone.kasiranto.model.WarungItem;
import com.digitalone.kasiranto.preferences.SessionManager;
import com.digitalone.kasiranto.service.RestAPIHelper;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityWarung extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    SessionManager                  session;
    private ProgressDialog          pDialog;
    private Toolbar                 toolbar;
    private int                     total,
                                    harga,
                                    jumlah,
                                    totaldetail,
                                    warung_id;
    private String                  nama;
    private SearchableSpinner       warungSpinner;
    private TextView                warungHarga,
                                    warungJumlah,
                                    warungTotal;
    private Button                  btnKurang,
                                    btnTambah,
                                    btnPesan,
                                    btnDetail;
    private Toolbar                 toolBar;
    private DecimalFormat fRupiah;
    private List<WarungItem> items;
    private ArrayList<String> namaList;
    private ArrayList<Integer>      hargaList,
            idList;
    private ArrayAdapter<String> adapter;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warung);
        initView();
        getWarungs();
    }

    private void initView(){
        total           = 0;
        harga           = 0;
        jumlah          = 0;
        totaldetail     = 0;
        warungSpinner   = findViewById(R.id.warung_spinner);
        warungJumlah    = findViewById(R.id.warung_jumlah);
        warungHarga     = findViewById(R.id.warung_harga);
        warungTotal     = findViewById(R.id.warung_total_detail);
        btnKurang       = findViewById(R.id.warung_btn_kurang);
        btnTambah       = findViewById(R.id.warung_btn_tambah);
        btnPesan        = findViewById(R.id.warung_btn_pesan);
        btnDetail       = findViewById(R.id.warung_btn_detail);
        toolBar         = findViewById(R.id.toolbarwarung);
        db              = new DBHelper(getApplicationContext());
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Warung");

        pDialog = new ProgressDialog(this);
        session = new SessionManager(this);
        btnTambah.setOnClickListener(this);
        btnKurang.setOnClickListener(this);
        btnPesan.setOnClickListener(this);
        btnDetail.setOnClickListener(this);
        warungSpinner.setOnItemSelectedListener(this);
        warungSpinner.setTitle("Pilih Item");
//        HashMap<String, String> user = session.getUser();
//        String levelname = user.get(SessionManager.KEY_LEVEL);
        namaList    = new ArrayList<>();
        hargaList   = new ArrayList<>();
        idList      = new ArrayList<>();
        pDialog.setCancelable(false);

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        formatRupiah();
    }

    private void getWarungs(){
        retrofit2.Call<Warung> call = RestAPIHelper.ServiceApi(getApplication()).getWarung();
        call.enqueue(new Callback<Warung>() {
            @Override
            public void onResponse(Call<Warung> call, Response<Warung> response) {
                if (response.body() != null){
                    items = response.body().getWarung();
                    for (int i=0; i<items.size(); i++){
                        namaList.add(items.get(i).getWarungNama());
                        hargaList.add(items.get(i).getWarungHarga());
                        idList.add(items.get(i).getWarungId());
                    }
                    adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.list_kafe_spinner, namaList);
                    adapter.add("List Items");
                    adapter.setDropDownViewResource(R.layout.list_kafe_spinner);
                    warungSpinner.setAdapter(adapter);
                    warungSpinner.setSelection(adapter.getCount()-1);
                }
            }

            @Override
            public void onFailure(Call<Warung> call, Throwable t) {

            }
        });
    }

    public void formatRupiah(){
        fRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setCurrencySymbol("Rp ");
        symbols.setMonetaryDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        fRupiah.setDecimalFormatSymbols(symbols);
    }

    private void tampilHarga(int harga){
        warungHarga.setText(fRupiah.format(harga));
    }

    private void tampilJumlah(int jumlah){
        warungJumlah.setText(String.valueOf(jumlah));
    }

    private void tampilTotal(int total){
        warungTotal.setText(fRupiah.format(total));
    }


    private void logoutUser() {
        session.setLogin(false);
        pDialog.setMessage("Logging out ...");
        showDialog();
        // Launching the login activity
        Intent intent = new Intent(ActivityWarung.this, ActivityLogin.class);
        startActivity(intent);
        hideDialog();
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.keluar:
                logoutUser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.warung_btn_tambah:
                jumlah = jumlah + 1;
                tampilJumlah(jumlah);
                break;
            case R.id.warung_btn_kurang:
                if (jumlah > 0){
                    jumlah = jumlah - 1;
                    tampilJumlah(jumlah);
                }
                break;
            case R.id.warung_btn_pesan:
                db.insertWarung(nama,String.valueOf(jumlah),String.valueOf(total),warung_id);
                totaldetail = totaldetail + total;
                tampilTotal(totaldetail);
                break;
            case R.id.warung_btn_detail:
                startActivity(new Intent(ActivityWarung.this, ActivityWarungCheckout.class));
                break;
        }
        total = harga * jumlah;
        tampilHarga(total);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        nama = parent.getItemAtPosition(position).toString();
        if (warungSpinner.getSelectedItem() == "List Items"){}
        else {
            harga = hargaList.get(position);
            warung_id = idList.get(position);
            jumlah = 0;
            tampilJumlah(jumlah);
            warungHarga.setText(fRupiah.format(harga));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
