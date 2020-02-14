
package com.duynam.myapplication.model.searchCity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("c")
    @Expose
    private String c;
    @SerializedName("zmw")
    @Expose
    private String zmw;
    @SerializedName("tz")
    @Expose
    private String tz;
    @SerializedName("tzs")
    @Expose
    private String tzs;
    @SerializedName("l")
    @Expose
    private String l;
    @SerializedName("ll")
    @Expose
    private String ll;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;

    protected Result(Parcel in) {
        name = in.readString();
        type = in.readString();
        c = in.readString();
        zmw = in.readString();
        tz = in.readString();
        tzs = in.readString();
        l = in.readString();
        ll = in.readString();
        lat = in.readString();
        lon = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getZmw() {
        return zmw;
    }

    public void setZmw(String zmw) {
        this.zmw = zmw;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getTzs() {
        return tzs;
    }

    public void setTzs(String tzs) {
        this.tzs = tzs;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getLl() {
        return ll;
    }

    public void setLl(String ll) {
        this.ll = ll;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(c);
        dest.writeString(zmw);
        dest.writeString(tz);
        dest.writeString(tzs);
        dest.writeString(l);
        dest.writeString(ll);
        dest.writeString(lat);
        dest.writeString(lon);
    }
}
