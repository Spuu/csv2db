package com.nordea.csv2db.repository;

import com.nordea.csv2db.model.db.JTD_BOND_IR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondIRRepository extends JpaRepository<JTD_BOND_IR, JTD_BOND_IR> {
}
