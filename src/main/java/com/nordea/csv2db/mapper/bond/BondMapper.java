package com.nordea.csv2db.mapper.bond;

import com.nordea.csv2db.model.csv.BondCsv;
import com.nordea.csv2db.model.db.Bond;
import org.mapstruct.Mapper;

@Mapper
public interface BondMapper {
    Bond csvToDto(BondCsv bondCsv);
}
