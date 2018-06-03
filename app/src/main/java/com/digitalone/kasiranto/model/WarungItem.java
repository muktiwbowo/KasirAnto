package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarungItem {
    @SerializedName("warung_id")
    @Expose
    private int warungId;
    @SerializedName("warung_nama")
    @Expose
    private String warungNama;
    @SerializedName("warung_harga")
    @Expose
    private int warungHarga;
    @SerializedName("warung_stok")
    @Expose
    private int warungStok;

    public int getWarungId(){
        return warungId;
    }
    public void setWarungId(int input){
        this.warungId = input;
    }
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
    public int getWarungStok(){
        return warungStok;
    }
    public void setWarungStok(int input){
        this.warungStok = input;
    }
}
