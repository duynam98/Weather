package com.duynam.myapplication.model.modelUsingHttp;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class CurrenLocalCity {

    public String name;
    public Double temp;
    public String icon;
    public Double latitude;
    public Double longtitude;

    public CurrenLocalCity() {
    }

    public CurrenLocalCity(String name, Double temp, String icon, Double latitude, Double longtitude) {
        this.name = name;
        this.temp = temp;
        this.icon = icon;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    @BindingAdapter("loadImageFromServer")
    public static void loadImage(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(url).into(imageView);
    }

}
