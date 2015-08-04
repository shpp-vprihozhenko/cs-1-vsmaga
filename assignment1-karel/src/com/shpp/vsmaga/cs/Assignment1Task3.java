package com.shpp.vsmaga.cs;

/* In this program Karel find the center of the southern line.
* */

import com.shpp.karel.KarelTheRobot;

public class Assignment1Task3 extends KarelTheRobot {

    public void run() throws Exception {
        FindCenterKarel();
    }


    /*Preconditions: Karel is located in southwest corner of his world facing east.
    *Result: In the center of the southern line located beeper, no another beepers in the world
    * */
    private void FindCenterKarel() throws Exception {
        createDiagonalLineFromStartToOppositeWall();
        goToWall();
        turnAround();
        moveDiagonallyDownToBeeper();
        moveDownToCenterOfLine();
        putBeeper();
        turnRight();
        moveToStart();
        cleanupDiagonalLine();
    }

    /*Preconditions: none.
    *Result: Karel turns around.
    * */
    private void turnAround() throws Exception{
        turnLeft();
        turnLeft();
    }

    /*Preconditions: Karel is located in southwest corner of his world facing east.
    *Result: Karel building two parallel lines diagonally to the opposite wall. Karel facing west
    * */
    private void createDiagonalLineFromStartToOppositeWall() throws Exception  {
        putBeeper();
        while (frontIsClear()){
            move();
            turnLeft();
            move();
            putBeeper();
            turnLeft();
            move();
            putBeeper();
            turnAround();
            move();
        }
        turnAround();
    }


    /*Preconditions: Karel looking to wall.
    *Result: Karel is near a wall, which he looked.
    * */
    private void goToWall() throws Exception  {
        while (frontIsClear()) {
            move();
        }
    }


    /*Preconditions: Karel is located in northwest corner of his world facing east.
    *Result: Karel moving diagonally down and stopped at the crossing one of the diagonal lines facing east
    * */
    private void moveDiagonallyDownToBeeper()  throws Exception  {
        while (noBeepersPresent()){
            move();
            turnRight();
            move();
            turnLeft();
        }

    }


    /*Preconditions: Karel is located at the crossroads of the two diagonals over the center facing east.
    *Result: Karel is located in one of the central cells of southern line.
    * */
    private void moveDownToCenterOfLine()  throws Exception {
        turnRight();
        goToWall();

    }

    /*Preconditions: none
    *Result: Karel turns right.
    * */
    private void turnRight()  throws Exception  {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    /*Preconditions: Karel is located at the center of southern line facing west.
    *Result: Karel is located at start position.
    * */
    private void moveToStart()  throws Exception  {
        goToWall();
        turnAround();

    }

    /*Preconditions: Karel is located in southwest corner of his world facing east.
    *Result: Karel cleaning two parallel lines diagonally to the opposite wall. Karel facing west
    * */
    private void cleanupDiagonalLine()  throws Exception  {
        pickBeeper();
        while (frontIsClear()){
            move();
            turnLeft();
            move();
            pickBeeper();
            turnLeft();
            move();
            pickBeeper();
            turnAround();
            move();
        }


    }


}