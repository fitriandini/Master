package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import backend.UserParam;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;


public class MainMenu {

	protected Shell shell;
	
	Button btnRegisterNewClient;
	UserParam userData = new UserParam();
	RegisterGUI regGUI = new RegisterGUI();
	InsertData insGUI = new InsertData();
	SearchData srcGUI = new SearchData();
	Display display;
	
	
	static MainMenu windowMainMenu;
	
	public void setUserData(UserParam authUser){
		this.userData = authUser;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			windowMainMenu = new MainMenu();
			windowMainMenu.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		SetMenuDisabled();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void close(){
		//display = Display.getDefault();
		while(!shell.isDisposed()){
			display.dispose();
		}
	}
	
	public void SetMenuDisabled(){
		//if client instead of consultant
		if(userData.GetUserPrivilege().equals("2")){
			btnRegisterNewClient.setEnabled(false);
			btnRegisterNewClient.setGrayed(true);
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 310);
		shell.setText("Financial Consultant");
		
		Label lblChooseMenuThat = new Label(shell, SWT.NONE);
		lblChooseMenuThat.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblChooseMenuThat.setBounds(76, 36, 278, 20);
		lblChooseMenuThat.setText("Choose menu that you want to do");
		
		btnRegisterNewClient = new Button(shell, SWT.RADIO);
		btnRegisterNewClient.setBounds(147, 75, 165, 20);
		btnRegisterNewClient.setText("Register New Client");
		
		Button btnInsertData = new Button(shell, SWT.RADIO);
		btnInsertData.setBounds(147, 101, 111, 20);
		btnInsertData.setText("Insert Data");
		
		Button btnSearchData = new Button(shell, SWT.RADIO);
		btnSearchData.setBounds(147, 127, 111, 20);
		btnSearchData.setText("Search Data");
		
		Button btnChoose = new Button(shell, SWT.NONE);
		btnChoose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnRegisterNewClient.getSelection()){
					regGUI.setUserData(userData);
					regGUI.open();
				}else if(btnInsertData.getSelection()){
					//consultant
					if(userData.GetUserPrivilege().equals("1")){
						insGUI.setUserData(userData);
						insGUI.open();
					}else if(userData.GetUserPrivilege().equals("2")){ //if client
						//insGUI.setUserData(userData);
						insGUI.setClientData(userData);
						insGUI.open();
					}
				}else if(btnSearchData.getSelection()){
					srcGUI.setUserData(userData);
					//windowMainMenu.close();
					srcGUI.open(userData.GetUserPrivilege());
				}
			}
		});
		btnChoose.setBounds(168, 175, 90, 30);
		btnChoose.setText("Choose");
		
		

	}
}
