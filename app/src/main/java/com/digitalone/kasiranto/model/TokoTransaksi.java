package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TokoTransaksi {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("toko_transaksi")
    @Expose
    private List<TokoTransaksiItem> tokoTransaksi;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<TokoTransaksiItem> getTokoTransaksi(){
        return tokoTransaksi;
    }
    public void setTokoTransaksi(List<TokoTransaksiItem> input){
        this.tokoTransaksi = input;
    }
}
