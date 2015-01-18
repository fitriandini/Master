package gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import backend.SearchInEncryptedData;
import backend.UserParam;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

//http://docstore.mik.ua/orelly/java-ent/security/ch13_07.htm
public class SearchData {

	protected Shell shell;
	
	UserParam userData = new UserParam();
	SearchInEncryptedData SE = new SearchInEncryptedData();
	private Text text_searchKey1;
	private Text text_searchKey2;
	private Text text_searchKey3;
	private Table table_Encrypted;
	private Table table_Decrypted;
	private TableColumn tblclmnClientId;
	
	public void setUserData(UserParam authUser){
		this.userData = authUser;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SearchData window = new SearchData();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void open(String privilege) {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		if(privilege.equals("2"))
			setTextClientID();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private void setTextClientID(){
		text_searchKey1.setText(userData.GetUserID());
		text_searchKey1.setEnabled(false);
		
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(680, 664);
		shell.setText("Search");
		
		Label lblSearch = new Label(shell, SWT.NONE);
		lblSearch.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblSearch.setBounds(194, 29, 226, 28);
		lblSearch.setText("Search In Encrypted Data");
		
		Combo combo_searchField1 = new Combo(shell, SWT.NONE);
		combo_searchField1.setEnabled(false);
		combo_searchField1.setItems(new String[] {"Client Identity", "Client Name", "Transaction"});
		combo_searchField1.setBounds(70, 83, 133, 28);
		combo_searchField1.setText("Client Identity");
		
		text_searchKey1 = new Text(shell, SWT.BORDER);
		text_searchKey1.setBounds(275, 85, 195, 26);
		
		Combo combo_LogicalOp1 = new Combo(shell, SWT.NONE);
		combo_LogicalOp1.setItems(new String[] {"NONE", "AND", "OR"});
		combo_LogicalOp1.setBounds(476, 85, 97, 28);
		combo_LogicalOp1.setText("NONE");
		
		Combo combo_searchField2 = new Combo(shell, SWT.NONE);
		combo_searchField2.setItems(new String[] {"NONE", "Client Name", "Transaction"});
		combo_searchField2.setBounds(70, 117, 133, 28);
		combo_searchField2.setText("NONE");
		
		text_searchKey2 = new Text(shell, SWT.BORDER);
		text_searchKey2.setBounds(275, 119, 195, 26);
		
		Combo combo_searchField3 = new Combo(shell, SWT.NONE);
		combo_searchField3.setItems(new String[] {"NONE", "Client Name", "Transaction"});
		combo_searchField3.setBounds(70, 151, 133, 28);
		combo_searchField3.setText("NONE");
		
		text_searchKey3 = new Text(shell, SWT.BORDER);
		text_searchKey3.setBounds(275, 153, 195, 26);
		
		Combo combo_LogicalOp2 = new Combo(shell, SWT.NONE);
		combo_LogicalOp2.setItems(new String[] {"NONE", "AND", "OR"});
		combo_LogicalOp2.setBounds(476, 119, 97, 28);
		combo_LogicalOp2.setText("NONE");
		
		Combo combo_MathOp1 = new Combo(shell, SWT.NONE);
		combo_MathOp1.setEnabled(false);
		combo_MathOp1.setItems(new String[] {">", ">=", "=", "<", "<="});
		combo_MathOp1.setBounds(209, 83, 60, 28);
		combo_MathOp1.setText("=");
		
		Combo combo_MathOp2 = new Combo(shell, SWT.NONE);
		combo_MathOp2.setItems(new String[] {">", ">=", "=", "<", "<="});
		combo_MathOp2.setBounds(209, 117, 60, 28);
		combo_MathOp2.setText("=");
		
		Combo combo_MathOp3 = new Combo(shell, SWT.NONE);
		combo_MathOp3.setItems(new String[] {">", ">=", "=", "<", "<="});
		combo_MathOp3.setBounds(209, 151, 60, 28);
		combo_MathOp3.setText("=");
				
		Label lblEncrypted = new Label(shell, SWT.NONE);
		lblEncrypted.setBounds(29, 220, 70, 20);
		lblEncrypted.setText("Encrypted");
		
		Label lblDecrypted = new Label(shell, SWT.NONE);
		lblDecrypted.setText("Decrypted");
		lblDecrypted.setBounds(29, 417, 70, 20);
		
		table_Encrypted = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table_Encrypted.setBounds(10, 249, 619, 154);
		table_Encrypted.setHeaderVisible(true);
		table_Encrypted.setLinesVisible(true);
		
		TableColumn tblclmnEtuple = new TableColumn(table_Encrypted, SWT.NONE);
		tblclmnEtuple.setWidth(101);
		tblclmnEtuple.setText("eTuple");
		
		tblclmnClientId = new TableColumn(table_Encrypted, SWT.NONE);
		tblclmnClientId.setWidth(119);
		tblclmnClientId.setText("Client ID");
		
		TableColumn tblclmnClientname = new TableColumn(table_Encrypted, SWT.NONE);
		tblclmnClientname.setWidth(119);
		tblclmnClientname.setText("Client Name");
		
		TableColumn tblclmnConsultantId = new TableColumn(table_Encrypted, SWT.NONE);
		tblclmnConsultantId.setWidth(125);
		tblclmnConsultantId.setText("Consultant ID");
		
		TableColumn tblclmnTransaction = new TableColumn(table_Encrypted, SWT.NONE);
		tblclmnTransaction.setWidth(145);
		tblclmnTransaction.setText("Transaction");
		
		table_Decrypted = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table_Decrypted.setLinesVisible(true);
		table_Decrypted.setHeaderVisible(true);
		table_Decrypted.setBounds(10, 443, 629, 165);
		
		TableColumn tblclmnClientIdDec = new TableColumn(table_Decrypted, SWT.NONE);
		tblclmnClientIdDec.setWidth(149);
		tblclmnClientIdDec.setText("Client ID");
		
		TableColumn tblclmnClientnameDec = new TableColumn(table_Decrypted, SWT.NONE);
		tblclmnClientnameDec.setWidth(159);
		tblclmnClientnameDec.setText("Client Name");
		
		TableColumn tblclmnConsultantIdDec = new TableColumn(table_Decrypted, SWT.NONE);
		tblclmnConsultantIdDec.setWidth(137);
		tblclmnConsultantIdDec.setText("Consultant ID");
		
		TableColumn tblclmnTransactionDec = new TableColumn(table_Decrypted, SWT.NONE);
		tblclmnTransactionDec.setWidth(167);
		tblclmnTransactionDec.setText("Transaction");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] fields = {combo_searchField1.getText(), combo_searchField2.getText(), combo_searchField3.getText()};
				String[] mathOp = {combo_MathOp1.getText(), combo_MathOp2.getText(), combo_MathOp3.getText()};
				String[] value = {text_searchKey1.getText(), text_searchKey2.getText(), text_searchKey3.getText()};
				String[] logicOp = {combo_LogicalOp1.getText(), combo_LogicalOp2.getText()};
				
				//get encrypted data
				ResultSet rs = SE.GetTableContent(fields, mathOp, value, logicOp);		
				try {
					//eTuple, clientID, clientName, consultantID, financial
					while(rs.next()){
						TableItem tblItemEnc = new TableItem(table_Encrypted, SWT.NONE);
						tblItemEnc.setText(new String[]{new String(rs.getBytes("eTuple")),  
								rs.getString("clientID"), rs.getString("clientName"), 
								rs.getString("consultantID"), rs.getString("financial")});
						System.out.println(rs.getString("clientID"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//get Decrypted Data
				String[][] res = null;
				if(userData.GetUserPrivilege().equals("1")){ //consultant
					System.out.println(userData.GetFilePath() + "  " + userData.GetConsultantID());
					res = SE.GetDecryptedRows(rs, userData.GetFilePath(), text_searchKey1.getText(), userData.GetUserPrivilege());
				}else if(userData.GetUserPrivilege().equals("2")){ //client
					System.out.println(userData.GetFilePath() + "  " + userData.GetConsultantID());
					res = SE.GetDecryptedRows(rs, userData.GetFilePath(), userData.GetConsultantID(), userData.GetUserPrivilege());
				}
				
				for(int i=0; i<res.length; i++){
					TableItem tblItemDec = new TableItem(table_Decrypted, SWT.NONE);
					for(int j=0; j<res[i].length; j++)
						System.out.println(res[i][j]);
					tblItemDec.setText(res[i]);
				}
			}
		});
		btnNewButton.setBounds(263, 195, 90, 30);
		btnNewButton.setText("Search");
		
		
		
		

	}
}
