package bankAccount;

import java.util.ArrayList;
import javax.swing.border.TitledBorder;



import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class BankAccountGUI extends JFrame{
	
	// Menu structure
	private JMenuBar myBar;
	private JMenu fileMenu, recordMenu;
	private JMenuItem fileLoad, fileSaveAs, removeAccount;
	
	
	//Arraylist of account objects
	private ArrayList <Account> arrData;

	//Table components
	private JTable myTable;
	private MyTableModel tm;
	private JScrollPane myPane;
	
		
	//Form panel components
	private JLabel nameLabel = new JLabel("Name");
    private JTextField nameField = new JTextField(10);
	private JLabel accountNoLabel = new JLabel("Account Number");
	private JTextField accountNoField= new JTextField(10);
	private JLabel balanceLabel = new JLabel("Balance");
	private JTextField balanceField= new JTextField(10);
	private JButton addButton = new JButton("Add Account");
	private JPanel formPnl = new JPanel(); 
	private JPanel tblPnl = new JPanel(); 
	
	//Thread panel
	private JPanel threadPnl = new JPanel();
	private JTextArea threadOutput = new JTextArea();
	private JButton threadButton = new JButton("Thread simulation");
	
	//File variables
	private File file = new File("accounts.dat");	
	private String currentDirectory;
	
	public BankAccountGUI(){  
		// Setting up menu
		setUpMenu();
		JFileChooser jfc = new JFileChooser();
		
		//create array of account objects and pass to custom TableModel
		arrData = new ArrayList<Account>();
		tm = new MyTableModel(arrData);		
		myTable = new JTable(tm);
		
		myPane = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		myTable.setSelectionForeground(Color.white);
		myTable.setSelectionBackground(Color.red);
		myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//create form panel
		 createFormPanel();
		 
		//Event Listeners
		 
		 //TODO:1 Add Account Event
		 addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = nameField.getText();
				int accNum = Integer.parseInt(accountNoField.getText());
				double balance =Double.parseDouble(balanceField.getText());
				
				arrData.add(new Account(name, accNum, balance));
				tm.fireTableDataChanged();
				nameField.setText(null);
				accountNoField.setText(null);
				balanceField.setText(null);
				
			}});

		 
		// TODO:2 Save the data to a file (save event handler)
		// Use provided function: writeDataFile() to save the data into the file
		 fileSaveAs.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					jfc.setCurrentDirectory((new File(System.getProperty("user.dir"))));
					if(!arrData.isEmpty()){
						
					int retVal = jfc.showSaveDialog(BankAccountGUI.this);
					
						try {
							writeDataFile(jfc.getSelectedFile());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(BankAccountGUI.this,"The I/O operations were compromised please try again");
						}
						
					arrData.clear();
					tm.fireTableDataChanged();
					
					}
					else{
						JOptionPane.showMessageDialog(BankAccountGUI.this,"There must be something to save"); 
					}
					
				}
				
			});


		// TODO:3 Loading the contents of a file into the table (load event handler)
		// Use provided function: readDataFile() to save the data into the file
		 fileLoad.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					jfc.setCurrentDirectory((new File(System.getProperty("user.dir"))));
					int retVal = jfc.showOpenDialog(BankAccountGUI.this);
					int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
					dialogButton = JOptionPane.showConfirmDialog(BankAccountGUI.this,
							"This will replace the existing data.\n Are you sure sure you want to do this?",
							"Select an Option", dialogButton);
					
					if(dialogButton == JOptionPane.YES_OPTION) {
						 try {
							readDataFile(jfc.getSelectedFile());
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 if(dialogButton == JOptionPane.NO_OPTION ||dialogButton == JOptionPane.CANCEL_OPTION ) {}
					}
					
					 
					
					for(int i =0; i<arrData.size(); i++)
					{
						tm.fireTableDataChanged();
					}
					
				}
				
			});
		 
		//TODO: 4 Remove Selected Account Event
		 removeAccount.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int rowtoDelete = myTable.getSelectedRow();
					 if(rowtoDelete>=0) {
						 arrData.remove(rowtoDelete);	
						 tm.fireTableRowsDeleted(rowtoDelete, rowtoDelete);
							
							}
					else if(myTable.getSelectedRow() < 0)
					{
						JOptionPane.showMessageDialog(BankAccountGUI.this,"You must select a row to delete"); 
					}
				}
				});
	      
		 
		//TODO: 5 Thread Button simulation Event
		 threadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				String at = JOptionPane.showInputDialog("Please enter the amount you want deposited");
				String ls = JOptionPane.showInputDialog("Please enter the amount of times you want that to be deposited");
				String amountWithdraw = JOptionPane.showInputDialog("Please enter the amount you want withdraw");
				String lsWithdraw = JOptionPane.showInputDialog("Please enter the amount of times you want that to be withdraw");
				double amount = Double.parseDouble(at);
				int loopTimes = Integer.parseInt(ls);
				
				
				DepositRunnable d1 = new DepositRunnable(arrData.get(myTable.getSelectedRow()),Double.parseDouble(amountWithdraw),Integer.parseInt(lsWithdraw));
				WithdrawlRunnable w1 = new WithdrawlRunnable(arrData.get(myTable.getSelectedRow()),amount,loopTimes );
				
				(new Thread (d1)).start();
				
				(new Thread (w1)).start();
				
				try {
					d1.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
				
				try {
					w1.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			
			}
			});
		
		
		// Adding menu bar
		
		setJMenuBar(myBar);
		
		//add scrollpane with table
		tblPnl.add(myPane);
		
		//add thread button to thread panel
		threadPnl.add(threadButton);
		threadPnl.add(threadOutput);
		
		//add panel for form
		add(formPnl, BorderLayout.NORTH);
		TitledBorder title = BorderFactory.createTitledBorder("Account Summary");
		tblPnl.setBorder(title);
		add(tblPnl, BorderLayout.CENTER);
		add(threadPnl, BorderLayout.SOUTH);
		
		
		this.setTitle("Bank Account Details");
		this.setVisible(true);
		this.pack();
	} // constructor


	private void setUpMenu() {
		fileLoad = new JMenuItem("Open");
		fileSaveAs = new JMenuItem("Save As");
		
		fileMenu = new JMenu("File");
		fileMenu.add(fileLoad);
		fileMenu.add(fileSaveAs);
		
		removeAccount = new JMenuItem("Remove Account");
		
		recordMenu = new JMenu("Account");
		recordMenu.add(removeAccount);
		
		myBar = new JMenuBar();
		myBar.add(fileMenu);
		myBar.add(recordMenu);

	}

	public void writeDataFile(File f) throws IOException, FileNotFoundException {
		FileOutputStream fileStream = null;
		ObjectOutputStream objectStream = null;
		try {
			//open stream
			fileStream =  new FileOutputStream(f);
			objectStream = new ObjectOutputStream(fileStream);
			//write to stream
			objectStream.writeObject(arrData);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
				try {
					objectStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public void readDataFile(File f) throws IOException, ClassNotFoundException {
		FileInputStream fileStream = null;
		ObjectInputStream objectStream = null;
		ArrayList<Account> accounts = new ArrayList<Account>(); 
		  try {
			//open stream
			fileStream =  new FileInputStream(f);
			objectStream = new ObjectInputStream(fileStream);
			//write to stream
			try {
				arrData =  (ArrayList<Account>) objectStream.readObject();
				this.arrData.clear();
				this.arrData.addAll(accounts);
					
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
				try {
					objectStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		}
	
	public void closeDown() {
			System.exit(0);
	}
	
	public void createFormPanel() {
		    formPnl.setLayout(new GridBagLayout());
			TitledBorder title = BorderFactory.createTitledBorder("Add Account");
			formPnl.setBorder(title);
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor =  GridBagConstraints.WEST;
		    c.insets = new Insets(10,0,0,0);  
		    formPnl.add(nameLabel, c);
		    c.gridx = 1;
		    formPnl.add(nameField, c);
			c.gridx = 0;
		    c.gridy = 1;
		    formPnl.add(accountNoLabel, c);
		    c.gridx = 1;
		    formPnl.add(accountNoField,c);
			c.gridx = 0;
		    c.gridy = 2;
		    formPnl.add(balanceLabel,c);
		    c.gridx = 1;
		    formPnl.add(balanceField,c);
			c.gridx = 0;
		    c.gridy = 3;
		    c.gridwidth = 2; 
		    c.fill = GridBagConstraints.NONE;
		    c.anchor =  GridBagConstraints.CENTER; 
		    formPnl.add(addButton, c);
	   }
	
	public static void main (String args[]){
		new BankAccountGUI();
	} // main
} //class