package com.shpp.cs.vsmaga;

        /* TODO: This program draws a grid of black squares in the center of the window with
        */

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Task5 extends WindowProgram {

    /* Constants controlling size of window
    * */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 400;

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    public void run() {

        double xStart = (getWidth() - ((NUM_COLS*BOX_SIZE) + ((NUM_COLS-1) *BOX_SPACING))) / 2 ; // starting coordinate x
        double yStart = (getHeight() - ((NUM_ROWS*BOX_SIZE) + ((NUM_ROWS-1)*BOX_SPACING))) / 2 ; // starting coordinate y
        double x = xStart; // coordinate x of every next box
        double y = yStart; // coordinate y of every next box

        for (int i=0; i < NUM_COLS; i++) {
            for (int j=0; j < NUM_ROWS; j++){
                x = xStart + ((BOX_SIZE + BOX_SPACING) * i);
                y = yStart + ((BOX_SIZE + BOX_SPACING) * j);
                drawBox(x, y);

            }


        }

    }

    /* This method draws a black box with specified coordinates
    * */
    private void drawBox(double x, double y) {
        GRect box = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        box.setFilled(true);
        box.setFillColor(Color.BLACK);
        add(box);
    }


}