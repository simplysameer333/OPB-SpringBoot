package com.banking.opb.repo;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@PropertySource(value="classpath:sql.properties")
@Configuration
@ConfigurationProperties
@Data
public class SqlQueries {

    private Map<String,String> queries;
}