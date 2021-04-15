import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUIApplication extends Frame implements ActionListener {

	CSVread loader = new CSVread();
	static List<List<String>> data = new ArrayList<>();
	Label lb1;
	TextField txt1;
	Button btn1, btn2, btn3, btn4, btn5;
	
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
			
			//print all lines in data
			int lineNo = 1;
	        for(List<String> line: data) {
	            int columnNo = 1;
	            for (String value: line) {
	                System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
	                columnNo++;
	            }
	            lineNo++;
	        }
		}
		if(e.getSource() == btn3) {	//Add Data event handler
			
		}
		if(e.getSource() == btn4) {	//Save Data event handler
			loader.outputCSV(data);
		}
		if(e.getSource() == btn5) {	//Visualize Data event handler
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUIApplication app = new GUIApplication();
		
		//data = loader.lines;
		app.setVisible(true);
		app.setLocation(600, 600);
	}
	
}
