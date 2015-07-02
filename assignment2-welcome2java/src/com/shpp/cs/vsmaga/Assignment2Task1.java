package com.shpp.cs.vsmaga;

/*This program solves the quadratic equation
* */

import com.shpp.cs.a.console.TextProgram;

public class Assignment2Task1 extends TextProgram {

    public void run() {
        rootsQuadraticEquation();
    }

    /*
    * This method receives from the user three coefficients and gives solutions of the quadratic equation
    * */
    private void rootsQuadraticEquation() {
        double a = readDouble("Enter a: ");
        double b = readDouble("Enter b: ");
        double c = readDouble("Enter c: ");
        double d = calculateDiscriminant(a, b, c);
        double sqrD = calculateSqrDiscriminant(d);

        if (d>0){
            calculateAndPrintTwoRoots(a, b, c, sqrD);

        }
        else if (d==0 ){
            calculateAndPrintOneRoot(a, b);

        }
        else {
            printNoRoots();
        }

    }

    /*
    * This method receives three coefficients and returns the discriminant of the quadratic equation
    * */
    private double calculateDiscriminant(double a, double b, double c) {
        double d = (b*b)-(4*a*c);
        return d;
    }

    /*
    * This method receives the discriminant of the quadratic equation and returns it's square root
    * */
    private double calculateSqrDiscriminant(double d) {
        double sqrD = Math.sqrt(d);
        return sqrD;
    }

    /*
    * This method receives three coefficients and square root of the discriminant.
    * Then calculates and displays two roots of quadratic equation
    * */
    private void calculateAndPrintTwoRoots(double a, double b, double c, double sqrD) {
        double x1 = ((b*(-1))+sqrD)/(2*a);
        double x2 = ((b*(-1))-sqrD)/(2*a);
        print("There are two roots: "+x1+" and "+x2);

    }

    /*
    * This method receives two coefficients.
    * Then calculates and displays one root of quadratic equation
    * */
    private void calculateAndPrintOneRoot(double a, double b) {
        double x = (b/(2*a))*(-1);
        print("There is one root: "+x);
    }

    /*
    * This method shows the message that there are no root of quadratic equation
    * */
    private void printNoRoots() {
        print("There are no real roots");
    }



}
