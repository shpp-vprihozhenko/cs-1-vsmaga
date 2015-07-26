package com.shpp.cs.vsmaga;

/* TODO: This program display number of syllables in word
* */

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Task1 extends TextProgram{
    public void run(){
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */

        while (true) {
            String word = readLine("Enter a single word or type END to exit the program: ");
            word = filterString(word);
            println("  Syllable count: " + syllablesIn(word));
            if (word.equals("end")){
                break;
            }
        }
    }

    /*Filter received string - return only letters of the string in lowercase*/
    private String filterString(String word) {
        String res = "";
        for (int i = 0; i < word.length(); i++){
            if (Character.isLetter(word.charAt(i))){
                res += Character.toLowerCase(word.charAt(i));
            }
        }
        return res;

    }

    /* Returns number of syllables in the received word*/
    private int syllablesIn(String word) {
        int counter = 0; //counter for syllables
        String vowel = "aeiouy"; //vowel letters of the English alphabet

        for (int i = 0; i < word.length(); i++){
            if (vowel.contains(Character.toString(word.charAt(i)))) { //if current letter is vowel

                /*If current letters is before penultimate letter and next is not vowel*/
                if (i < word.length() - 1 && !vowel.contains(Character.toString(word.charAt(i+1)))){
                    counter++;
                    
                /*If current letters is penultimate and next is vowel*/
                } else if(i == word.length() - 2 && vowel.contains(Character.toString(word.charAt(i+1)))){
                    counter++;

                /*If current letters is final and not 'E'*/
                } else if (i == word.length() - 1 && word.charAt(i) != 'e'){
                    counter++;
                }
            }
        }

        if (counter == 0){
            return 1;
        }
        return counter;
    }
}