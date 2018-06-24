package com.digitalone.kasiranto.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.fragment.FragmentKafe;
import com.digitalone.kasiranto.fragment.FragmentKolamRenang;
import com.digitalone.kasiranto.adapter.AdapterViewPager;
import com.digitalone.kasiranto.preferences.SessionManager;

public class ActivityKafeRenang extends AppCompatActivity implements View.OnClickListener {
    SessionManager session;
    ProgressDialog pDialog;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    public static Activity fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kafe);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayoutkafe);
        toolbar = findViewById(R.id.toolbarkafe);
        setSupportActionBar(toolbar);
        fa = this;
        pDialog = new ProgressDialog(this);
        session = new SessionManager(this);

        pDialog.setCancelable(false);

        if (!session.isLoggedIn()) {
            logoutUser();
        }
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        AdapterViewPager adapter = new AdapterViewPager(getSupportFragmentManager());
        adapter.addFragment(new FragmentKafe(),"Kafe");
        adapter.addFragment(new FragmentKolamRenang(),"Kolam Renang");

        viewPager.setAdapter(adapter);
    }

    private void logoutUser() {
        session.setLogin(false);
        pDialog.setMessage("Logging out ...");
        showDialog();
        Intent intent = new Intent(ActivityKafeRenang.this, ActivityLogin.class);
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

    }
}
