package com.digitalone.kasiranto.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.fragment.FragmentHome;
import com.digitalone.kasiranto.fragment.FragmentTransaksi;
import com.digitalone.kasiranto.preferences.SessionManager;

import java.util.HashMap;

public class ActivitySuperUser extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    SessionManager              session;
    TextView                    tLevel;
    private ProgressDialog      pDialog;
    BottomNavigationView        bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_user);
        initView();
        if (savedInstanceState == null){
            loadHomeFragment();
        }
    }

    private void initView(){
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(this);

        pDialog = new ProgressDialog(this);
        session = new SessionManager(this);

//        HashMap<String, String> user = session.getUser();
//        String levelname = user.get(SessionManager.KEY_LEVEL);

        pDialog.setCancelable(false);

        if (!session.isLoggedIn()) {
            logoutUser();
        }
        //tLevel.setText(levelname);
    }

    private void logoutUser() {
        session.setLogin(false);
        pDialog.setMessage("Logging out ...");
        showDialog();
        // Launching the login activity
        Intent intent = new Intent(ActivitySuperUser.this, ActivityLogin.class);
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
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.keluar:
                logoutUser();
                break;
            case R.id.ubah_password:
                startActivity(new Intent(ActivitySuperUser.this, ActivityUbahPassword.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homee:
                loadHomeFragment();
                return true;
            case R.id.transaksi:
                loadTransaksiFragment();
                return true;
        }
        return false;
    }

    private void loadHomeFragment() {
        FragmentHome fragment   = FragmentHome.newInstance();
        FragmentTransaction ft  = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, fragment);
        ft.commit();
    }

    private void loadTransaksiFragment() {
        FragmentTransaksi fragment  = FragmentTransaksi.newInstance();
        FragmentTransaction ft      = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, fragment);
        ft.commit();
    }

}
