package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Transaksi {
    @Expose
    @SerializedName("error")
    private boolean error;
    @Expose
    @SerializedName("transaksi")
    private List<TransaksiItem> transaksi;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<TransaksiItem> getTransaksi(){
        return transaksi;
    }
    public void setTransaksi(List<TransaksiItem> input){
        this.transaksi = input;
    }
}
