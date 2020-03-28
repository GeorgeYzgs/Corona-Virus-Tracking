package com.spring.coronavirus.models;

/**
 * @author George.Giazitzis
 */
public class LocationStats {

    private String state;
    private String country;
    private int latestTotalCases;
    private int diffFromPreviousDayCases;
    private int latestTotalDeaths;
    private int diffFromPreviousDayDeaths;
    private int latestTotalRecovered;
    private int diffFromPreviousDayRecovered;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public int getDiffFromPreviousDayCases() {
        return diffFromPreviousDayCases;
    }

    public void setDiffFromPreviousDayCases(int diffFromPreviousDayCases) {
        this.diffFromPreviousDayCases = diffFromPreviousDayCases;
    }

    public int getLatestTotalDeaths() {
        return latestTotalDeaths;
    }

    public void setLatestTotalDeaths(int latestTotalDeaths) {
        this.latestTotalDeaths = latestTotalDeaths;
    }

    public int getDiffFromPreviousDayDeaths() {
        return diffFromPreviousDayDeaths;
    }

    public void setDiffFromPreviousDayDeaths(int diffFromPreviousDayDeaths) {
        this.diffFromPreviousDayDeaths = diffFromPreviousDayDeaths;
    }

    public int getLatestTotalRecovered() {
        return latestTotalRecovered;
    }

    public void setLatestTotalRecovered(int latestTotalRecovered) {
        this.latestTotalRecovered = latestTotalRecovered;
    }

    public int getDiffFromPreviousDayRecovered() {
        return diffFromPreviousDayRecovered;
    }

    public void setDiffFromPreviousDayRecovered(int diffFromPreviousDayRecovered) {
        this.diffFromPreviousDayRecovered = diffFromPreviousDayRecovered;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                ", diffFromPreviousDayCases=" + diffFromPreviousDayCases +
                ", latestTotalDeaths=" + latestTotalDeaths +
                ", diffFromPreviousDayDeaths=" + diffFromPreviousDayDeaths +
                ", latestTotalRecovered=" + latestTotalRecovered +
                ", diffFromPreviousDayRecovered=" + diffFromPreviousDayRecovered +
                '}';
    }
}
