package gui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RegistrationDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_userID;
	private Text text_Name;
	private Text text_Privilege;
	
	private String userID = "";
	private String name = "";
	private String privilege = "";

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public RegistrationDialog(Shell parent, int style) {
		super(parent, style);
		setText("Registration");
	}
	
	public RegistrationDialog(Shell parent, int style, String id, String username, String privilege) {
		super(parent, style);
		setText("Registration");
		this.userID = id;
		this.name = username;
		this.privilege = privilege;
	}
	
	
	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(284, 274);
		shell.setText(getText());
		
		Label lblYouHaveBeen = new Label(shell, SWT.NONE);
		lblYouHaveBeen.setBounds(40, 26, 207, 25);
		lblYouHaveBeen.setText("You have been registered");
		
		Label lblUserId = new Label(shell, SWT.NONE);
		lblUserId.setBounds(21, 75, 70, 20);
		lblUserId.setText("User ID");
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Name");
		lblName.setBounds(21, 114, 70, 20);
		
		Label lblPrivilege = new Label(shell, SWT.NONE);
		lblPrivilege.setText("Privilege");
		lblPrivilege.setBounds(21, 155, 70, 20);
		
		text_userID = new Text(shell, SWT.BORDER);
		text_userID.setEditable(false);
		text_userID.setBounds(97, 75, 145, 26);
		text_userID.setText(userID);
		
		text_Name = new Text(shell, SWT.BORDER);
		text_Name.setEditable(false);
		text_Name.setBounds(97, 114, 145, 26);
		text_Name.setText(name);
		
		text_Privilege = new Text(shell, SWT.BORDER);
		text_Privilege.setEditable(false);
		text_Privilege.setBounds(97, 149, 145, 26);
		text_Privilege.setText(privilege);
		
		Button btnOK = new Button(shell, SWT.NONE);
		btnOK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
				getParent().close();
			}
		});
		btnOK.setBounds(107, 181, 90, 30);
		btnOK.setText("OK");

	}

}
