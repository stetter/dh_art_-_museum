package com.stetter.dhartmuseum.data.network;

import com.stetter.dhartmuseum.model.ObjectResponse;

import io.reactivex.Observable;

import static com.stetter.dhartmuseum.data.network.RetrofitService.API_KEY;

public class ObjectRemoteRepository {

    public Observable<ObjectResponse> searchItems(String item) {
        return RetrofitService.getApiService().getObjects(item, API_KEY);
    }
}
