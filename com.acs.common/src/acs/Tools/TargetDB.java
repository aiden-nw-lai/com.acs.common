package acs.Tools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TargetDB extends BaseDB{
	
	public static void main(String[] args) {
		
        String _dbURL = "jdbc:mysql://127.0.0.1:3306/rpt_db";
        String _user = "aiden";
        String _pass = "aiden";
        
		TargetDB s_db = new TargetDB();
		// TODO Auto-generated method stub
		if (s_db.test_Connection(_dbURL, _user, _pass)){
			System.out.println("Normal Connected");
		} else {
			System.out.println("Fail");
			
		}
		 
     
        
        
	}

	
}
