
package com.stetter.dhartmuseum.home.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "GalleryRecords")
public class GalleryRecord {

    @Expose
    private Long floor;
    @Expose
    private Long galleryid;
    @Expose
    private String gallerynumber;
    @Expose
    @PrimaryKey
    @NonNull
    private Long id;
    @Expose
    private String lastupdate;
    @Expose
    private String name;
    @Expose
    private Long objectcount;
    @Expose
    private String theme;

    public Long getFloor() {
        return floor;
    }

    public Long getGalleryid() {
        return galleryid;
    }

    public String getGallerynumber() {
        return gallerynumber;
    }

    public Long getId() {
        return id;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public String getName() {
        return name;
    }

    public Long getObjectcount() {
        return objectcount;
    }

    public String getTheme() {
        return theme;
    }

    public static class Builder {

        private Long floor;
        private Long galleryid;
        private String gallerynumber;
        private Long id;
        private String lastupdate;
        private String name;
        private Long objectcount;
        private String theme;

        public GalleryRecord.Builder withFloor(Long floor) {
            this.floor = floor;
            return this;
        }

        public GalleryRecord.Builder withGalleryid(Long galleryid) {
            this.galleryid = galleryid;
            return this;
        }

        public GalleryRecord.Builder withGallerynumber(String gallerynumber) {
            this.gallerynumber = gallerynumber;
            return this;
        }

        public GalleryRecord.Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public GalleryRecord.Builder withLastupdate(String lastupdate) {
            this.lastupdate = lastupdate;
            return this;
        }

        public GalleryRecord.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public GalleryRecord.Builder withObjectcount(Long objectcount) {
            this.objectcount = objectcount;
            return this;
        }

        public GalleryRecord.Builder withTheme(String theme) {
            this.theme = theme;
            return this;
        }

        public GalleryRecord build() {
            GalleryRecord galleryRecord = new GalleryRecord();
            galleryRecord.floor = floor;
            galleryRecord.galleryid = galleryid;
            galleryRecord.gallerynumber = gallerynumber;
            galleryRecord.id = id;
            galleryRecord.lastupdate = lastupdate;
            galleryRecord.name = name;
            galleryRecord.objectcount = objectcount;
            galleryRecord.theme = theme;
            return galleryRecord;
        }

    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public void setGalleryid(Long galleryid) {
        this.galleryid = galleryid;
    }

    public void setGallerynumber(String gallerynumber) {
        this.gallerynumber = gallerynumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setObjectcount(Long objectcount) {
        this.objectcount = objectcount;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
