package com.duynam.myapplication.view.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.myapplication.R;
import com.duynam.myapplication.adapter.HomeAdapter;
import com.duynam.myapplication.adapter.HourAdapter;
import com.duynam.myapplication.database.DatabaseHelper;
import com.duynam.myapplication.databinding.ActivityHomeBinding;
import com.duynam.myapplication.model.CurrenWeather;
import com.duynam.myapplication.model.modelUsingHttp.FullMoon;
import com.duynam.myapplication.model.searchCity.Result;
import com.duynam.myapplication.model.sevendayweather.Day;
import com.duynam.myapplication.model.sevendayweather.Timeframe;
import com.duynam.myapplication.utils.Utils;
import com.duynam.myapplication.view.listcity.ListCityFragment;
import com.duynam.myapplication.view.searchcity.SearchCityFragment;

import java.util.ArrayList;
import java.util.List;

import im.delight.android.location.SimpleLocation;

public class HomeActivity extends AppCompatActivity implements HomeActivityViewModel.OnLoadList{

    public ActivityHomeBinding homeBinding;
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
    private LinearLayoutManager layoutManager;
    private HomeActivityViewModel homeActivityViewModel;
    public static final int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initAdapter();
        initFragment();
        initPermission();
        callFragmentCity();
        scroolLayout();
        setLayoutRecycleview(homeBinding.rvListForecast, homeBinding.rvList24hour);
        setHeight();
        homeBinding.tvTocdoxe.setText(String.valueOf(Utils.random()));
        setDatetoText();
    }

    // đặt lại chiều cao full screen cho ScrollView
    public void setHeight() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenHeight = size.y;
        ViewGroup.LayoutParams params = homeBinding.container1.getLayoutParams();
        params.height = screenHeight;
    }

    // Call fragmentCity khi nhấn menu
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

    // replace FragmentSearchCity khi nhấn FloatingButton
    public void callFragmentSearchCity() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame_fragmentCity, fragmentSearchCity);
        fragmentTransaction.commit();
    }

    // set layout cho recycleview
    public void setLayoutRecycleview(RecyclerView rv_forecast, RecyclerView rv_list24) {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_forecast.setLayoutManager(new LinearLayoutManager(this));
        rv_forecast.setAdapter(homeAdapter);
        rv_list24.setLayoutManager(layoutManager);
        rv_list24.setAdapter(hourAdapter);
    }

    // nhấn mũi tên ScrollView trượt xuống hoặc lên
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

    // xin quyền location
    public void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                }
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                return;
            }
            homeActivityViewModel.checkLocationgetData(this, homeBinding.tvCity);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    homeActivityViewModel.checkLocationgetData(this, homeBinding.tvCity);
                } else {

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //khởi tạo adapter
    private void initAdapter(){
        timeframeList = new ArrayList<>();
        daylistFull = new ArrayList<>();
        homeAdapter = new HomeAdapter(this, daylistFull);
        hourAdapter = new HourAdapter(this, timeframeList);
    }

    //Khởi tạo Fragment
    private void initFragment(){
        fragmentListCity = new ListCityFragment();
        fragmentSearchCity = new SearchCityFragment();
    }

    //set currentDate lên textview
    private void setDatetoText(){
        homeBinding.tvCurrendate.setText(Utils.getCurrendate(getString(R.string.month)));
    }

    private void initDataBinding() {
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        simpleLocation = new SimpleLocation(this);
        homeActivityViewModel = new HomeActivityViewModel(this);
        homeActivityViewModel.setOnLoadList(this);
        homeBinding.setCurrentViewModel(homeActivityViewModel);
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
