
package com.stetter.dhartmuseum.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GalleryResponse {

    @Expose
    @SerializedName("info")
    private GalleryInfo galleryInfo;
    @Expose
    @SerializedName("records")
    private List<GalleryRecord> galleryRecords;

    public GalleryInfo getGalleryInfo() {
        return galleryInfo;
    }

    public List<GalleryRecord> getGalleryRecords() {
        return galleryRecords;
    }

    public static class Builder {

        private GalleryInfo galleryInfo;
        private List<GalleryRecord> galleryRecords;

        public GalleryResponse.Builder withInfo(GalleryInfo galleryInfo) {
            this.galleryInfo = galleryInfo;
            return this;
        }

        public GalleryResponse.Builder withRecords(List<GalleryRecord> galleryRecords) {
            this.galleryRecords = galleryRecords;
            return this;
        }

        public GalleryResponse build() {
            GalleryResponse galleryResponse = new GalleryResponse();
            galleryResponse.galleryInfo = galleryInfo;
            galleryResponse.galleryRecords = galleryRecords;
            return galleryResponse;
        }

    }

}
