package com.shpp.cs.vsmaga;

/* TODO: This program draws a caterpillar with specified number of segments
* using random colors of segments
*  */

import acm.graphics.GOval;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Task6 extends WindowProgram {

    /* Constants controlling size of window
    * */
    public static final int APPLICATION_WIDTH = 640;
    public static final int APPLICATION_HEIGHT = 165;

    /* Constant controlling diameter of the drawing circles  */
    private static final int CIRCLE_DIAMETER = 100;

    /* Constants controlling offset of drawing circles*/
    private static final int X_OFFSET = 60;
    private static final int Y_OFFSET = 20;

    /* Constants controlling starting coordinates of drawing circles*/
    private static final int X_START = 0;
    private static final int Y_START = 20;

    /* Number of circles*/
    private static final int CIRCLES_NUM = 10;

    /* Create random generator*/
    private RandomGenerator rgen = RandomGenerator.getInstance();

    public void run() {

        double x = X_START; // X for every new circle
        double y = Y_START; // Y for every new circle
        double yOffset = Y_OFFSET; // offset, which will change in a cycle to (+/- 20) relative to the start
        y = Y_START + yOffset; // change Y on the +20 immediately

        for (int i = 0; i<CIRCLES_NUM; i++){
            drawCircle(x, y); //drawing circle
            x = x + X_OFFSET; // set the coordinate X for a new circle
            yOffset = yOffset * (-1); // change offset to +/- (up/down circle)
            y = Y_START + yOffset; //// set the coordinate Y for a new circle
        }
    }

    /*This method draws circle with received coordinates, specified diameter and random color
    * */
    private void drawCircle(double x, double y) {

        GOval circle = new GOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        circle.setFilled(true);
        circle.setColor(rgen.nextColor());
        circle.setFillColor(rgen.nextColor());
        add(circle);
    }


}