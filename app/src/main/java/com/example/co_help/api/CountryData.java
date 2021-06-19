package com.example.co_help.api;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CountryData {

    @SerializedName("updated")
    private String lastupdatedtime;

    @SerializedName("country")
    private String country;

    @SerializedName("active")
    private String active;

    @SerializedName("cases")
    private String confirmed;

    @SerializedName("todayCases")
    private String deltaconfirmed;

    @SerializedName("deaths")
    private String deaths;

    @SerializedName("todayDeaths")
    private String deltadeaths;

    @SerializedName("recovered")
    private String recovered;

    @SerializedName("todayRecovered")
    private String deltarecovered;

    private Map<String, String> countryInfo;

    public CountryData(String lastupdatedtime, String country, String active, String confirmed, String deltaconfirmed, String deaths, String deltadeaths, String recovered, String deltarecovered, Map<String, String> countryInfo) {
        this.lastupdatedtime = lastupdatedtime;
        this.country = country;
        this.active = active;
        this.confirmed = confirmed;
        this.deltaconfirmed = deltaconfirmed;
        this.deaths = deaths;
        this.deltadeaths = deltadeaths;
        this.recovered = recovered;
        this.deltarecovered = deltarecovered;
        this.countryInfo = countryInfo;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeltaconfirmed() {
        return deltaconfirmed;
    }

    public void setDeltaconfirmed(String deltaconfirmed) {
        this.deltaconfirmed = deltaconfirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getDeltadeaths() {
        return deltadeaths;
    }

    public void setDeltadeaths(String deltadeaths) {
        this.deltadeaths = deltadeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeltarecovered() {
        return deltarecovered;
    }

    public void setDeltarecovered(String deltarecovered) {
        this.deltarecovered = deltarecovered;
    }

    public Map<String, String> getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(Map<String, String> countryInfo) {
        this.countryInfo = countryInfo;
    }
}
