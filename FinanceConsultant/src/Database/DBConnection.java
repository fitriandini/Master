package Database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class DBConnection {
	
	public Connection StartConnection(){
		String username = "root";
		String password = "root";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String jdbcConn = "jdbc:mysql://localhost/financial_db";
				          
		try {
			conn = (Connection) DriverManager.getConnection(jdbcConn, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public ResultSet ReadDB(Connection dbConn, String sql){
		ResultSet rs = null;

		// create the java statement
	    Statement st;
		try {
			st = (Statement) dbConn.createStatement();
			// execute the query, and get a java resultset
			rs = (ResultSet) st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public int GetTotalRows(String tableName){
		DBConnection dbconn = new DBConnection();
		Connection conn = StartConnection();
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
	
	
}
