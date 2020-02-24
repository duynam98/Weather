package com.duynam.myapplication.view.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.duynam.myapplication.R;
import com.duynam.myapplication.adapter.HomeAdapter;
import com.duynam.myapplication.adapter.HourAdapter;
import com.duynam.myapplication.adapter.ListCityAdapter;
import com.duynam.myapplication.database.CurrenWeatherDAO;
import com.duynam.myapplication.database.DatabaseHelper;
import com.duynam.myapplication.databinding.ActivityHomeBinding;
import com.duynam.myapplication.model.CurrenWeather;
import com.duynam.myapplication.model.modelUsingHttp.FullMoon;
import com.duynam.myapplication.view.listcity.ListCityFragment;
import com.duynam.myapplication.model.searchCity.Result;
import com.duynam.myapplication.model.sevendayweather.Day;
import com.duynam.myapplication.model.sevendayweather.Timeframe;
import com.duynam.myapplication.utils.Utils;
import com.duynam.myapplication.view.searchcity.SearchCityFragment;

import java.util.ArrayList;
import java.util.List;

import im.delight.android.location.SimpleLocation;

public class HomeActivity extends AppCompatActivity implements HomeActivityViewModel.OnLoadList{

    public ActivityHomeBinding homeBinding;
    private double latitude, longtitude;
    private List<Day> daylistFull;
    private HomeAdapter homeAdapter;
    private HourAdapter hourAdapter;
    private List<Timeframe> timeframeList;
    private SimpleLocation simpleLocation;
    private FragmentTransaction fragmentTransaction;
    boolean rotation;
    private SearchCityFragment fragmentSearchCity;
    private ListCityFragment fragmentListCity;
    public FragmentManager fragmentManager;
    private Result result;
    private LinearLayoutManager layoutManager;
    private DatabaseHelper databaseHelper;
    private CurrenWeatherDAO dao;
    private ListCityAdapter listCityAdapter;
    public final int MY_PERMISSIONS_REQUEST = 1000;
    private HomeActivityViewModel homeActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initPremission();
        latitude = simpleLocation.getLatitude();
        longtitude = simpleLocation.getLongitude();
        callFragmentCity();
        scroolLayout();
        setLayoutRecycleview();
        setHeight();
        homeBinding.tvTocdoxe.setText(String.valueOf(Utils.random()));
    }

    public void setHeight() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenHeight = size.y;
        ViewGroup.LayoutParams params = homeBinding.container1.getLayoutParams();
        params.height = screenHeight;
    }

    public void callFragmentCity() {
        homeBinding.imgMenuFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame_fragmentCity, fragmentListCity);
                fragmentTransaction.commit();
            }
        });

    }

    public void callFragmentSearchCity() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame_fragmentCity, fragmentSearchCity);
        fragmentTransaction.commit();
    }

    public void setLayoutRecycleview() {
        homeBinding.rvListForecast.setLayoutManager(new LinearLayoutManager(this));
        homeBinding.rvListForecast.setAdapter(homeAdapter);
        homeBinding.rvList24hour.setLayoutManager(layoutManager);
        homeBinding.rvList24hour.setAdapter(hourAdapter);
    }


    public void scroolLayout() {
        homeBinding.imgNextContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rotation == false) {
                    homeBinding.scrool.smoothScrollTo(0, (int) (homeBinding.container1.getHeight() - 200));
                    homeBinding.imgNextContainer.animate().rotation(180).setDuration(500).start();
                    rotation = true;
                } else {
                    homeBinding.scrool.smoothScrollTo(0, -(homeBinding.container1.getHeight() - 200));
                    homeBinding.imgNextContainer.animate().rotation(360).setDuration(500).start();
                    rotation = false;
                }

            }
        });
    }

    public void initPremission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            1000);
                }
            }
            homeActivityViewModel.checkLocationgetData(this, homeBinding.tvCity);
        } else {
            homeActivityViewModel.checkLocationgetData(this, homeBinding.tvCity);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    homeActivityViewModel.checkLocationgetData(this, homeBinding.tvCity);
                } else {

                }
                return;
            }
        }
    }

    private void initDataBinding() {
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        databaseHelper = new DatabaseHelper(this);
        dao = new CurrenWeatherDAO(databaseHelper);
        homeBinding.tvCurrendate.setText(Utils.getCurrendate(getString(R.string.month)));
        fragmentListCity = new ListCityFragment();
        fragmentSearchCity = new SearchCityFragment();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        timeframeList = new ArrayList<>();
        daylistFull = new ArrayList<>();
        simpleLocation = new SimpleLocation(this);
        homeActivityViewModel = new HomeActivityViewModel(this);
        homeActivityViewModel.setOnLoadList(this);
        homeBinding.setCurrentViewModel(homeActivityViewModel);
        homeAdapter = new HomeAdapter(this, daylistFull);
        hourAdapter = new HourAdapter(this, timeframeList);
    }

    @Override
    public void getDayList(List<Day> days) {
        homeAdapter.setData(days.subList(0, 3));
        homeBinding.rvListForecast.setLayoutManager(new LinearLayoutManager(this));
        homeBinding.rvListForecast.setAdapter(homeAdapter);
        homeBinding.tvAddForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeAdapter.addData(days.subList(3, 7));
                homeBinding.tvAddForecast.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void getTimeList(List<Timeframe> timeframes) {
        hourAdapter.setData(timeframes);
        homeBinding.rvList24hour.setLayoutManager(layoutManager);
        homeBinding.rvList24hour.setAdapter(hourAdapter);
    }

    @Override
    public void getCurrentWether(CurrenWeather currenWeather) {
        homeBinding.setCurrentWeather(currenWeather);
    }

    @Override
    public void getFullMoonFinish(FullMoon moon) {

    }

}
