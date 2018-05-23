package com.digitalone.kasiranto.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

public class SessionManager {
    private static String TAG = SharedPreferences.class.getSimpleName();
    public static final String KEY_LEVEL = "level";
    private static final String PREF_NAME = "KasirPref";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void stroreLevel(String level){
        editor.putString(KEY_LEVEL, level);
        editor.putBoolean(KEY_IS_LOGGEDIN, true);
        editor.commit();
    }

    public void storeUser(String level){
        editor.putString(KEY_LEVEL, level);
        editor.commit();
    }

    public HashMap<String,String> getUser(){
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_LEVEL, preferences.getString(KEY_LEVEL, null));
        return user;
    }

    public String getLevel(){
        return preferences.getString(KEY_LEVEL, null);
    }

    public void setLogin(boolean isLoggedIn){
        editor.putBoolean(KEY_IS_LOGGEDIN,isLoggedIn);
        editor.commit();
        Log.d(TAG, "User login session modified");
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(KEY_IS_LOGGEDIN,false);
    }
}
