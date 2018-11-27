package com.stetter.dhartmuseum.data.network;

import com.stetter.dhartmuseum.model.ExhibtionResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("/exhibition")
        Observable<ExhibtionResponse> getObras (

                @Query("api_key") String api_key
        );
}
