package com.finplapp.chartist;

import java.util.List;

abstract class DataOfDiagrams {

    private List<String> labels;

    public DataOfDiagrams() {
    }

    public DataOfDiagrams(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}

