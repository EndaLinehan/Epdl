
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import net.miginfocom.swing.MigLayout;

public class Timer extends JFrame {
	
	// Interface components
	
	// Fonts to be used
	Font countdownFont = new Font("Arial", Font.BOLD, 20);
	Font elapsedFont = new Font("Arial", Font.PLAIN, 14);
	
	// Labels and text fields
	JLabel countdownLabel = new JLabel("Seconds remaining:");
	JTextField countdownField = new JTextField(15);
	JLabel elapsedLabel = new JLabel("Time running:");
	JTextField elapsedField = new JTextField(15);
	JButton startButton = new JButton("START");
	JButton pauseButton = new JButton("PAUSE");
	JButton stopButton = new JButton("STOP");
	
	// The text area and the scroll pane in which it resides
	JTextArea display;
	
	JScrollPane myPane;
	
	// These represent the menus
	JMenuItem saveData = new JMenuItem("Save data", KeyEvent.VK_S);
	JMenuItem displayData = new JMenuItem("Display data", KeyEvent.VK_D);
	
	JMenu options = new JMenu("Options");
	
	JMenuBar menuBar = new JMenuBar();
	
	// These booleans are used to indicate whether the START button has been clicked
	boolean started;
	
	// and the state of the timer (paused or running)
	boolean paused;
	
	// Number of seconds
	long totalSeconds = 0;
	long secondsToRun = 0;
	long secondsSinceStart = 0;
	
	// This is the thread that performs the countdown and can be started, paused and stopped
	TimerThread countdownThread;

	// Interface constructed
	Timer() {
		
		setTitle("Timer Application");
		
    	MigLayout layout = new MigLayout("fillx");
    	JPanel panel = new JPanel(layout);
    	getContentPane().add(panel);
    	
    	options.add(saveData);
    	options.add(displayData);
    	menuBar.add(options);
    	
    	panel.add(menuBar, "spanx, north, wrap");
    	
    	MigLayout centralLayout = new MigLayout("fillx");
    	
    	JPanel centralPanel = new JPanel(centralLayout);
    	
    	GridLayout timeLayout = new GridLayout(2,2);
    	
    	JPanel timePanel = new JPanel(timeLayout);
    	
    	countdownField.setEditable(false);
    	countdownField.setHorizontalAlignment(JTextField.CENTER);
    	countdownField.setFont(countdownFont);
    	countdownField.setText("00:00:00");
    	
    	timePanel.add(countdownLabel);
    	timePanel.add(countdownField);

    	elapsedField.setEditable(false);
    	elapsedField.setHorizontalAlignment(JTextField.CENTER);
    	elapsedField.setFont(elapsedFont);
    	elapsedField.setText("00:00:00");
    	
    	timePanel.add(elapsedLabel);
    	timePanel.add(elapsedField);

    	centralPanel.add(timePanel, "wrap");
    	
    	GridLayout buttonLayout = new GridLayout(1, 3);
    	
    	JPanel buttonPanel = new JPanel(buttonLayout);
    	
    	buttonPanel.add(startButton);
    	buttonPanel.add(pauseButton, "");
    	buttonPanel.add(stopButton, "");
    	
    	centralPanel.add(buttonPanel, "spanx, growx, wrap");
    	
    	panel.add(centralPanel, "wrap");
    	
    	display = new JTextArea(100,150);
        display.setMargin(new Insets(5,5,5,5));
        display.setEditable(false);
        
        JScrollPane myPane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(myPane, "alignybottom, h 100:320, wrap");
        
        
        // Initial state of system
        paused = false;
        started = false;
        
        // Allowing interface to be displayed
    	setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        // TODO: SAVE: This method should allow the user to specify a file name to which to save the contents of the text area using a 
        // JFileChooser. You should check to see that the file does not already exist in the system.


        
        // TODO: DISPLAY DATa: This method should retrieve the contents of a file representing a previous report using a JFileChooser.
        // The result should be displayed as the contents of a dialog object.


        
        // TODO: START: This method should check to see if the application is already running, and if not, launch a TimerThread object.
		// If the application is running, you may wish to ask the user if the existing thread should be stopped and a new thread started.
        // It should begin by launching a TimerDialog to get the number of seconds to count down, and then pass that number of seconds along
		// with the seconds since the start (0) to the TimerThread constructor.
		// It can then display a message in the text area stating how long the countdown is for.

        
        // TODO: PAUSE: This method should call the TimerThread object's pause method and display a message in the text area
        // indicating whether this represents pausing or restarting the timer.

        
        // TODO: STOP: This method should stop the TimerThread object and use appropriate methods to display the stop time
        // and the total amount of time remaining in the countdown (if any).

    	
	}
	
	// TODO: These methods can be used in the action listeners above.
	public synchronized void writeDataFile(File f) throws IOException, FileNotFoundException {

	}
	
	// TODO: These methods can be used in the action listeners above.
	public synchronized String readDataFile(File f) throws IOException, ClassNotFoundException {
		
		String result = new String();
  
		return result;
	}

    public static void main(String[] args) {

        Timer timer = new Timer();

    }

}

