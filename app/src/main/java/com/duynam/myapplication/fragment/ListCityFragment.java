package com.duynam.myapplication.fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.duynam.myapplication.R;
import com.duynam.myapplication.adapter.ListCityAdapter;
import com.duynam.myapplication.database.CurrenWeatherDAO;
import com.duynam.myapplication.database.DatabaseHelper;
import com.duynam.myapplication.databinding.FragmentListcityBinding;
import com.duynam.myapplication.httpUrlConnection.GetWeatherCityHttp;
import com.duynam.myapplication.model.City;
import com.duynam.myapplication.model.CurrenWeather;
import com.duynam.myapplication.model.modelUsingHttp.CurrenLocalCity;
import com.duynam.myapplication.view.HomeActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ListCityFragment extends Fragment {

    FragmentListcityBinding binding;
    private double latitude, longtitude;
    private CurrenWeather currenWeather;
    private City city;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SearchCityFragment fragmentSearchCity;
    private DatabaseHelper databaseHelper;
    private CurrenWeatherDAO dao;
    private LinearLayoutManager layoutManager;
    private ListCityAdapter adapter;
    private GetWeatherCityHttp weatherCityHttp;
    public List<CurrenLocalCity> localCityList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listcity, container, false);
        init();

        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentSearchCity = new SearchCityFragment();
        callFragmentSearchCity();
        adapter = new ListCityAdapter(getContext(), localCityList);
        binding.rvListCity.setLayoutManager(layoutManager);
        binding.rvListCity.setAdapter(adapter);
        binding.btnAddCity.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.bg_edt_search_city)));
        binding.setListcity(this);
        updateData();
        return binding.getRoot();
    }

    public void init() {
        currenWeather = new CurrenWeather();
        databaseHelper = new DatabaseHelper(getContext());
        dao = new CurrenWeatherDAO(databaseHelper);
        layoutManager = new LinearLayoutManager(getContext());
        localCityList = dao.getAllNote();
    }

    public void removeView(View view) {
        getActivity().getSupportFragmentManager().beginTransaction().remove(ListCityFragment.this).commit();
    }

    public void callFragmentSearchCity() {
        binding.btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).callFragmentSearchCity();
            }
        });
    }

//    public void getCurrentWeather(String latlong) {
//        RetrofitWeather.getInstance().getCurrenWeather(latlong, Constant.app_id, Constant.app_key).enqueue(new Callback<CurrenWeather>() {
//            @Override
//            public void onResponse(Call<CurrenWeather> call, Response<CurrenWeather> response) {
//                if (response.code() == 200) {
//                    latitude = response.body().getLat();
//                    longtitude = response.body().getLon();
//                    City city = dao.geCity(getCurrentNameCity(latitude, longtitude));
//                    if (city == null){
//                        City addcity = new City(latitude, longtitude, getCurrentNameCity(latitude, longtitude),
//                                response.body().getTempC(), Untils.convertImageName(response.body().getWxIcon()));
//                        dao.insertCity(addcity);
//                    }else {
//                        City updatecity = new City();
//                        updatecity.setLatitude(latitude);
//                        updatecity.setLongtitude(longtitude);
//                        updatecity.setName_city(getCurrentNameCity(latitude, longtitude));
//                        updatecity.setTemp_C(response.body().getTempC());
//                        updatecity.setImage(Untils.convertImageName(response.body().getWxIcon()));
//                        dao.updateNote(updatecity);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CurrenWeather> call, Throwable t) {
//
//            }
//        });
//    }

    public String getCurrentNameCity(double latitude, double longtitude) {
        String cityname = null;
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longtitude, 1);
            cityname = addressList.get(0).getAdminArea();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityname;
    }

    private void updateData() {
        if (localCityList.size() != 0) {
            for (int i = 0; i < dao.getAllNote().size(); i++) {
                String latlong = localCityList.get(i).getLatitude() + "," + localCityList.get(i).getLongtitude();
                //getCurrentWeather(latlong);
            }
        }

    }


}
