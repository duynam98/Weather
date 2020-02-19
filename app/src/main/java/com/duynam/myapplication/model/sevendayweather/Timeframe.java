
package com.duynam.myapplication.model.sevendayweather;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timeframe {


    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("wx_desc")
    @Expose
    private String wxDesc;
    @SerializedName("wx_icon")
    @Expose
    private String wxIcon;
    @SerializedName("temp_c")
    @Expose
    private Double tempC;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView imageView, String name){
        Glide.with(imageView.getContext())
                .load(imageView.getResources().getIdentifier(name, "drawable", imageView.getContext().getPackageName()))
                .into(imageView);
    }

}
