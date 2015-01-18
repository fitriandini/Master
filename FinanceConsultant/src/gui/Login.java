package gui;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import backend.AuthenticateUser;
import backend.UserParam;
import org.eclipse.wb.swt.SWTResourceManager;


public class Login {

	protected Shell shell;
	private Text text_filepath;
	AuthenticateUser authUser = new AuthenticateUser();
	UserParam userData = new UserParam();
	private Text text_userID;
	
	MainMenu menu = new MainMenu();
	static Login windowLogin;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			windowLogin = new Login();
			windowLogin.open();
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
	
	public void close(){
		Display display = Display.getDefault();
		while(!shell.isDisposed()){
			display.dispose();
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Login");
		
		Label lblUserId = new Label(shell, SWT.NONE);
		lblUserId.setBounds(10, 71, 70, 20);
		lblUserId.setText("User ID");
		
		text_userID = new Text(shell, SWT.BORDER);
		text_userID.setBounds(130, 65, 170, 26);
		
		Label lblPrivateKeyPath = new Label(shell, SWT.NONE);
		lblPrivateKeyPath.setBounds(10, 102, 114, 20);
		lblPrivateKeyPath.setText("Key Path");
		
		text_filepath = new Text(shell, SWT.BORDER);
		text_filepath.setBounds(130, 99, 170, 26);
		
		Button btnBrowse = new Button(shell, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int returnVal = jfc.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = jfc.getSelectedFile();
					text_filepath.setText(file.getPath());
				}
			}
		});
		
		btnBrowse.setBounds(316, 97, 90, 30);
		btnBrowse.setText("Browse");
		
		Button btnAuthenticate = new Button(shell, SWT.NONE);
		btnAuthenticate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//authenticate user and safe user data into struct UserData
				userData = authUser.KeyAuthentication(text_filepath.getText());
				if(text_userID.getText().equals(userData.GetUserID())){
					menu.setUserData(userData);
					windowLogin.close();
					menu.open();
				}
				MessageBox msgBox = new MessageBox(shell);
				msgBox.setMessage("Your credential is invalid");
				msgBox.open();

				//System.out.println(userData.GetUserID());
				
			}
		});
		btnAuthenticate.setBounds(152, 148, 114, 30);
		btnAuthenticate.setText("Authenticate");
		
		Label lblLogin = new Label(shell, SWT.NONE);
		lblLogin.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblLogin.setBounds(180, 10, 56, 36);
		lblLogin.setText("Login");
		
	}

}
