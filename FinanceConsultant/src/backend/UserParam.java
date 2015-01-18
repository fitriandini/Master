package backend;


public class UserParam {
	String userID = "";
	String userName = "";
	String userPrivilege = "";
	String consultantID = "";
	String filepath = "";
	
	public UserParam(){
		
	}
	
	public void SetUserData(String ID, String name, String privilege, String consultantID ){
		this.userID = ID;
		this.userName = name;
		this.consultantID = consultantID;
		this.userPrivilege = privilege;
	}
	
	public String GetCombinedData(){
		String combi = this.userID + "-" + this.userName + "-" +  this.consultantID + "-" ;
		return combi;
	}
	
	public void SetFilePath(String filepath){
		this.filepath = filepath;
	}
	
	public String GetFilePath(){
		return this.filepath;
	} 
	
	public String GetUserID(){
		return this.userID;
	}
	
	public String GetUserName(){
		return this.userName;
	}
	
	public String GetUserPrivilege(){
		return this.userPrivilege;
	}
	
	public String GetConsultantID(){
		return this.consultantID;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
