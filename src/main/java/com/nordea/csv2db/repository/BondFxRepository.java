package com.nordea.csv2db.repository;

import com.nordea.csv2db.model.db.BondFx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondFxRepository extends JpaRepository<BondFx, BondFx> {
}
