package com.digitalone.kasiranto.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TiketMasuk {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("tiketmasuk")
    @Expose
    private List<TiketMasukItem> kafes;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<TiketMasukItem> getTiketMasuk(){
        return kafes;
    }
    public void setTiketMasuk(List<TiketMasukItem> input){
        this.kafes = input;
    }
}
