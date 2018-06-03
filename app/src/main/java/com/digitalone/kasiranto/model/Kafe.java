package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Kafe {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("kafe")
    @Expose
    private List<KafeItem> kafe;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<KafeItem> getKafe(){
        return kafe;
    }
    public void setKafe(List<KafeItem> input){
        this.kafe = input;
    }
}
