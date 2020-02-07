
package com.duynam.myapplication.model.sevendayweather;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.duynam.myapplication.R;
import com.duynam.myapplication.view.HomeActivity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timeframe {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("utcdate")
    @Expose
    private String utcdate;
    @SerializedName("utctime")
    @Expose
    private Integer utctime;
    @SerializedName("wx_desc")
    @Expose
    private String wxDesc;
    @SerializedName("wx_code")
    @Expose
    private Double wxCode;
    @SerializedName("wx_icon")
    @Expose
    private String wxIcon;
    @SerializedName("temp_c")
    @Expose
    private Double tempC;
    @SerializedName("temp_f")
    @Expose
    private Double tempF;
    @SerializedName("feelslike_c")
    @Expose
    private Double feelslikeC;
    @SerializedName("feelslike_f")
    @Expose
    private Double feelslikeF;
    @SerializedName("winddir_deg")
    @Expose
    private Double winddirDeg;
    @SerializedName("winddir_compass")
    @Expose
    private String winddirCompass;
    @SerializedName("windspd_mph")
    @Expose
    private Double windspdMph;
    @SerializedName("windspd_kmh")
    @Expose
    private Double windspdKmh;
    @SerializedName("windspd_kts")
    @Expose
    private Double windspdKts;
    @SerializedName("windspd_ms")
    @Expose
    private Double windspdMs;
    @SerializedName("windgst_mph")
    @Expose
    private Double windgstMph;
    @SerializedName("windgst_kmh")
    @Expose
    private Double windgstKmh;
    @SerializedName("windgst_kts")
    @Expose
    private Double windgstKts;
    @SerializedName("windgst_ms")
    @Expose
    private Double windgstMs;
    @SerializedName("cloud_low_pct")
    @Expose
    private Double cloudLowPct;
    @SerializedName("cloud_mid_pct")
    @Expose
    private Double cloudMidPct;
    @SerializedName("cloud_high_pct")
    @Expose
    private Double cloudHighPct;
    @SerializedName("cloudtotal_pct")
    @Expose
    private Double cloudtotalPct;
    @SerializedName("precip_mm")
    @Expose
    private Double precipMm;
    @SerializedName("precip_in")
    @Expose
    private Double precipIn;
    @SerializedName("rain_mm")
    @Expose
    private Double rainMm;
    @SerializedName("rain_in")
    @Expose
    private Double rainIn;
    @SerializedName("snow_mm")
    @Expose
    private Double snowMm;
    @SerializedName("snow_in")
    @Expose
    private Double snowIn;
    @SerializedName("snow_accum_cm")
    @Expose
    private Double snowAccumCm;
    @SerializedName("snow_accum_in")
    @Expose
    private Double snowAccumIn;
    @SerializedName("prob_precip_pct")
    @Expose
    private String probPrecipPct;
    @SerializedName("humid_pct")
    @Expose
    private Double humidPct;
    @SerializedName("dewpoint_c")
    @Expose
    private Double dewpointC;
    @SerializedName("dewpoint_f")
    @Expose
    private Double dewpointF;
    @SerializedName("vis_km")
    @Expose
    private Double visKm;
    @SerializedName("vis_mi")
    @Expose
    private Double visMi;
    @SerializedName("slp_mb")
    @Expose
    private Double slpMb;
    @SerializedName("slp_in")
    @Expose
    private Double slpIn;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getUtcdate() {
        return utcdate;
    }

    public void setUtcdate(String utcdate) {
        this.utcdate = utcdate;
    }

    public Integer getUtctime() {
        return utctime;
    }

    public void setUtctime(Integer utctime) {
        this.utctime = utctime;
    }

    public String getWxDesc() {
        return wxDesc;
    }

    public void setWxDesc(String wxDesc) {
        this.wxDesc = wxDesc;
    }

    public Double getWxCode() {
        return wxCode;
    }

    public void setWxCode(Double wxCode) {
        this.wxCode = wxCode;
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

    public Double getTempF() {
        return tempF;
    }

    public void setTempF(Double tempF) {
        this.tempF = tempF;
    }

    public Double getFeelslikeC() {
        return feelslikeC;
    }

    public void setFeelslikeC(Double feelslikeC) {
        this.feelslikeC = feelslikeC;
    }

    public Double getFeelslikeF() {
        return feelslikeF;
    }

    public void setFeelslikeF(Double feelslikeF) {
        this.feelslikeF = feelslikeF;
    }

    public Double getWinddirDeg() {
        return winddirDeg;
    }

    public void setWinddirDeg(Double winddirDeg) {
        this.winddirDeg = winddirDeg;
    }

    public String getWinddirCompass() {
        return winddirCompass;
    }

    public void setWinddirCompass(String winddirCompass) {
        this.winddirCompass = winddirCompass;
    }

    public Double getWindspdMph() {
        return windspdMph;
    }

    public void setWindspdMph(Double windspdMph) {
        this.windspdMph = windspdMph;
    }

    public Double getWindspdKmh() {
        return windspdKmh;
    }

    public void setWindspdKmh(Double windspdKmh) {
        this.windspdKmh = windspdKmh;
    }

    public Double getWindspdKts() {
        return windspdKts;
    }

    public void setWindspdKts(Double windspdKts) {
        this.windspdKts = windspdKts;
    }

    public Double getWindspdMs() {
        return windspdMs;
    }

    public void setWindspdMs(Double windspdMs) {
        this.windspdMs = windspdMs;
    }

    public Double getWindgstMph() {
        return windgstMph;
    }

    public void setWindgstMph(Double windgstMph) {
        this.windgstMph = windgstMph;
    }

    public Double getWindgstKmh() {
        return windgstKmh;
    }

    public void setWindgstKmh(Double windgstKmh) {
        this.windgstKmh = windgstKmh;
    }

    public Double getWindgstKts() {
        return windgstKts;
    }

    public void setWindgstKts(Double windgstKts) {
        this.windgstKts = windgstKts;
    }

    public Double getWindgstMs() {
        return windgstMs;
    }

    public void setWindgstMs(Double windgstMs) {
        this.windgstMs = windgstMs;
    }

    public Double getCloudLowPct() {
        return cloudLowPct;
    }

    public void setCloudLowPct(Double cloudLowPct) {
        this.cloudLowPct = cloudLowPct;
    }

    public Double getCloudMidPct() {
        return cloudMidPct;
    }

    public void setCloudMidPct(Double cloudMidPct) {
        this.cloudMidPct = cloudMidPct;
    }

    public Double getCloudHighPct() {
        return cloudHighPct;
    }

    public void setCloudHighPct(Double cloudHighPct) {
        this.cloudHighPct = cloudHighPct;
    }

    public Double getCloudtotalPct() {
        return cloudtotalPct;
    }

    public void setCloudtotalPct(Double cloudtotalPct) {
        this.cloudtotalPct = cloudtotalPct;
    }

    public Double getPrecipMm() {
        return precipMm;
    }

    public void setPrecipMm(Double precipMm) {
        this.precipMm = precipMm;
    }

    public Double getPrecipIn() {
        return precipIn;
    }

    public void setPrecipIn(Double precipIn) {
        this.precipIn = precipIn;
    }

    public Double getRainMm() {
        return rainMm;
    }

    public void setRainMm(Double rainMm) {
        this.rainMm = rainMm;
    }

    public Double getRainIn() {
        return rainIn;
    }

    public void setRainIn(Double rainIn) {
        this.rainIn = rainIn;
    }

    public Double getSnowMm() {
        return snowMm;
    }

    public void setSnowMm(Double snowMm) {
        this.snowMm = snowMm;
    }

    public Double getSnowIn() {
        return snowIn;
    }

    public void setSnowIn(Double snowIn) {
        this.snowIn = snowIn;
    }

    public Double getSnowAccumCm() {
        return snowAccumCm;
    }

    public void setSnowAccumCm(Double snowAccumCm) {
        this.snowAccumCm = snowAccumCm;
    }

    public Double getSnowAccumIn() {
        return snowAccumIn;
    }

    public void setSnowAccumIn(Double snowAccumIn) {
        this.snowAccumIn = snowAccumIn;
    }

    public String getProbPrecipPct() {
        return probPrecipPct;
    }

    public void setProbPrecipPct(String probPrecipPct) {
        this.probPrecipPct = probPrecipPct;
    }

    public Double getHumidPct() {
        return humidPct;
    }

    public void setHumidPct(Double humidPct) {
        this.humidPct = humidPct;
    }

    public Double getDewpointC() {
        return dewpointC;
    }

    public void setDewpointC(Double dewpointC) {
        this.dewpointC = dewpointC;
    }

    public Double getDewpointF() {
        return dewpointF;
    }

    public void setDewpointF(Double dewpointF) {
        this.dewpointF = dewpointF;
    }

    public Double getVisKm() {
        return visKm;
    }

    public void setVisKm(Double visKm) {
        this.visKm = visKm;
    }

    public Double getVisMi() {
        return visMi;
    }

    public void setVisMi(Double visMi) {
        this.visMi = visMi;
    }

    public Double getSlpMb() {
        return slpMb;
    }

    public void setSlpMb(Double slpMb) {
        this.slpMb = slpMb;
    }

    public Double getSlpIn() {
        return slpIn;
    }

    public void setSlpIn(Double slpIn) {
        this.slpIn = slpIn;
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView imageView, String name){
        Glide.with(imageView.getContext())
                .load(imageView.getResources().getIdentifier(name, "drawable", imageView.getContext().getPackageName()))
                .into(imageView);
    }

}
