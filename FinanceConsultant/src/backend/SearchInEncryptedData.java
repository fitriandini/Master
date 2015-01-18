package backend;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.SecretKey;

import Database.SearchOperation;

public class SearchInEncryptedData {
	SearchOperation SO = new SearchOperation();
	String[] fieldGUI = {"Client Identity", "Client Name", "Transaction", "NONE"};
	
	/** MAPPING THE QUERY **/
	private String FieldMapping(String fieldQuery){
		String field = "";
		
		if(fieldQuery.equals(fieldGUI[0])){
			field = "clientID";
		}else if(fieldQuery.equals(fieldGUI[1])){
			field = "clientName";
		}else if (fieldQuery.equals(fieldGUI[2])){
			field = "financial";
		}else if(fieldQuery.equals(fieldGUI[3])){
			field = "";
		}
		return field;
	}
	
	
	private int ValueMapping(String value, String field){
		MappingFunction MF = new MappingFunction();
		int encValue = 0;
		if(field.equals(fieldGUI[0])){ //clientID
			int id = Integer.parseInt(value.substring(2));
			encValue = MF.MappingClientID(id);
		}else if(field.equals(fieldGUI[1])){
			encValue = MF.MappingClientName(value.charAt(0));
		}else if(field.equals(fieldGUI[2])){
			encValue = MF.MappingFinancialTransaction(Integer.parseInt(value));
		}
		return encValue;
	}
	
	private String GenerateQueryEncryptedResult(String[] fields, String[] mathOp, String[] value, String[] logicOp){
		String sql = "SELECT eTuple, clientID, clientName, consultantID, financial FROM  encrypteddatabase WHERE  ";
		for(int i=0; i<fields.length; i++){
			sql += FieldMapping(fields[i]) + " ";
			sql += mathOp[i] + " ";
			if(fields[i].equals("Transaction")){
				sql += ValueMapping(value[i], fields[i]) + " ";
			}else{
				sql += " '"+ ValueMapping(value[i], fields[i]) + "' ";
			}
			if(i>= logicOp.length || logicOp[i].equals("NONE") ){
				break;
			}else{
				sql += logicOp[i] + " ";
			}
		}
		return sql;
	}
	
	public ResultSet GetTableContent(String[] fields, String[] mathOp, String[] value, String[] logicOp){
		String oriSQL = GenerateQueryEncryptedResult(fields, mathOp, value, logicOp);
		System.out.println(oriSQL);
		ResultSet rs = SO.SearchEncryptedData(oriSQL);
		
		return rs;
	}
	
	//if consultant : get consPrivKey Path and client ID
	//if client: get clientPrivKey Path and consID
	public String[][] GetDecryptedRows(ResultSet rs, String filepath, String pubKeyID, String privilege){
		String[][] res = null;
		AESEncryptionDecryption aed = new AESEncryptionDecryption();
		SecretKey aesKey = null;
		if(privilege.equals("1")){ //consultant
			aesKey = aed.GenerateSecretKeyConsultant(filepath, pubKeyID);
		}else if(privilege.equals("2")){ //client
			aesKey = aed.GenerateSecretKeyClient(filepath, pubKeyID);
		}
		
		
		int rows = 0, cols = 4;
		try {
			if(rs.last()){
				rows = rs.getRow();
				rs.beforeFirst();
			}
			res = new String[rows][];
			int i = 0;
			while(rs.next()){
				byte[] eTupleByte = rs.getBytes("eTuple");
				byte[] dec = aed.AESDecryption(eTupleByte, aesKey);
				String sDec = new String(dec);
				res[i] = sDec.split("-");
				System.out.println(sDec);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	
/*
	
	private String GenerateQueryOriginal(String[] fields, String[] mathOp, String[] value, String[] logicOp){
		String sql = "SELECT eTuple, clientID, clientName, consultantID, financial FROM  encrypteddatabase WHERE  ";
		for(int i=0; i<fields.length; i++){
			sql += FieldMapping(fields[i]) + " ";
			sql += mathOp[i] + " ";
			if(fields[i].equals("Transaction")){
				sql += value[i] + " ";
			}else{
				sql += " '"+ value[i] + "' ";
			}
			if(i>= logicOp.length || logicOp[i].equals("NONE") ){
				break;
			}else{
				sql += logicOp[i] + " ";
			}
		}
		return sql;
	}*/
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
