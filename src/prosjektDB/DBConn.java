package prosjektDB;

import java.sql.*;
import java.util.Properties;

public class DBConn {
	protected Connection conn;
	
	public DBConn () {
	}
	
	public void connect() {
		 try {
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
			 Properties p = new Properties ();
			 p.put("user","henriksy");
			 p.put("password","passord169");
			 conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no/dbname?autoReconnect=true&useSSL=false",p);
		 } catch (Exception e) {
			 throw new RuntimeException("Unable to connect", e);
		}
	} 
}