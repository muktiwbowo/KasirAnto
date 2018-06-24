package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KafeDateItem {
    @SerializedName("nama_item_kafe")
    @Expose
    private String namaItemKafe;
    @SerializedName("harga_item_kafe")
    @Expose
    private int hargaItemKafe;
    @SerializedName("kt_jumlah")
    @Expose
    private String ktJumlah;
    @SerializedName("kt_total")
    @Expose
    private String ktTotal;
    @SerializedName("kt_datetime")
    @Expose
    private String ktDatetime;

    public String getNamaItemKafe(){
        return namaItemKafe;
    }
    public void setNamaItemKafe(String input){
        this.namaItemKafe = input;
    }
    public int getHargaItemKafe(){
        return hargaItemKafe;
    }
    public void setHargaItemKafe(int input){
        this.hargaItemKafe = input;
    }
    public String getKtJumlah(){
        return ktJumlah;
    }
    public void setKtJumlah(String input){
        this.ktJumlah = input;
    }
    public String getKtTotal(){
        return ktTotal;
    }
    public void setKtTotal(String input){
        this.ktTotal = input;
    }
    public String getKtDatetime(){
        return ktDatetime;
    }
    public void setKtDatetime(String input){
        this.ktDatetime = input;
    }
}
