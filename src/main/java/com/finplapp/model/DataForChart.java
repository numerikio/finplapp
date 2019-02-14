package com.finplapp.model;

import java.util.ArrayList;

public class DataForChart {

    private ArrayList<String> labels;

    private ArrayList<ArrayList> series;

    public DataForChart(ArrayList<String> labels, ArrayList<ArrayList> series) {
        this.labels = labels;
        this.series = series;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }

    public ArrayList<ArrayList> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<ArrayList> series) {
        this.series = series;
    }
}
