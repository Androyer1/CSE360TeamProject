import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CSVread {

	//create a 2d ArrayList to store csv values
	static List<List<String>> lines = new ArrayList<>();
//    static String [] values; //array to hold csv files

	//default constructor
	public CSVread() {
		
	}
	
	
	
    public static void main(String[] args){
//         readCSV(); //reading in the file into csv format

        //print values of csv file
//        System.out.println(lines.get(1).get(2));
        int lineNo = 1;
        for(List<String> line: lines) {
            int columnNo = 1;
            for (String value: line) {
                System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
                columnNo++;
            }
            lineNo++;
        }
//        outputCSV(values);//outputting into csv file
    }

    static void readCSV(String str){	//static bool readCSV?

        try{//try catch block to read file
            Scanner in = new Scanner(System.in);
            String line = "";


//             System.out.println("What is the file path?");
		//should check if 'str' is a valid string
            String input = str
            File file = new File(input);

            BufferedReader br = new BufferedReader(new FileReader(file));
            
            while((line = br.readLine()) != null){
                String[] values = line.split(","); //cuts csv file into the array. 1-d array lol I'll work on making it 2d 
                lines.add(Arrays.asList(values));
//                System.out.println(Arrays.toString(values));
            }
//            inputStream.close();
        }

        catch (FileNotFoundException e){//error handling
            System.out.println("No such file was found");
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }


    static void outputCSV(List<List<String>> data){//outputting to a file method, need to work on this method still. 

        List<List<String>> list = data;
        String delim = "-";
 
        StringBuilder sb = new StringBuilder();
 
        int i = 0;
        while (i < list.size() - 1)
        {
            sb.append(list.get(i));
            sb.append(delim);
            i++;
        }
        sb.append(list.get(i));
 
        String res = sb.toString();
        System.out.println(res);
        
        try{

            String[] split = (res.split("]"));

        FileWriter writer = new FileWriter("output.csv");

        for(String s : split) {
            String[] split2 = s.split("]");
            writer.write(Arrays.asList(split2).stream().collect(Collectors.joining("]")));
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
