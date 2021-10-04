import java.util.*;

public class RepTest {

    public static void main(String[] args) {

        // ett enkelt exempel

        MyDate d = new MyDate(2000,4,14);
        Iterator<MyDate> it = d.repeatWeekly(5);
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // tuffare automatiska test

        Random r = new Random();
        boolean testOK = true;
        int testCount = 10;
        try {
            for (int i=0; i<testCount; i++) {
                int c = r.nextInt(1000);
                MyDate prev = new MyDate(1999,12,24);
                MyDate curr = prev.next().next().next().next().next().next().next();
                it = curr.repeatWeekly(c);
                for (int j=0; j<c; j++) {
                    if (!it.hasNext()) {
                        System.out.println("Too few elements in Iterator.");
                        testOK = false;
                        break;
                    }
                    curr = it.next();
                    if (!curr.equals(prev.next().next().next().next().next().next().next())) {
                        System.out.println("Incorrect date in Iterator.");
                        testOK = false;
                        break;
                    }
                    prev = curr;
                }
                if (it.hasNext()) {
                    System.out.println("Too many elements in Iterator.");
                    testOK = false;
                }
                if (!testOK) { break; }
            }
        } catch (Exception e) {
            testOK = false;
        }
        if (testOK) {
            System.out.println("RepTest OK.");
        } else {
            System.out.println("RepTest failed.");
        }

    }

}
