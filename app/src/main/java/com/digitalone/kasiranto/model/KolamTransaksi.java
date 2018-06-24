package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KolamTransaksi {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("kolamikan_transaksi")
    @Expose
    private List<KolamIkanTransaksiItem> kafeTransaksi;

    public boolean getError() {
        return error;
    }

    public void setError(boolean input) {
        this.error = input;
    }

    public List<KolamIkanTransaksiItem> getKafeTransaksi() {
        return kafeTransaksi;
    }

    public void setKafeTransaksi(List<KolamIkanTransaksiItem> input) {
        this.kafeTransaksi = input;
    }
}
