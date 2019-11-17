package acs.Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;

public class _master_Sync1 {
	public static void main(String[] args) throws Exception {
		
        String _dbURL = "jdbc:sqlserver://192.168.61.240:1433;DatabaseName=NGLA15";
        String _user = "aiden";
        String _pass = "aiden";
        
		SourceDB s_db = new SourceDB();
		// TODO Auto-generated method stub
		if (s_db.test_Connection(_dbURL, _user, _pass)){
			//System.out.println("Normal Connected");
		} else {
			//System.out.println("Fail");
			
		}
		 
		
	   String sql = "select TerminalCategoryCode, TerminalCategoryDesc ";
	   sql = sql + " from TerminalCategory";
	   

	   
			
//-------
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection c = null;
			try {
				c =  s_db.getConnection(_dbURL, _user, _pass);
				ps = c.prepareStatement(sql);
				rs = ps.executeQuery();
				//List list = new ArrayList();
				ArrayList <String[]> result = new ArrayList<String[]>();
				
				 ResultSetMetaData rsmd = rs.getMetaData();
				 int columnCount = rsmd.getColumnCount();
				int row_count = 0;
				while (rs.next()) {
					row_count++;
					String[] row = new String[columnCount];
					for (int i=0; i <columnCount ; i++)
				    {
				       row[i] = rs.getString(i + 1);
				     //  System.out.println(rsmd.getColumnLabel(i+1));
				       int type = rsmd.getColumnType(i+1);
			            if (type == Types.VARCHAR || type == Types.CHAR) {
			               // System.out.println("String");
			            } else if (type == Types.DATE ){
			            	//System.out.print(rs.getDate(i+1));
			            } else if (type == Types.TIMESTAMP){
			            //	System.out.print(rs.getTimestamp(i+1));			            	
			            } else {
			               // System.out.print(rs.getLong(i+1));
			            }
				     //  System.out.println(row[i]);
				    }
					//System.out.println(row_count);
				    result.add(row);
				}
				// Update back into target Database
				Source_import src_db = new Source_import("NG");
				
				String _target_sql = "Delete from TBL_MERCHANT";
				src_db.execute_SQL(_target_sql);
				
				_target_sql = "INSERT INTO TBL_TerminalCategory(CODE, Description, Address, ContactPerson , Phone ,  Email ,  Mobile ,  CityCode ,  ApplicationCode , CreateBy ,  CreateDate, UpdateBy, UpdateDate,  AcquirerCode,MasterMerchantCode, UsedFor, AccessRightsCode, UPLOADBY, UPLOAD_DT) values (?,?, ?, ?, ?,  ?,  ?,  ?,  ?, ?,  ?, ?, ?,  ?,?, ?,?, 'SYSEM', NOW())";
						
				
				for (int i =0 ; i< result.size(); i++) {
					
					   String[] _COLUMNS = result.get(i);
				//	   System.out.println("Columns:" + _COLUMNS.length);

					   src_db.update(_target_sql, _COLUMNS);
					   
				} 
				
				//System.out.println("end...");
				// validate the number of record being insert same as the number of record had been inserted
				src_db.disconnect();
			} catch (Exception e) {
				
					throw e;
				
			}finally{
				if (rs != null) {
					
						rs.close();
					
				}
				if (ps != null) {
					ps.close();
				}
				if (c != null) {
					c.close();
				}
			}
			
     
        
        
	}
}
