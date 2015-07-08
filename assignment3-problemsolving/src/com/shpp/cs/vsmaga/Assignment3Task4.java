package com.shpp.cs.vsmaga;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Task4 extends WindowProgram {

    /* Constants controlling size of window
    * */
    public static final int APPLICATION_WIDTH = 700;
    public static final int APPLICATION_HEIGHT = 500;

    /* The width and height of each brick. */
    private static final int BRICK_WIDTH = 100;
    private static final int BRICK_HEIGHT = 50;

    /* Number of brick in base level */
    private static final int BRICKS_IN_BASE = 6;

    public void run() {

        int vertOffset = 1; // vertical offset for every new level from the flor

        for (int i = BRICKS_IN_BASE; i >= 1; i--){
                drawLevel(i, vertOffset);
                vertOffset++;
        }
    }

    /* This method draws level of bricks with recived number of bricks and vertical offset in the center of window
    * */
    private void drawLevel(int i, int vertOffset) {

        int horOffset = 0; //horizontal offset for every new brick from the first brick

        for (int j = i; j >= 1; j--){
            double x = ((getWidth() - (BRICK_WIDTH * i)) / 2) + (BRICK_WIDTH * horOffset); // x coordinate for every new brick
            double y = getHeight() - (BRICK_HEIGHT * vertOffset); // y coordinate for every new brick
            drawBrick(x, y);
            horOffset++; //offset for next brick in level
        }

    }

    /*This method draws rectangle with received coordinates and specified size
    * */
    private void drawBrick(double x, double y) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFillColor(Color.YELLOW);
        brick.setFilled(true);
        add(brick);
    }
}
