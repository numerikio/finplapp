package com.finplapp.chartist;

import java.util.List;

public class DatasOfLineDiagram extends DataOfDiagrams {

    private List<List> series;

    public DatasOfLineDiagram(List<String> labels, List<List> series) {
        super(labels);
        this.series = series;
    }

    public List<List> getSeries() {
        return series;
    }

    public void setSeries(List<List> series) {
        this.series = series;
    }
}
