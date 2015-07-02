package com.shpp.cs.vsmaga;
/*
* This program draws four circles in each corner of the window and the rectangle,
* the angles of which pass through the centers of the circles.
* */

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Task2 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;
    private static final double CIRCLE_DIAMETER = 100;

    /*
    * This method draws four circles in each corner of the window and the rectangle,
    * the angles of which pass through the centers of the circles.
    * */
    public void run() {
        double widthWindow = getWidth();
        double heightWindow = getHeight();

        addNorthWestCircle(0, 0, CIRCLE_DIAMETER);
        addNorthEastCircle(widthWindow - CIRCLE_DIAMETER, 0, CIRCLE_DIAMETER);
        addSouthWestCircle(0, heightWindow - CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        addSouthEastCircle(widthWindow - CIRCLE_DIAMETER, heightWindow - CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        addRectangle(CIRCLE_DIAMETER /2, CIRCLE_DIAMETER /2, widthWindow - CIRCLE_DIAMETER, heightWindow - CIRCLE_DIAMETER);

    }


    /*
    *This method draws the circle in northwest corner of the window
    * */
    private void addNorthWestCircle(double x, double y, double diameter) {
        drawCircle(x, y, diameter);
    }

    /*
    *This method draws the circle in southwest corner of the window
    * */
    private void  addSouthWestCircle(double x, double y, double diameter) {
        drawCircle(x, y, diameter);
    }

    /*
    *This method draws the circle in northeast corner of the window
    * */
    private void addNorthEastCircle(double x, double y, double diameter) {
        drawCircle(x, y, diameter);
    }

    /*
    *This method draws the circle in southeast corner of the window
    * */
    private void  addSouthEastCircle(double x, double y, double diameter) {
        drawCircle(x, y, diameter);
    }



    /*
    *This method draws the black circle with specified coordinates and diameter
    * */
    private void drawCircle(double x, double y, double diameter) {
        GOval circle = new GOval(x, y, diameter, diameter);
        circle.setFilled(true);
        Color color = Color.BLACK;
        circle.setColor(color);
        add(circle);
    }

    /*
    * This method draws a white rectangle with specified coordinates and size
    * */
    private void addRectangle(double x, double y, double width, double height) {
        GRect rect = new GRect(x, y, width, height);
        Color color = Color.WHITE;
        rect.setFilled(true);
        rect.setColor(color);
        add(rect);
    }


}