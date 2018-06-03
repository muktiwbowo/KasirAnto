package com.digitalone.kasiranto.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokoItem {
    @SerializedName("toko_id")
    @Expose
    private int tokoId;
    @SerializedName("toko_nama")
    @Expose
    private String tokoNama;
    @SerializedName("toko_harga")
    @Expose
    private int tokoHarga;
    @SerializedName("toko_stok")
    @Expose
    private int tokoStok;

    public int getTokoId(){
        return tokoId;
    }
    public void setTokoId(int input){
        this.tokoId = input;
    }
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
    public int getTokoStok(){
        return tokoStok;
    }
    public void setTokoStok(int input){
        this.tokoStok = input;
    }
}
