package com.shpp.vsmaga.cs;

/* In this program Karel repair poles, which are located every four cells
* */

import com.shpp.karel.KarelTheRobot;

public class Assignment1Task2 extends KarelTheRobot {

    public void run() throws Exception {
        buildingPolesKarel();

    }

    /*Preconditions: Karel is located in southwest corner of his world facing east.
    *Result: Karel repair all poles and located at the top of the last pole facing north.
    * */
    private void buildingPolesKarel() throws Exception{
        while (frontIsClear()){
            repairPole();
            goDown();
            goToNextPole();
        }
        repairPole();
    }

    /*Preconditions: Karel is located at the down of the pole facing east.
    *Result: Karel is located at the top of the repaired pole facing north.
    * */
    private void repairPole() throws Exception {
        turnLeft();
        while (frontIsClear()){
            checkAndRepairBeeper();
            move();
        }
        checkAndRepairBeeper();

    }

    /*Preconditions: Karel is located in any cell.
    *Result: Karel puts a beeper into the cell if it is not there.
    * */
    private void checkAndRepairBeeper() throws Exception{
        if (noBeepersPresent()) {
            putBeeper();
        }
    }




    /*Preconditions: Karel is located in the top of pole facing north.
    *Result: Karel is located in the down of pole facing east.
    * */
    private void goDown() throws Exception {
        turnArround();
        while (frontIsClear()){
            move();
        }
        turnLeft();
    }

    /*Preconditions: none.
    *Result: Karel turns around.
    * */
    private void turnArround() throws Exception {
        turnLeft();
        turnLeft();
    }

    /*Preconditions: Karel is located in the down of pole facing north.
    *Result: Karel is located in the down of next pole facing north.
    * */
    private void goToNextPole() throws Exception{
        move();
        move();
        move();
        move();
    }

}