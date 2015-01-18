package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import Database.InsertTransaction;
import Database.SearchOperation;
import backend.UserParam;
import org.eclipse.wb.swt.SWTResourceManager;

public class InsertData {

	protected Shell shell;
	private Text text_userName;
	private Text text_transaction;
	private Text text_consultantID;
	private Text text_clientID;
	private Button btnSearch;
	
	SearchOperation srcOp = new SearchOperation();
	
	UserParam userData = new UserParam();
	UserParam clientData = new UserParam();
	
	static InsertData windowIns;
	private Label lblInsertData;
	
	public void setUserData(UserParam authUser){
		this.userData = authUser;
	}
	
	public void setClientData(UserParam clientUser){
		this.clientData = clientUser;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			windowIns = new InsertData();
			windowIns.open();
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
		setTextBoxData();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void close(){
		Display display = Display.getDefault();
		while(!shell.isDisposed()){
			display.dispose();
		}
	}
	
	public void setTextBoxData(){
		text_clientID.setText(clientData.GetUserID());
		text_consultantID.setText(clientData.GetConsultantID());
		text_userName.setText(clientData.GetUserName());
		if(userData.GetUserPrivilege().equals("1")){ //if consultant
			SetEditableTextBox(true);
			btnSearch.setVisible(true);
		}else{
			SetEditableTextBox(false);
			btnSearch.setVisible(false);
		}
	}
	
	private void SetEditableTextBox(boolean enable){
		text_clientID.setEnabled(enable);
		text_clientID.setEditable(enable);
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setTouchEnabled(true);
		shell.setModified(true);
		shell.setSize(445, 290);
		shell.setText("Client Insert Data");
		
		Label lblUserId = new Label(shell, SWT.NONE);
		lblUserId.setText("User ID");
		lblUserId.setBounds(39, 53, 88, 20);
		
		text_clientID = new Text(shell, SWT.BORDER);
		text_clientID.setEnabled(true);
		text_clientID.setEditable(true);
		text_clientID.setBounds(142, 50, 188, 26);
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setBounds(39, 82, 70, 20);
		lblName.setText("Name");
		
		text_userName = new Text(shell, SWT.BORDER);
		text_userName.setEnabled(false);
		text_userName.setEditable(false);
		text_userName.setBounds(142, 79, 188, 26);
		
		Label lblTransaction = new Label(shell, SWT.NONE);
		lblTransaction.setBounds(39, 143, 88, 20);
		lblTransaction.setText("Transaction");
		
		text_transaction = new Text(shell, SWT.BORDER);
		text_transaction.setEnabled(true);
		text_transaction.setBounds(142, 137, 188, 26);
		
		Label lblConsultantId = new Label(shell, SWT.NONE);
		lblConsultantId.setText("Consultant ID");
		lblConsultantId.setBounds(39, 111, 88, 20);
		
		text_consultantID = new Text(shell, SWT.BORDER);
		text_consultantID.setEnabled(false);
		text_consultantID.setEditable(false);
		text_consultantID.setBounds(142, 108, 188, 26);
		
		
		Button btnInsertData = new Button(shell, SWT.NONE);
		btnInsertData.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InsertTransaction insTrans = new InsertTransaction();
				//Insert to Original Table
				insTrans.InsertNewTransaction(clientData, Integer.parseInt(text_transaction.getText()));
				//Insert To Encrypted Table
				String privilege = "0", filepath = "";
				if(userData.GetUserPrivilege().isEmpty()){ //client;
					privilege = "2";
					filepath = clientData.GetFilePath();
				}else{ //consultant
					privilege = "1";
					filepath = userData.GetFilePath();
				}
				insTrans.InsertEncryptedTransaction(filepath, clientData, Integer.parseInt(text_transaction.getText()), privilege);
				
				//notification
				MessageBox msgBox = new MessageBox(shell);
				msgBox.setMessage("Data has been successfully inserted");
				msgBox.open();
				msgBox.getParent().close();
			}
		});
		btnInsertData.setBounds(137, 186, 133, 30);
		btnInsertData.setText("Insert Data");
		
		btnSearch = new Button(shell, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clientData = srcOp.GetClientFromID(text_clientID.getText());
				setTextBoxData();
			}
		});
		btnSearch.setBounds(340, 46, 76, 30);
		btnSearch.setText("Search");
		
		lblInsertData = new Label(shell, SWT.NONE);
		lblInsertData.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblInsertData.setBounds(161, 10, 101, 26);
		lblInsertData.setText("Insert Data");

	}
}
