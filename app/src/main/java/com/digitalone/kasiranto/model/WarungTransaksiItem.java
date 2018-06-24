package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarungTransaksiItem {
    @SerializedName("warung_nama")
    @Expose
    private String warungNama;
    @SerializedName("warung_harga")
    @Expose
    private int warungHarga;
    @SerializedName("wt_jumlah")
    @Expose
    private String wtJumlah;
    @SerializedName("wt_total")
    @Expose
    private String wtTotal;
    @SerializedName("wt_datetime")
    @Expose
    private String wtDatetime;

    public String getWarungNama(){
        return warungNama;
    }
    public void setWarungNama(String input){
        this.warungNama = input;
    }
    public int getWarungHarga(){
        return warungHarga;
    }
    public void setWarungHarga(int input){
        this.warungHarga = input;
    }
    public String getWtJumlah(){
        return wtJumlah;
    }
    public void setWtJumlah(String input){
        this.wtJumlah = input;
    }
    public String getWtTotal(){
        return wtTotal;
    }
    public void setWtTotal(String input){
        this.wtTotal = input;
    }
    public String getWtDatetime(){
        return wtDatetime;
    }
    public void setWtDatetime(String input){
        this.wtDatetime = input;
    }
}
