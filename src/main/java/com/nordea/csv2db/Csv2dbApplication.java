package com.nordea.csv2db;

import com.nordea.csv2db.config.ImportProperties;
import com.nordea.csv2db.mapper.bond.BondMapper;
import com.nordea.csv2db.mapper.bond.SimpleBondMapper;
import com.nordea.csv2db.model.csv.BondCsv;
import com.nordea.csv2db.service.BondRepositoryRegistry;
import com.nordea.csv2db.service.CsvReader;
import com.nordea.csv2db.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class Csv2dbApplication {

    private static final String CSV = ".csv";

    @Autowired
    ImportProperties importProperties;
    @Autowired
    ResourceService resourceService;
    @Autowired
    CsvReader csvReader;
    @Autowired
    BondRepositoryRegistry bondRepositoryRegistry;

    public static void main(String[] args) {
        SpringApplication.run(Csv2dbApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {

            log.info("Importing BONDS started.");

            importProperties.getBond()
                    .parallelStream()
                    .forEach(this::importElementProcess);

            log.info("Importing Bonds ended.");
        };
    }

    @SuppressWarnings("unchecked")
    private void importElementProcess(ImportProperties.ImportElement importElement) {
        Optional<? extends Class<?>> clazz = bondRepositoryRegistry.findEntityClass(importElement.getTable());

        if (clazz.isEmpty()) {
            log.warn("Class: {} not found.", clazz.toString());
            return;
        }

        BondRepositoryRegistry.RegisterEntry registerEntry = bondRepositoryRegistry.getRegisterEntry(clazz.get());
        List<Resource> resourceList = resourceService.loadResources(importElement.getSource());

        BondMapper bondMapper = new SimpleBondMapper();

        registerEntry.getJpaRepository().saveAll(
                (Iterable) resourceList.parallelStream()
                        .filter(resource -> Objects.requireNonNull(resource.getFilename()).endsWith(CSV))
                        .map(resource -> csvReader.readCsvAsObjects(resource, BondCsv.class))
                        .flatMap(Collection::parallelStream)
                        .filter(bond -> !bond.getZeroSimulation())
                        .map(bondMapper::csvToDto)
                        .map(registerEntry.getBeanTransformer())
                        .collect(Collectors.toList()));

    }
}
