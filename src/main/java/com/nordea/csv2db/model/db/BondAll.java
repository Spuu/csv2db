package com.nordea.csv2db.model.db;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@IdClass(BondAll.class)
@Table(name = "JTD_BOND_ALL")
public class BondAll extends Bond implements Serializable {
}
