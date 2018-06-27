package com.digitalone.kasiranto.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.preferences.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ActivityLogin extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = ActivityLogin.class.getSimpleName();
    private Spinner spinnerLevel;
    private SessionManager session;
    private EditText userPassword;
    private Button btnMasuk;
    private ProgressDialog pDialog;

    private String level, password;
    private static final String URL_LOGIN
            = "https://fcmskripsweet.000webhostapp.com/kasiranto/LoginKasir.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spinnerLevel = findViewById(R.id.spinnerlevel);
        userPassword = findViewById(R.id.password);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        btnMasuk = findViewById(R.id.btn_masuk);
        spinnerLevel.setOnItemSelectedListener(this);
        List<String> levelitems = new ArrayList<>();
        levelitems.add("Pilih Pengguna");
        levelitems.add("Superuser");
        levelitems.add("Toko");
        levelitems.add("Warung");
        levelitems.add("Cafe / Kolam Renang");
        levelitems.add("Penginapan / Pemancingan");
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,levelitems);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(levelAdapter);
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = userPassword.getText().toString();
                if (!password.isEmpty()) {
                    // login user
                    loadLogin(level, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        session = new SessionManager(this);
        if (session.isLoggedIn()){
            if (session.getLevel().equals("Superuser")){
                startActivity(new Intent(ActivityLogin.this, ActivitySuperUser.class));
                finish();
            }else if (session.getLevel().equals("Toko")){
                startActivity(new Intent(ActivityLogin.this, ActivityToko.class));
                finish();
            }else if (session.getLevel().equals("Warung")){
                startActivity(new Intent(ActivityLogin.this, ActivityWarung.class));
                finish();
            }else if (session.getLevel().equals("Cafe / Kolam Renang")){
                startActivity(new Intent(ActivityLogin.this, ActivityKafeRenang.class));
                finish();
            }else if (session.getLevel().equals("Penginapan / Pemancingan")){
                startActivity(new Intent(ActivityLogin.this, ActivityKolamIkan.class));
                finish();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        level = parent.getItemAtPosition(position).toString();
        if (level.equals("Pilih Pengguna")){
            return;
        }else {
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Pilih salah satu terlebih dahulu", Toast.LENGTH_SHORT).show();
    }

    private void loadLogin(final String level, final String password) {
        pDialog.setMessage("Loading ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        session.setLogin(true);

                        JSONObject user = jObj.getJSONObject("user");
                        String lev = user.getString("level");
                        session.stroreLevel(lev);
                        session.storeUser(lev);
                        switch (lev){
                            case "Superuser":
                                startActivity(new Intent(ActivityLogin.this, ActivitySuperUser.class));
                                finish();
                                break;
                            case "Toko":
                                startActivity(new Intent(ActivityLogin.this, ActivityToko.class));
                                finish();
                                break;
                            case "Warung":
                                startActivity(new Intent(ActivityLogin.this, ActivityWarung.class));
                                finish();
                                break;
                            case "Cafe / Kolam Renang":
                                startActivity(new Intent(ActivityLogin.this, ActivityKafeRenang.class));
                                finish();
                                break;
                            case "Penginapan / Pemancingan":
                                startActivity(new Intent(ActivityLogin.this, ActivityKolamIkan.class));
                                finish();
                                break;
                        }
                        //session.storeUsername(nname,eemail);
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(ActivityLogin.this,
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(ActivityLogin.this, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(ActivityLogin.this,
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("level", level);
                params.put("password", password);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strReq);
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

