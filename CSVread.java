import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVread {


    static String [] values; //array to hold csv files
    public static void main(String[] args){
        readCSV(); //reading in the file into csv format

        outputCSV(values);//outputting into csv file
    }

    static void readCSV(){

        try{//try catch block to read file
            Scanner in = new Scanner(System.in);
            String line = "";


            System.out.println("What is the filename?");
            String input = in.nextLine();
            File file = new File(input);

            BufferedReader br = new BufferedReader(new FileReader(file));
            
            while((line = br.readLine()) != null){
                values = line.split(","); //cuts csv file into the array. 1-d array lol I'll work on making it 2d 
            }
        }

        catch (FileNotFoundException e){//error handling
            System.out.println("No such file was found");
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }


    static void outputCSV(String[] values2){//outputting to a file method, need to work on this method still. 

        try{

            BufferedWriter br = new BufferedWriter(new FileWriter("outputfile.csv"));//writing to a output file
            StringBuilder sb = new StringBuilder();

            
            for (String element : values2) {
                sb.append(element);
                sb.append(",");
        }

            br.write(sb.toString());
            br.close();

         }

        catch (IOException e) {
            System.out.println("\nError writing to output.csv!");
            e.printStackTrace();
       }
    }
}


