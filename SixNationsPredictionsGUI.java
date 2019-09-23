package GroupB;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


import java.awt.event.*;
import java.awt.*;
import java.io.*;


public class SixNationsPredictionsGUI extends JFrame{

	// Menu structure
	JMenuBar myBar;
	JMenu fileMenu, recordMenu,  exitMenu;
	JMenuItem fileLoad, fileSaveAs, addPrediction, removePrediction, exitProgram;
	
		
	// Array of data types to be used in combo box when defining new structure
	private String[] teamsArr = {"Ireland", "England", "Wales", "France", "Scotland", "Italy"};
	private JComboBox teamComboBox = new JComboBox(teamsArr);

	// Table Model
	JPanel p;
	MyTableModel tm;
	JTable myTable;
	JScrollPane myPane;
	
	ArrayList<MyPrediction> predictions = new ArrayList<MyPrediction>(); 
	
	// Used to indicate whether data is already in a file
	File currentFile;
	
	public SixNationsPredictionsGUI(){  
		// Setting up menu
		createMenuBar();
		JFileChooser jfc = new JFileChooser();
		
		p = new JPanel();
		tm = new MyTableModel(predictions);
		myTable = new JTable(tm);
		myTable.setPreferredScrollableViewportSize(new Dimension(500, 100));
		myPane = new JScrollPane(myTable);
		myTable.setSelectionForeground(Color.white);
		myTable.setSelectionBackground(Color.red);
		myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		//setup combobox
        setUpTeamColumn(myTable, myTable.getColumnModel().getColumn(1));

        // Associating event listeners with menu items
        
        fileLoad.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfc.setCurrentDirectory((new File(System.getProperty("user.dir"))));
				int retVal = jfc.showOpenDialog(SixNationsPredictionsGUI.this);
				int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
				dialogButton = JOptionPane.showConfirmDialog(SixNationsPredictionsGUI.this,
						"This will replace the existing data.\n Are you sure sure you want to do this?",
						"Select an Option", dialogButton);
				
				if(dialogButton == JOptionPane.YES_OPTION) {
					 readDataFile(jfc.getSelectedFile());
					 if(dialogButton == JOptionPane.NO_OPTION ||dialogButton == JOptionPane.CANCEL_OPTION ) {}
				}
				
				 
				
				for(int i =0; i<predictions.size(); i++)
				{
					tm.fireTableDataChanged();
					
				}
				
			}
			
		});
        fileSaveAs.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				jfc.setCurrentDirectory((new File(System.getProperty("user.dir"))));
				if(!predictions.isEmpty()){
					
				int retVal = jfc.showSaveDialog(SixNationsPredictionsGUI.this);
				
					writeDataFile(jfc.getSelectedFile());
					
				
			
				predictions.clear();
				}
				else{
					JOptionPane.showMessageDialog(SixNationsPredictionsGUI.this,"There must be something to save"); 
				}
				
			}
			
		});
        //TODO:(1) File - Open - Loading the contents of a file into the table, including all warning messages
        // Use provided function: readDataFile() to save the data into the file
		
        //TODO:(2) Save the data from the table into the file. 
		// Use provided function: writeDataFile() to save the data into the file
		

        //TODO:(3) Prediction - Add Row
        addPrediction.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name="";
				String team="";
				int score=0;
				double betAmount=0;
				String woodenSpoon="";
				MyPrediction prediction = new MyPrediction(name,team,score,betAmount,woodenSpoon);
				predictions.add(prediction);
				tm.fireTableDataChanged();
				tm.isCellEditable(tm.getRowCount(), tm.getColumnCount());
			}});
        
        //TODO:(4) Prediction - Remove Row including warning messages
        removePrediction.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int rowtoDelete = myTable.getSelectedRow();
				 if(rowtoDelete>=0) {
					 predictions.remove(rowtoDelete);	
					 tm.fireTableRowsDeleted(rowtoDelete, rowtoDelete);
						
						}
				else if(myTable.getSelectedRow() < 0)
				{
					JOptionPane.showMessageDialog(SixNationsPredictionsGUI.this,"You must select a row to delete"); 
				}
			}
			});
      
		// exits program from menu
		exitProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDown();
			}
		});

		// exits program by closing window
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				closeDown();
			}
		}); // end windowlistener
		
		// Adding menu bar and table to panel
		this.setJMenuBar(myBar);
		this.add(myPane, BorderLayout.CENTER);
		
		this.setTitle("2019 Six Nations Rubgy");
		this.setVisible(true);
		this.pack();
	} // constructor

	private void createMenuBar() {
		fileLoad = new JMenuItem("Open");
		fileSaveAs = new JMenuItem("Save As");
		
		fileMenu = new JMenu("File");
		fileMenu.add(fileLoad);
		fileMenu.add(fileSaveAs);
		
		addPrediction = new JMenuItem("Add");
		removePrediction = new JMenuItem("Remove");
		
		recordMenu = new JMenu("Prediction");
		recordMenu.add(addPrediction);
		recordMenu.add(removePrediction);
		
		
		exitProgram = new JMenuItem("Exit Program");
		exitMenu = new JMenu("Exit");
		exitMenu.add(exitProgram);
		
		myBar = new JMenuBar();
		myBar.add(fileMenu);
		myBar.add(recordMenu);
		myBar.add(exitMenu);
	}

    public void setUpTeamColumn(JTable table,TableColumn teamColumn) {	  
      	  teamColumn.setCellEditor(new DefaultCellEditor(teamComboBox));
      	
          //Set up tool tips for the sport cells.
          DefaultTableCellRenderer renderer =
                  new DefaultTableCellRenderer();
          renderer.setToolTipText("Click for combo box");
          teamColumn.setCellRenderer(renderer);
      }
    
	public void writeDataFile(File f) {
		FileOutputStream fileStream = null;
		ObjectOutputStream objectStream = null;
		try {
			//open stream
			fileStream =  new FileOutputStream(f);
			objectStream = new ObjectOutputStream(fileStream);
			//write to stream
			objectStream.writeObject(predictions);

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

	public void readDataFile(File f) {
		  
	    	{
	    		FileInputStream fileStream = null;
	    		ObjectInputStream objectStream = null;
	    		ArrayList<MyPrediction> predictions = new ArrayList<MyPrediction>(); 
	    		  try {
	    			//open stream
	    			fileStream =  new FileInputStream(f);
	    			objectStream = new ObjectInputStream(fileStream);
	    			//write to stream
	    			try {
	    				predictions =  (ArrayList<MyPrediction>) objectStream.readObject();
	    				this.predictions.clear();
	    				this.predictions.addAll(predictions);
	    					//customersTemp.add((Customer)objectStream.readObject());
	    				
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
	    		  
				}}

	public void closeDown() {
			System.exit(0);
	}

	public static void main (String args[]){
		new SixNationsPredictionsGUI();
	} // main
} //class