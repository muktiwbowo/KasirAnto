package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KolamIkanDate {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("kolamikan")
    @Expose
    private List<KolamIkanDateItem> kafe;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<KolamIkanDateItem> getDateKafe(){
        return kafe;
    }
    public void setDateKafe(List<KolamIkanDateItem> input){
        this.kafe = input;
    }
}
