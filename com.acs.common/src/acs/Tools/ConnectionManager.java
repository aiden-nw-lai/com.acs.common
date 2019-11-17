package acs.Tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionManager {
	
	public Connection getConnection(){
		     Connection con = null;
	        PreparedStatement pst = null;
	        ResultSet rs = null;
	      //  System.out.print(System.getProperty("user.dir"));
	        Properties props = new Properties();
	        FileInputStream in = null;

	        try {
	        	//Should retreive from database of mySQL
	            in = new FileInputStream("rptdb.properties");
	            props.load(in);

	        } catch (FileNotFoundException ex) {

	            Logger lgr = Logger.getLogger(ConnectionManager.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);

	        } catch (IOException ex) {

	            Logger lgr = Logger.getLogger(ConnectionManager.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);

	        } finally {
	            ;
	            try {
	                 if (in != null) {
	                     in.close();
	                 }
	            } catch (IOException ex) {
	                Logger lgr = Logger.getLogger(ConnectionManager.class.getName());
	                lgr.log(Level.SEVERE, ex.getMessage(), ex);
	            }
	        }

	        String _dbURL = props.getProperty("db.url");
	        System.out.println(_dbURL);
	        String _user = props.getProperty("db.user");
	        String _pass = props.getProperty("db.passwd");
	        SourceDB s_db = new SourceDB();
	        
	       
			// TODO Auto-generated method stub
			if (s_db.test_Connection(_dbURL, _user, _pass)){
				System.out.println("Normal Connected");
			} else {
				System.out.println("Fail");
				
			}
			return s_db.getConnection(_dbURL, _user, _pass);
	        
	}
	

}
