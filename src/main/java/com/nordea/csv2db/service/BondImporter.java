package com.nordea.csv2db.service;

import com.nordea.csv2db.model.csv.Bond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BondImporter {

    private static final String COMA = ", ";
    private static final String QUOTE = "\'";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void write(String table, Bond bond) {

        String statement = String.format("insert into %s values (%s)", table, mapToValues(bond));

        try {
            jdbcTemplate.execute(statement);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String mapToValues(Bond bond) {
        String values = enquote(bond.getScenarioDate()) + COMA +
                        bond.getMvUnitNotional() + COMA +
                        enquote(bond.getIsin()) + COMA +
                        enquote(bond.getIsin().substring(0, 2).toUpperCase());

        return values;
    }

    private String enquote(String str) {
        return QUOTE + str + QUOTE;
    }
}
