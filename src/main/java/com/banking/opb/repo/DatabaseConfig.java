package com.banking.opb.repo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
        	 String seprator = System.getProperty("file.separator");
            
            String filename = "https://drive.google.com/uc?id=1QKsEANKUOsy6xGrVaEuJ34FIXGshuR1Q&export=download";
    		
			File assetsInfo = new File("wallet.zip");
			FileUtils.copyURLToFile(new URL(filename), assetsInfo);
			
			String zipFilePath = assetsInfo.getAbsolutePath();
			String path = zipFilePath.replace("\\wallet.zip", "");
			
			if ("\\".equals(seprator)) {
				path = path.replace("/", seprator);
				zipFilePath = zipFilePath.replace("/", seprator);
            }
			
			//unzip(zipFilePath, path);
			unzipFile(zipFilePath);
			String oracle_net_wallet_location = path + seprator + "wallet";
			System.out.println(path + seprator + "wallet");
            
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
	
	public void unzipFile(String zipName) {
	       // String zipName = "data.zip";
	    	
	        try (FileInputStream fis = new FileInputStream(zipName);
	             ZipInputStream zis =
	                 new ZipInputStream(new BufferedInputStream(fis))) {

	            ZipEntry entry;
	            int i = 0;

	            // Read each entry from the ZipInputStream until no
	            // more entry found indicated by a null return value
	            // of the getNextEntry() method.
	            while ((entry = zis.getNextEntry()) != null) {
	            	if (i==0) {
	            		File f = new File(entry.getName());
	            		f.getParentFile().mkdirs();
	            	}
	                System.out.println("Unzipping: " + entry.getName());

	                int size;
	                byte[] buffer = new byte[2048];

	                try (FileOutputStream fos =
	                         new FileOutputStream(entry.getName());
	                     BufferedOutputStream bos =
	                         new BufferedOutputStream(fos, buffer.length)) {

	                    while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
	                        bos.write(buffer, 0, size);
	                    }
	                    bos.flush();
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

}
