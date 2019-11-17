package acs.Tools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;



public class BaseDB {
	final static Logger logger = Logger.getLogger(BaseDB.class);
	private Connection conn = null;
	private String dbURL = "";
	private String user = "";
	private String pass = "";
	
	
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public Connection getConnection(String _dbURL, String _user, String _pass){
		
		 try {
			 	conn = null;
	            String dbURL = _dbURL;
	            String user = _user;
	            String pass = _pass;
	            
	            conn = DriverManager.getConnection(dbURL, user, pass);
	            if (conn != null) {
	                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
	                if (logger.isDebugEnabled()){
		                logger.debug("Driver name: " + dm.getDriverName());
		                logger.debug("Driver version: " + dm.getDriverVersion());
		                logger.debug("Product name: " + dm.getDatabaseProductName());
		                logger.debug("Product version: " + dm.getDatabaseProductVersion());
	                }
	            }
	 
	        } catch (SQLException ex) {
	           	 if (logger.isDebugEnabled()){
	        		 logger.debug(ex.getMessage(), ex);
	        	 }
	        } 
		return conn;
		
	}
	public boolean test_Connection(String _dbURL, String _user, String _pass){
		   boolean _flag = false;
		   try {
			   Connection _conn = getConnection(_dbURL, _user, _pass);
			   if (_conn != null){
	                _flag = true;
	            }	 
	        } finally {
	            try {
	                if (conn != null && !conn.isClosed()) {
	                    conn.close();
	                }
	            } catch (SQLException ex) {
	            	 if (logger.isDebugEnabled()){
	            		 logger.debug(ex.getMessage());
	            	 }
	                ex.printStackTrace();
	                
	            }
	        }
		   
		   return _flag;
	        
	}
	
	
}
