package com.example.mvvmretrofit.activities.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmretrofit.activities.model.MovieModel;
import com.example.mvvmretrofit.activities.network.APIService;
import com.example.mvvmretrofit.activities.network.RetroInstance;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieRepository {

    private static MovieRepository movieRepository;
    MutableLiveData<List<MovieModel.Datum>> moviesList = new MutableLiveData<>();


    public static MovieRepository getInstance() {

        if (movieRepository == null) {

            movieRepository = new MovieRepository();

        }

        return movieRepository;
    }


    public MutableLiveData<List<MovieModel.Datum>> getAllData(int page) {

        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);


        Observable<MovieModel> observable = apiService.getMovieList(page);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {


                    }

                    @Override
                    public void onNext(@NonNull MovieModel movieModel) {

                        moviesList.setValue(movieModel.getData());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {


                    }

                    @Override
                    public void onComplete() {


                    }
                });

        return moviesList;


    }


}
