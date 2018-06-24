package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KafeDate {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("kafe")
    @Expose
    private List<KafeDateItem> kafe;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<KafeDateItem> getDateKafe(){
        return kafe;
    }
    public void setDateKafe(List<KafeDateItem> input){
        this.kafe = input;
    }
}
