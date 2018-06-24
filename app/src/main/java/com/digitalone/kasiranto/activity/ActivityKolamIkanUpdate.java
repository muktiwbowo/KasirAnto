package com.digitalone.kasiranto.activity;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.model.AdminMessage;
import com.digitalone.kasiranto.service.RestAPIHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityKolamIkanUpdate extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNama, edtHarga, edtStok;
    private Button btnUpdate, btnHapus;
    private TextView edtId;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toko_update);
        toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edtId = findViewById(R.id.toko_update_id);
        edtNama = findViewById(R.id.toko_update_nama);
        edtHarga = findViewById(R.id.toko_update_harga);
        edtStok = findViewById(R.id.toko_update_stok);
        btnUpdate = findViewById(R.id.toko_btn_update);
        btnHapus = findViewById(R.id.toko_btn_hapus);
        btnHapus.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nama = intent.getStringExtra("nama");
        String harga = intent.getStringExtra("harga");
        String stok = intent.getStringExtra("stok");
        edtId.setText(id);
        edtNama.setText(nama);
        edtHarga.setText(harga);
        edtStok.setText(stok);
    }

    private void updateKolamIkan(String id, String nama, String harga, String stok){
        retrofit2.Call<AdminMessage> call = RestAPIHelper.ServiceApi(getApplication()).updateKolamIkan(id,nama,harga,stok);
        call.enqueue(new Callback<AdminMessage>() {
            @Override
            public void onResponse(Call<AdminMessage> call, Response<AdminMessage> response) {
                if (response.body() != null){
                    boolean error   = response.body().getError();
                    String msg      = response.body().getMsg();
                    if (!error){
                        Toast.makeText(ActivityKolamIkanUpdate.this, msg, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ActivityKolamIkanUpdate.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AdminMessage> call, Throwable t) {
                Toast.makeText(ActivityKolamIkanUpdate.this, "Cek koneksi internet anda", Toast.LENGTH_SHORT).show();            }
        });
    }

    private void deleteKolamIkan(String id){
        retrofit2.Call<AdminMessage> call = RestAPIHelper.ServiceApi(getApplication()).deleteKolamIkan(id);
        call.enqueue(new Callback<AdminMessage>() {
            @Override
            public void onResponse(Call<AdminMessage> call, Response<AdminMessage> response) {
                if (response.body() != null){
                    boolean error   = response.body().getError();
                    String msg      = response.body().getMsg();
                    if (!error){
                        Toast.makeText(ActivityKolamIkanUpdate.this, msg, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ActivityKolamIkanUpdate.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AdminMessage> call, Throwable t) {
                Toast.makeText(ActivityKolamIkanUpdate.this, "Cek koneksi internet anda", Toast.LENGTH_SHORT).show();            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toko_btn_update:
                updateKolamIkan(edtId.getText().toString(), edtNama.getText().toString(),
                        edtHarga.getText().toString(), edtStok.getText().toString());

                break;
            case R.id.toko_btn_hapus:
                deleteKolamIkan(edtId.getText().toString());
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
