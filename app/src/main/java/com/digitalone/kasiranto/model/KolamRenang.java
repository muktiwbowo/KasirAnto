
package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KolamRenang {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("kolamrenang")
    @Expose
    private List<KolamRenangItem> KolamIkan;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<KolamRenangItem> getKafe(){
        return KolamIkan;
    }
    public void setKafe(List<KolamRenangItem> input){
        this.KolamIkan = input;
    }
}
