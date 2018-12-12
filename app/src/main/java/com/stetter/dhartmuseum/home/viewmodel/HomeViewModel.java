package com.stetter.dhartmuseum.home.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.stetter.dhartmuseum.data.database.Database;
import com.stetter.dhartmuseum.data.local.ObjectLocalRepository;
import com.stetter.dhartmuseum.data.network.ObjectRemoteRepository;
import com.stetter.dhartmuseum.home.model.GalleryRecord;
import com.stetter.dhartmuseum.home.model.GalleryResponse;
import com.stetter.dhartmuseum.model.ObjectResponse;
import com.stetter.dhartmuseum.model.Record;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.digiwood.digiwood.data.util.AppUtil.isNetworkConnected;
import static com.stetter.dhartmuseum.data.database.Database.getDatabase;
import static com.stetter.dhartmuseum.data.network.RetrofitService.API_KEY;
import static com.stetter.dhartmuseum.data.network.RetrofitService.getApiService;

public class HomeViewModel extends AndroidViewModel {

    public MutableLiveData<List<GalleryRecord>> galleryLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Record>> objectLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Record>> objectByGalleryIdLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoadingGallery = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoadingObject = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoadingObjectByGalleryId = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<Throwable> galleryLiveDataError = new MutableLiveData<>();
    MutableLiveData<Throwable> objectLiveDataError = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void getGalleryRecords(int floor) {

        if (isNetworkConnected(getApplication())) {
            disposable.add(
                    getApiService().getGalleries(floor, API_KEY)
                            .map(response -> saveGalleries(response))
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(disposable -> isLoadingGallery.setValue(true))
                            .doOnTerminate(() -> {
                                isLoadingGallery.setValue(false);
                            })
                            .subscribe(response -> galleryLiveData.setValue(response.getGalleryRecords()),
                                    throwable -> Log.i("LOG", "Error: " + throwable.getMessage()))
            );
        } else {
            disposable.add(
                    Database.getDatabase(getApplication()).galleryRecordDAO().getAll()
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(disposable -> {
                                isLoadingGallery.setValue(true);
                            })
                            .doOnTerminate(() -> {
                                isLoadingGallery.setValue(false);
                            })
                            .subscribe(galleryRecordList -> galleryLiveData.setValue(galleryRecordList),
                                    throwable -> Log.i("LOG", "Error: " + throwable.getMessage()))
            );
        }

    }

    public void getObjects(String keyword) {

        if (isNetworkConnected(getApplication())) {

            disposable.add(
                    getApiService().getObjects(keyword, API_KEY)
                            .map(response -> {
                                return saveObject(response);
                            })
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(disposable -> isLoadingGallery.setValue(true))
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
                    getDatabase(getApplication()).objectDAO().getAll()
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
        getDatabase(getApplication()).objectDAO().insert(response.getRecords());
        return response;
    }

    private GalleryResponse saveGalleries(GalleryResponse response) {
        Database.getDatabase(getApplication()).galleryRecordDAO().insert(response.getGalleryRecords());
        return response;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    public void searchItem(String item) {
        if (isNetworkConnected(getApplication())) {
            getFromNetwork(item);
        } else {
            getFromLocal();
        }
    }

    private void getFromLocal() {
        ObjectLocalRepository localRepository = new ObjectLocalRepository();

        disposable.add(localRepository.getLocalRecords(getApplication().getApplicationContext())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {
                    objectLiveData.setValue(results);
                }, throwable -> {
                    Log.i("LOG", "Error: " + throwable.getMessage());
                }));
    }

    private void getFromNetwork(String item) {
        ObjectRemoteRepository remoteRepository = new ObjectRemoteRepository();

        disposable.add(remoteRepository.searchItems(item)
                .subscribeOn(Schedulers.newThread())
                .map(this::saveItems)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemResponse -> {
                    objectLiveData.setValue(itemResponse.getRecords());
                }, throwable -> {
                    // Se deu erro mostramos o log
                    Log.i("LOG", "Error: " + throwable.getMessage());
                }));
    }

    public ObjectResponse saveItems(ObjectResponse response) {
        ObjectLocalRepository localRepository = new ObjectLocalRepository();
        localRepository.insertItems(getApplication(), response.getRecords());
        return response;
    }
}