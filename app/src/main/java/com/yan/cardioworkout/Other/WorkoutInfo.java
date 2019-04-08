package com.yan.cardioworkout.Other;

import android.os.CountDownTimer;

public class WorkoutInfo {
    private static WorkoutInfo instance = null;

    private static int restTime = 5;
    private static int roundNum = 10;
    private final static String STARTWORKOUT = "START WORKOUT";
    private static int currentRound = 1;
    private final static int ROUNDTIME = 60;
    private static boolean endWorkout = false;

    public static WorkoutInfo getInstance() {
        if(instance == null) {
            instance = new WorkoutInfo();
        }
        return instance;
    }

    public static int getCurrentRound() {
        return currentRound;
    }

    public static boolean isEndWorkout() {
        return endWorkout;
    }

    public static void reset(){
        currentRound = 1;
        endWorkout = false;
    }

    public static void setEndWorkout(boolean endWorkout) {
        WorkoutInfo.endWorkout = endWorkout;
    }

    public static void incRound(){
        currentRound ++;
        if(currentRound > roundNum){
            currentRound = 1;
            setEndWorkout(true);
        }
    }

    public static int getROUNDTIME() {
        return ROUNDTIME;
    }

    public static void setCurrentRound(int currentRound) {
        WorkoutInfo.currentRound = currentRound;
    }

    public static int getRestTime() {
        return restTime;
    }

    public static void setRestTime(int restTime) {
        WorkoutInfo.restTime = restTime;
    }

    public static int getRoundNum() {
        return roundNum;
    }

    public static void setRoundNum(int roundNum) {
        WorkoutInfo.roundNum = roundNum;
    }

    public static String getNewTextContent(){
        String returnText = STARTWORKOUT + " ";
        int totalTime = 60 * roundNum + restTime * (roundNum - 1);
        int min = totalTime/60;
        int sec = totalTime%60;
        returnText += "(" + min + ":" + sec + "MIN)";
        return returnText;
    }
}
