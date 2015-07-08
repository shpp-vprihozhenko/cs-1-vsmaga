package com.shpp.cs.vsmaga;

/* TODO: This program takes from the user two numbers BASE and POWER, and outputs the result of BASE in power of POWER
* */

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Task3 extends TextProgram {

    /* This method taker from user numbers and then outputs the result of exponentiation
    * */
    public void run () {
        double base = readDouble("Enter base: ");
        int power = readInt("Enter power: ");
        double exp = raiseToPower(base, power);
        print("Number " + base + " in power of " + power + " is " + exp);

    }

    /* This method raises to power different numbers with + and - power
    * */
    private double raiseToPower(double base, int power) {
        double exp = base;

        if (power > 0){ // if power is +

          exp = raiseToPowerPlus(base, power);
        }
        else if (power < 0){
            exp = raiseToPowerMinus(base, power); // if power is -
        }
        else if (power == 0){ // of power is 0
            exp = 1;
        }
        return exp;
    }

    /* This method raises to power numbers with +power
    * */
    private double raiseToPowerPlus(double base, int power) {
        double exp = base;
        for (int i=1; i<power; i++){
            exp = exp * base;
        }
        return exp;
    }

    /* This method raises to power numbers with -power
    * */
    private double raiseToPowerMinus(double base, int power) {
        double exp = base;
        power = power * (-1);
        exp = 1 / (raiseToPowerPlus(base, power));
        return exp;
    }
}
