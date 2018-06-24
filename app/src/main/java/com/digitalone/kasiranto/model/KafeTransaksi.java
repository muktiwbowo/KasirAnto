package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KafeTransaksi {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("kafe_transaksi")
    @Expose
    private List<KafeTransaksiItem> kafeTransaksi;

    public boolean getError() {
        return error;
    }

    public void setError(boolean input) {
        this.error = input;
    }

    public List<KafeTransaksiItem> getKafeTransaksi() {
        return kafeTransaksi;
    }

    public void setKafeTransaksi(List<KafeTransaksiItem> input) {
        this.kafeTransaksi = input;
    }
}
