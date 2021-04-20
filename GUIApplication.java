package newPackage;

import java.awt.*; //importing the neccesary libraries for the project
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.*;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*; //importing javaswing lirbaries used for tables
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;

public class GUIApplication extends JFrame implements ActionListener { //main GUIApplication which holds JFrame

	static List<List<String>> lines = new ArrayList<>(); //lines which is an arraylist used for reading csv files
	int isPressed = 0; //ispressed used to check if user sumbits the file 
	private JFrame frame;  //intializing the gui for the application
	JLayeredPane layeredPane;
	JLayeredPane layeredPane_1;
	JTable EndTable = new JTable();

	JPanel panel_1; //panels intialized for the gui
	JPanel panel_2;
	JPanel panel_3;
	JPanel panel_4;
	JPanel panel_5;
	JTextField textField_1; //textfields used mainly for reading in user value
	JTextField textField_2;
	JTextField textField_3;
	JTextField textField_4;
	JTextField textField_5;
	JTextField textField_6;

	JTextField textField_7; // load data textField

	// CSVread loader = new CSVread(); //Use these to store data for visualization
	static List<List<String>> data = new ArrayList<>();
	List<Integer> dosesByLocation = new ArrayList<>();
	List<String> doses_By_Location = new ArrayList<>();
	List<Integer> dosesByType = new ArrayList<>();
	List<String> doses_By_Type = new ArrayList<>();

	JButton btnNewButton; //button needed for the GUI
	JButton btnLoadData;
	JButton btnAddData;
	JButton btnSaveData;
	JButton btnVisualizeData;
	JButton btnSubmit_2;
	JButton btnSubmit_3;

	Color[] colorList = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.WHITE,
			Color.BLACK, Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.PINK, Color.ORANGE }; 
	int numDoses = 0;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	public PieDataset createPieData(Map<String, Long> map) { //PIedata cast which is used to create an array with locations
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (Map.Entry<String, Long> entry : map.entrySet()) {
			dataset.setValue(entry.getKey(), entry.getValue());
//		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		return dataset;
	}

	private CategoryDataset createDataset(Map<String, Long> map) { //data set used to store which vaccines were used
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Map.Entry<String, Long> entry : map.entrySet()) {
			dataset.addValue(entry.getValue(), entry.getKey(), "Frequency");
//		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		return dataset;
	}

	public void switchPanels(JPanel panel) { 
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public GUIApplication() {
		initialize();
		// Begin drawing
	}

	private void initialize() { //drawing the GUI, creating the neccesary panels/buttons for the GUI
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new CardLayout(0, 0));

		panel_1 = new JPanel();
		layeredPane.add(panel_1, "name_128811368633400");
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		layeredPane.add(panel_2, "name_128732159168300");
		SpringLayout sl_panel_2 = new SpringLayout();
		panel_2.setLayout(sl_panel_2);

		panel_3 = new JPanel();
		layeredPane.add(panel_3, "name_128767402394400");
		SpringLayout sl_panel_3 = new SpringLayout();
		panel_3.setLayout(sl_panel_3);

		JLabel lblId = new JLabel("ID:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblId, 10, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblId, 10, SpringLayout.WEST, panel_3);
		panel_3.add(lblId);

		textField_1 = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField_1, -3, SpringLayout.NORTH, lblId);
		panel_3.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblLastName, 13, SpringLayout.SOUTH, lblId);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblLastName, 0, SpringLayout.WEST, lblId);
		panel_3.add(lblLastName);

		textField_2 = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField_2, 6, SpringLayout.SOUTH, textField_1);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField_2);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField_2, 42, SpringLayout.EAST, lblLastName);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField_2, -228, SpringLayout.EAST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, textField_2);
		panel_3.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblFirstName = new JLabel("First Name:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblFirstName, 16, SpringLayout.SOUTH, lblLastName);
		sl_panel_3.putConstraint(SpringLayout.EAST, lblFirstName, 0, SpringLayout.EAST, lblLastName);
		panel_3.add(lblFirstName);

		textField_3 = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField_3, -3, SpringLayout.NORTH, lblFirstName);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField_3, 42, SpringLayout.EAST, lblFirstName);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField_3, -228, SpringLayout.EAST, panel_3);
		panel_3.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblVaccineType = new JLabel("Vaccine Type:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblVaccineType, 16, SpringLayout.SOUTH, lblFirstName);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblVaccineType, 0, SpringLayout.WEST, lblId);
		panel_3.add(lblVaccineType);

		textField_4 = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField_4, -3, SpringLayout.NORTH, lblVaccineType);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField_4, 29, SpringLayout.EAST, lblVaccineType);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField_4, -228, SpringLayout.EAST, panel_3);
		panel_3.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblVaccineDate = new JLabel("Vaccine Date:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblVaccineDate, 16, SpringLayout.SOUTH, lblVaccineType);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblVaccineDate, 0, SpringLayout.WEST, lblId);
		panel_3.add(lblVaccineDate);

		textField_5 = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField_5, -3, SpringLayout.NORTH, lblVaccineDate);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField_5, 0, SpringLayout.WEST, textField_1);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField_5, -228, SpringLayout.EAST, panel_3);
		panel_3.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblVaccineLocation = new JLabel("Vaccine Location:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblVaccineLocation, 23, SpringLayout.SOUTH, lblVaccineDate);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblVaccineLocation, 0, SpringLayout.WEST, lblId);
		panel_3.add(lblVaccineLocation);

		textField_6 = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField_6, -3, SpringLayout.NORTH, lblVaccineLocation);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField_6, 13, SpringLayout.EAST, lblVaccineLocation);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField_6, 0, SpringLayout.EAST, textField_1);
		panel_3.add(textField_6);
		textField_6.setColumns(10);

		panel_4 = new JPanel();
		layeredPane.add(panel_4, "name_128805992442300");
		SpringLayout sl_panel_4 = new SpringLayout();
		panel_4.setLayout(sl_panel_4);

		JLabel lblSaved = new JLabel("Saved!");
		sl_panel_4.putConstraint(SpringLayout.NORTH, lblSaved, 10, SpringLayout.NORTH, panel_4);
		sl_panel_4.putConstraint(SpringLayout.WEST, lblSaved, 10, SpringLayout.WEST, panel_4);
		panel_4.add(lblSaved);

		panel_5 = new JPanel();
		layeredPane.add(panel_5, "name_128773653199000");

