package com.banking.opb.repo;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@PropertySource("classpath:config.properties")
public class DatabaseConfig {
	@Value("${oracle_tns_url}")
	private String oracle_tns_url;
	
	@Value("${oracle_database_password}")
	private String oracle_database_password;
	
	@Value("${oracle_database_username}")
	private String oracle_database_username;
	
	@Bean
    public DataSource dataSource() {
        OracleDataSource dataSource = null;
        try {
        	
        	 PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

             // Ant-style path matching
            Resource[] resources = resolver.getResources("/wallet/**");
            
            String oracle_net_wallet_location = resources[0].getURI().toString().replace(resources[0].getFilename(), "");
            oracle_net_wallet_location = oracle_net_wallet_location.replace("file:/", "");
            dataSource = new OracleDataSource();
            Properties props = new Properties();
            props.put("oracle.net.wallet_location", "(source=(method=file)(method_data=(directory="+oracle_net_wallet_location+")))");
            props.put("oracle.net.tns_admin", oracle_net_wallet_location);
            props.put("user", oracle_database_username);
    		props.put("password", oracle_database_password);
            dataSource.setConnectionProperties(props);
            dataSource.setURL(oracle_tns_url);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

}
