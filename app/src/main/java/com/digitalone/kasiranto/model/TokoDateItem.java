package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokoDateItem {
    @SerializedName("toko_nama")
    @Expose
    private String namaToko;
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

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public int getTokoHarga() {
        return tokoHarga;
    }

    public void setTokoHarga(int tokoHarga) {
        this.tokoHarga = tokoHarga;
    }

    public String getTtJumlah() {
        return ttJumlah;
    }

    public void setTtJumlah(String ttJumlah) {
        this.ttJumlah = ttJumlah;
    }

    public String getTtTotal() {
        return ttTotal;
    }

    public void setTtTotal(String ttTotal) {
        this.ttTotal = ttTotal;
    }

    public String getTtDatetime() {
        return ttDatetime;
    }

    public void setTtDatetime(String ttDatetime) {
        this.ttDatetime = ttDatetime;
    }
}
