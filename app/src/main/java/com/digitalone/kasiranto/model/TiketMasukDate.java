package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TiketMasukDate {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("tiketmasuk_date")
    @Expose
    private List<TiketMasukDateItem> tiketmasukDate;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<TiketMasukDateItem> getTiketmasukDate(){
        return tiketmasukDate;
    }
    public void setTiketmasukDate(List<TiketMasukDateItem> input){
        this.tiketmasukDate = input;
    }

}
