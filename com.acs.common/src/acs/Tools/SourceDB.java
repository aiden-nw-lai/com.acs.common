package acs.Tools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;



public class SourceDB extends BaseDB{
	
	 private String _dbURL = "jdbc:sqlserver://192.168.61.97:1433;DatabaseName=NGLA15";
     private String _user = "aiden";
     private String _pass = "aiden";
     
	public String get_dbURL() {
		return _dbURL;
	}
	public void set_dbURL(String _dbURL) {
		this._dbURL = _dbURL;
	}
	public String get_user() {
		return _user;
	}
	public void set_user(String _user) {
		this._user = _user;
	}
	public String get_pass() {
		return _pass;
	}
	public void set_pass(String _pass) {
		this._pass = _pass;
	}

	
}
