package acs.Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;

public class Testing2 {
	public static void main(String[] args) throws Exception {
		
       ConnectionManager CM = new ConnectionManager();
       String sql = "SELECT * FROM TBL_TXNJOURNAL ";
			
//-------
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection c = null;
			try {
				c = CM.getConnection();
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
					System.out.println(row_count);
				    result.add(row);
				}
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
