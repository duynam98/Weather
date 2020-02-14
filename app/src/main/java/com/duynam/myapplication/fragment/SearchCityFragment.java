package com.duynam.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.duynam.myapplication.R;
import com.duynam.myapplication.adapter.SearchCityAdapter;
import com.duynam.myapplication.data.RetrofitWeather;
import com.duynam.myapplication.databinding.FragmentSearchCityBinding;
import com.duynam.myapplication.model.searchCity.CityListResult;
import com.duynam.myapplication.model.searchCity.Result;
import com.duynam.myapplication.view.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCityFragment extends Fragment implements SearchCityAdapter.OnItemClickListener {

    private FragmentSearchCityBinding binding;
    private List<Result> resultList;
    private SearchCityAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_city, container, false);
        resultList = new ArrayList<>();
        adapter = new SearchCityAdapter(resultList, getContext());

        gridLayoutManager = new GridLayoutManager(getContext(), 4);
        binding.rvListCitySearch.setLayoutManager(gridLayoutManager);
        binding.rvListCitySearch.setAdapter(adapter);
        adapter.setListener(this);
        binding.setSearchcity(this);
        return binding.getRoot();
    }

    public void getCity() {
        RetrofitWeather.getInstanceCity().searchCity(binding.edtSearchCity.getText().toString().trim()).enqueue(new Callback<CityListResult>() {
            @Override
            public void onResponse(Call<CityListResult> call, Response<CityListResult> response) {
                if (response.code() == 200) {
                    if (response.body().getResults().size() != 0) {
                        resultList.addAll(response.body().getResults());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), R.string.no_city, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CityListResult> call, Throwable t) {

            }
        });
    }

    @Override
    public void Onclick(Result result) {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.putExtra("latlongcity", result);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void searchCity(View view) {
        resultList.clear();
        getCity();
    }

    public void deleteSearchText(View view) {
        binding.edtSearchCity.setText("");
    }

    public void removeView(View view){
        getActivity().getSupportFragmentManager().beginTransaction().remove(SearchCityFragment.this).commit();
    }



}
