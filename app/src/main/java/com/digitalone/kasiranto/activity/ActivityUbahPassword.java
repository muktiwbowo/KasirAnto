package com.digitalone.kasiranto.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.model.AdminMessage;
import com.digitalone.kasiranto.service.RestAPIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityUbahPassword extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Toolbar     toolBar;
    private Spinner     spinner;
    private EditText    edtPass, edtRePass;
    private Button      btnUbah;
    private String      level;
    private int         idPass;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);
        initView();
    }

    private void initView(){
        idPass = 0;
        toolBar = findViewById(R.id.toolbar_ubah_password);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Ubah Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner     = findViewById(R.id.spinner_ubah_password);
        edtPass     = findViewById(R.id.password_ubah);
        edtRePass   = findViewById(R.id.repassword);
        btnUbah     = findViewById(R.id.btn_ubah_password);

        btnUbah.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        List<String> levelitems = new ArrayList<>();
        levelitems.add("Select User");
        levelitems.add("Superuser");
        levelitems.add("Warung");
        levelitems.add("Toko");
        levelitems.add("KafeRenang");
        levelitems.add("KolamIkan");
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,levelitems);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(levelAdapter);
    }

    private void ubahPassword(String password, String id){
        pDialog.setMessage("Logging in ...");
        showDialog();

        retrofit2.Call<AdminMessage> call = RestAPIHelper.ServiceApi(getApplication()).updatePassword(password, id);
        call.enqueue(new Callback<AdminMessage>() {
            @Override
            public void onResponse(Call<AdminMessage> call, Response<AdminMessage> response) {
                if (response.body() != null){
                    boolean error   = response.body().getError();
                    String msg      = response.body().getMsg();
                    if (!error){
                        Toast.makeText(ActivityUbahPassword.this, msg, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ActivityUbahPassword.this, msg, Toast.LENGTH_SHORT).show();
                    }
                    hideDialog();
                }
            }

            @Override
            public void onFailure(Call<AdminMessage> call, Throwable t) {
                Toast.makeText(ActivityUbahPassword.this, "Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        level   = parent.getItemAtPosition(position).toString();
        if (level.equals("Select User")){
            return;
        }else if (level.equals("Superuser")){
            idPass  = 1;
        }else if (level.equals("Warung")){
            idPass  = 2;
        }else if (level.equals("Toko")){
            idPass  = 3;
        }else if (level.equals("KafeRenang")){
            idPass  = 4;
        }else if (level.equals("KolamIkan")){
            idPass  = 5;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Please select your level status", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ubah_password:
                if (!edtPass.getText().toString().equals(edtRePass.getText().toString())){
                    Toast.makeText(ActivityUbahPassword.this, "Password tidak sesuai", Toast.LENGTH_SHORT).show();
                }else {
                    ubahPassword(edtPass.getText().toString(), String.valueOf(idPass));
                }
                break;
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
