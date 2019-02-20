package com.finplapp.model;

import java.util.ArrayList;
import java.util.List;

public class DataForChart {

    private List<String> labels;

    private List<List> series;

    public DataForChart(List<String> labels, List<List> series) {
        this.labels = labels;
        this.series = series;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<List> getSeries() {
        return series;
    }

    public void setSeries(List<List> series) {
        this.series = series;
    }
}
