import java.math.*;

public class Pi {

    public static void main(String[] args) {
        int decimalCount = 70;
        if (args.length > 0) {
            decimalCount = Integer.valueOf(args[0]);
        }

        System.out.println("\nCaclculating...");

        RatNum res = new RatNum();
        for(int i = 0; i < 70; i++){
            RatNum digitExtract = new RatNum(4 + "/" + (8 * i + 1));
            digitExtract = digitExtract.sub(new RatNum(2 + "/" + (8 * i + 4)));
            digitExtract = digitExtract.sub(new RatNum(1 + "/" + (8 * i + 5)));
            digitExtract = digitExtract.sub(new RatNum(1 + "/" + (8 * i + 6)));
            digitExtract = digitExtract.mul(new RatNum(1 + "/" + RatNum.pow(16, i)));
            res = res.add(digitExtract);
            //System.out.println("Ratnum: " + digitExtract.toString() + " i: " + i + " Sum: " + res.toString() + "\n");
        }

        // kod för utskriften (behöver inte ändras)
        // denna kod antar att den första decimalen av res är något annat än 0
        // (i pi är den första decimalen 1)
        RatNum m = new RatNum(1,1);
        RatNum ten = new RatNum(10,1);
        for (int k=0; k<decimalCount; k++) {
            m = m.mul(ten);
        }
        System.out.print("\npi = ");
        String intPart = res.toIntString();
        System.out.print(intPart);
        System.out.print(".");
        RatNum digits = res.sub(RatNum.parse(intPart));
        System.out.print(digits.mul(m).toIntString());
        System.out.println("...");
    }

}
