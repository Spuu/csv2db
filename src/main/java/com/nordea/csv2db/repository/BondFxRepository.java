package com.nordea.csv2db.repository;

import com.nordea.csv2db.model.db.JTD_BOND_FX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondFxRepository extends JpaRepository<JTD_BOND_FX, JTD_BOND_FX> {
}
