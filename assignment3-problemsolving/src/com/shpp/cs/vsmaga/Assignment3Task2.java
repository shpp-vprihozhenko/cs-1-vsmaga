package com.shpp.cs.vsmaga;

/* TODO: This program gives some positive integer number - N
* if N - even, divide it by 2
* if N - is odd, then multiply by 3 and add 1
* and continue this process until N is equal to 1
**/

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Task2 extends TextProgram{

    public void run(){

        int n = readInt("Enter a positive integer number N: ");
        if (n > 0){ // check that number is positive
        hailstoneNumber(n);
        }
        else {
            println("You enter a wrong number!!!");
        }

    }

    /*This method receives the number and applies to it formulas described in task
    * */
    private void hailstoneNumber(int n) {
        int oldN = 0; //for saving the previous value of N for output in message
        while (n != 1){
            if (n%2 != 0) { // if N is odd

                oldN = n; // save the previous value
                n = (n *3) + 1; //
                println(oldN + " is odd so I make 3n + 1: " + n);

            } else { // if N is even

                oldN = n; // save the previous value
                n = n / 2;
                println(oldN + " is even so I take half: " + n);
            }
        }
        print("The end...");
    }
}
