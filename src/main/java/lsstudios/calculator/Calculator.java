package lsstudios.calculator;

import java.util.ArrayList;

public class Calculator {


    //function to calculate the result for one Subject
    public int average(ArrayList<Integer> smallGrades, int bigGrade, boolean level){
        //determines based on the Gradingsystem which function will be used
        if(level){
            return average_high(smallGrades,bigGrade);
        } else {
            return average_low(smallGrades, bigGrade);
        }
    }

    /*
    function to calculate the total grade for one Semester
    the Grades from the LK must be entered twice in the ArrayList!
     */
    public int total(ArrayList<Integer> averages, boolean level){
        //determines based on the Gradingsystem wich function will be used
        if(level){
            return total_high(averages);
        }else {
            return total_low(averages);
        }
    }

    private int average_low(ArrayList<Integer> smallGrades, int bigGrade){
        //calculates the result for one Subject in a Semester for the Gradingsystem 1-6
        int divider = 0;
        int temp = 0;
        //sums up the small grades and counts how many you have
        for(int n: smallGrades){
            divider = divider+1;
            temp = temp + n;
        }
        //calculates the average of the small grades and rounds them
        temp = Math.round((float)temp/divider);
        //calculates the final result and rounds it
        temp = Math.round((temp+bigGrade)/2f);
        return temp;
    }

    private int average_high(ArrayList<Integer> smallGrades, int bigGrade){
        //calculates the result for one Subject in a Semester for the Gradingsystem 0-15
        int temp = 0;
        int divider = 0;
        //sums up the small grades and counts how many you have
        for(int n: smallGrades){
            divider = divider+1;
            temp = temp + n;
        }
        //calculates the average of the small grades and rounds them
        temp = Math.round((float)temp/divider);
        //calculates the final result and rounds it
        temp = Math.round((float) (temp+bigGrade)/2);
        return temp;
    }

    private int total_low(ArrayList<Integer> averages){
        //calculates the total Grade for the Semester in the Gradingsystem 1-6
        int temp = 0;
        int divider = 0;
        //sums up all Grades and counts them
        for(int n: averages){
            divider = divider+1;
            temp = temp + n;
        }
        //calculates the finale result
        temp = Math.round( (float) temp/divider);
        return temp;
    }

    private int total_high(ArrayList<Integer> averages){
        //calculates the total Grade for the Semester in the Gradingsystem 0-15
        int temp = 0;
        int divider = 0;
        //sums up all Grades and counts them
        for(int n: averages){
            divider = divider+1;
            temp = temp + n;
        }
        //calculates the finale result
        temp = Math.round((float) temp/divider);
        return temp;
    }
}
