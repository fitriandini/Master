package backend;

public class MappingFunction {
	
	public int MappingClientID(int clientID){
		int interval = 10;
		int mapClientID = 0;
		if(clientID % interval == 0){
			mapClientID = (clientID/interval);
		}else{
			mapClientID = (clientID/interval)+1;
		}
		return mapClientID;
	}
	
	public int MappingClientName(char firstNameLetterLowercase){
		int interval = 6;
		int baseLetter = ((int) firstNameLetterLowercase - (int) 'a')+1;
		int mapclientName = 0;
		if(baseLetter % interval == 0){
			mapclientName = (baseLetter /interval)+10-1; 
		}else{
			mapclientName = (baseLetter /interval)+10;
		}
		return mapclientName;
	}
	
	public int MappingConsultantID(int consultantID){
		int interval = 10;
		int mapConsultantID = 0;	
		if(consultantID % interval == 0){
			mapConsultantID = (consultantID/interval)+10;
		}else{
			mapConsultantID = (consultantID/interval)+10+1;
		}
		return mapConsultantID;
	}
	
	public int MappingFinancialTransaction(int value){
		int interval = 100;
		int mapFinance = 0;
		if(value % interval == 0){
			mapFinance = (value/interval)-1;
		}else{
			mapFinance = (value/interval);
		}
		return mapFinance;
	}
	
	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		MappingFunction mf = new MappingFunction();
		
		String name = "gbcd", id = "000009";
		int finance = 90;
		
		int mID = mf.MappingClientID(Integer.parseInt(id));
		System.out.println(mID);
		mID = mf.MappingConsultantID(Integer.parseInt(id));
		System.out.println(mID);
		int fin = mf.MappingFinancialTransaction(finance);
		System.out.println(fin);
		int mname = mf.MappingClientName(name.toLowerCase().charAt(0));
		System.out.println(mname);
	}*/

}
