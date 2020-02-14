
package com.duynam.myapplication.model.searchCity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityListResult {

    @SerializedName("powerdBy")
    @Expose
    private String powerdBy;
    @SerializedName("Results")
    @Expose
    private List<Result> results = null;

    public String getPowerdBy() {
        return powerdBy;
    }

    public void setPowerdBy(String powerdBy) {
        this.powerdBy = powerdBy;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
