package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TokoDate {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("toko")
    @Expose
    private List<TokoDateItem> toko;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<TokoDateItem> getDateToko(){
        return toko;
    }
    public void setDateToko(List<TokoDateItem> input){
        this.toko = input;
    }
}
