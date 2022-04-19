package edu.wssu.pico.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
	
	Connection SQLCon = null;
	public SQLUtils newSQL = null;
	
	public DatabaseManager() {
		try {
			newSQL = new SQLUtils();
			SQLCon =  newSQL.getConnection();
			
		} catch (Exception e1) {
			e1.printStackTrace(System.out);
		}
	} // default constructor
	
	public String verifyAdminCredentials(String userName, String passWord) {
		ResultSet currentSessions = null;
        String adminID = null;
        
        try {
			currentSessions = newSQL.fetchResult("SELECT id FROM `admins` WHERE username=\""+userName+"\" and password=\""+passWord+"\"", false);
			while(currentSessions.next()) {
				adminID = currentSessions.getString(1);
			}; // should only be one entry..
			currentSessions.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
        return adminID;
	}
	
	public boolean verifyAdminSession(String sessionID) {
		boolean result = false;
		try {
			ResultSet foundSession = newSQL.fetchResult("SELECT account_id FROM `sessions` WHERE session_key=\""+sessionID+"\"", false);
			if(foundSession != null && foundSession.next()) {
				result = true;
			}
			foundSession.close();
		} catch(SQLException e) {
			System.out.println(System.out);
		}
		
		return result;
	}
	
}