package com.stetter.dhartmuseum.data.network;

import com.stetter.dhartmuseum.home.model.GalleryResponse;
import com.stetter.dhartmuseum.model.ObjectResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @GET("/object")
    Observable<ObjectResponse> getObjects(
            @Query("keyword") String keyword,
            @Query("apikey") String apikey
    );

    @GET("/gallery")
    Observable<GalleryResponse> getGalleries(
            @Query("floor") int floor,
            @Query("apikey") String apiKey
    );
    @GET("/object")
    Observable<ObjectResponse> getObjectsGallery(
            @Query("gallery") long gallery,
            @Query("apikey") String apikey
    );
}
