package com.nordea.csv2db.service;

import com.nordea.csv2db.model.csv.BondCsv;
import com.nordea.csv2db.model.db.JTD_BOND_ALL;
import com.nordea.csv2db.model.db.JTD_BOND_FX;
import com.nordea.csv2db.model.db.JTD_BOND_IR;
import com.nordea.csv2db.repository.BondAllRepository;
import com.nordea.csv2db.repository.BondFxRepository;
import com.nordea.csv2db.repository.BondIRRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.EntityType;
import java.io.InvalidClassException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

@Service
public class BondRepositoryRegistry {

    @Autowired EntityManager entityManager;
    @Autowired BondAllRepository bondAllRepository;
    @Autowired BondFxRepository bondFxRepository;
    @Autowired BondIRRepository bondIRRepository;

    private HashMap<Class, RegisterEntry> map;

    @PostConstruct
    private void init() {
        map = new HashMap<>();
        map.put(JTD_BOND_ALL.class, new RegisterEntry(bondAllRepository, new BeanTransformer<BondCsv, JTD_BOND_ALL>(JTD_BOND_ALL.class)));
        map.put(JTD_BOND_FX.class, new RegisterEntry(bondFxRepository, new BeanTransformer<BondCsv, JTD_BOND_FX>(JTD_BOND_FX.class)));
        map.put(JTD_BOND_IR.class, new RegisterEntry(bondIRRepository, new BeanTransformer<BondCsv, JTD_BOND_IR>(JTD_BOND_IR.class)));
    }

    public RegisterEntry getRegisterEntry(Class clazz) {
        return map.get(clazz);
    }

    public RegisterEntry getRegisterEntry(String tableName) throws InvalidClassException {
        Optional<? extends Class<?>> clazz = findEntityClass(tableName);
        return clazz.map(this::getRegisterEntry).orElseThrow(() -> new InvalidClassException("Missing class: {}", tableName));

    }

    public Optional<? extends Class<?>> findEntityClass(String tableName) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        Predicate<EntityType> equalsTableName = e -> e.getName().equals(tableName);

        Optional<? extends Class<?>> clazz = entities.stream()
                .filter(equalsTableName)
                .map(Bindable::getBindableJavaType)
                .findFirst();

        return clazz;
    }

    @AllArgsConstructor
    @Data
    public static class RegisterEntry {
        private JpaRepository jpaRepository;
        private BeanTransformer beanTransformer;
    }
}
