package com.shpp.cs.vsmaga;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Assignment5Task4 extends TextProgram{

    private static final String CSV_FILE = "test.csv";
    private static final int NUMBER_OF_COLUMN = 1;

    public void run() {
        ArrayList<String> column = extractColumn(CSV_FILE, NUMBER_OF_COLUMN );

        for (int i = 0; i < column.size(); i++) {
            println("[" + column.get(i) + "] ");
        }
    }

    private ArrayList<String> extractColumn(String csvFile, int numberOfColumn) {

        ArrayList<ArrayList<String>>csv = parseAllCsv(csvFile);
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < csv.size(); i++){
            result.add(csv.get(i).get(numberOfColumn));
        }
        return result;
    }

    private ArrayList<ArrayList<String>> parseAllCsv(String csvFile) {
        ArrayList<String> buffer = createCsvLines(csvFile);
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < buffer.size(); i++){

            result.add(fieldsIn(buffer.get(i)));
        }

        return result;
    }

    /*Tokenizes the input CSV file line and returns all the fields in that line in array.
    * */
    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> fields = new ArrayList<String>();
        int start = 0; //start position for every new cell
        int end = 0; // end position for every new cell
        while (end != -1) { //repeat while not the end of the line
            if (line.charAt(start) != '"') { //condition for cell without symbol "
                end = line.indexOf(',',start);
                if (end != -1) {
                    fields.add(line.substring(start, end)); // cut a part of the string between START and END

                } else {
                    fields.add(line.substring(start)); // cut a part of the string from START to the end of line

                }
                start = end +1 ;

            } else if (line.charAt(start) == '"') { //condition for cell with symbol "
                end = line.indexOf("\",", start);
                if (end != -1) {
                    fields.add(line.substring(start + 1, end)); // cut a part of the string between START and END

                } else {
                    fields.add(line.substring(start+1, line.length() - 1)); // cut a part of the string from START to the end of line
                }
                start = end + 2 ;
            }
        }
        return fields;
    }

    /*  Read received file to array */
    private ArrayList<String> createCsvLines(String csvFile) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            BufferedReader file = new BufferedReader(new FileReader(csvFile));
            int i = 0;
            while (true){
                String word = file.readLine();
                result.add(word);
                i++;
                if (word == null){
                    break;
                }
            }
            result.remove(result.size() - 1); //delete last "Null" element

        } catch (IOException e){
            println("Error reading file!!!");

        }
        return result;
    }
}
