public class RatNumTest4 {

    public static boolean ok = true;

    public static void fail(String testName) {
        ok = false;
        System.out.println("Failure in test: " + testName);
    }

    public static void main(String[] args) {

        RatNum zero = new RatNum(0);
        RatNum half = new RatNum(1,2);
        RatNum one = new RatNum(1);
        RatNum third = new RatNum(1,3);
        RatNum ten = new RatNum(10);

        // divide by 10 many times, make sure value always decreases
        RatNum x = new RatNum(1000000,1);
        try {
            for (int i=1; i<50; i++) {
                RatNum y = x.div(ten);
                if (x.lessThan(y)) {
                    fail("divide many times");
                    break;
                }
                x = y;
                System.out.println(x);
            }
        } catch (StackOverflowError e) {
            fail("divide many times");
        }

        // computes 1/2 + 1/4 + 1/8 + 1/16 + 1/32 ...
        RatNum acc = zero;
        RatNum next = half;
        RatNum m = new RatNum(10000000);
        try {
            for (int i=0; i<30; i++) {
                acc = acc.add(next);
                next = next.mul(half);
                System.out.println("0."+acc.mul(m).toIntString());
                if (acc.lessThan(zero) || one.lessThan(acc)) {
                    fail("sum towards 1");
                    break;
                }
            }
        } catch (StackOverflowError e) {
            fail("sum towards 1");
        }

        if (ok) {
            System.out.println();
            System.out.println("All tests passed!");
            System.out.println();
        }

    }

}
