package com.nordea.csv2db.repository;

import com.nordea.csv2db.model.db.JTD_BOND_ALL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondAllRepository extends JpaRepository<JTD_BOND_ALL, JTD_BOND_ALL> {
}
