package com.duynam.myapplication.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class City {

    public double latitude;
    public double longtitude;
    public String name_city;
    public double temp_C;
    public String image;

    public City() {
    }

    public City(double latitude, double longtitude, String name_city, double temp_C, String image) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.name_city = name_city;
        this.temp_C = temp_C;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getTemp_C() {
        return temp_C;
    }

    public void setTemp_C(double temp_C) {
        this.temp_C = temp_C;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getName_city() {
        return name_city;
    }

    public void setName_city(String name_city) {
        this.name_city = name_city;
    }

    @BindingAdapter("localcity")
    public static void loadImage(ImageView imageView, String name){
        Glide.with(imageView.getContext())
                .load(imageView.getResources().getIdentifier(name, "drawable", imageView.getContext().getPackageName()))
                .into(imageView);
    }

}
