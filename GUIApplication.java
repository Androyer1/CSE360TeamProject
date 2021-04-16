import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Slice {
	double value;
	Color color;
	public Slice(double value, Color color) {  
		this.value = value;
		this.color = color;
	}
}

public class GUIApplication extends Frame implements ActionListener {

	CSVread loader = new CSVread();
	static List<List<String>> data = new ArrayList<>();
	List<Integer> dosesByLocation = new ArrayList<>();
	List<String> doses_By_Location = new ArrayList<>();
	List<Integer> dosesByType = new ArrayList<>();
	List<String> doses_By_Type = new ArrayList<>();
	Label lb1;
	TextField txt1;
	Button btn1, btn2, btn3, btn4, btn5;
	Color[] colorList = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.WHITE,
			Color.BLACK, Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.PINK, Color.ORANGE};
	int numDoses = 0;
	
	public Slice[] createSlices() {
		Slice[] slices = new Slice[numDoses];
		int colorInt = 0;
		for(int i = 0; i < numDoses; i++) {
			
			if(colorInt >= colorList.length) 
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
	
	public GUIApplication() {
		btn1 = new Button("About");
		btn2 = new Button("Load Data");
		btn3 = new Button("Add Data");
		btn4 = new Button("Save Data");
		btn5 = new Button("Visualize Data");
		
		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(btn5);
		
		setSize(500,500);
		setTitle("GUI Application");
		setLayout(new FlowLayout());
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btn1) {	//About event handler
			
		}
		if(e.getSource() == btn2) {	//Load Data event handler
			System.out.println("CLICKED");
			loader.readCSV();
			data = loader.lines;
			
			//Store values in separate arrays for easy access
			int lineNo = 1;
	        for(List<String> line: data) {
	            int columnNo = 1;
	            for (String value: line) {
	                System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
	                
	                if(columnNo == 6) {
	                	//Check if Location is already in list doses_By_Location - false then continue
	                	boolean locFound = false;
	                	int occurences = 0;
	                	for(String location: doses_By_Location) {
	                		if(value.equals(location)) {
	                			System.out.println("Location found already");
	                			locFound = true;
	                		}
	                	}
	                	
	                	//Find total number of occurences of this location.
	                	if(!locFound) {
	                		int lineNo2 = 1;
		                	for(List<String> line2: data) {
		                		int columnNo2 = 1;
//		                		if(line2.get(6).equals(value)) {
//		                			test to see if this works
//		                		}
		                		for (String value2: line2) {
		                			if(columnNo2 == 6) {
		                				if(value.equals(value2)) {
		                					occurences++;
		                				}
		                			}
		                		}
		                	}
	                	}
	                	
	                	//Add number occurences to dosesByLocation
	                	dosesByLocation.add(occurences);
	                }
	                
	                columnNo++;
	            }
	            lineNo++;
	            numDoses++;
	        }
		}
		if(e.getSource() == btn3) {	//Add Data event handler
			
		}
		if(e.getSource() == btn4) {	//Save Data event handler
			
			loader.outputCSV(data);
		}
		if(e.getSource() == btn5) {	//Visualize Data event handler
			
			if(data.isEmpty()) {
				System.out.println("Please enter data");
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUIApplication app = new GUIApplication();
		
		//data = loader.lines;
		app.setVisible(true);
		app.setLocation(300, 300);
	}
	
}
