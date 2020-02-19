package com.duynam.myapplication.view.searchcity;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.duynam.myapplication.data.RetrofitWeather;
import com.duynam.myapplication.model.searchCity.CityListResult;
import com.duynam.myapplication.model.searchCity.Result;
import com.duynam.myapplication.ui.WP7ProgressBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCityViewModel extends ViewModel {

    private List<Result> resultList = new ArrayList<>();
    private OnSearchCity onSearchCity;
    public WP7ProgressBar progress;

    public void setOnSearchCity(OnSearchCity onSearchCity) {
        this.onSearchCity = onSearchCity;
    }

    public SearchCityViewModel(WP7ProgressBar progress) {
        this.progress = progress;
    }

    public void getCity(String query, View view, String mess, int dur) {
        progress.showProgressBar();
        RetrofitWeather.getInstanceCity().searchCity(query).enqueue(new Callback<CityListResult>() {
            @Override
            public void onResponse(Call<CityListResult> call, Response<CityListResult> response) {
                if (response.code() == 200) {
                    progress.hideProgressBar();
                    if (response.body().getResults().size() != 0) {
                        resultList.clear();
                        resultList.addAll(response.body().getResults());
                        onSearchCity.getCity(resultList);
                    } else {
                        showSnackBar(view, mess, dur);
                    }
                }
            }

            @Override
            public void onFailure(Call<CityListResult> call, Throwable t) {

            }
        });
    }

    public interface OnSearchCity {
        void getCity(List<Result> results);
    }

    public void showSnackBar(View view, String mess, int dur) {
        final Snackbar snackbar = Snackbar.make(view, mess, dur);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

}
