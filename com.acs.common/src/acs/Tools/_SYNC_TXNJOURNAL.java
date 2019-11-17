package acs.Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;

public class _SYNC_TXNJOURNAL {
	public static void main(String[] args) throws Exception {
		
        String _dbURL = "jdbc:sqlserver://192.168.61.97:1433;DatabaseName=NGLA15";
        String _user = "aiden";
        String _pass = "aiden";
        
		SourceDB s_db = new SourceDB();
		// TODO Auto-generated method stub
		if (s_db.test_Connection(s_db.get_dbURL(), s_db.get_user(), s_db.get_pass())){
			System.out.println("Normal Connected");
		} else {
			System.out.println("Fail");
			
		}
		 
		
		 String sql = "SELECT 'CASH' AS TXNATURE, TxnTypeCode, TicketTypeCode, '' as ProdTypeCode ,  SamCAN ,  SamCTC ,  CardCAN ,  CardCTC ,  CONVERT(VARCHAR(8), UploadDateTime, 112),   UploadDateTime,   TxnDateTime,    CONVERT(VARCHAR(6), TxnDateTime, 112) AS period_Analysis_yymm ,   CONVERT(VARCHAR(8), TxnDateTime, 112) AS period_Analysis_yymmDD,   TicketCount as QTY,   TxnAmount,   BalanceBefore ,   BalanceAfter ,   TerminalCode,   PositionCode,   LineCode,   GetOffStationID ,   GetOnStationID ,   EnterDateTime ,   TripNo ,   DriverCode ,   InspectorCode ,   TranRowID ,   'NG' as SUB_SYSTEM   FROM CashTransaction  ";
		 sql = sql + " where UploadDateTime >='2016-01-04' and UploadDateTime < '2016-01-05' ";
			  //sql = sql + " ";
	  // sql = sql + " from TERMINALCATEGORY";

	   
			
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
				
				String _target_sql = "Delete from TBL_TerminalCategory";
				src_db.execute_SQL(_target_sql);
			
				_target_sql = "INSERT INTO TBL_TXNJOURNAL(TXNNature, TxnTypeCode, TicketTypeCode , ProdTypeCode ,  SamCAN ,  SamCTC ,  CardCAN ,  CardCTC , UploadDateReference ,  UploadDateTime, TxnDateTime, Period_Analysis_yymm,  Period_Analysis_dd,ProdQty, TxnAmount , BalanceBefore,  BalanceAfter,  PositionCode, TerminalCode, LineCode, GetOffStationID,  GetOnStationID, EnterDateTime, TripNo, DriverCode, InspectorCode, TranRowID,  SUB_SYSTEM, CREATE_USER, CREATE_IP) values (?, ?, ?, ? ,  ? ,  ?,  ?,  ? , ? ,  ?, ?, ?,  ?,?, ?, ?,  ?,  ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?,  ?, 'SYSTEM', '127.0.0.1')";
					
				System.out.println("row size:" + result.size());
				for (int i =0 ; i< result.size(); i++) {
					
					   String[] _COLUMNS = result.get(i);
					   System.out.println("Columns:" + _COLUMNS.length);

					   src_db.update(_target_sql, _COLUMNS);
					   
				} 
				
				System.out.println("end...");
				// update the sign of the txnjournal
				_target_sql = "UPDATE TBL_TXNJOURNAL AS JRL  INNER JOIN TBL_TXNATURE CTRL ";
				_target_sql = _target_sql + " ON CTRL.TXNTYPECODE = JRL.TXNTYPECODE AND ";
				_target_sql = _target_sql + " CTRL.SUBTYPECODE = JRL.TICKETTYPECODE ";
				_target_sql = _target_sql + "  SET JRL.SIGN = CTRL.SIGN ";
				_target_sql = _target_sql + "   WHERE JRL.SIGN = -2";
				
				src_db.execute_SQL(_target_sql);				
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
