package com.nordea.csv2db.model.db;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@IdClass(BondIR.class)
@Table(name = "JTD_BOND_IR")
public class BondIR extends Bond implements Serializable {
}
