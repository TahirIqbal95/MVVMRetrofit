package com.example.mvvmretrofit.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmretrofit.R;
import com.example.mvvmretrofit.activities.adapter.MovieListAdapter;
import com.example.mvvmretrofit.activities.adapter.MovieListAdapter.ItemClickListener;
import com.example.mvvmretrofit.activities.model.MovieModel;
import com.example.mvvmretrofit.activities.viewmodel.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private List<MovieModel.Datum> movieModelList;
    private MovieListAdapter adapter;
    private MovieListViewModel viewModel;

    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView noresult;

    private boolean isScrolling;
    private int currentItem;
    private int totalItem, scrollItem;
    private int page = 1;


    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 2; //your total page
    private int currentPage = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieModelList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progessbar);
        noresult = findViewById(R.id.noResultTv);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieListAdapter(this, movieModelList, this);
        recyclerView.setAdapter(adapter);





        viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        SignInApiCall(2);




    /*    viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.getMoviesListObserver().observe(this, new Observer<List<MovieModel.Datum>>() {
            @Override
            public void onChanged(List<MovieModel.Datum> movieModels) {
                if (movieModels != null) {
                    movieModelList = movieModels;
                    adapter.setMovieList(movieModels);
                    noresult.setVisibility(View.GONE);
                } else {
                    noresult.setVisibility(View.VISIBLE);
                }
            }
        });


        viewModel.makeApiCall(page);*/

    }


    @Override
    public void onMovieClick(MovieModel.Datum movie) {
        Toast.makeText(this, "Clicked Movie Name is : " + movie.getFirstName().toString(), Toast.LENGTH_SHORT).show();
    }

    private void SignInApiCall(int page) {





////////////////////////////////////////////////////////////////////////////////////////////////////
        viewModel.getResponse(page).observe(this, new Observer<List<MovieModel.Datum>>() {
            @Override
            public void onChanged(List<MovieModel.Datum> data) {

                if (data != null) {
                    movieModelList.addAll(data);

                    adapter.setMovieList(data);
//                    adapter.notifyDataSetChanged();

                    noresult.setVisibility(View.GONE);
                } else {
                    noresult.setVisibility(View.VISIBLE);
                }

            }
        });
    }
}