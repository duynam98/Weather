package com.duynam.myapplication.model.modelUsingHttp;

public class FullMoon {

    public int timestamp;
    public String dateTimeISO;
    public int code;
    public String name;

    public FullMoon(int timestamp, String dateTimeISO, int code, String name) {
        this.timestamp = timestamp;
        this.dateTimeISO = dateTimeISO;
        this.code = code;
        this.name = name;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getDateTimeISO() {
        return dateTimeISO;
    }

    public void setDateTimeISO(String dateTimeISO) {
        this.dateTimeISO = dateTimeISO;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
