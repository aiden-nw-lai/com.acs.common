package acs.Tools;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import com.jcraft.jsch.Logger;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.security.*;

import acs.Tools.Util1;

public class Testing {
	
	  final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Testing.class);
	public static void main(String[] args) {
		Security.setProperty("crypto.policy", "unlimited");
	  System.out.println("xxx");
	  logger.info("xxxx");
	
	//  System.out.println(acs.Tools.Util1.decrypted_string("ACSKey1234#", "jtCZFbzg0f84hnEE6CJlEg=="));
	  System.out.println(acs.Tools.Util1.decrypted_string("ArNYS82r0Nup61nB3viluj6iiujpxmbQ", "ACSKey1234#"));
	//  System.out.println(Util1.decrypted_string("ACSKey1234#", "jtCZFbzg0f84hnEE6CJlEg=="));
	}
		
	public static void mainx(String[] args) throws Exception {
		
        String _dbURL = "jdbc:mysql://192.168.62.168:3306;DatabaseName=queryview";
        String _user = "root";
        String _pass = "roota";
        
		SourceDB s_db = new SourceDB();
		// TODO Auto-generated method stub
		if (s_db.test_Connection(_dbURL, _user, _pass)){
			System.out.println("Normal Connected");
		} else {
			System.out.println("Fail");
			
		}
		 
		
	   String sql = "SELECT 'CASH' AS TXNATURE, TxnTypeCode, TicketTypeCode, '' as ProdTypeCode ,  SamCAN ,  SamCTC ,  CardCAN ,  CardCTC ,  CONVERT(VARCHAR(8), UploadDateTime, 112),   UploadDateTime,   TxnDateTime,    CONVERT(VARCHAR(6), TxnDateTime, 112) AS period_Analysis_yymm ,   CONVERT(VARCHAR(8), TxnDateTime, 112) AS period_Analysis_yymmDD,   TicketCount as QTY,   TxnAmount,   BalanceBefore ,   BalanceAfter ,   TerminalCode,   PositionCode,   LineCode,   GetOffStationID ,   GetOnStationID ,   EnterDateTime ,   TripNo ,   DriverCode ,   InspectorCode ,   TranRowID ,   'NG' as SUB_SYSTEM   FROM CashTransaction  where UploadDateTime >='2016-01-02' and UploadDateTime < '2016-01-04' ";
			
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
				
				
				String _target_sql = "INSERT INTO TBL_TXNJOURNAL(TXNNature, TxnTypeCode, TicketTypeCode , ProdTypeCode ,  SamCAN ,  SamCTC ,  CardCAN ,  CardCTC , UploadDateReference ,  UploadDateTime, TxnDateTime, Period_Analysis_yymm,  Period_Analysis_dd,ProdQty, TxnAmount , BalanceBefore,  BalanceAfter,  PositionCode, TerminalCode, LineCode, GetOffStationID,  GetOnStationID, EnterDateTime, TripNo, DriverCode, InspectorCode, TranRowID,  SUB_SYSTEM, CREATE_USER, CREATE_IP) values (?, ?, ?, ? ,  ? ,  ?,  ?,  ? , ? ,  ?, ?, ?,  ?,?, ?, ?,  ?,  ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?,  ?, 'SYSTEM', '127.0.0.1')";
				Source_import src_db = new Source_import("NG");
						
				System.out.println("row size:" + result.size());
				for (int i =0 ; i< result.size(); i++) {
					
					   String[] _COLUMNS = result.get(i);
					   System.out.println("Columns:" + _COLUMNS.length);

					   src_db.update(_target_sql, _COLUMNS);
					   
				} 
				System.out.println("end...");
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
