package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import backend.UserParam;
import Database.RegisterUser;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * 
 * @author fitria
 * register gui only available when the logged in user is consultant
 */

public class RegisterGUI {

	protected Shell shell;
	private Text text_name;
	private Text text_consultantID;
	UserParam userData = new UserParam();
	static RegisterGUI windowReg;
	
	public void setUserData(UserParam authUser){
		this.userData = authUser;
	}


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			windowReg = new RegisterGUI();
			windowReg.open();
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
		setRegisterData();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private void setRegisterData(){
		text_consultantID.setText(userData.GetUserID());
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(336, 290);
		shell.setText("Financial Consultant");
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setBounds(25, 70, 70, 20);
		lblName.setText("Name");
		
		text_name = new Text(shell, SWT.BORDER);
		text_name.setToolTipText("username maximum 6 character");
		text_name.setBounds(122, 67, 165, 26);
		
		Combo combo_privilege = new Combo(shell, SWT.NONE);
		combo_privilege.setItems(new String[] {"Consultant", "Client"});
		combo_privilege.setBounds(122, 128, 165, 28);
		combo_privilege.setText("Consultant");
		
		Label lblPrivilege = new Label(shell, SWT.NONE);
		lblPrivilege.setBounds(27, 131, 70, 20);
		lblPrivilege.setText("Privilege");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RegisterUser regUser = new RegisterUser();
				int accessID = 0;
				if(combo_privilege.getText().equals("Consultant")){
					accessID = 1;
				}else if(combo_privilege.getText().equals("Client")){
					accessID = 2;
				}
				String userID = regUser.InsertNewUser(text_name.getText(), accessID, text_consultantID.getText());
				RegistrationDialog regDialog = new RegistrationDialog(shell, SWT.OK, userID, text_name.getText(), combo_privilege.getText());
				regDialog.open();
			}
		});
		btnNewButton.setBounds(122, 189, 90, 30);
		btnNewButton.setText("Register");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabel.setBounds(84, 23, 155, 20);
		lblNewLabel.setText("Register New Client");
		
		text_consultantID = new Text(shell, SWT.BORDER);
		text_consultantID.setEditable(false);
		text_consultantID.setEnabled(false);
		text_consultantID.setToolTipText("username maximum 6 character");
		text_consultantID.setBounds(122, 96, 165, 26);
		
		Label lblConsultantId = new Label(shell, SWT.NONE);
		lblConsultantId.setText("Consultant ID");
		lblConsultantId.setBounds(25, 99, 91, 20);

	}
}
