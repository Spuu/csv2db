package com.nordea.csv2db.model.db;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(JTD_BOND_ALL.class)
public class JTD_BOND_ALL extends Bond implements Serializable {
}
