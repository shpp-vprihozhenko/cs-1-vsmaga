package com.shpp.cs.vsmaga;

/* TODO: Aerobics organizer that asks the user for the number of minutes spent the last seven days on exercises.
*  Then responds enough he did aerobics for cardiovascular health  and whether he was
*  involved in aerobics for blood pressure all week.
* */

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Task1 extends TextProgram{

    /* Days in week*/
    private static int DAYS_IN_WEEK = 7;

    /* Minimum minutes of aerobics for cardiovascular health and blood pressure in day*/
    private static int MIN_MINUTES_CARDIO = 30;
    private static int MIN_MINUTES_BLOOD = 40;

    /*Minimum days of aerobics for cardiovascular health and blood pressure per week*/
    private static int MIN_DAYS_CARDIO = 5;
    private static int MIN_DAYS_BLOOD = 3;

    public void run () {
        int dayWithCardio = 0; //counter for days with minimum 30 min. of aerobics per week.
        int dayWithBlood = 0; // counter for days with minimum 40 min. of aerobics per week.

        /* asks user for minutes of aerobics for every day*/
        for (int i = 1; i <= DAYS_IN_WEEK; i++) {
            int minutes = readInt("How many minutes did you do aerobics on day "+ i + "? " );

            /* checks if there is enough exercises for blood pressure in the day*/
            if (minutes >= MIN_MINUTES_BLOOD){
                dayWithBlood++; // incrementing counter of days with right time of aerobics for blood pressure
            }

            /* checks if there is enough exercises for cardiovascular health in the day*/
            if (minutes >= MIN_MINUTES_CARDIO){
                 dayWithCardio++; // incrementing counter of days with right time of aerobics for cardiovascular health

            }

        }

        aerobicsCalculator(dayWithCardio, dayWithBlood);
    }

    /*This method calculates and displays enough to carry out exercises and if not displays how many more need to engage
    * */
    private void aerobicsCalculator(int dayWithCardio, int dayWithBlood) {

        calculateCardio(dayWithCardio);
        calculateBlood(dayWithBlood);

    }


    /*This method calculates and displays enough to carry out exercises for cardiovascular health
    * and if not displays how many more need to engage
    * */
    private void calculateCardio(int dayWithCardio) {
        println("Cardiovascular health:");
        if (dayWithCardio >= MIN_DAYS_CARDIO){
            println("   Great job! You've done enough exercise for cardiovascular health.");
        }
        else {
            println("   You needed to train hard for at least " + (MIN_DAYS_CARDIO - dayWithCardio) + " more day a week!");
        }
    }

    /*This method calculates and displays enough to carry out exercises for blood pressure
    * and if not displays how many more need to engage
    * */
    private void calculateBlood(int dayWithBlood) {
        println("Blood pressure:");
        if (dayWithBlood >= MIN_DAYS_BLOOD){
            println("   Great job! You've done enough exercise for blood pressure.");
        }
        else {
            println("   You needed to train hard for at least " + (MIN_DAYS_BLOOD - dayWithBlood) + " more day a week!");
        }
    }
}
