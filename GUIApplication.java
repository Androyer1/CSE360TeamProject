import java.awt.*;
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
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

class Slice {
	double value;
	Color color;

	public Slice(double value, Color color) {
		this.value = value;
		this.color = color;
	}
}

public class GUIApplication extends JFrame implements ActionListener {

	static List<List<String>> lines = new ArrayList<>();
	int hello = 0;
	private JFrame frame;
	JLayeredPane layeredPane;
	
	JPanel panel_1;
	JPanel panel_2;
	JPanel panel_3;
	JPanel panel_4;
	JPanel panel_5;
	
	JTextField textField;	//Add data textFields
	JTextField textField_1;	
	JTextField textField_2;
	JTextField textField_3;
	JTextField textField_4;
	JTextField textField_5;
	JTextField textField_6;
	
	JTextField textField_7; //load data textField

	CSVread loader = new CSVread();	//Use these to store data for visualization
	static List<List<String>> data = new ArrayList<>();
	List<Integer> dosesByLocation = new ArrayList<>();
	List<String> doses_By_Location = new ArrayList<>();
	List<Integer> dosesByType = new ArrayList<>();
	List<String> doses_By_Type = new ArrayList<>();

	JButton btnNewButton;
	JButton btnLoadData;
	JButton btnAddData;
	JButton btnSaveData;
	JButton btnVisualizeData;
	JButton btnSubmit_2;
	JButton btnSubmit_3;

