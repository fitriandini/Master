package Database;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import backend.GenerateKey;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

public class RegisterUser {
	String ServerPubKeyPath = "./Server/ServerPUBKEY";
	String ServerPrivKeyPath = "./Server/ServerPRIVKEY";
	
	DBConnection con = new DBConnection();
	
	private String GetUserID(int access_id){
		String userID = "";
		Connection conn = con.StartConnection();
		ResultSet rs = con.ReadDB(conn, "SELECT MAX(user_ID) as user_ID from user_database");
		int rows = GetTotalRows();
		try {
			if(rows > 0 && rs.next()){	
				String temp = rs.getString("user_ID");
				temp = temp.substring(2);
				//increment ID +1
				int id = Integer.parseInt(temp) + 1;
				temp = String.format("%06d", id);
				if(access_id == 1) //consultant
					userID = "CN" + temp;
				else if(access_id == 2) //client
					userID = "CF" + temp;
			}else {
				if(access_id == 1) //consultant
					userID = "CN000001";
				else if(access_id == 2) //client
					userID = "CF000001";
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userID;
	}
	
	private int GetTotalRows(){
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.StartConnection();
		ResultSet rs = dbconn.ReadDB(conn, "Select count(*) as totalRows from user_database ");
		
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
	
	public String InsertNewUser(String name, int accessID, String consultantID){
		String userID = GetUserID(accessID);
		Connection conn = con.StartConnection();
		String sql = "INSERT INTO user_database (user_ID, name, access_ID, consultantID) VALUES(?, ?, ?, ?) ";
		PreparedStatement prepStmt;
		try {
			prepStmt = conn.prepareStatement(sql);
			prepStmt.setString(1, userID);
			prepStmt.setString(2, name);
			prepStmt.setInt(3, accessID);
			prepStmt.setString(4, consultantID);
			prepStmt.execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Generate Key
		GenerateKey genKey = new GenerateKey();
		//read the server's public key
		PublicKey serverPubKey = genKey.ByteToPublicKey(genKey.readKeyFile(ServerPubKeyPath));		
		//create pubkey and privkey for user
		genKey.createKeyUser(serverPubKey, userID, accessID);
		
		return userID;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegisterUser ru = new RegisterUser();
		String userID = ru.GetUserID(1);
		System.out.println(userID);
	}

}
