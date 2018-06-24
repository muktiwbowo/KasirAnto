package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarungDateItem {
    @SerializedName("warung_nama")
    @Expose
    private String warung_nama;
    @SerializedName("warung_harga")
    @Expose
    private int warung_harga;
    @SerializedName("wt_jumlah")
    @Expose
    private String wtJumlah;
    @SerializedName("wt_total")
    @Expose
    private String wtTotal;
    @SerializedName("wt_datetime")
    @Expose
    private String wtDatetime;

    public String getWarung_nama() {
        return warung_nama;
    }

    public void setWarung_nama(String warung_nama) {
        this.warung_nama = warung_nama;
    }

    public int getWarung_harga() {
        return warung_harga;
    }

    public void setWarung_harga(int warung_harga) {
        this.warung_harga = warung_harga;
    }

    public String getWtJumlah() {
        return wtJumlah;
    }

    public void setWtJumlah(String wtJumlah) {
        this.wtJumlah = wtJumlah;
    }

    public String getWtTotal() {
        return wtTotal;
    }

    public void setWtTotal(String wtTotal) {
        this.wtTotal = wtTotal;
    }

    public String getWtDatetime() {
        return wtDatetime;
    }

    public void setWtDatetime(String wtDatetime) {
        this.wtDatetime = wtDatetime;
    }
}
