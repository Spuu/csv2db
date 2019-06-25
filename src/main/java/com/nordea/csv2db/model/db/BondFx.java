package com.nordea.csv2db.model.db;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@IdClass(BondFx.class)
@Table(name = "JTD_BOND_FX")
public class BondFx extends Bond implements Serializable {
}
