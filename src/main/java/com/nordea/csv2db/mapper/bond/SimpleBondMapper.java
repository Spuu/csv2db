package com.nordea.csv2db.mapper.bond;

import com.nordea.csv2db.model.csv.BondCsv;
import com.nordea.csv2db.model.db.Bond;

import java.math.BigDecimal;
import java.sql.Date;

public class SimpleBondMapper implements BondMapper {

    @Override
    public Bond csvToDto(BondCsv bondCsv) {
        Bond bond = Bond.builder()
                .scenarioDate(Date.valueOf(bondCsv.getScenarioDate()))
                .mvUnit(BigDecimal.valueOf(bondCsv.getMvUnitNotional()))
                .isin(bondCsv.getIsin())
                .countryCode(bondCsv.getIsin().substring(0, 2).toUpperCase())
                .build();

        return bond;
    }
}
