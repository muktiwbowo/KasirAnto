package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KolamRenangItem {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nama_item_kolamrenang")
    @Expose
    private String namaItemKafe;
    @SerializedName("harga_item_kolamrenang")
    @Expose
    private int hargaItemKafe;
    @SerializedName("stok_item_kolamrenang")
    @Expose
    private int stokItemKafe;

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
    public int getStokItemKafe(){
        return stokItemKafe;
    }
    public void setStokItemKafe(int input){
        this.stokItemKafe = input;
    }
}
