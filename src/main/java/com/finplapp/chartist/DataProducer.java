package com.finplapp.chartist;

import java.time.LocalDate;
import java.util.List;

public interface DataProducer {

    DataOfDiagrams getData(List<LocalDate> list);
}
