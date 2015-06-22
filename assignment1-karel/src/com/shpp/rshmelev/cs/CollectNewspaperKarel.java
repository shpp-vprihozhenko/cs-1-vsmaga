package com.shpp.rshmelev.cs;

import com.shpp.karel.KarelTheRobot;

public class CollectNewspaperKarel extends KarelTheRobot {

    public void run() throws Exception {
        goToNewspaper();
        pickNewspaper();
        backToStart();
    }

    private void goToNewspaper() throws Exception  {
        goToWall();
        turnRight();
        goToExit();
        turnLeft();
    }

    private void goToWall() throws Exception{
        while (frontIsClear()){
            move();
        }
    }

    private void goToExit() throws Exception{
        while (leftIsBlocked()){
            move();
        }
    }

    private void pickNewspaper() throws Exception{
        findNewspaper();
        pickBeeper();
        turnAround();
        goHome();

    }

    private void findNewspaper() throws Exception{
        while(noBeepersPresent()){
            move();
        }
    }

    private void goHome() throws Exception{
        while (rightIsClear()){
            move();
        }
        move();
        turnRight();
    }

    private void backToStart() throws Exception {
        goToWall();
        turnLeft();
        goToWall();
        turnAround();
    }


    private void turnRight() throws Exception  {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    private void turnAround() throws Exception  {
        turnLeft();
        turnLeft();
    }

}