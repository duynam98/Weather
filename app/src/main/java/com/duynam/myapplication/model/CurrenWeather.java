
package com.duynam.myapplication.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrenWeather {

    @SerializedName("lat")
    @Expose
    private Double lat;

    @SerializedName("lon")
    @Expose
    private Double lon;

    @SerializedName("wx_desc")
    @Expose
    private String wxDesc;

    @SerializedName("wx_icon")
    @Expose
    private String wxIcon;

    @SerializedName("temp_c")
    @Expose
    private Double tempC;

    @SerializedName("feelslike_c")
    @Expose
    private Double feelslikeC;

    @SerializedName("humid_pct")
    @Expose
    private Double humidPct;

    @SerializedName("windspd_kmh")
    @Expose
    private Double windspdKmh;

    @SerializedName("dewpoint_c")
    @Expose
    private Double dewpointC;
    @SerializedName("winddir_deg")
    @Expose
    private int winddirdeg;

    public int getWinddirdeg() {
        return winddirdeg;
    }

    public void setWinddirdeg(int winddirdeg) {
        this.winddirdeg = winddirdeg;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getWxDesc() {
        return wxDesc;
    }

    public void setWxDesc(String wxDesc) {
        this.wxDesc = wxDesc;
    }

    public String getWxIcon() {
        return wxIcon;
    }

    public void setWxIcon(String wxIcon) {
        this.wxIcon = wxIcon;
    }

    public Double getTempC() {
        return tempC;
    }

    public void setTempC(Double tempC) {
        this.tempC = tempC;
    }

    public Double getFeelslikeC() {
        return feelslikeC;
    }

    public void setFeelslikeC(Double feelslikeC) {
        this.feelslikeC = feelslikeC;
    }

    public Double getHumidPct() {
        return humidPct;
    }

    public void setHumidPct(Double humidPct) {
        this.humidPct = humidPct;
    }

    public Double getWindspdKmh() {
        return windspdKmh;
    }

    public void setWindspdKmh(Double windspdKmh) {
        this.windspdKmh = windspdKmh;
    }

    public Double getDewpointC() {
        return dewpointC;
    }

    public void setDewpointC(Double dewpointC) {
        this.dewpointC = dewpointC;
    }

    @BindingAdapter("currenImage")
    public static void loadImage(ImageView imageView, String name){
        Glide.with(imageView.getContext())
                .load(imageView.getResources().getIdentifier(name, "drawable", imageView.getContext().getPackageName()))
                .into(imageView);
    }

}
