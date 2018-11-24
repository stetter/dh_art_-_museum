package com.stetter.dhartmuseum.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.stetter.dhartmuseum.model.Exhibition;
import com.stetter.dhartmuseum.model.ExhibtionResponse;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.stetter.dhartmuseum.data.database.Database.getDatabase;
import static com.stetter.dhartmuseum.data.network.RetrofitService.API_KEY;
import static com.stetter.dhartmuseum.data.network.RetrofitService.getApiService;
import static com.stetter.dhartmuseum.util.AppUtil.isConnected;

public class ExhibitionViewModel extends AndroidViewModel {
    public MutableLiveData<List<Exhibition>> exhibitionLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> exhibitionLiveDataError = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public ExhibitionViewModel(@NonNull Application application) {
        super(application);
    }

    public void getExhibition(String apiKey) {

        if (isConnected(getApplication())) {

            disposable.add(
                    getApiService().getObras(API_KEY)
                            .map((ExhibtionResponse response) -> {
                                return saveExhibition(response);
                            })
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe((Disposable disposable) -> {
                                isLoading.setValue(true);
                            })
                            .doOnTerminate(() -> {
                                isLoading.setValue(false);
                            })
                            .subscribe((ExhibtionResponse response) ->{
                                    exhibitionLiveData.setValue(response.getRecords());
                             }
                             , throwable -> {
            Log.i("LOG", "Error: " + throwable.getMessage());
        })

            );

    } else {

        disposable.add(
                getDatabase(getApplication()).exhibitionDao().getAll()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> {
                            isLoading.setValue(true);
                        })
                        .doOnTerminate(() -> {
                            isLoading.setValue(false);
                        })
                        .subscribe((List<Exhibition> exhibtions) ->
                                        exhibitionLiveData.setValue(exhibtions)
                                , (Throwable throwable) -> {
                                    Log.i("LOG", "Error: " + throwable.getMessage());
                                })

        );
    }

}



    private ExhibtionResponse saveExhibition(ExhibtionResponse response) {
        getDatabase(getApplication()).exhibitionDao().insert((Exhibition) response.getRecords());
        return response;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
