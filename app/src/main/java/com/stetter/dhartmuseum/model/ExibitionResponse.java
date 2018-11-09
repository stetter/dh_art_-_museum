
package com.stetter.dhartmuseum.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ExibitionResponse {

    @Expose
    @SerializedName( "info" )
    private Info info;
    @Expose
    @SerializedName("records")
    private List<Exhibition> exhibitions;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Exhibition> getExhibitions() {
        return exhibitions;
    }

    public void setExhibitions(List<Exhibition> records) {
        this.exhibitions = records;
    }

}
