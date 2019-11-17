package acs.Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class TargetConnection {
	  private static String JDBC_CONNECTION_URL = "jdbc:mysql://localhost:3306/rpt_db?generateSimpleParameterMetadata=true";
      private static String JDBC_USERNAME = "root";
      private static String JDBC_PASSWORD = "root";
      private final static Logger logger = Logger.getLogger(TargetConnection.class
                              .getName());
      
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Connection conn =  getConnection();
		 try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
	      Connection con = null;
	  	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}
          try {
        	  
        	  JDBC_CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/rpt_db?";
                    //  Class.forName("com.mysql.jdbc.Driver");
                      con = DriverManager.getConnection(JDBC_CONNECTION_URL,
                                              JDBC_USERNAME, JDBC_PASSWORD);

    
          } catch (SQLException e) {
                      logger.severe(e.getMessage());
        
		}

          return con;
	}

}
