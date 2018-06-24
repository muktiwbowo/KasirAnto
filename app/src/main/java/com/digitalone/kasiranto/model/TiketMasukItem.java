package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TiketMasukItem {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nama_item_tiketmasuk")
    @Expose
    private String namaItemKafe;
    @SerializedName("harga_item_tiketmasuk")
    @Expose
    private int hargaItemKafe;

    public int getId(){
        return id;
    }
    public void setId(int input){
        this.id = input;
    }
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

}
