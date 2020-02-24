package com.duynam.myapplication.view.listcity;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.duynam.myapplication.database.CurrenWeatherDAO;
import com.duynam.myapplication.database.DatabaseHelper;
import com.duynam.myapplication.httpUrlConnection.GetWeatherCityHttp;
import com.duynam.myapplication.model.modelUsingHttp.CurrenLocalCity;
import com.duynam.myapplication.utils.Constant;
import java.util.List;

public class ListCityViewModel extends ViewModel {

    private Context context;
    private DatabaseHelper databaseHelper;
    private CurrenWeatherDAO dao;
    private List<CurrenLocalCity> localCityList;

    public ListCityViewModel(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
        this.dao = new CurrenWeatherDAO(databaseHelper);
    }

    public void getDataCity(double latitude, double longtitude) {
        GetWeatherCityHttp http = new GetWeatherCityHttp();
        http.execute(Constant.OPENWEATHER + "?" + "lat=" + latitude + "&" + "lon=" + longtitude + "&" + Constant.APPID_OPENWEATHER);
        GetWeatherCityHttp.OnLoadComplete onLoadComplete = new GetWeatherCityHttp.OnLoadComplete() {
            @Override
            public void onListLoadComplete(CurrenLocalCity localCityList) {
                dao.updateNote(localCityList);
            }
        };
        http.setOnLoadComplete(onLoadComplete);
    }

    public void UpdateCitytoMenu() {
        localCityList = dao.getAllCity();
        if (localCityList.size() != 0) {
            for (int i = 0; i < dao.getAllCity().size(); i++) {
                CurrenLocalCity city = dao.getCity(dao.getAllCity().get(i).getName());
                if (city != null) {
                    getDataCity(localCityList.get(i).getLatitude(), localCityList.get(i).getLongtitude());
                }
            }
        }
    }

    public void deleteCity(CurrenLocalCity cityname) {
        dao.delete(cityname);
    }

}
