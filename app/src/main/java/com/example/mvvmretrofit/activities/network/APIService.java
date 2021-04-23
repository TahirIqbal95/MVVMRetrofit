package com.example.mvvmretrofit.activities.network;

import com.example.mvvmretrofit.activities.model.MovieModel;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {


    @GET("users")
    Observable<MovieModel> getMovieList(@Query("page") int id);
}
