package edu.wssu.pico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;

public class SQLUtils {


	String userName = "";
	String password = "";
	String dbms = "";
	String serverName = "";
	String portNumber = "";
	String dbName = "";
	
	Connection conn = null;
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		userName = System.getenv("userName"); //prop.getProperty("userName"); 
		password = System.getenv("password"); //prop.getProperty("password"); 
		dbms = System.getenv("dbms"); //prop.getProperty("dbms"); 
		serverName = System.getenv("serverName"); //prop.getProperty("serverName"); 
		portNumber = System.getenv("portNumber"); //prop.getProperty("portNumber"); 
		dbName = System.getenv("dbName"); //prop.getProperty("dbName"); 
		
	    if (this.dbms.equals("mysql")) {
	    	String connectionURL = "jdbc:" + this.dbms + "://" +
	                   this.serverName +
	                   ":" + this.portNumber + "/" +
	                   this.dbName;
	        conn = DriverManager.getConnection(
	        			connectionURL,
	                   userName, password);
	    } else if (this.dbms.equals("derby")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + this.dbms + ":" +
	                   this.dbName +
	                   ";create=true",
	                   userName, password);
	    }
	    return conn;
	}
	
	public void executeQuery(PreparedStatement query)
	    throws SQLException {
		
	    try {
	        query.executeUpdate();
	    } catch (SQLException e ) {
	        System.out.println(e);
	    } finally {
	        if (query != null) { query.close(); }
	    }
	}
	
	public ResultSet fetchResult(String query, boolean closeRS)
	    throws SQLException {

	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        stmt = conn.prepareStatement(query);
	        rs = stmt.executeQuery();
	    } catch (SQLException e ) {
	    	e.printStackTrace(System.out);
	    } finally {
	        if (stmt != null && closeRS ) { stmt.close(); }
	    }
	return rs;
	}
}
