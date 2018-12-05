package com.stetter.dhartmuseum.home.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.stetter.dhartmuseum.data.database.Database;
import com.stetter.dhartmuseum.home.model.GalleryRecord;
import com.stetter.dhartmuseum.home.model.GalleryResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.digiwood.digiwood.data.util.AppUtil.isNetworkConnected;
import static com.stetter.dhartmuseum.data.network.RetrofitService.API_KEY;
import static com.stetter.dhartmuseum.data.network.RetrofitService.getApiService;

public class GalleryViewModel extends AndroidViewModel {

    public MutableLiveData<List<GalleryRecord>> galleryLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> galleryLiveDataError = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public GalleryViewModel(@NonNull Application application) {
        super(application);
    }

    public void getGalleryRecords() {

        if (isNetworkConnected(getApplication())) {
            disposable.add(
                    getApiService().getGalleries(API_KEY)
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
                                    isLoading.setValue(true);
                                }
                            })
                            .doOnTerminate(() -> {
                                isLoading.setValue(false);
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
                                isLoading.setValue(true);
                            })
                            .doOnTerminate(() -> {
                                isLoading.setValue(false);
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