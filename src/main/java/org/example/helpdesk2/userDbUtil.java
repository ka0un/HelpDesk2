package org.example.helpdesk2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class userDbUtil {
	private DataSource dataSource;

	public userDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void close(Connection myConn, Statement mystmt, ResultSet myRs) {
		try {
			if(myRs != null) {
				myRs.close();
				
			}
			if(mystmt != null) {
				mystmt.close();
				
			}
			if(myConn != null) {
				myConn.close();
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public boolean validateUser(String email, String pw) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		User theUser = null;
		boolean isValid = false;
		
		try {
			
			myConn = dataSource.getConnection();
			String sql = "select * from user where email=? and password=?";
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, email);
			myStmt.setString(2, pw);
			
			myRs= myStmt.executeQuery();
			
			
                if (myRs.next()) {
                    isValid = true; // User exists
                }}
          catch(Exception e) {
        	  e.printStackTrace();
          }
			
		finally {
			close(myConn, myStmt, myRs);
			return isValid;
		}
		
	}
	
	
	
	
}
