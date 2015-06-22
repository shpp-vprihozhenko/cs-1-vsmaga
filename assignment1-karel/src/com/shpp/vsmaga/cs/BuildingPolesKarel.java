package com.shpp.vsmaga.cs;

import com.shpp.karel.KarelTheRobot;

public class BuildingPolesKarel extends KarelTheRobot {

    public void run() throws Exception {
        while (frontIsClear()){
            repairPole();
            goDown();
            goToNextPole();
        }
        repairPole();

    }

    private void repairPole() throws Exception {
        turnLeft();
        while (frontIsClear()){
            checkAndRepairBeeper();
            move();
        }
        checkAndRepairBeeper();

    }

    private void checkAndRepairBeeper() throws Exception{
        if (noBeepersPresent()) {
            putBeeper();
        }
    }




    private void goDown() throws Exception {
        turnArround();
        while (frontIsClear()){
            move();
        }
        turnLeft();
    }

    private void turnArround() throws Exception {
        turnLeft();
        turnLeft();
    }

    private void goToNextPole() throws Exception{
        move();
        move();
        move();
        move();
    }

}