package com.shpp.cs.vsmaga;

/* This program simulates St. Petersburg lottery
* */

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

public class Assignment3Task5 extends TextProgram {

    /* Size of the bank for the end of the game*/
    private static final int MAX_BANK = 20;

    /* Starting bet*/
    private static final int START_BET = 1;

    private RandomGenerator rgen = RandomGenerator.getInstance();

    public void run (){

        int bank = 0; //bank of each new round of the game
        int totalBank = 0; //total bank
        int counter = 0; // counter for number of rounds

        while(totalBank < MAX_BANK){ // plays a rounds of game while total < 20$ and prints notifications
            bank = playGame();
            totalBank = totalBank + bank;
            println("This game, you earned "+bank+"$");
            println("Your total is "+totalBank+"$");
            counter++;
        }

        println("It took "+counter+" games to earn "+MAX_BANK+"$");

    }

    /* This method plays one round of St. Petersburg game
    * */
    private int playGame() {

        int bank = START_BET; // start is 1$

        while (rgen.nextInt(2) == 1){ // while falls heads (1) increase the bank twice
            bank *= 2;
        }

        return bank;
    }

}
