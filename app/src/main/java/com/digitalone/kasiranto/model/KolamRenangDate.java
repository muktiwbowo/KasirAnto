package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KolamRenangDate {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("kolamrenang")
    @Expose
    private List<KolamrenangDateItem> kafe;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<KolamrenangDateItem> getDateKafe(){
        return kafe;
    }
    public void setDateKafe(List<KolamrenangDateItem> input){
        this.kafe = input;
    }
}
