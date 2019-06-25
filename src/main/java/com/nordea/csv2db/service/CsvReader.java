package com.nordea.csv2db.service;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class CsvReader {
    public <T> List<T> readCsvAsObjects(Resource resource, Class<T> clazz) {
        try {
            return new CsvToBeanBuilder<T>(new FileReader(resource.getFile()))
                    .withIgnoreQuotations(true)
                    .withType(clazz)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
