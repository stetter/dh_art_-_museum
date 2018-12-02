package com.stetter.dhartmuseum.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.stetter.dhartmuseum.model.ObjectResponse;
import com.stetter.dhartmuseum.model.Obras;
import com.stetter.dhartmuseum.model.Record;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.digiwood.digiwood.data.util.AppUtil.isNetworkConnected;
import static com.stetter.dhartmuseum.data.local.ObjectDatabase.getDatabase;
import static com.stetter.dhartmuseum.data.network.RetrofitService.API_KEY;
import static com.stetter.dhartmuseum.data.network.RetrofitService.getApiService;

public class ObjectViewModel extends AndroidViewModel {

    public MutableLiveData<List<Record>> objectLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> objectLiveDataError = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public ObjectViewModel(@NonNull Application application) {
        super(application);
    }

    public void getObjects() {

        if (isNetworkConnected(getApplication())) {

            disposable.add(
                    getApiService().getObjects(API_KEY)
                           /* .map(response -> {
                                return saveObject(response);
                            })*/
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
                                isLoading.setValue(true);
                            })
                            .doOnTerminate(() -> {
                                isLoading.setValue(false);
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

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
