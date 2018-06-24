package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokoTransaksiItem {
    @SerializedName("toko_nama")
    @Expose
    private String tokoNama;
    @SerializedName("toko_harga")
    @Expose
    private int tokoHarga;
    @SerializedName("tt_jumlah")
    @Expose
    private String ttJumlah;
    @SerializedName("tt_total")
    @Expose
    private String ttTotal;
    @SerializedName("tt_datetime")
    @Expose
    private String ttDatetime;

    public String getTokoNama(){
        return tokoNama;
    }
    public void setTokoNama(String input){
        this.tokoNama = input;
    }
    public int getTokoHarga(){
        return tokoHarga;
    }
    public void setTokoHarga(int input){
        this.tokoHarga = input;
    }
    public String getTtJumlah(){
        return ttJumlah;
    }
    public void setTtJumlah(String input){
        this.ttJumlah = input;
    }
    public String getTtTotal(){
        return ttTotal;
    }
    public void setTtTotal(String input){
        this.ttTotal = input;
    }
    public String getTtDatetime(){
        return ttDatetime;
    }
    public void setTtDatetime(String input){
        this.ttDatetime = input;
    }
}
