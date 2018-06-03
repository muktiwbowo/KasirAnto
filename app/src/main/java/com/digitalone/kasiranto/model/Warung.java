package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Warung {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("warung")
    @Expose
    private List<WarungItem> warung;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<WarungItem> getWarung(){
        return warung;
    }
    public void setWarung(List<WarungItem> input){
        this.warung = input;
    }
}
