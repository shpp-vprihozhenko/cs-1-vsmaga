package com.shpp.cs.vsmaga;

/* This program asks the user for three letters
*  and then displays words from English dictionary that can be composed of these letters
* */

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Assignment5Task3 extends TextProgram {

    /* Path and name for dictionary file*/
    private static final String DICTONARY_FILE = "en-dictionary.txt";

    /* Number of letters to ask the user*/
    private static final int NUMBER_OF_LETTERS = 3;

    /* This method ask the user for three letters and finds the right words in infinity loop*/
    public void run(){
        ArrayList<String> dict = readDictonary(); // read file to  array
        String word = "";
        while (true){
            word = readLine("Enter " + NUMBER_OF_LETTERS + " letters: ");
            word = filterString(word);
            if (word.length() != NUMBER_OF_LETTERS){
                println("You have entered wrong number of letters");
            }
            else {
                playGame(word, dict);
            }
        }
    }

    /* This method finds the words from the array that composed of the letters of received string.
    *  It find the the first letter in the first element of array, then if it finds find the second
    *  letter from this place and same with the third letter. If all three letters find in element
    *  it displays this element and goes goes to the next element of array
    *  */
    private void playGame(String word, ArrayList<String> dict) {
        int pos = -1;
        int counter = 0;
        for(String elem: dict){

                for (int i = 0; i < NUMBER_OF_LETTERS; i++) {
                    pos = elem.indexOf(word.charAt(i), pos +1);
                    if (pos == -1) {
                        break;
                    }
                    if (i == NUMBER_OF_LETTERS - 1) {
                        println(elem);
                        counter++;
                        pos = -1;
                    }
                }

        }
        println(counter + " words found");

    }

    /* Read received file to array*/
    private ArrayList<String> readDictonary() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            BufferedReader file = new BufferedReader(new FileReader(DICTONARY_FILE));
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

    /* Delete all symbols that is not letters and transform them to lowercase */
    private String filterString(String word) {
        String res = "";
        for (int i = 0; i < word.length(); i++){
            if (Character.isLetter(word.charAt(i))){
                res += Character.toLowerCase(word.charAt(i));
            }
        }
        return res;

    }
}
