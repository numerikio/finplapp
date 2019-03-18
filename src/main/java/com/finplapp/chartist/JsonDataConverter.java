package com.finplapp.chartist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("dataConverter")
public class JsonDataConverter implements DataConverter {

    static final Logger logger = LoggerFactory.getLogger(JsonDataConverter.class);

    @Override
    public String getConvertedData(DataOfDiagrams dataOfDiagrams) {
        try {
            return new ObjectMapper().writeValueAsString(dataOfDiagrams);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
