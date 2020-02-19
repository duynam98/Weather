package com.duynam.myapplication.model.modelUsingHttp;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class CurrenLocalCity implements Parcelable {

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

    protected CurrenLocalCity(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            temp = null;
        } else {
            temp = in.readDouble();
        }
        icon = in.readString();
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longtitude = null;
        } else {
            longtitude = in.readDouble();
        }
    }

    public static final Creator<CurrenLocalCity> CREATOR = new Creator<CurrenLocalCity>() {
        @Override
        public CurrenLocalCity createFromParcel(Parcel in) {
            return new CurrenLocalCity(in);
        }

        @Override
        public CurrenLocalCity[] newArray(int size) {
            return new CurrenLocalCity[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (temp == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(temp);
        }
        dest.writeString(icon);
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        if (longtitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longtitude);
        }
    }
}
