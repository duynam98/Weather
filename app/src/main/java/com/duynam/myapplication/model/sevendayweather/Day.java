
package com.duynam.myapplication.model.sevendayweather;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("sunrise_time")
    @Expose
    private String sunriseTime;
    @SerializedName("sunset_time")
    @Expose
    private String sunsetTime;
    @SerializedName("moonrise_time")
    @Expose
    private String moonriseTime;
    @SerializedName("moonset_time")
    @Expose
    private String moonsetTime;
    @SerializedName("temp_max_c")
    @Expose
    private Double tempMaxC;
    @SerializedName("temp_min_c")
    @Expose
    private Double tempMinC;
    @SerializedName("precip_total_mm")
    @Expose
    private Double precipTotalMm;
    @SerializedName("rain_total_mm")
    @Expose
    private Double rainTotalMm;

    @SerializedName("prob_precip_pct")
    @Expose
    private Double probPrecipPct;
    @SerializedName("humid_max_pct")
    @Expose
    private Double humidMaxPct;

    @SerializedName("Timeframes")
    @Expose
    private List<Timeframe> timeframes = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public String getMoonriseTime() {
        return moonriseTime;
    }

    public void setMoonriseTime(String moonriseTime) {
        this.moonriseTime = moonriseTime;
    }

    public String getMoonsetTime() {
        return moonsetTime;
    }

    public void setMoonsetTime(String moonsetTime) {
        this.moonsetTime = moonsetTime;
    }

    public Double getTempMaxC() {
        return tempMaxC;
    }

    public void setTempMaxC(Double tempMaxC) {
        this.tempMaxC = tempMaxC;
    }

    public Double getTempMinC() {
        return tempMinC;
    }

    public void setTempMinC(Double tempMinC) {
        this.tempMinC = tempMinC;
    }

    public Double getPrecipTotalMm() {
        return precipTotalMm;
    }

    public void setPrecipTotalMm(Double precipTotalMm) {
        this.precipTotalMm = precipTotalMm;
    }

    public Double getRainTotalMm() {
        return rainTotalMm;
    }

    public void setRainTotalMm(Double rainTotalMm) {
        this.rainTotalMm = rainTotalMm;
    }

    public Double getProbPrecipPct() {
        return probPrecipPct;
    }

    public void setProbPrecipPct(Double probPrecipPct) {
        this.probPrecipPct = probPrecipPct;
    }

    public Double getHumidMaxPct() {
        return humidMaxPct;
    }

    public void setHumidMaxPct(Double humidMaxPct) {
        this.humidMaxPct = humidMaxPct;
    }

    public List<Timeframe> getTimeframes() {
        return timeframes;
    }

    public void setTimeframes(List<Timeframe> timeframes) {
        this.timeframes = timeframes;
    }

    @BindingAdapter("dayImage")
    public static void loadImage(ImageView imageView, String name){
        Glide.with(imageView.getContext())
                .load(imageView.getResources().getIdentifier(name, "drawable", imageView.getContext().getPackageName()))
                .into(imageView);
    }

}
