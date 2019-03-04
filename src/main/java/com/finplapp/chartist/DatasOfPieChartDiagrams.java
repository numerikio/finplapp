package com.finplapp.chartist;

import java.util.List;

public class DatasOfPieChartDiagrams extends DataOfDiagrams {

    private List<Double> series;

    public DatasOfPieChartDiagrams(List<String> labels, List<Double> series) {
        super(labels);
        this.series = series;
    }

    public List<Double> getSeries() {
        return series;
    }

    public void setSeries(List<Double> series) {
        this.series = series;
    }
}
