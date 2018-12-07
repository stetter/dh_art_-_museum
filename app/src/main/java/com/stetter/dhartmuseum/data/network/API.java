package com.stetter.dhartmuseum.data.network;

import com.stetter.dhartmuseum.home.model.GalleryResponse;
import com.stetter.dhartmuseum.model.ObjectResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    // Buscar uma lista de filmes de uma categoria
    @GET("/object")
    Observable<ObjectResponse> getObjects(
           /* @Query("classification") String classification,
            @Query("q") String q,*/
            @Query("sort") String sort,
            @Query("apikey") String apikey
    );

    @GET("/gallery")
    Observable<GalleryResponse> getGalleries(
            @Query("floor") int floor,
            @Query("apikey") String apiKey);
}
