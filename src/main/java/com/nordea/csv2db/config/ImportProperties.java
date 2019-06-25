package com.nordea.csv2db.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties("import")
@Data
public class ImportProperties {

    private List<ImportElement> bond = new ArrayList<>();

    @Data
    public static class ImportElement {
        private String table;
        private String source;
    }
}
