import java.math.BigInteger;

/**
 *  An object that represents a fraction with the properties numerator and denominator
 */
public class RatNum {

    /**
     *  The numerator of a fraction, can be negative
     */
    private final BigInteger cNumerator;
    /**
     *  The denominator of a fraction, is always >0
     */
    private final BigInteger cDenominator;

    /**
     * Creates a fraction object with numerator and denominator. The (de)nominator will be in their smallest forms that is allowed by the fraction.
     * @param n The numerator of a fraction
     * @param d The denominator of a fraction, cannot be 0
     * @exception NumberFormatException When denominator is 0
     */
    private RatNum(BigInteger n, BigInteger d){
        if(d.equals(BigInteger.valueOf(0))){
            throw new NumberFormatException();
        }else if(d.signum() == -1){
            d = d.multiply(BigInteger.valueOf(-1));
            n = n.multiply(BigInteger.valueOf(-1));
        }
        BigInteger gcd = n.gcd(d);
        cNumerator = n.divide(gcd);
        cDenominator = d.divide(gcd);
        //System.out.println("! " + cNumerator + "/" + cDenominator);
    }


    /**
     * Call private constructor to make use of BigIntegers instead.
     * @param numerator The numerator of a fraction
     * @param denominator The denominator of a fraction, cannot be 0
     */
    public RatNum(int numerator, int denominator){
        this(new BigInteger(String.valueOf(numerator)), new BigInteger(String.valueOf(denominator)));
        //System.out.println("?" + numerator + "/" + denominator);
    }

    /**
     * Converts a whole number to a fraction object where the denominator will be 1.
     * @param number The whole number which will be used as the numerator of the fraction object
     */
    public RatNum(int number){
        cNumerator = BigInteger.valueOf(number);
        cDenominator = BigInteger.valueOf(1);
    }

    /**
     * Parses a string that may contain a fraction and converts it into a fraction object
     * @param fraction A string that contains a fraction which follows the format "a/b"
     * @see #parse(String) 
     */
    public RatNum(String fraction){
        RatNum num = parse(fraction);
        cNumerator = BigInteger.valueOf(num.getNumerator());
        cDenominator = BigInteger.valueOf(num.getDenominator());
    }

    /**
     * Creates a copy of an existing fraction object.
     * @see RatNum
     * @param ratNum An existing fraction object.
     */
    public RatNum(RatNum ratNum){
        cNumerator = BigInteger.valueOf(ratNum.getNumerator());
        cDenominator = BigInteger.valueOf(ratNum.getDenominator());
    }

    /**
     *  Creates a fraction object with the resulting value 0 (0/1 to be more specific)
     * @see RatNum
     */
    public RatNum(){
        cNumerator = BigInteger.valueOf(0);
        cDenominator = BigInteger.valueOf(1);
    }

    /**
     * Calculates the greatest common divisor by using the euclidean algorithm
     * @exception IllegalArgumentException when both parameters are 0
     * @return Greatest common divisor (which is also >0)
     */
    public static int gcd(int m, int n){
        if(m == 0 && n == 0){
            throw new IllegalArgumentException();
        }

        if(n > m){
            int temp = m;
            m = n;
            n = temp;
        }

        // For the moment saves the latest modulus calculation while m contains the previous modulus calculation.
        // The loop ends when n aka the current calculation is 0 as according to the euclidean algorithm.
        while (n != 0){
            int denominator = n;
            n = m % denominator;
            m = denominator;
        }

        // GCD is always positive since by definition it's >0
        if(m < 0){
            m *= -1;
        }

        return m;
    }

    /**
     * @return The numerator of the fraction object
     */
    public int getNumerator() {
        int numerator = cNumerator.intValue();
        return numerator;
    }

    /**
     * @return The denominator of the fraction object
     */
    public int getDenominator(){
        int denominator = cDenominator.intValue();
        return denominator;
    }

