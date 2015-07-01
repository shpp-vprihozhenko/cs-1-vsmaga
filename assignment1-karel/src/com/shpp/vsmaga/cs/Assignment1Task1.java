package com.shpp.vsmaga.cs;

/*Karel lives in a square house.
*Karel starts in the north-west corner of his house, and he needs to pick up a newspaper,
* which lies on the doorstep of his house.
*After the selection of a newspaper must go back to its start position.
* */

import com.shpp.karel.KarelTheRobot;

public class Assignment1Task1 extends KarelTheRobot {

    public void run() throws Exception {
        CollectNewsPaper();
    }

    /*Preconditions: Karel is located in northwest corner of his house facing east.
    *Newspaper is located on the doorstep.
    *Result: Karel take a newspaper and located at start position.
    * */
    private void CollectNewsPaper() throws Exception {
        goToNewspaper();
        pickNewspaper();
        backToStart();
    }

    /*Preconditions: Karel is located in northwest corner of his house facing east.
    *Result: Karel located at the exit of his house facing to newspaper
    * */
    private void goToNewspaper() throws Exception  {
        goToWall();
        turnRight();
        goToExit();
        turnLeft();
    }

    /*Preconditions: Karel is watching to wall.
    *Result: Karel is located at this wall
    * */
    private void goToWall() throws Exception{
        while (frontIsClear()){
            move();
        }
    }

    /*Preconditions: Karel is located at northeast corner of his house facing south.
    *Result: Karel is located at the exit of his home facing south
    * */
    private void goToExit() throws Exception{
        while (leftIsBlocked()){
            move();
        }
    }

    /*Preconditions: Karel is located at the exit of his house facing east.
    *Result: Karel is located at the start position with the newspaper
    * */
    private void pickNewspaper() throws Exception{
        findNewspaper();
        pickBeeper();
        turnAround();
        goHome();

    }

    /*Preconditions: Karel is located at the exit of his house facing east.
    *Result: Karel is located in the cell with beeper (newspaper)
    * */
    private void findNewspaper() throws Exception{
        while(noBeepersPresent()){
            move();
        }
    }

    /*Preconditions: Karel is located in the cell, where was beeper facing west.
    *Result: Karel is located at the exit of his house facing north
    * */
    private void goHome() throws Exception{
        while (rightIsClear()){
            move();
        }
        move();
        turnRight();
    }

    /*Preconditions: Karel is located at the exit of his house facing north.
    *Result: Karel is located at start position.
    * */
    private void backToStart() throws Exception {
        goToWall();
        turnLeft();
        goToWall();
        turnAround();
    }

    /*Preconditions: none
    *Result: Karel turns right.
    * */
    private void turnRight() throws Exception  {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    /*Preconditions: none
    *Result: Karel turns around.
    * */
    private void turnAround() throws Exception  {
        turnLeft();
        turnLeft();
    }

}