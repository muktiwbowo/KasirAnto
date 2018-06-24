package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WarungDate {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("warung")
    @Expose
    private List<WarungDateItem> warung;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<WarungDateItem> getDateWarung(){
        return warung;
    }
    public void setDateWarung(List<WarungDateItem> input){
        this.warung = input;
    }
}