//		layeredPane_1 = new JLayeredPane();
//		panel_5.add(layeredPane_1);
//		
//		JPanel panel_6 = new JPanel();
//		panel_6.setBounds(-28, 21, 470, 390);
//		layeredPane_1.add(panel_6);
//		
//		JPanel panel_7 = new JPanel();
//		panel_7.setLayout(new java.awt.BorderLayout());
//		panel_7.setBounds(-28, 21, 470, 390);
//		layeredPane_1.add(panel_7);

		btnNewButton_2 = new JButton("Show Frequency of Vaccine Types"); //button on visualize data for showing frequency 
		btnNewButton_2.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { //runs when the button is pressed, creates a bar chart of the frequencies
				Map<String, Integer> map = new HashMap<>();
				Map<String, Long> result = lines.stream().skip(1)
						.collect(Collectors.groupingBy(list -> list.get(3), Collectors.counting())); 
				System.out.println(result);
				JFreeChart barChart = ChartFactory.createBarChart("Vaccine Frequency by Type", "Vaccine Type",
						"Frequency", createDataset(result), PlotOrientation.VERTICAL, true, true, false);
				ChartFrame CP = new ChartFrame("Bar Chart", barChart);
				CP.setVisible(true);
				CP.setSize(450, 500);
			}
		});
		panel_5.add(btnNewButton_2);

		btnNewButton_1 = new JButton("Show Frequency of Vaccine Location"); //button for pie data for showing freq of location
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //when button is pressed, shows a pie chart of freq of location
				Map<String, Integer> map = new HashMap<>();
				Map<String, Long> result = lines.stream().skip(1)
						.collect(Collectors.groupingBy(list -> list.get(5), Collectors.counting()));
				System.out.println(result);
				PieDataset dataset = createPieData(result);
				JFreeChart chart = ChartFactory.createPieChart("Doses By Location", dataset, true, true, false);
				PiePlot p = (PiePlot) chart.getPlot();
				ChartFrame CP = new ChartFrame("Pie Chart", chart);
				CP.setVisible(true);
				CP.setSize(450, 500);
			}
		});
		panel_5.add(btnNewButton_1);

		JLabel lblTeamidk = new JLabel("Team 49"); //label for group name
		lblTeamidk.setBounds(10, 10, 180, 14);
		lblTeamidk.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblTeamidk);
		
		//labels used for team member names
		JLabel lblNewLabel = new JLabel("Anna Mendenhall");
		lblNewLabel.setBounds(10, 36, 208, 14);
		panel_1.add(lblNewLabel);

		JLabel lblAndrewMiller = new JLabel("Andrew Miller");
		lblAndrewMiller.setBounds(10, 56, 180, 14);
		panel_1.add(lblAndrewMiller);

		JLabel lblKhangNguyen = new JLabel("Khang Nguyen");
		lblKhangNguyen.setBounds(10, 76, 180, 14);
		panel_1.add(lblKhangNguyen);

		JLabel lblSathyaKumaraguru = new JLabel("Sathya Kumaraguru");
		lblSathyaKumaraguru.setBounds(10, 96, 180, 14);
		panel_1.add(lblSathyaKumaraguru);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		btnNewButton = new JButton("About");
		btnNewButton.addActionListener(this);
		panel.add(btnNewButton);

		btnLoadData = new JButton("Load Data"); //load data button which asks user for input and creates a table
		btnLoadData.addActionListener(this);
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel_2);
				if (isPressed != 1) { //if the file isn't read yet it will run this if statement
					String userenters;
					userenters = JOptionPane.showInputDialog("What is the filepath?");
					isPressed = readCSV(userenters);
				}
				String[] header = { "ID", "Last Name", "First Name", "Vaccine Type", "Vaccination Date", //creating a table header
						"Vaccine Location" };
				String[][] tempTable = new String[lines.size()][]; 
				int i = 0;
				for (List<String> next : lines) { //goes through the arraylist and stores it in lines(dataset)
					tempTable[i++] = next.toArray(new String[next.size()]); // return Object[][]
				}
				DefaultTableModel model = new DefaultTableModel(tempTable, header);
				model.removeRow(0);
				EndTable.setModel(model);
				EndTable.setBounds(30, 40, 200, 300);
				panel_2.add(new JScrollPane(EndTable));
			}
		});
		panel.add(btnLoadData);

		btnSubmit_3 = new JButton("Submit"); //submit button which takes in user input fields and stores it in the table
		sl_panel_3.putConstraint(SpringLayout.NORTH, btnSubmit_3, 23, SpringLayout.SOUTH, textField_6);
		sl_panel_3.putConstraint(SpringLayout.WEST, btnSubmit_3, 138, SpringLayout.WEST, panel_3);
		btnSubmit_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //getting the user input text and adding it to the array
				//taking in user values and setting it to the right variable
				String id = textField_1.getText();
				String Lname = textField_2.getText();
				String Fname = textField_3.getText();
				String Vtype = textField_4.getText();
				String Vdate = textField_5.getText();
				String Loc = textField_6.getText();
				
				//putting out an output message if there is a blank line
				if (textField_1.getText().equals("")) { 
					JOptionPane.showOptionDialog(null, "Id is empty!", "Error!", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				}
				if (textField_2.getText().equals("")) {
					JOptionPane.showOptionDialog(null, "Last Name is empty!", "Error!", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				}
				if (textField_3.getText().equals("")) {
					JOptionPane.showOptionDialog(null, "First Name is empty!", "Error!", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				}
				if (textField_4.getText().equals("")) {
					JOptionPane.showOptionDialog(null, "Vaccine Type is empty!", "Error!", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				}
				if (textField_5.getText().equals("")) {
					JOptionPane.showOptionDialog(null, "Vaccine Date is empty!", "Error!", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				}
				if (textField_6.getText().equals("")) {
					JOptionPane.showOptionDialog(null, "Location is empty!", "Error!", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				}
				
				//if there is no blank lines, the values are added to the list
				else {
					List<String> new_row = Arrays.asList(id, Lname, Fname, Vtype, Vdate, Loc);
					lines.add(new_row);
				}
				textField_1.setText(""); //setting the fieldtexts back to null
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");

			}
		});
		panel_3.add(btnSubmit_3);

		btnAddData = new JButton("Add Data");
		btnAddData.addActionListener(this);
		panel.add(btnAddData);

		btnSaveData = new JButton("Save Data");
		btnSaveData.addActionListener(this);
		panel.add(btnSaveData);

		btnVisualizeData = new JButton("Visualize Data");
		btnVisualizeData.addActionListener(this);
		panel.add(btnVisualizeData);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btnNewButton) { // About event handler
			switchPanels(panel_1);
		}
		if (e.getSource() == btnLoadData) { // Load Data event handler
			switchPanels(panel_2);

		}
		if (e.getSource() == btnSubmit_2) {

		}
		if (e.getSource() == btnAddData) { // Add Data event handler
			switchPanels(panel_3);
		}

		if (e.getSource() == btnSaveData) { // Save Data event handler
			switchPanels(panel_4);
			outputCSV(data);
		}

		if (e.getSource() == btnVisualizeData) { // Visualize Data event handler
			switchPanels(panel_5);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIApplication window = new GUIApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	int readCSV(String str) { // read csv file which takes in a string(userfile) and stores the csv values

		try {// try catch block to read file
			Scanner in = new Scanner(System.in);
			String line = "";

			// should check if 'str' is a valid string
			String input = str;
			File file = new File(input);

			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				String[] values = line.split(","); // cuts csv file into the array.
				lines.add(Arrays.asList(values));
			}
			return 1;
		}

		catch (FileNotFoundException e) {// error handling
			JOptionPane.showOptionDialog(null, "No such file was found!", "Error!", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}

	}

	static void outputCSV(List<List<String>> data) {// outputting to a file method, which goes through the arraylist and into a csv file

		data = lines; //setting data equal to the input array
		String delim = ""; 

		StringBuilder sb = new StringBuilder(); //intializing the stringbuilder

		int i = 0;
		while (i < data.size() - 1) {  //goes thorugh the array and appends, incremting until it finishes the entire file
			sb.append(data.get(i));
			sb.append(delim);
			i++;
		}
		sb.append(data.get(i));
		String res = sb.toString(); //save the entire file into a string

		try { //try catch block which prints out to the output csv 
			String[] split = (res.split("]")); //splits when it hits this char
			FileWriter writer = new FileWriter("output.csv"); //output file name

			for (String s : split) { //goes through each line and puts it in the file
				String[] split2 = s.split("]");
				writer.write(Arrays.asList(split2).stream().collect(Collectors.joining("]")));
				writer.write("\n");
			}
			writer.close();

		} catch (IOException e) { //error handling if it can't write to the file
			System.out.println("\nError writing to output.csv!");
			e.printStackTrace();
		}
	}

}
