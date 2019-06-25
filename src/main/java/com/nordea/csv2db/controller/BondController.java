package com.nordea.csv2db.controller;

import com.nordea.csv2db.model.db.BondAll;
import com.nordea.csv2db.model.db.BondFx;
import com.nordea.csv2db.model.db.BondIR;
import com.nordea.csv2db.repository.BondAllRepository;
import com.nordea.csv2db.repository.BondFxRepository;
import com.nordea.csv2db.repository.BondIRRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BondController {

    private final BondAllRepository bondAllRepository;

    private final BondFxRepository bondFxRepository;

    private final BondIRRepository bondIRRepository;

    public BondController(BondAllRepository bondAllRepository, BondFxRepository bondFxRepository, BondIRRepository bondIRRepository) {
        this.bondAllRepository = bondAllRepository;
        this.bondFxRepository = bondFxRepository;
        this.bondIRRepository = bondIRRepository;
    }

    @GetMapping(path = "/bondAll")
    List<BondAll> getBondAllList() {
        return bondAllRepository.findAll();
    }

    @GetMapping(path = "/bondFx")
    List<BondFx> getBondFxList() {
        return bondFxRepository.findAll();
    }

    @GetMapping(path = "/bondIR")
    List<BondIR> getBondIrList() {
        return bondIRRepository.findAll();
    }
}
