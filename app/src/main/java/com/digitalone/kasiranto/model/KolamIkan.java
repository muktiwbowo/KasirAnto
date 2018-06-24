
package com.digitalone.kasiranto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KolamIkan {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("KolamIkan")
    @Expose
    private List<KolamIkanItem> KolamIkan;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<KolamIkanItem> getKafe(){
        return KolamIkan;
    }
    public void setKafe(List<KolamIkanItem> input){
        this.KolamIkan = input;
    }
}
