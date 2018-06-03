package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Toko {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("toko")
    @Expose
    private List<TokoItem> toko;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<TokoItem> getToko(){
        return toko;
    }
    public void setToko(List<TokoItem> input){
        this.toko = input;
    }
}
