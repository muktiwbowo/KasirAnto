package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransaksiItem {
    @Expose
    @SerializedName("transaksi_id")
    private int transaksiId;
    @Expose
    @SerializedName("t_nama")
    private String tNama;
    @Expose
    @SerializedName("t_jumlah")
    private String tJumlah;
    @Expose
    @SerializedName("t_total")
    private String tTotal;
    @Expose
    @SerializedName("t_datetime")
    private String tDatetime;

    public int getTransaksiId(){
        return transaksiId;
    }
    public void setTransaksiId(int input){
        this.transaksiId = input;
    }
    public String getTNama(){
        return tNama;
    }
    public void setTNama(String input){
        this.tNama = input;
    }
    public String getTJumlah(){
        return tJumlah;
    }
    public void setTJumlah(String input){
        this.tJumlah = input;
    }
    public String getTTotal(){
        return tTotal;
    }
    public void setTTotal(String input){
        this.tTotal = input;
    }
    public String getTDatetime(){
        return tDatetime;
    }
    public void setTDatetime(String input){
        this.tDatetime = input;
    }
}
