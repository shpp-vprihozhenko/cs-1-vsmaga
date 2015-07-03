package com.shpp.cs.vsmaga;

        /* TODO: This program draws a flag of Lithuania in the center of the window
        * and draws description in southeastern corner of the window
        */

import acm.graphics.GLabel;
import acm.graphics.GRect;

import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Task4 extends WindowProgram {

    /* Constants controlling size of window
    * */
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 600;


    /* Constants specifies the colors of the Lithuanian flag
    * */
    private static final Color LIT_YELLOW = new Color(252, 209, 22);
    private static final Color LIT_GREEN = new Color(0, 150, 110);
    private static final Color LIT_RED = new Color(214, 38, 18);


    /* Constants specifies size of flag
    * */
    private static final double FLAG_WIDTH = 500;
    private static final double FLAG_HEIGHT = 300;


    /* Constants specifies number of colors in flag
    * */
    private static final double NUMBER_OF_COLORS = 3;


    /* Constants specifies down and right padding of label
    * */
    private static final double LABEL_PADDING = 10;

    public void run() {
        double x = (getWidth() - FLAG_WIDTH)/2;
        double y = (getHeight() - FLAG_HEIGHT)/2;
        drawFlag(x, y);
        drawLabel();

    }



    /* This method draws Lithuanian flag with specified coordinates
    * */
    private void drawFlag(double x, double y) {
        drawYellow(x, y);
        drawGreen(x, y);
        drawRed(x, y);
    }

    /* This method draws yellow part of flag
    * */
    private void drawYellow(double x, double y) {
        drawRectangle(x, y, FLAG_WIDTH, (FLAG_HEIGHT / NUMBER_OF_COLORS), LIT_YELLOW);
    }

    /* This method draws green part of flag
    * */
    private void drawGreen(double x, double y) {
        y=y+(FLAG_HEIGHT / NUMBER_OF_COLORS);
        drawRectangle(x, y, FLAG_WIDTH,(FLAG_HEIGHT / NUMBER_OF_COLORS), LIT_GREEN);
    }

    /* This method draws red part of flag
    * */
    private void drawRed(double x, double y) {
        y=y+((FLAG_HEIGHT / NUMBER_OF_COLORS) * 2);
        drawRectangle(x, y, FLAG_WIDTH,(FLAG_HEIGHT / NUMBER_OF_COLORS), LIT_RED);

    }

    /* This method draws rectangle with specified coordinates, size and color
    * */
    private void drawRectangle(double x, double y, double width, double height, Color litColor) {

        GRect rect = new GRect(x, y, width, height);
        rect.setFilled(true);
        rect.setFillColor(litColor);
        rect.setColor(litColor);
        add(rect);

    }

    /* This method draws label "Flag of Lithuania" in southeastern corner of the window
    * */
    private void drawLabel() {
        GLabel label = new GLabel("Flag of Lithuania");
        label.setFont("Arial-25");
        double x = getWidth() - label.getWidth() - LABEL_PADDING;
        double y = getHeight() - label.getDescent() - LABEL_PADDING;
        add(label, x, y);
    }



}