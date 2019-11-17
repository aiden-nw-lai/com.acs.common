package acs.Tools;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class Source_import {
	Connection _connection = null;
	public Source_import(String SubSystem){
		// Connection Manager needed to updated so that will base on the subsystem to retreive the connection
		// SubSystem to connect to the scheduler and 
		// TBL_SYSTEM_DETAIL THE SUBSYSTEM_ID FOUND FROM TBL_SUBSYTEM
		// THE KEY SRC_DB_URL
		// SRC_DB_PASS
		// SRC_DB_TOKEN
		//----
		  ConnectionManager CM = new ConnectionManager();
		  _connection = CM.getConnection(); 
	}
	public void execute_SQL(String p_preparestmt){
		
		   try {
			PreparedStatement preparedStmt = _connection.prepareStatement(p_preparestmt);
			ParameterMetaData paramMetaData = preparedStmt.getParameterMetaData();
			preparedStmt.execute();
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}	 
		
	public void update(String p_preparestmt, String[] _COLUMNS ){
		
		   try {
			PreparedStatement preparedStmt = _connection.prepareStatement(p_preparestmt);
			ParameterMetaData paramMetaData = preparedStmt.getParameterMetaData();
			//System.out.println("meta Data:"+paramMetaData);
			   for (int y =0; y < _COLUMNS.length; y++){
				   //System.out.println(_COLUMNS[y]);
				   //System.out.println(paramMetaData.getParameterTypeName(y));
				   System.out.println(y + ":" + paramMetaData.getParameterClassName(y+1));
				   preparedStmt.setString(y+1,_COLUMNS[y] );
			   }
			   preparedStmt.execute();
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	public void disconnect() throws SQLException{
		if (!(_connection.isClosed() )){
			_connection.close();
		}
		
	}
}
