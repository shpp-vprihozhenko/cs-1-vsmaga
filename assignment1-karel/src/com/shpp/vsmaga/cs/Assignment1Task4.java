package com.shpp.vsmaga.cs;

/* In this program Karel builds a chessboard in different worlds using beepers.
* */

import com.shpp.karel.KarelTheRobot;

public class Assignment1Task4 extends KarelTheRobot {

    public void run() throws Exception {
        chessKarel();

    }

    /*Preconditions: Karel is located at the southwest corner facing east.
    *Result: Beepers are in chessboard order over the world.
    * */
    private void chessKarel()throws Exception{
        if(frontIsClear()) {
            makeFirstLine(); //for worlds with one ore more lines
            while (frontIsClear()) {
                move();
                turnRight();
                makeSecondLine();
                if (frontIsClear()) {
                    move();
                    turnRight();
                    makeFirstLine();

                }
            }
        }
        else {
            if (leftIsClear()){ //for world with only one column
                turnLeft();
                makeFirstLine();
            }
            else {
                putBeeper(); //for world with only one cell
            }
        }
    }



    /*Preconditions: Karel is located at the beginning of line
    *Result: Karel builds line beginning with a beeper
    * */
    private void makeFirstLine() throws Exception {
        while (frontIsClear()) {
            putBeeper();
            move();
            if (frontIsClear()){
                move();

            }
        }
        turnAround();
        move();
        turnAround();
        buildLastCell();
        goHome();
        turnRight();

    }

    /*Preconditions: Karel is located at the beginning of line
    *Result: Karel builds line beginning without a beeper.
    * */
    private void makeSecondLine()throws Exception{
        while (frontIsClear()) {
            move();
            if (frontIsClear()){
                putBeeper();
                move();
            }
        }
        turnAround();
        move();
        turnAround();
        buildLastCell();
        goHome();
        turnRight();
    }

    /*Preconditions: karel is located in penultimate cell.
    *Result: Karel builds the last cell correctly
    * */
    private void buildLastCell() throws Exception{
        if (noBeepersPresent()){
            move();
            putBeeper();
        }
        else {
            move();
        }
    }

    /*Preconditions: Karel is located at the end of line facing east.
    *Result: Karel is located at the start of line facing west.
    * */
    private void goHome() throws Exception{
        turnAround();
        while (frontIsClear()){
            move();
        }

    }

    /*Preconditions: none
    *Result: Karel turns around.
    * */
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

    /*Preconditions: none
    *Result: Karel turns right.
    * */
    private void turnRight()throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

}