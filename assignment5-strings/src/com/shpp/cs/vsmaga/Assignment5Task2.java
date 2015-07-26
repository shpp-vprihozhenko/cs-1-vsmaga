package com.shpp.cs.vsmaga;

/* This program adds two positive integers of any length
* */

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Task2 extends TextProgram{
    public void run(){
        /* Sit in a loop, reading numbers and adding them. */
        while (true){
            String n1 = readLine("Enter the first number: ");
            String n2 = readLine("Enter the second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));

        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        /*find a short string and give it on the alignment*/
        if (n1.length() > n2.length()){
            n2 = alignString(n1, n2);
        }
        else {
            n1 = alignString(n2, n1);
        }

        return addEqualLengthNumericStrings(n1, n2);
    }

    /* Adds two string representations of nonnegative integers with equal length.
    *  Return the result in string format
    * */
    private String addEqualLengthNumericStrings(String n1, String n2) {
        String result = "";
        int inMind = 0;
        int sum = 0;

        for (int i = n1.length()- 1; i >=0; i--){

            int intN1 = Character.getNumericValue(n1.charAt(i)); //convert characters into number
            int intN2 = Character.getNumericValue(n2.charAt(i));

            sum = intN1 + intN2 + inMind;
            inMind = 0;

            if (sum >= 10){
                inMind = 1;
                result = Integer.toString(sum - 10) + result;
            } else {
                result = Integer.toString(sum) + result;
            }
        }
        if (inMind == 1){
            result = Integer.toString(inMind) + result; //convert numbers into string
        }
        return result;
    }

    /*This method aligns two lines adding to the beginning of the short zeros*/
    private String alignString(String longStr, String shortStr) {
        int length = longStr.length() - shortStr.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                shortStr = "0" + shortStr;
            }
        }
        return shortStr;
    }
}
