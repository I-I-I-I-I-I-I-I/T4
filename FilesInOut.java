import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.lang.Number;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.*;

/**
 * 
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOut {

    public static void main(String[] args) throws FileNotFoundException {

        String path = System.getProperty("user.dir");
        File fileIn = null;
        File fileOut = null;

        /*
         * This block of 'ifs' and 'trys' checks for the "-u" modifier, then tries to
         * find the files in positions 1 & 2 instead if the modifier is present The '-u'
         * in args[0] (if present), is never actually used to modify anything until the
         * block starting at LINE 62
         */
        if (args[0].equals("-u")) {
            try {
                fileIn = new File(path + "\\" + args[1]);
                fileOut = new File(path + "\\" + args[2]);
            } catch (Exception e) {
                System.err.println(
                        "Invalid Command line Arguments : ('[Optional Modifier] , [input file] , [output file]')");
            }

        } else {
            try {
                fileIn = new File(path + "\\" + args[0]);
                fileOut = new File(path + "\\" + args[1]);
            } catch (Exception e) {
                System.err.println(
                        "Invalid Command line Arguments : ('[Optional Modifier] , [input file] , [output file]')");
            }

        }

        Scanner fileScan = new Scanner(fileIn);
        PrintWriter fileWrite = new PrintWriter(fileOut);

        // Scans through every line until there is no more line left to be scanning.
        while (fileScan.hasNext()) {
            String currentLine = fileScan.nextLine();

            if (args[0].equals("-u")) {
                fileWrite.write(names(currentLine).toUpperCase() + "\n");
            } else {
                fileWrite.write(names(currentLine) + "\n");
            }
        }

        fileWrite.close();
        fileScan.close();

    } // main

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

    //substring sequence that cuts up the date and reformats it as a new String in a dd/MM/yyyy format. Didn't use the date functions of Java cause they were annoying me.
    public static String dateFormat(String date) {

            String dd = date.substring(0,2);
            String MM = date.substring(2, 4);
            String yyyy = date.substring(4);

            String finalDate = dd + "/" + MM + "/" + yyyy;

            return finalDate;

            }

} // FilesInOut
