package com.duynam.myapplication.view;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.myapplication.R;
import com.duynam.myapplication.adapter.HomeAdapter;
import com.duynam.myapplication.adapter.HourAdapter;
import com.duynam.myapplication.data.RetrofitWeather;
import com.duynam.myapplication.databinding.ActivityHomeBinding;
import com.duynam.myapplication.model.CurrenWeather;
import com.duynam.myapplication.model.sevendayweather.Day;
import com.duynam.myapplication.model.sevendayweather.Forecast;
import com.duynam.myapplication.model.sevendayweather.Timeframe;
import com.duynam.myapplication.untils.Constant;
import com.duynam.myapplication.untils.Untils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    public static TextView tv_currendate;
    public static TextView tv_city;
    public TextView tv_addforecast;
    CurrenWeather currenWeather;
    Day day;
    ActivityHomeBinding homeBinding;
    double latitude, longtitude;
    List<Day> dayList3;
    List<Day> daylistFull;
    RecyclerView rv_forecast;
    RecyclerView rv_24hour;
    HomeAdapter adapter;
    HourAdapter hourAdapter;
    List<Timeframe> timeframeList;
    ConstraintLayout container1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initView();

        dayList3 = new ArrayList<>();
        daylistFull = new ArrayList<>();
        timeframeList = new ArrayList<>();
        adapter = new HomeAdapter(this, dayList3);
        hourAdapter = new HourAdapter(this, timeframeList);
        Untils.getCurrendate(homeBinding.tvCurrendate, getResources().getString(R.string.month));
        currenWeather = new CurrenWeather();
        day = new Day();
        getCurrentLocation();

        rv_forecast.setLayoutManager(new LinearLayoutManager(this));
        rv_forecast.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_24hour.setLayoutManager(layoutManager);
        rv_24hour.setAdapter(hourAdapter);

        DrawerLayout drawerLayout = homeBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        homeBinding.setHome(this);


    }

    public void getCurrentWeather(String latlong) {
        RetrofitWeather.getInstance().getCurrenWeather(latlong, Constant.app_id, Constant.app_key).enqueue(new Callback<CurrenWeather>() {
            @Override
            public void onResponse(Call<CurrenWeather> call, Response<CurrenWeather> response) {
                currenWeather.setDewpointC(response.body().getDewpointC());
                currenWeather.setTempC(response.body().getTempC());
                currenWeather.setWxDesc(response.body().getWxDesc());
                currenWeather.setFeelslikeC(response.body().getFeelslikeC());
                currenWeather.setHumidPct(response.body().getHumidPct());
                currenWeather.setWindspdKmh(response.body().getWindspdKmh());
                homeBinding.setCurrentWeather(currenWeather);
            }

            @Override
            public void onFailure(Call<CurrenWeather> call, Throwable t) {

            }
        });
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
                Collections.sort(timeframeList, new Comparator<Timeframe>() {
                    @Override
                    public int compare(Timeframe o1, Timeframe o2) {
                        if (o1.getTime() >= Untils.getCurrenHour()) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                });
                adapter.notifyDataSetChanged();
                hourAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getCurrentLocation() {
        FusedLocationProviderClient mlocation = LocationServices.getFusedLocationProviderClient(this);
        mlocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    latitude = location.getLatitude();
                    longtitude = location.getLongitude();
                    String baseLatlong = latitude + "," + longtitude;
                    getCurrentNameCity(latitude, longtitude);
                    getCurrentWeather(baseLatlong);
                    get7dayForecast(baseLatlong);
                }
            }
        });

    }


    public void getCurrentNameCity(double latitude, double longtitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longtitude, 1);
            String city = addressList.get(0).getAdminArea();
            tv_city.setText(city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMore4(View view) {
        homeBinding.tvAddForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setData(daylistFull);
            }
        });
        homeBinding.tvAddForecast.setVisibility(View.GONE);
    }

    private void initView() {
        tv_currendate = findViewById(R.id.tv_currendate);
        tv_city = findViewById(R.id.tv_city);
        rv_forecast = findViewById(R.id.rv_listForecast);
        rv_24hour = findViewById(R.id.rv_list24hour);
        container1 = findViewById(R.id.container1);
        tv_addforecast = findViewById(R.id.tv_addForecast);
    }

    public static String checkWeather(Day day){
        if (day.getHumidMaxPct() > 50){
            return "isorainswrsday";
        }else if (day.getTempMinC() > 25.0){
            return "sunny";
        }else if (day.getTempMinC() < 20 && day.getHumidMaxPct() > 50){
            return "mist";
        }
        return "cloudy";
    }


}
