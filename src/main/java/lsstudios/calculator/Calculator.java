package lsstudios.calculator;

import java.util.ArrayList;

public class Calculator {



    public int average(ArrayList<Integer> smallGrades, int bigGrade, boolean level){
        if(level){
            return average_high(smallGrades,bigGrade);
        } else {
            return average_low(smallGrades, bigGrade);
        }
    }

    public int total(ArrayList<Integer> averages, boolean level){
        if(level){
            return total_high(averages);
        }else {
            return total_low(averages);
        }
    }

    private int average_low(ArrayList<Integer> smallGrades, int bigGrade){
        //berechnung durchschnit Notensystem 1-6
        int divider = 0;
        int temp = 0;
        for(int n: smallGrades){
            divider = divider+1;
            temp = temp + n;
        }
        temp = Math.round((float)temp/divider);
        temp = Math.round((temp+bigGrade)/2f);

        return temp;
    }

    private int average_high(ArrayList<Integer> smallGrades, int bigGrade){
        //berchnung durchschnit Notensystem 0-15
        int temp = 0;
        int divider = 0;
        for(int n: smallGrades){
            divider = divider+1;
            temp = temp + n;
        }
        temp = Math.round((float)temp/divider);
        temp = Math.round((float) (temp+bigGrade)/2);
        return temp;
    }

    private int total_low(ArrayList<Integer> averages){
        // berechnung Gesamtnote 1-6
        int temp = 0;
        int divider = 0;
        for(int n: averages){
            divider = divider+1;
            temp = temp + n;
        }
        temp = Math.round( (float) temp/divider);
        return temp;
    }

    private int total_high(ArrayList<Integer> averages){
        // berechnung Gesamtnote 0-15
        int temp = 0;
        int divider = 0;
        for(int n: averages){
            divider = divider+1;
            temp = temp + n;
        }
        temp = Math.round((float) temp/divider);
        return temp;
    }
}
