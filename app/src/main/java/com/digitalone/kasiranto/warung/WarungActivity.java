package com.digitalone.kasiranto.warung;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.akun.LoginActivity;
import com.digitalone.kasiranto.akun.SuperUserActivity;
import com.digitalone.kasiranto.preferences.SessionManager;

import java.util.HashMap;

public class WarungActivity extends AppCompatActivity {

    SessionManager session;
    TextView tLevel;
    private ProgressDialog pDialog;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warung);

        tLevel = findViewById(R.id.txtlevel);
        toolbar = findViewById(R.id.toolbarwarung);
        setSupportActionBar(toolbar);
        pDialog = new ProgressDialog(this);
        session = new SessionManager(this);

        HashMap<String, String> user = session.getUser();
        String levelname = user.get(SessionManager.KEY_LEVEL);

        pDialog.setCancelable(false);

        if (!session.isLoggedIn()) {
            logoutUser();
        }
        tLevel.setText(levelname);
    }

    private void logoutUser() {
        session.setLogin(false);
        pDialog.setMessage("Logging out ...");
        showDialog();
        // Launching the login activity
        Intent intent = new Intent(WarungActivity.this, LoginActivity.class);
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
}
