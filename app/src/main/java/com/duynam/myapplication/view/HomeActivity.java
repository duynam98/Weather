package com.duynam.myapplication.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.duynam.myapplication.R;
import com.duynam.myapplication.adapter.HomeAdapter;
import com.duynam.myapplication.adapter.HourAdapter;
import com.duynam.myapplication.data.RetrofitWeather;
import com.duynam.myapplication.database.CurrenWeatherDAO;
import com.duynam.myapplication.database.DatabaseHelper;
import com.duynam.myapplication.databinding.ActivityHomeBinding;
import com.duynam.myapplication.fragment.ListCityFragment;
import com.duynam.myapplication.fragment.SearchCityFragment;
import com.duynam.myapplication.model.City;
import com.duynam.myapplication.model.CurrenWeather;
import com.duynam.myapplication.model.searchCity.Result;
import com.duynam.myapplication.model.sevendayweather.Day;
import com.duynam.myapplication.model.sevendayweather.Forecast;
import com.duynam.myapplication.model.sevendayweather.Timeframe;
import com.duynam.myapplication.untils.Constant;
import com.duynam.myapplication.untils.Untils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import im.delight.android.location.SimpleLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private CurrenWeather currenWeather;
    private Day day;
    private City city;
    public ActivityHomeBinding homeBinding;
    private double latitude, longtitude;
    private List<Day> dayList3;
    private List<Day> daylistFull;
    private HomeAdapter adapter;
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
    private List<City> cities;
    private CurrenWeatherDAO dao;
    public final int MY_PERMISSIONS_REQUEST = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPremission();
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        init();
        callFragmentCity();
        checkLocationgetData();
        scroolLayout();
        setLayoutRecycleview();

        homeBinding.setHome(this);
        homeBinding.setCity(city);

        setHeight();

    }

    public void getCurrentWeather(String latlong) {
        RetrofitWeather.getInstance().getCurrenWeather(latlong, Constant.app_id, Constant.app_key).enqueue(new Callback<CurrenWeather>() {
            @Override
            public void onResponse(Call<CurrenWeather> call, Response<CurrenWeather> response) {
                if (response.code() == 200) {
                    latitude = response.body().getLat();
                    longtitude = response.body().getLon();
                    currenWeather.setDewpointC(response.body().getDewpointC());
                    currenWeather.setTempC(response.body().getTempC());
                    currenWeather.setWxDesc(response.body().getWxDesc());
                    currenWeather.setFeelslikeC(response.body().getFeelslikeC());
                    currenWeather.setHumidPct(response.body().getHumidPct());
                    currenWeather.setWindspdKmh(response.body().getWindspdKmh());
                    City city = dao.geCity(getCurrentNameCity(latitude, longtitude));
                    if (city == null){
                        City addcity = new City(latitude, longtitude, getCurrentNameCity(latitude, longtitude), response.body().getTempC());
                        dao.insertCity(addcity);
                    }else {
                        City updatecity = new City();
                        updatecity.setLatitude(latitude);
                        updatecity.setLongtitude(longtitude);
                        updatecity.setName_city(getCurrentNameCity(latitude, longtitude));
                        updatecity.setTemp_C(response.body().getTempC());
                        dao.updateNote(updatecity);
                    }
                    homeBinding.setCurrentWeather(currenWeather);
                }
            }

            @Override
            public void onFailure(Call<CurrenWeather> call, Throwable t) {

            }
        });
    }

    public void getLatlong() {
        latitude = simpleLocation.getLatitude();
        longtitude = simpleLocation.getLongitude();
        getCurrentNameCity(latitude, longtitude);
        String latlong = latitude + "," + longtitude;
        getCurrentWeather(latlong);
        get7dayForecast(latlong);
    }

    public void get7dayForecast(String latlong) {
        RetrofitWeather.getInstance().get7dayWeather(latlong, Constant.app_id, Constant.app_key).enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                daylistFull.addAll(response.body().getDays());
                adapter.setData(daylistFull.subList(0, 3));
                day.setSunriseTime(daylistFull.get(0).getSunriseTime());
                day.setSunsetTime(daylistFull.get(0).getSunsetTime());
                homeBinding.setDay(day);
                timeframeList.addAll(response.body().getDays().get(0).getTimeframes());
                homeBinding.setTimeframe(timeframeList.get(0));
//                Collections.sort(timeframeList, new Comparator<Timeframe>() {
//                    @Override
//                    public int compare(Timeframe o1, Timeframe o2) {
//                        if (o1.getTime() >= Untils.getCurrenHour()) {
//                            return 1;
//                        } else {
//                            return -1;
//                        }
//                    }
//                });
                adapter.notifyDataSetChanged();
                hourAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public String getCurrentNameCity(double latitude, double longtitude) {
        String cityname = null;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longtitude, 1);
            cityname = addressList.get(0).getAdminArea();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityname;
    }

    public void addMore4(View view) {
        adapter.setData(daylistFull);
        homeBinding.tvAddForecast.setVisibility(View.GONE);
    }

    public static String checkWeather(Day day) {
        if (day.getHumidMaxPct() > 50) {
            return "isorainswrsday";
        } else if (day.getTempMinC() > 25.0) {
            return "sunny";
        } else if (day.getTempMinC() < 20 && day.getHumidMaxPct() > 50) {
            return "mist";
        }
        return "cloudy";
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

    public void init() {
        databaseHelper = new DatabaseHelper(this);
        dao = new CurrenWeatherDAO(databaseHelper);
        simpleLocation = new SimpleLocation(this);
        dayList3 = new ArrayList<>();
        daylistFull = new ArrayList<>();
        timeframeList = new ArrayList<>();
        adapter = new HomeAdapter(this, dayList3);
        hourAdapter = new HourAdapter(this, timeframeList);
        Untils.getCurrendate(homeBinding.tvCurrendate, getResources().getString(R.string.month));
        currenWeather = new CurrenWeather();
        day = new Day();
        fragmentListCity = new ListCityFragment();
        fragmentSearchCity = new SearchCityFragment();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }

    public void setLayoutRecycleview() {
        homeBinding.rvListForecast.setLayoutManager(new LinearLayoutManager(this));
        homeBinding.rvListForecast.setAdapter(adapter);
        homeBinding.rvList24hour.setLayoutManager(layoutManager);
        homeBinding.rvList24hour.setAdapter(hourAdapter);
    }

    public void checkLocationgetData() {
        result = getIntent().getParcelableExtra("latlongcity");
        if (result == null) {
            getLatlong();
        } else {
            String latlong = result.getLat() + "," + result.getLon();
            getCurrentWeather(latlong);
            getCurrentNameCity(Double.parseDouble(result.getLat()), Double.parseDouble(result.getLon()));
            get7dayForecast(latlong);
        }
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

    public void initPremission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

                }else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            1000);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    
                } else {
                    initPremission();
                }
                return;
            }
        }
    }


}
