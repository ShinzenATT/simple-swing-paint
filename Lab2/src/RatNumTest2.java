class RatNumTest2 {

    private static void fel(int nr) {
        System.out.println("RatNumTest2: Fel nummer " + nr);
        System.exit(1);
    }

    public static void divTester() {
        RatNumLab2 r;

        // test av konstruktor
        r = new RatNumLab2(9);
        if (r.getNumerator() != 9 || r.getDenominator() != 1)
            fel(1);
        r = new RatNumLab2(4, 9);
        if (r.getNumerator() != 4 || r.getDenominator() != 9)
            fel(2);
        r = new RatNumLab2(49, 168);
        if (r.getNumerator() != 7 || r.getDenominator() != 24)
            fel(3);
        RatNumLab2 r2 = new RatNumLab2(r);
        if (r2.getNumerator() != 7 || r2.getDenominator() != 24)
            fel(4);
        RatNumLab2 x = new RatNumLab2();
        if (x.getNumerator() != 0 || x.getDenominator() != 1)
            fel(5);
        if (r2.getNumerator() == 0 || r2.getDenominator() == 1)
            fel(6);
        RatNumLab2 y = new RatNumLab2(5);
        if (y.getNumerator() != 5 || y.getDenominator() != 1)
            fel(7);
        RatNumLab2 z = new RatNumLab2(20, 4);
        if (z.getNumerator() != 5 || z.getDenominator() != 1)
            fel(8);
        RatNumLab2 w = new RatNumLab2(0,1);
        if (w.getNumerator() != 0 || w.getDenominator() != 1)
            fel(9);
        RatNumLab2 q = new RatNumLab2(y);
        if (q.getNumerator() != 5 || q.getDenominator() != 1)
            fel(10);

        // test av negativa parametrar
        r = new RatNumLab2(-49, 168);
        if (r.getNumerator() != -7 || r.getDenominator() != 24)
            fel(11);
        r = new RatNumLab2(49, -168);
        if (r.getNumerator() != -7 || r.getDenominator() != 24)
            fel(12);
        r = new RatNumLab2(-49, -168);
        if (r.getNumerator() != 7 || r.getDenominator() != 24)
            fel(13);

        // Test av exception
        boolean ok = false;
        try {
            q = new RatNumLab2(5,0);
        }
        catch (NumberFormatException e1) {ok = true;}
        catch (Exception e2) {}
        if (!ok)
            fel(14);
    }

    public static void main(String[] arg) {
        divTester();
        System.out.println("Inga fel!");
    }
}
