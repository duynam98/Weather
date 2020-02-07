
package com.duynam.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrenWeather {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("alt_m")
    @Expose
    private Double altM;
    @SerializedName("alt_ft")
    @Expose
    private Double altFt;
    @SerializedName("wx_desc")
    @Expose
    private String wxDesc;
    @SerializedName("wx_code")
    @Expose
    private Integer wxCode;
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
    @SerializedName("humid_pct")
    @Expose
    private Double humidPct;
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
    @SerializedName("winddir_deg")
    @Expose
    private Double winddirDeg;
    @SerializedName("winddir_compass")
    @Expose
    private String winddirCompass;
    @SerializedName("cloudtotal_pct")
    @Expose
    private Double cloudtotalPct;
    @SerializedName("vis_km")
    @Expose
    private Double visKm;
    @SerializedName("vis_mi")
    @Expose
    private Double visMi;
    @SerializedName("vis_desc")
    @Expose
    private Object visDesc;
    @SerializedName("slp_mb")
    @Expose
    private Double slpMb;
    @SerializedName("slp_in")
    @Expose
    private Double slpIn;
    @SerializedName("dewpoint_c")
    @Expose
    private Double dewpointC;
    @SerializedName("dewpoint_f")
    @Expose
    private Double dewpointF;

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

    public Double getAltM() {
        return altM;
    }

    public void setAltM(Double altM) {
        this.altM = altM;
    }

    public Double getAltFt() {
        return altFt;
    }

    public void setAltFt(Double altFt) {
        this.altFt = altFt;
    }

    public String getWxDesc() {
        return wxDesc;
    }

    public void setWxDesc(String wxDesc) {
        this.wxDesc = wxDesc;
    }

    public Integer getWxCode() {
        return wxCode;
    }

    public void setWxCode(Integer wxCode) {
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

    public Double getHumidPct() {
        return humidPct;
    }

    public void setHumidPct(Double humidPct) {
        this.humidPct = humidPct;
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

    public Double getCloudtotalPct() {
        return cloudtotalPct;
    }

    public void setCloudtotalPct(Double cloudtotalPct) {
        this.cloudtotalPct = cloudtotalPct;
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

    public Object getVisDesc() {
        return visDesc;
    }

    public void setVisDesc(Object visDesc) {
        this.visDesc = visDesc;
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

}
