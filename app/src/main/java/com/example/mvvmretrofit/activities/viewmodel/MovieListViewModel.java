package com.example.mvvmretrofit.activities.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmretrofit.activities.model.MovieModel;

import java.util.List;

public class MovieListViewModel extends ViewModel {

//    private MovieRepository movieRepository;

   /* public MovieListViewModel() {
        movieRepository = new MovieRepository();
    }*/

    public MutableLiveData<List<MovieModel.Datum>> getResponse(int page) {

        return MovieRepository.getInstance().getAllData(page);
    }
}
