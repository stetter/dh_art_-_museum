
package com.stetter.dhartmuseum.model;

import java.util.List;
import com.google.gson.annotations.Expose;


public class ExibtionResponse {

    @Expose
    private Info info;
    @Expose
    private List<Record> records;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

}
