package com.nordea.csv2db.model.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bond {
    // "error","errorText","isin","mvUnitNotional","scenarioDate","scenarioSpecId","tradehubMessageId","type","zeroSimulation"

    @CsvBindByName
    private Boolean error;

    @CsvBindByName
    private String errorText;

    @CsvBindByName
    private String isin;

    @CsvBindByName
    private Double mvUnitNotional;

    @CsvBindByName
    private String scenarioDate;

    @CsvBindByName
    private String scenarioSpecId;

    @CsvBindByName
    private String tradehubMessageId;

    @CsvBindByName
    private String type;

    @CsvBindByName
    private Boolean zeroSimulation;
}
