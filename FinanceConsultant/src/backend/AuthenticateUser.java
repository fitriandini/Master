package backend;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.crypto.SecretKey;

import com.mysql.jdbc.Connection;

import Database.DBConnection;


//reference: http://docstore.mik.ua/orelly/java-ent/security/ch13_07.htm
public class AuthenticateUser {
	GenerateKey genKey = new GenerateKey();
	final int IDlength = 8;
	
	public UserParam KeyAuthentication(String filepath){
		UserParam userData = new UserParam();		
		String filename = filepath.substring(filepath.length()-(IDlength+genKey.privKeyName.length()), filepath.length());
		System.out.println(filename);
		String userID = filename.substring(0, IDlength);
		userData = GetUserDatabase(userID);
		userData.SetFilePath(filepath);
		
		//read Server key 
		PublicKey serverPubKey = genKey.ByteToPublicKey(genKey.readKeyFile(genKey.serverPath+genKey.serverpubKeyName));
		PrivateKey serverPrivateKey = genKey.ByteToPrivate(genKey.readKeyFile(genKey.serverPath+genKey.serverprivKeyName));
		System.out.println("Get Server Key");
		
		//read user public key from server's
		PublicKey clientPubKey = null;
		if(userData.GetUserPrivilege().equals("1")){ //consultant
			clientPubKey = genKey.ByteToPublicKey(genKey.readKeyFile(genKey.consOnServerPath+userID+genKey.pubKeyName));
		}else if(userData.GetUserPrivilege().equals("2")){
			clientPubKey = genKey.ByteToPublicKey(genKey.readKeyFile(genKey.clientOnServerPath+userID+genKey.pubKeyName));
		}
		
		//read user private key
		PrivateKey clientPrivKey = genKey.ByteToPrivate(genKey.readKeyFile(filepath));	
		System.out.println("Get User Key");
		
		//generate secret key
		SecretKey serverSecret = genKey.DHSecretKeyAgreeement(clientPubKey, serverPrivateKey);
		System.out.println("Key Agreement User Public Key - Server Private Key");
		
		SecretKey clientSecret = genKey.DHSecretKeyAgreeement(serverPubKey, clientPrivKey);
		System.out.println("Key Agreement Client Public Key - User Private Key");
		
		if(!serverSecret.equals(clientSecret)){
			userData = null;
		}		
		return userData;
	}
		
		
	private UserParam GetUserDatabase(String userID){
		UserParam userData = new UserParam();
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.StartConnection();
		
		String sql = "SELECT user_ID, name, access_ID, ConsultantID from user_database where user_ID = ?";
		PreparedStatement prepstmt;
		try {
			prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, userID);
			ResultSet rs = prepstmt.executeQuery();
			rs.next();			
			userData.SetUserData(rs.getString("user_ID"), rs.getString("name"), rs.getString("access_ID"), rs.getString("ConsultantID"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userData;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
