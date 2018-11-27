
package com.stetter.dhartmuseum.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ExhibtionResponse {


    @Expose
    @SerializedName("info")
    private Info info;

    @Expose
    @SerializedName("records")
    private List<Exhibition> records;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Exhibition> getRecords() {
        return records;
    }

    public void setRecords(List<Exhibition> records) {
        this.records = records;
    }
}
