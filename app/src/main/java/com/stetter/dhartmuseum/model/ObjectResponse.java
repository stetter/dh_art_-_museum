
package com.stetter.dhartmuseum.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ObjectResponse {

    @Expose
    @SerializedName("info")
    private ObjectInfo objectInfo;
    @Expose
    @SerializedName("records")
    private List<Record> records;

    public ObjectInfo getObjectInfo() {
        return objectInfo;
    }

    public void setObjectInfo(ObjectInfo objectInfo) {
        this.objectInfo = objectInfo;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

}
