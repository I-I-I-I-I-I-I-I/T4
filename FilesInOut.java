import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class filesInOut
{

    public static void main(String[] args) 
    {

        String fileIn;
        File inputfile;
        Scanner inFile = null;;
        Scanner sc = new Scanner(System.in);
        PrintWriter fileOutput = null;

        System.out.println("Give input filename");
               
        try {
            
            fileIn = sc.nextLine();
            inputfile = new File(fileIn);
            inFile = new Scanner(inputfile);

        } catch (IOException e) {
            
            System.err.println("IOException: " + e.getMessage() + " not found");

        }

        System.out.println("Give output filename");
        try {

                String outputFile = sc.nextLine();
                fileOutput = new PrintWriter(outputFile);
        } 
        catch (IOException e) {
            
            System.err.println("IOException: "+ e.getMessage() + " not found.");
            System.exit(0);
        } 

          
        while (inFile.hasNext()) {
            String currentLine = inFile.nextLine();

            try {
                if (args[0].equals("-u")) {
                    fileOutput.write(names(currentLine).toUpperCase() + "\n");
            } 
        }
        catch (Exception e) {
                fileOutput.write(names(currentLine) + "\n");
            }
        }

        fileOutput.close();
        fileOutput.close();
    }


    /*
     * Uses the string split method to split each name and number from eachother,
     * resulting in a maximum of 4 values (Array length of 3). Uses whitespace as a
     * delimiter, not the best if people decide to seperate their middle names from
     * their first names using a "." Also handles all the date and name
     * formatting/casing
     */
    public static String names(String currentLine) {
        String[] seperated = currentLine.split(" ");
        String fullNameCased = "";
        String date = dateFormat(seperated[seperated.length - 1]);
        int loopCounter = 0;

        for (int x = 0; x < seperated.length - 1; x++) {

            //this IF detects if it's working with a Middle name and adds on the full stop.
            if(seperated.length == 4 && x == 1)
                {
                    fullNameCased = fullNameCased + " " + NameCaser(seperated[x] + ".");
                }
            else
                {
                    fullNameCased = fullNameCased + " " + NameCaser(seperated[x]);
                }
                loopCounter++;

        }

        fullNameCased = fullNameCased + " " + date;

        return fullNameCased;

    }

    //substring sequence that cuts up the date and reformats it as a new String in a dd/MM/yyyy format. Didn't use the date functions of Java cause they were annoying me.
    public static String dateFormat(String date) {

        String dd = date.substring(0,2);
        String MM = date.substring(2, 4);
        String yyyy = date.substring(4);

        String finalDate = dd + "/" + MM + "/" + yyyy;

        return finalDate;

        }

     /*
     * In charge of adding the upper case to the start of the names, including
     * capitalising the middle name letter Using substrings to slice the first
     * letter off the front and stick an upper case one back on.
     */
    public static String NameCaser(String currentLine) {

        String finalName;
        String NameLetter = currentLine.substring(0, 1).toUpperCase();

        // Determines the first name by creating a substring of everything after the
        // first letter and before the space.
        try {
            String NameCut = currentLine.substring(1);
            finalName = NameLetter + NameCut;
        } catch (Exception e) {
            finalName = NameLetter;
        }

        return finalName;
    }


}