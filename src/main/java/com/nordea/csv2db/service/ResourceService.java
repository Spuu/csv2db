package com.nordea.csv2db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ResourceService {

    @Autowired
    AnnotationConfigApplicationContext applicationContext;

    public List<Resource> loadResources(String pattern) {
        try {
            return Arrays.asList(
                    ResourcePatternUtils
                            .getResourcePatternResolver(applicationContext)
                            .getResources(pattern)
            );
        } catch(IOException e) {
            return Collections.emptyList();
        }
    }
}