	Color[] colorList = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.WHITE,
			Color.BLACK, Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.PINK, Color.ORANGE };
	int numDoses = 0;

	public Slice[] createSlices() {
		Slice[] slices = new Slice[numDoses];
		int colorInt = 0;
		for (int i = 0; i < numDoses; i++) {

			if (colorInt >= colorList.length)
				colorInt = 0;

			Slice tmp = new Slice(dosesByLocation.get(i), colorList[colorInt]);
			slices[i] = tmp;

			colorInt++;
		}
		return slices;
	}

	public void paint(Graphics g) {
		drawPie((Graphics2D) g, getBounds(), createSlices());
	}

	void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
		double total = 0.0D;

		for (int i = 0; i < slices.length; i++) {
			total += slices[i].value;
		}
		double curValue = 0.0D;
		int startAngle = 0;
		for (int i = 0; i < slices.length; i++) {
			startAngle = (int) (curValue * 360 / total);
			int arcAngle = (int) (slices[i].value * 360 / total);
			g.setColor(slices[i].color);
			g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
			curValue += slices[i].value;
		}
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

	private void initialize() {
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
		
		JLabel lblDate = new JLabel("Date:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblDate, 8, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, lblDate, 90, SpringLayout.WEST, panel_3);
		panel_3.add(lblDate);
		
		textField = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField, 5, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblDate);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField, 156, SpringLayout.EAST, lblDate);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblId, 12, SpringLayout.SOUTH, lblDate);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblId, 10, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblDate, 0, SpringLayout.WEST, lblId);
		panel_3.add(lblId);
		
		textField_1 = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField_1, 6, SpringLayout.SOUTH, textField);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, textField);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField);
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblLastName, 12, SpringLayout.SOUTH, lblId);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblLastName, 10, SpringLayout.WEST, panel_3);
		panel_3.add(lblLastName);
		
		textField_2 = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField_2, 6, SpringLayout.SOUTH, textField_1);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField_2, 0, SpringLayout.WEST, textField);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, textField);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblFirstName, 12, SpringLayout.SOUTH, lblLastName);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblFirstName, 10, SpringLayout.WEST, panel_3);
		panel_3.add(lblFirstName);
		
		textField_3 = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField_3, 6, SpringLayout.SOUTH, textField_2);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField_3, 0, SpringLayout.WEST, textField);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField_3, 0, SpringLayout.EAST, textField);
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblVaccineType = new JLabel("Vaccine Type:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblVaccineType, 12, SpringLayout.SOUTH, lblFirstName);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblVaccineType, 10, SpringLayout.WEST, panel_3);
		panel_3.add(lblVaccineType);
		
		textField_4 = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField_4, 6, SpringLayout.SOUTH, textField_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField_4, 0, SpringLayout.WEST, textField);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField_4, 0, SpringLayout.EAST, textField);
		panel_3.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblVaccineDate = new JLabel("Vaccine Date:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblVaccineDate, 12, SpringLayout.SOUTH, lblVaccineType);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblVaccineDate, 10, SpringLayout.WEST, panel_3);
		panel_3.add(lblVaccineDate);
		
		textField_5 = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField_5, 6, SpringLayout.SOUTH, textField_4);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField_5, 0, SpringLayout.WEST, textField);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField_5, 0, SpringLayout.EAST, textField);
		panel_3.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblVaccineLocation = new JLabel("Vaccine Location:");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblVaccineLocation, 12, SpringLayout.SOUTH, lblVaccineDate);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblVaccineLocation, 0, SpringLayout.WEST, lblDate);
		panel_3.add(lblVaccineLocation);
		
		textField_6 = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textField_6, 6, SpringLayout.SOUTH, textField_5);
		sl_panel_3.putConstraint(SpringLayout.WEST, textField_6, 0, SpringLayout.WEST, textField);
		sl_panel_3.putConstraint(SpringLayout.EAST, textField_6, 0, SpringLayout.EAST, textField);
		panel_3.add(textField_6);
		textField_6.setColumns(10);
		
		btnSubmit_3 = new JButton("Submit");
		btnSubmit_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_panel_3.putConstraint(SpringLayout.NORTH, btnSubmit_3, 6, SpringLayout.SOUTH, textField_6);
		sl_panel_3.putConstraint(SpringLayout.EAST, btnSubmit_3, 0, SpringLayout.EAST, textField);
		panel_3.add(btnSubmit_3);
		
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
		
		JLabel lblVisualizingData = new JLabel("Visualizing Data");
		panel_5.add(lblVisualizingData);
		
		
		JLabel lblTeamidk = new JLabel("Team #idk");
		lblTeamidk.setBounds(10, 10, 180, 14);
		lblTeamidk.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblTeamidk);
		
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
		
		btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(this);
		panel.add(btnLoadData);
		
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
			if(hello != 1){
			String userenters;
			userenters= JOptionPane.showInputDialog ("What is the filepath?");	
			hello = readCSV(userenters);
			}
			String[] header = {"ID", "Last Name", "First Name", "Vaccine Type", "Vaccination Date", "Vaccine Location" };
					String[][] tempTable = new String[lines.size()][]; 
					int i = 0;
					for (List<String> next : lines) {
						tempTable[i++] = next.toArray(new String[next.size()]); // return Object[][]
				}
			JTable EndTable = new JTable(tempTable,header);
			EndTable.setBounds(30, 40, 200, 300);
			panel_2.add(new JScrollPane(EndTable));	
}
		if (e.getSource() == btnSubmit_2) {
			loader.readCSV(textField_7.getText());
			System.out.println("Past this");
			data = loader.lines;
			// Store values in separate arrays for easy access
			int lineNo = 1;
			for (List<String> line : data) {
				int columnNo = 1;
				for (String value : line) {
					System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
					if (columnNo == 6) {
						// Check if Location is already in list doses_By_Location - false then continue
						boolean locFound = false;
						int occurences = 0;
						for (String location : doses_By_Location) {
							if (value.equals(location)) {
								System.out.println("Location found already");
								locFound = true;
							}
						}
						// Find total number of occurences of this location.
						if (!locFound) {
							int lineNo2 = 1;
							for (List<String> line2 : data) {
								int columnNo2 = 1;
//		                		if(line2.get(6).equals(value)) {
//		                			test to see if this works
//		                		}
								for (String value2 : line2) {
									if (columnNo2 == 6) {
										if (value.equals(value2)) {
											occurences++;
										}
									}
								}
							}
						}
						// Add number occurences to dosesByLocation
						dosesByLocation.add(occurences);
					}
					columnNo++;
				}
				lineNo++;
				numDoses++;
			}
			
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
			if (data.isEmpty()) {
				System.out.println("Please enter data");
			}
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


	int readCSV(String str){	//static bool readCSV?

        try{//try catch block to read file
            Scanner in = new Scanner(System.in);
            String line = "";


//             System.out.println("What is the file path?");
		//should check if 'str' is a valid string
            String input = str;
            File file = new File(input);

            BufferedReader br = new BufferedReader(new FileReader(file));
            
            while((line = br.readLine()) != null){
                String[] values = line.split(","); //cuts csv file into the array. 1-d array lol I'll work on making it 2d 
                lines.add(Arrays.asList(values));
            	
            }
//            inputStream.close();
        }

        catch (FileNotFoundException e){//error handling
            System.out.println("No such file was found");
        }
        catch (IOException e){
            e.printStackTrace();
        }

		return 1;
		


    }

	

	static void outputCSV(List<List<String>> data){//outputting to a file method, need to work on this method still. 

        data = lines;
        String delim = "";
 
        StringBuilder sb = new StringBuilder();
 
        int i = 0;
        while (i < data.size() - 1)
        {
            sb.append(data.get(i));
            sb.append(delim);
            i++;
        }
        sb.append(data.get(i));
 
        String res = sb.toString();
        
        try{

            String[] split = (res.split("]"));

        FileWriter writer = new FileWriter("output.csv");

        for(String s : split) {
            String[] split2 = s.split("]");
            writer.write(Arrays.asList(split2).stream().collect(Collectors.joining("[")));
            writer.write("\n"); 
        }

        writer.close();

    }
        catch (IOException e) {
            System.out.println("\nError writing to output.csv!");
            e.printStackTrace();
       }
    }


}
