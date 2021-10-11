//package src;

import java.util.*;

public class TestMyDate {

    public static void main(String[] args) {

        MyDate d1, d2;
        boolean testOK = true;

        // test 1: försöker hitta ett fel i konstruktorn

        final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        boolean exceptionCheck = true;

        // Check if exception appears in day 0
        try {
            d1 = new MyDate(2021, 01, 0);
            exceptionCheck = false;
        } catch(Exception e){
            exceptionCheck = true;
        }
        if(!exceptionCheck){
            testOK = false;
            System.out.println("Error: Allows day 0 in date.");
        }

        // Chcks if exception appears when day is out of bounds of a month
        for(int i = 0; i < 12; i++){
            try {
                d1 = new MyDate(2021, i + 1, daysInMonth[i] + 1);
                exceptionCheck = false;
                } catch(Exception e){
                    exceptionCheck = true;
                }
                if(!exceptionCheck){
                    testOK = false;
                    System.out.println("Error: Allows day to be out of bounds in month " + (i + 1) + ". Day shouldn't be larger than " + daysInMonth[i]);
                }
        }

        try {
            d1 = new MyDate(2021, 00, 1);
            exceptionCheck = false;
            } catch(Exception e){
                exceptionCheck = true;
            }
            if(!exceptionCheck){
                testOK = false;
                System.out.println("Error: Allows month 0 in date.");
            }

        try {
            d1 = new MyDate(2021, 13, 0);
            exceptionCheck = false;
            } catch(Exception e){
                exceptionCheck = true;
            }
            if(!exceptionCheck){
                testOK = false;
                System.out.println("Error: Allows month 13 in date.");
            }

            try {
                d1 = new MyDate(-2021, -01, -01);
                exceptionCheck = false;
                } catch(Exception e){
                    exceptionCheck = true;
                }
                if(!exceptionCheck){
                    testOK = false;
                    System.out.println("Error: Allows negative date.");
                }

        try{
            d1 = new MyDate(2024, 2, 29);
        }catch(Exception e){
            testOK = false;
            System.out.println("Error: Throws exception on leap year date");
        }


        if (testOK) {
            System.out.println("Test 1 OK.");
        } else {
            System.out.println("Test 1 failed.");
        }

        // test 2: försöker hitta ett fel i compareTo
        testOK = true;
        d1 = new MyDate( 2000, 6, 25);

        MyDate[] dates = {
            new MyDate(2000, 6, 25),
            new MyDate(2001, 6, 25),
            new MyDate(2000, 6, 27),
            new MyDate(2000, 6, 24),
            new MyDate(2000, 7, 25),
            new MyDate(1998, 6, 29),
            new MyDate(2000, 5, 29),
            new MyDate(2000, 7, 24)
        };
        int[] expected = {0, -1, -1, 1, -1, 1, 1, -1};

        for(int i = 0; i < dates.length; i++){
            int r = d1.compareTo(dates[i]);

            if(r != expected[i]){
                System.out.println("Error: Expected " + expected[i] + " and got " + r + " regarding " 
                + dates[i].getYear() + '-' + dates[i].getMonth() + '-' + dates[i].getDay()
                + " compared to 2000-06-25");
                testOK = false;
            }
        }

        if (testOK) {
            System.out.println("Test 2 OK.");
        } else {
            System.out.println("Test 2 failed.");
        }

        // test 3: försöker hitta ett fel i equals
        // skriv din lösning här

        testOK = true;
        d1 = new MyDate(2021, 06, 06);
        d2 = new MyDate(2021, 06, 06);
        if(!d1.equals(d2)){
            System.out.println("Error: Equals method only compares pointer adress");
            testOK = false;
        }

        if (testOK) {
            System.out.println("Test 3 OK.");
        } else {
            System.out.println("Test 3 failed.");
        }

    }

}
