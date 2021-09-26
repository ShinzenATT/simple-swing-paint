public class Pi {

    public static void main(String[] args) {
        int decimalCount = 70;
        if (args.length > 0) {
            decimalCount = Integer.valueOf(args[0]);
        }

        RatNum res = new RatNum(0,1);
        // ersätt följande två rader med din lösning
        res = res.add(new RatNum(1,3));
        res = res.add(new RatNum(1,3));

        // kod för utskriften (behöver inte ändras)
        // denna kod antar att den första decimalen av res är något annat än 0
        // (i pi är den första decimalen 1)
        RatNum m = new RatNum(1,1);
        RatNum ten = new RatNum(10,1);
        for (int k=0; k<decimalCount; k++) {
            m = m.mul(ten);
        }
        System.out.print("pi = ");
        String intPart = res.toIntString();
        System.out.print(intPart);
        System.out.print(".");
        RatNum digits = res.sub(RatNum.parse(intPart));
        System.out.print(digits.mul(m).toIntString());
        System.out.println("...");
    }

}
