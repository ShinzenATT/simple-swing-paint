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


        if (testOK) {
            System.out.println("Test 1 OK.");
        } else {
            System.out.println("Test 1 failed.");
        }

        // test 2: försöker hitta ett fel i compareTo
        d1 = new MyDate(2020, 05, 30);
        d2 = new MyDate(2000, 06, 28);
        testOK = true;
         int x = d1.compareTo(d2);
         switch (x){
             case -1:

         }
        // skriv din lösning här

        if (testOK) {
            System.out.println("Test 2 OK.");
        } else {
            System.out.println("Test 2 failed.");
        }

        // test 3: försöker hitta ett fel i equals

        // skriv din lösning här

        if (testOK) {
            System.out.println("Test 3 OK.");
        } else {
            System.out.println("Test 3 failed.");
        }

    }

}
