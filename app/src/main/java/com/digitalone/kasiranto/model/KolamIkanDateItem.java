package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KolamIkanDateItem {
    @SerializedName("nama_item_KolamIkan")
    @Expose
    private String namaItemKafe;
    @SerializedName("harga_item_KolamIkan")
    @Expose
    private int hargaItemKafe;
    @SerializedName("kit_jumlah")
    @Expose
    private String ktJumlah;
    @SerializedName("kit_total")
    @Expose
    private String ktTotal;
    @SerializedName("kit_datetime")
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
