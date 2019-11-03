package com.banking.opb;

import java.security.Provider;
import java.security.Security;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;
import oracle.security.pki.OraclePKIProvider;

public class WalletTest {
	public static void main(String[] args) {
		Connection conn;
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String walletPath = "F://Softwares//OracleSQLDeveloper//wallet//";
		System.setProperty("oracle.net.tns_admin", walletPath);
		System.setProperty("oracle.net.ssl_server_dn_match", "yes");
		System.setProperty("tcp.validnode_checking", "no");
		OracleDataSource ds = new OracleDataSource();
		Properties props = new Properties();
		System.setProperty("oracle.net.wallet_location", 
				"(SOURCE=(METHOD=FILE)(METHOD_DATA=(DIRECTORY="+walletPath+ ")))");
		props.put("user", "admin");
		props.put("password", "Openbankingpoc_123");
		ds.setConnectionProperties(props);
		ds.setURL("jdbc:oracle:thin:@(description=(address=(protocol=tcps)(port=1522)(host=adb.uk-london-1.oraclecloud.com))(connect_data=(service_name=ecyfn4igydm8di2_openbankingpoc_high.atp.oraclecloud.com))(security=(ssl_server_cert_dn=\"CN=adwc.eucom-central-1.oraclecloud.com,OU=Oracle BMCS FRANKFURT,O=Oracle Corporation,L=Redwood City,ST=California,C=US\")))");
		
		
		
		Provider p;
		p = new OraclePKIProvider();
		Security.insertProviderAt(p, 3);
		conn = ds.getConnection();
		System.out.println(conn);
		
		Statement stmt = conn.createStatement();
        ResultSet rset;
        rset = stmt.executeQuery("SELECT USER FROM DUAL" );
        while (rset.next()) {
            System.out.println( "Oracle User = " + rset.getString(1) );
        }
        rset.close();
        stmt.close();
        conn.close();

		} catch (SQLException ex) {
		System.out.println(ex);

		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}
	}
}
