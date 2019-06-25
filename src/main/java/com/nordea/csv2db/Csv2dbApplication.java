package com.nordea.csv2db;

import com.nordea.csv2db.config.ImportProperties;
import com.nordea.csv2db.model.csv.Bond;
import com.nordea.csv2db.service.BondImporter;
import com.nordea.csv2db.service.CsvReader;
import com.nordea.csv2db.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class Csv2dbApplication {

    private static final String CSV = ".csv";

    @Autowired
    ResourceService resourceService;

    @Autowired
    CsvReader csvReader;

    @Autowired
    BondImporter bondImporter;

    public static void main(String[] args) {
        SpringApplication.run(Csv2dbApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ImportProperties importProperties) {
        return (args) -> {

            System.out.println("START");

            importProperties.getBond()
                    .parallelStream()
                    .forEach(this::importElementProcess);

            System.out.println("END");
        };
    }

    private void importElementProcess(ImportProperties.ImportElement importElement) {
        String table = importElement.getTable();
        List<Resource> resourceList = resourceService.loadResources(importElement.getSource());

        resourceList.parallelStream()
                .map(resource -> csvReader.readCsvAsObjects(resource, Bond.class))
                .flatMap(Collection::parallelStream)
                .filter(bond -> !bond.getZeroSimulation())
                .forEach(bond -> bondImporter.write(table, (Bond) bond));
    }
}
