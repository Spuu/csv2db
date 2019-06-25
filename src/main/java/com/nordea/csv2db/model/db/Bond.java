package com.nordea.csv2db.model.db;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class Bond {
    @Id
    @Column(name = "SCENARIO_DATE")
    @Type(type = "date")
    private Date scenarioDate;

    @Id
    @Column(name = "MV_UNIT", columnDefinition="NUMBER")
    @Type(type = "big_decimal")
    private BigDecimal mvUnit;

    @Id
    @Column(name = "ISIN", length = 12)
    private String isin;

    @Id
    @Column(name = "COUNTRY_CODE", length = 2)
    private String countryCode;
}