    /**
     * Converts the fraction object to a string
     * @return A string in the format of "a/b"
     */
    public String toString(){
        String str = "";
        BigInteger whole = cNumerator.divide(cDenominator);
        BigInteger rest = cNumerator.remainder(cDenominator);
        if(!whole.equals(BigInteger.valueOf(0)) || cNumerator.equals(BigInteger.valueOf(0))){
            str += whole + " ";
        }
        if(!rest.equals(BigInteger.valueOf(0))){
            str += (cNumerator.subtract(whole.multiply(cDenominator))) + "/" + cDenominator;
        }
        return str;
    }

    public String toIntString(){
        BigInteger value = cNumerator.divide(cDenominator);
        return value.toString();
    }

    /**
     * Parses a string that contains a fraction
     * @param str A string that contains the fraction in format of "a/b"
     * @return A fraction object that is derived from the string
     * @exception NumberFormatException When the parameter doesn't follow the "a/b" format or contains any non-numeric letters
     */
    public static RatNum parse(String str){
        String[] numbers = str.split("/");

        if(!str.contains("/")) {
            return new RatNum(new BigInteger(str), BigInteger.valueOf(1));
        }
        else if(numbers.length != 2) {
            throw new NumberFormatException();
        }
        BigInteger numerator = new BigInteger(numbers[0]);
        BigInteger denominator = new BigInteger(numbers[1]);
        return new RatNum(numerator, denominator);
    }

    /**
     * Adds together fractions and returns the result
     * @param ratNum A fraction to add with the current object
     * @return A {@link RatNum} object with the sum from the addition
     */
    public RatNum add(RatNum ratNum){
        BigInteger numerator = cNumerator.multiply(BigInteger.valueOf(ratNum.getDenominator())).add(BigInteger.valueOf(ratNum.getNumerator()).multiply(cDenominator));
        // The constructor will shorten the fraction if needed
        return new RatNum(numerator, cDenominator.multiply(BigInteger.valueOf(ratNum.getDenominator())));
    }

    /**
     * Subtracts the current fraction with the argument and returns the resulting fraction
     * @param ratNum The fraction that is in the right-hand side of the subtraction
     * @return A {@link RatNum} object containing the difference from the subtraction
     */
    public RatNum sub(RatNum ratNum){
        BigInteger numerator = cNumerator.multiply(BigInteger.valueOf(ratNum.getDenominator())).subtract(BigInteger.valueOf(ratNum.getNumerator()).multiply(cDenominator));
        // The constructor will shorten the fraction if needed
        return new RatNum(numerator, cDenominator.multiply(BigInteger.valueOf(ratNum.getDenominator())));
    }

    /**
     * Multiplies two fractions with each other and then returns the resulting fraction
     * @param ratNum A fraction to multiply with the current fraction
     * @return A {@link RatNum} object containing the result of multiplication
     */
    public RatNum mul(RatNum ratNum){
        return new RatNum(cNumerator.multiply(BigInteger.valueOf(ratNum.getNumerator())), cDenominator.multiply(BigInteger.valueOf(ratNum.getDenominator())));
    }

    /**
     * Divides two fractions with each other and returns the resulting fraction
     * @param ratNum The fraction that will be used as the denominator of the division
     * @return A {@link RatNum} object that contains the result from the division
     */
    public RatNum div(RatNum ratNum){
        // Uses inverted multiplication for division
        return new RatNum(cNumerator.multiply(BigInteger.valueOf(ratNum.getDenominator())), cDenominator.multiply(BigInteger.valueOf(ratNum.getNumerator())));
    }

    /**
     * Checks if the current fraction is less than the argument fraction
     * @param ratNum A fraction to be compared to the implicit argument to see if it's bigger
     * @return A boolean which is true when the argument is bigger than the implicit argument or otherwise false.
     */
    public boolean lessThan(RatNum ratNum){
        BigInteger lessThan = cNumerator.multiply(BigInteger.valueOf(ratNum.getDenominator())).subtract(BigInteger.valueOf(ratNum.getNumerator()).multiply(cDenominator));
        if(lessThan.signum() == -1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Compares two fractions to see if they're the same value
     * @param obj fraction value to be compared to
     * @return true when the fractions are the same value otherwise false
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof RatNum ratNum)){
            return false;
        }
        return cNumerator.equals(BigInteger.valueOf(ratNum.getNumerator())) && cDenominator.equals(BigInteger.valueOf(ratNum.getDenominator()));
    }
}
