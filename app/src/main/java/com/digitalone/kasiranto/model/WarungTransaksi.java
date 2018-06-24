package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WarungTransaksi {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("warung_transaksi")
    @Expose
    private List<WarungTransaksiItem> warungTransaksi;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<WarungTransaksiItem> getWarungTransaksi(){
        return warungTransaksi;
    }
    public void setWarungTransaksi(List<WarungTransaksiItem> input){
        this.warungTransaksi = input;
    }
}
