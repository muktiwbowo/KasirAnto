package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TiketMasukDateItem {
    @SerializedName("nama_item_tiketmasuk")
    @Expose
    private String namaItemTiketmasuk;
    @SerializedName("harga_item_tiketmasuk")
    @Expose
    private int hargaItemTiketmasuk;
    @SerializedName("tmt_jumlah")
    @Expose
    private String tmtJumlah;
    @SerializedName("tmt_total")
    @Expose
    private String tmtTotal;
    @SerializedName("tmt_datetime")
    @Expose
    private String tmtDatetime;

    public String getNamaItemTiketmasuk(){
        return namaItemTiketmasuk;
    }
    public void setNamaItemTiketmasuk(String input){
        this.namaItemTiketmasuk = input;
    }
    public int getHargaItemTiketmasuk(){
        return hargaItemTiketmasuk;
    }
    public void setHargaItemTiketmasuk(int input){
        this.hargaItemTiketmasuk = input;
    }
    public String getTmtJumlah(){
        return tmtJumlah;
    }
    public void setTmtJumlah(String input){
        this.tmtJumlah = input;
    }
    public String getTmtTotal(){
        return tmtTotal;
    }
    public void setTmtTotal(String input){
        this.tmtTotal = input;
    }
    public String getTmtDatetime(){
        return tmtDatetime;
    }
    public void setTmtDatetime(String input){
        this.tmtDatetime = input;
    }
}
