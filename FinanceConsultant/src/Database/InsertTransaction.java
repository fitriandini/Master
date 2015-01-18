package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.crypto.SecretKey;

import backend.AESEncryptionDecryption;
import backend.MappingFunction;
import backend.UserParam;

import com.mysql.jdbc.Connection;

public class InsertTransaction {
	
	DBConnection con = new DBConnection();
	
	public String InsertNewTransaction(UserParam userData, int transaction){
		Connection conn = con.StartConnection();
		String sql = "INSERT INTO financial_database (clientID, clientName, consultantID, transaction) VALUES(?, ?, ?, ?) ";
		PreparedStatement prepStmt;
		try {
			prepStmt = conn.prepareStatement(sql);
			prepStmt.setString(1, userData.GetUserID());
			prepStmt.setString(2, userData.GetUserName());
			prepStmt.setString(3, userData.GetConsultantID());
			prepStmt.setInt(4, transaction);
			prepStmt.execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userData.GetUserID();
	}
	
	
	
	public String InsertEncryptedTransaction(String filepath, UserParam insertedData, int transaction, String userPrivilege){
		AESEncryptionDecryption aed = new AESEncryptionDecryption();
		MappingFunction MF = new MappingFunction();
		
		Connection conn = con.StartConnection();
		//To Do eTuple
		SecretKey aesKey = null;
		System.out.println(userPrivilege);
		if(userPrivilege.equals("1")){//if Consultant
			aesKey = aed.GenerateSecretKeyConsultant(filepath, insertedData.GetUserID());
		}else if(userPrivilege.equals("2")){ //Client
			aesKey = aed.GenerateSecretKeyClient(filepath, insertedData.GetConsultantID());
		}
		byte[] eTuple = aed.AESEncryption(aesKey, insertedData.GetCombinedData() + transaction);
		
		String sql = "INSERT INTO encrypteddatabase (eTuple, clientID, clientName, consultantID, financial) VALUES(?, ?, ?, ?, ?) ";
		PreparedStatement prepStmt;
		try {
			prepStmt = conn.prepareStatement(sql);
			prepStmt.setBytes(1, eTuple);
			int cliID = Integer.parseInt(insertedData.GetUserID().substring(2, insertedData.GetUserID().length()));
			prepStmt.setInt(2, MF.MappingClientID(cliID));
			prepStmt.setInt(3, MF.MappingClientName(insertedData.GetUserName().charAt(0)));
			int consID = Integer.parseInt(insertedData.GetConsultantID().substring(2, insertedData.GetConsultantID().length()));
			prepStmt.setInt(4, MF.MappingConsultantID(consID));
			prepStmt.setInt(5, MF.MappingFinancialTransaction(transaction));
			prepStmt.execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return insertedData.GetUserID();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String cliID = "CF000002", cliName = "ik", consID = "CF000001";
		int financial = 120;
		UserParam userData = new UserParam();
		userData.SetUserData(cliID, cliName, "2", consID);
		
		/*SecretKey aesKey = new SecretKeySpec(new byte[16], "AES");
		//userData.SetSecretKey(aesKey);
		InsertTransaction IT = new InsertTransaction();
		IT.InsertNewTransaction(userData, financial);
		IT.InsertEncryptedTransaction(userData, financial);
		
		DatabaseOperation DO = new DatabaseOperation();
		byte[] res = DO.GetDatabaseDecrypted("1");
		
		AESEncryptionDecryption aed = new AESEncryptionDecryption();
		byte[] dec = aed.AESDecryption(res, aesKey);
		System.out.println(new String(dec));
		
		*/
		
	}

}
