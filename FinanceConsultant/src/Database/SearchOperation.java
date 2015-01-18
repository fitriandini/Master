package Database;

import java.sql.SQLException;

import backend.UserParam;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

public class SearchOperation {
	
	public UserParam GetClientFromID(String clientID){
		DBConnection con = new DBConnection();
		UserParam clientData = new UserParam();
		Connection conn = con.StartConnection();
		String sql = "SELECT user_ID, name, access_ID, consultantID from user_database WHERE user_ID= '"+clientID +"'";
		System.out.println(sql);
		ResultSet rs = con.ReadDB(conn, sql);

		try {
			rs.next();
			clientData.SetUserData(rs.getString("user_ID"), rs.getString("name"), rs.getString("access_ID"), rs.getString("consultantID"));
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clientData;
	}
	
	
	
	
	public ResultSet SearchEncryptedData(String sql){
		DBConnection con = new DBConnection();
		Connection conn = con.StartConnection();
		ResultSet rs = null;
		
		rs = con.ReadDB(conn, sql);
		
		return rs;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
