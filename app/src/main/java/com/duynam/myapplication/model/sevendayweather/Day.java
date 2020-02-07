
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
    @SerializedName("temp_max_f")
    @Expose
    private Double tempMaxF;
    @SerializedName("temp_min_c")
    @Expose
    private Double tempMinC;
    @SerializedName("temp_min_f")
    @Expose
    private Double tempMinF;
    @SerializedName("precip_total_mm")
    @Expose
    private Double precipTotalMm;
    @SerializedName("precip_total_in")
    @Expose
    private Double precipTotalIn;
    @SerializedName("rain_total_mm")
    @Expose
    private Double rainTotalMm;
    @SerializedName("rain_total_in")
    @Expose
    private Double rainTotalIn;
    @SerializedName("snow_total_mm")
    @Expose
    private Double snowTotalMm;
    @SerializedName("snow_total_in")
    @Expose
    private Double snowTotalIn;
    @SerializedName("prob_precip_pct")
    @Expose
    private Double probPrecipPct;
    @SerializedName("humid_max_pct")
    @Expose
    private Double humidMaxPct;
    @SerializedName("humid_min_pct")
    @Expose
    private Double humidMinPct;
    @SerializedName("windspd_max_mph")
    @Expose
    private Double windspdMaxMph;
    @SerializedName("windspd_max_kmh")
    @Expose
    private Double windspdMaxKmh;
    @SerializedName("windspd_max_kts")
    @Expose
    private Double windspdMaxKts;
    @SerializedName("windspd_max_ms")
    @Expose
    private Double windspdMaxMs;
    @SerializedName("windgst_max_mph")
    @Expose
    private Double windgstMaxMph;
    @SerializedName("windgst_max_kmh")
    @Expose
    private Double windgstMaxKmh;
    @SerializedName("windgst_max_kts")
    @Expose
    private Double windgstMaxKts;
    @SerializedName("windgst_max_ms")
    @Expose
    private Double windgstMaxMs;
    @SerializedName("slp_max_in")
    @Expose
    private Double slpMaxIn;
    @SerializedName("slp_max_mb")
    @Expose
    private Double slpMaxMb;
    @SerializedName("slp_min_in")
    @Expose
    private Double slpMinIn;
    @SerializedName("slp_min_mb")
    @Expose
    private Double slpMinMb;
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

    public Double getTempMaxF() {
        return tempMaxF;
    }

    public void setTempMaxF(Double tempMaxF) {
        this.tempMaxF = tempMaxF;
    }

    public Double getTempMinC() {
        return tempMinC;
    }

    public void setTempMinC(Double tempMinC) {
        this.tempMinC = tempMinC;
    }

    public Double getTempMinF() {
        return tempMinF;
    }

    public void setTempMinF(Double tempMinF) {
        this.tempMinF = tempMinF;
    }

    public Double getPrecipTotalMm() {
        return precipTotalMm;
    }

    public void setPrecipTotalMm(Double precipTotalMm) {
        this.precipTotalMm = precipTotalMm;
    }

    public Double getPrecipTotalIn() {
        return precipTotalIn;
    }

    public void setPrecipTotalIn(Double precipTotalIn) {
        this.precipTotalIn = precipTotalIn;
    }

    public Double getRainTotalMm() {
        return rainTotalMm;
    }

    public void setRainTotalMm(Double rainTotalMm) {
        this.rainTotalMm = rainTotalMm;
    }

    public Double getRainTotalIn() {
        return rainTotalIn;
    }

    public void setRainTotalIn(Double rainTotalIn) {
        this.rainTotalIn = rainTotalIn;
    }

    public Double getSnowTotalMm() {
        return snowTotalMm;
    }

    public void setSnowTotalMm(Double snowTotalMm) {
        this.snowTotalMm = snowTotalMm;
    }

    public Double getSnowTotalIn() {
        return snowTotalIn;
    }

    public void setSnowTotalIn(Double snowTotalIn) {
        this.snowTotalIn = snowTotalIn;
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

    public Double getHumidMinPct() {
        return humidMinPct;
    }

    public void setHumidMinPct(Double humidMinPct) {
        this.humidMinPct = humidMinPct;
    }

    public Double getWindspdMaxMph() {
        return windspdMaxMph;
    }

    public void setWindspdMaxMph(Double windspdMaxMph) {
        this.windspdMaxMph = windspdMaxMph;
    }

    public Double getWindspdMaxKmh() {
        return windspdMaxKmh;
    }

    public void setWindspdMaxKmh(Double windspdMaxKmh) {
        this.windspdMaxKmh = windspdMaxKmh;
    }

    public Double getWindspdMaxKts() {
        return windspdMaxKts;
    }

    public void setWindspdMaxKts(Double windspdMaxKts) {
        this.windspdMaxKts = windspdMaxKts;
    }

    public Double getWindspdMaxMs() {
        return windspdMaxMs;
    }

    public void setWindspdMaxMs(Double windspdMaxMs) {
        this.windspdMaxMs = windspdMaxMs;
    }

    public Double getWindgstMaxMph() {
        return windgstMaxMph;
    }

    public void setWindgstMaxMph(Double windgstMaxMph) {
        this.windgstMaxMph = windgstMaxMph;
    }

    public Double getWindgstMaxKmh() {
        return windgstMaxKmh;
    }

    public void setWindgstMaxKmh(Double windgstMaxKmh) {
        this.windgstMaxKmh = windgstMaxKmh;
    }

    public Double getWindgstMaxKts() {
        return windgstMaxKts;
    }

    public void setWindgstMaxKts(Double windgstMaxKts) {
        this.windgstMaxKts = windgstMaxKts;
    }

    public Double getWindgstMaxMs() {
        return windgstMaxMs;
    }

    public void setWindgstMaxMs(Double windgstMaxMs) {
        this.windgstMaxMs = windgstMaxMs;
    }

    public Double getSlpMaxIn() {
        return slpMaxIn;
    }

    public void setSlpMaxIn(Double slpMaxIn) {
        this.slpMaxIn = slpMaxIn;
    }

    public Double getSlpMaxMb() {
        return slpMaxMb;
    }

    public void setSlpMaxMb(Double slpMaxMb) {
        this.slpMaxMb = slpMaxMb;
    }

    public Double getSlpMinIn() {
        return slpMinIn;
    }

    public void setSlpMinIn(Double slpMinIn) {
        this.slpMinIn = slpMinIn;
    }

    public Double getSlpMinMb() {
        return slpMinMb;
    }

    public void setSlpMinMb(Double slpMinMb) {
        this.slpMinMb = slpMinMb;
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
