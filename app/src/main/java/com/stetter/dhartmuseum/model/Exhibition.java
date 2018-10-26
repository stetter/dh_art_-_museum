
package com.stetter.dhartmuseum.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "exhibition")
public class Exhibition {

    @Expose
    @SerializedName( "begindate" )
    private String begindate;

    @Expose
    @SerializedName( "color" )
    private Object color;

    @Expose
    @SerializedName( "description" )
    private Object description;

    @Expose
    private String enddate;

    @Expose
    private Long exhibitionid;

    @PrimaryKey
    @Expose
    private Long id;

    @Expose
    private String lastupdate;

    @Expose
    private Object shortdescription;

    @Expose
    private Long temporalorder;

    @Expose
    private String title;

    @Expose

    private List<Venue> venues;


    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public Object getColor() {
        return color;
    }

    public void setColor(Object color) {
        this.color = color;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public Long getExhibitionid() {
        return exhibitionid;
    }

    public void setExhibitionid(Long exhibitionid) {
        this.exhibitionid = exhibitionid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Object getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(Object shortdescription) {
        this.shortdescription = shortdescription;
    }

    public Long getTemporalorder() {
        return temporalorder;
    }

    public void setTemporalorder(Long temporalorder) {
        this.temporalorder = temporalorder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

}
