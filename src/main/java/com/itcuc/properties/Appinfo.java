package com.itcuc.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:appinfo.properties")
@Data
public class Appinfo {
    @Value("${projectName}")
    private String projectName;
    @Value("${projectVersion}")
    private String projectVersion;
}
