package com.stetter.dhartmuseum.home.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.stetter.dhartmuseum.data.database.Database;
import com.stetter.dhartmuseum.home.model.GalleryRecord;
import com.stetter.dhartmuseum.home.model.GalleryResponse;
import com.stetter.dhartmuseum.model.ObjectResponse;
import com.stetter.dhartmuseum.model.Record;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.digiwood.digiwood.data.util.AppUtil.isNetworkConnected;
import static com.stetter.dhartmuseum.data.database.Database.getDatabase;
import static com.stetter.dhartmuseum.data.network.RetrofitService.API_KEY;
import static com.stetter.dhartmuseum.data.network.RetrofitService.getApiService;

public class HomeViewModel extends AndroidViewModel {

    public MutableLiveData<List<GalleryRecord>> galleryLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> galleryLiveDataError = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingGallery = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<List<Record>> objectLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> objectLiveDataError = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoadingObject = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void getGalleryRecords(int floor) {

        if (isNetworkConnected(getApplication())) {
            disposable.add(
                    getApiService().getGalleries(floor, API_KEY)
                            .map(new Function<GalleryResponse, GalleryResponse>() {
                                @Override
                                public GalleryResponse apply(GalleryResponse response) throws Exception {
                                    return saveGalleries(response);
                                }
                            })
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(new Consumer<Disposable>() {
                                @Override
                                public void accept(Disposable disposable) throws Exception {
                                    isLoadingGallery.setValue(true);
                                }
                            })
                            .doOnTerminate(() -> {
                                isLoadingGallery.setValue(false);
                            })
                            .subscribe(new Consumer<GalleryResponse>() {
                                @Override
                                public void accept(GalleryResponse response) throws Exception {
                                    galleryLiveData.setValue(response.getGalleryRecords());
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.i("LOG", "Error: " + throwable.getMessage());
                                }
                            })
            );
        } else {
            disposable.add(
                    Database.getDatabase(getApplication()).getGalleryRecordDAO().getAll()
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(disposable -> {
                                isLoadingGallery.setValue(true);
                            })
                            .doOnTerminate(() -> {
                                isLoadingGallery.setValue(false);
                            })
                            .subscribe(new Consumer<List<GalleryRecord>>() {
                                @Override
                                public void accept(List<GalleryRecord> galleryRecordList) throws Exception {
                                    galleryLiveData.setValue(galleryRecordList);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.i("LOG", "Error: " + throwable.getMessage());
                                }
                            })
            );
        }

    }

    public void getObjects(String sort) {

        if (isNetworkConnected(getApplication())) {

            disposable.add(
                    getApiService().getObjects(sort, API_KEY)
                            /* .map(response -> {
                                 return saveObject(response);
                             })*/
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(new Consumer<Disposable>() {
                                @Override
                                public void accept(Disposable disposable) throws Exception {
                                    isLoadingGallery.setValue(true);
                                }
                            })
                            .doOnTerminate(() -> {
                                isLoadingGallery.setValue(false);
                            })
                            .subscribe(response ->
                                            objectLiveData.setValue(response.getRecords())
                                    , throwable -> {
                                        Log.i("LOG", "Error: " + throwable.getMessage());
                                    })

            );

        } else {

            disposable.add(
                    getDatabase(getApplication()).movieDAO().getAll()
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(disposable -> {
                                isLoadingGallery.setValue(true);
                            })
                            .doOnTerminate(() -> {
                                isLoadingGallery.setValue(false);
                            })
                            .subscribe(record ->
                                            objectLiveData.setValue(record)
                                    , throwable -> {
                                        Log.i("LOG", "Error: " + throwable.getMessage());
                                    })

            );
        }
    }

    private ObjectResponse saveObject(ObjectResponse response) {
        getDatabase(getApplication()).movieDAO().insert(response.getRecords());
        return response;
    }

    private GalleryResponse saveGalleries(GalleryResponse response) {
        Database.getDatabase(getApplication()).getGalleryRecordDAO().insert(response.getGalleryRecords());
        return response;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}