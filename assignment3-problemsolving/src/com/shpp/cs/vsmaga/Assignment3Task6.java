package com.shpp.cs.vsmaga;

/* TODO: This program simulates  5 seconds of the game Pacman
* */
import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Task6 extends WindowProgram {
    /* Constants controlling size of window*/
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 300;

    /*constants controlling time between frames and total time of animation*/
    private static final double PAUSE_TIME = 3.5;
    private static final int ANIMATION_TIME = 5000;

    /*pacman radius and start position*/
    private static final double PACMAN_RADIUS = 50.0;
    private static final double PACMAN_START_X =  -50.0;

    /*constants controlling degrees of pacman's closed mouth*/
    private static final int PACMAN_CLOSE_START = 3;
    private static final int PACMAN_CLOSE_SWEEP = 357;

    /*constants controlling degrees of pacman's opened mouth*/
    private static final int PACMAN_OPEN_START = 30;
    private static final int PACMAN_OPEN_SWEEP = 300;

    /*constants controlling radius of points and it's number in game world*/
    private static final double POINT_RADIUS = 5;
    private static final int POINTS_NUM = 10;

    /*constants controlling width of wall in maze and it's offset from border*/
    private static final double MAZE_WIDTH = 30;
    private static final double MAZE_OFFSET = 30;


    /* This methods creates game world and made pacman to eat points
    * */
    public  void run(){
        setBackground(Color.BLACK);
        GArc pacman = getPacman(PACMAN_START_X, (getHeight() / 2) - PACMAN_RADIUS, PACMAN_RADIUS);
        add(pacman);
        drawPointLine();
        drawMaze();
        runPacman(pacman);
    }

    /* This method simulates pacman's movement and how it eats points
    * */
    private void runPacman(GArc pacman) {


        for (int i = 0; i <= ANIMATION_TIME / (PAUSE_TIME *2); i++){ // limiting the animation to 5 sec.

            closeMouth(pacman);
            pause(PAUSE_TIME);
            openMouth(pacman);
            pause(PAUSE_TIME);
            pacman.move(1, 0);
            checkAndEatPoint(pacman);

        }
    }

    /* Check whether there is a point in the mouth and eats it
    * */
    private void checkAndEatPoint(GArc pacman) {
        GObject point = getElementAt(pacmanMouth(pacman));
        if(point != null){
            remove(point);

        }
    }

    /* Returns the coordinates of pacman mouth
    * */
    private GPoint pacmanMouth(GArc pacman) {
        GPoint mouth = new GPoint((pacman.getX()+(PACMAN_RADIUS *2)+1), pacman.getY() + PACMAN_RADIUS);
        return mouth;
    }

    /* Open pacman's mouth
    * */
    private void openMouth(GArc pacman) {
        pacman.setStartAngle(PACMAN_OPEN_START);
        pacman.setSweepAngle(PACMAN_OPEN_SWEEP);
    }

    /* Close pacman's mouth
    * */
    private void closeMouth(GArc pacman) {
        pacman.setStartAngle(PACMAN_CLOSE_START);
        pacman.setSweepAngle(PACMAN_CLOSE_SWEEP);
    }

    /* Draws a maze with two walls...
    * */
    private void drawMaze() {
        drawWall(MAZE_OFFSET, MAZE_OFFSET);
        drawWall(MAZE_OFFSET, getHeight() - MAZE_OFFSET - MAZE_WIDTH);
    }

    /* Draws a wall with recived coordinates and specified size
    * */
    private void drawWall(double x, double y) {
        GRect wall = new GRect(x, y, getWidth() - (MAZE_OFFSET*2), MAZE_WIDTH);
        wall.setFilled(true);
        wall.setFillColor(Color.BLUE);
        add(wall);
    }

    /* Draws lie of points with specified number of points
    * */
    private void drawPointLine() {
        for(int i =0; i <POINTS_NUM; i++){
            drawPoint(i);
        }
    }

    /* Draws point with recived order
    * */
    private void drawPoint(int i) {
        double x = 0;
        double y = 0;
        x = (getWidth() / (POINTS_NUM + 1))+((getWidth() / (POINTS_NUM + 1))) * i;
        y = (getHeight() / 2) - POINT_RADIUS;
        GOval point = new GOval(x, y, POINT_RADIUS*2, POINT_RADIUS*2);
        point.setFilled(true);
        point.setFillColor(Color.WHITE);
        add(point);
    }

    /* Returns Pacman
    * */
    private GArc getPacman(double x, double y, double radius) {
        GArc pacman = new GArc(x, y, radius*2, radius*2, PACMAN_CLOSE_START, PACMAN_CLOSE_SWEEP);
        pacman.setFilled(true);
        pacman.setFillColor(Color.YELLOW);
        pacman.setColor(Color.BLACK);
        return pacman;
    }


}
