
package com.stetter.dhartmuseum.home.model;

import com.google.gson.annotations.Expose;

public class GalleryInfo {

    @Expose
    private Long page;
    @Expose
    private Long pages;
    @Expose
    private Long totalrecords;
    @Expose
    private Long totalrecordsperquery;

    public Long getPage() {
        return page;
    }

    public Long getPages() {
        return pages;
    }

    public Long getTotalrecords() {
        return totalrecords;
    }

    public Long getTotalrecordsperquery() {
        return totalrecordsperquery;
    }

    public static class Builder {

        private Long page;
        private Long pages;
        private Long totalrecords;
        private Long totalrecordsperquery;

        public GalleryInfo.Builder withPage(Long page) {
            this.page = page;
            return this;
        }

        public GalleryInfo.Builder withPages(Long pages) {
            this.pages = pages;
            return this;
        }

        public GalleryInfo.Builder withTotalrecords(Long totalrecords) {
            this.totalrecords = totalrecords;
            return this;
        }

        public GalleryInfo.Builder withTotalrecordsperquery(Long totalrecordsperquery) {
            this.totalrecordsperquery = totalrecordsperquery;
            return this;
        }

        public GalleryInfo build() {
            GalleryInfo galleryInfo = new GalleryInfo();
            galleryInfo.page = page;
            galleryInfo.pages = pages;
            galleryInfo.totalrecords = totalrecords;
            galleryInfo.totalrecordsperquery = totalrecordsperquery;
            return galleryInfo;
        }

    }

}