package Database;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

public class DatabaseOperation {
	
	public int GetTotalRows(String tableName){
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.StartConnection();
		ResultSet rs = dbconn.ReadDB(conn, "Select count(*) as totalRows from " +tableName);
		
		int rowNum = 0;
		try {
			rs.first();
			rowNum = rs.getInt("totalRows");
			conn.close();
			//System.out.println(rs.getString("totalRows"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowNum;
	}
	
	public byte[] GetDatabaseDecrypted(String clientID){
		byte[] result = null;
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.StartConnection();
		ResultSet rs = dbconn.ReadDB(conn, "Select eTuple  from encrypteddatabase where clientID = 1");
		try {
			rs.next();
			result = rs.getBytes("eTuple");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public void InsertData(){
		
	}
	
	
}

/* @Fitria 14.01.15 */