/**
 *  An object that represents a fraction with the properties nominator and denominator
 */
public class RatNum {
    /**
     *  The nominator of a fraction, can be negative
     */
    private int Nominator;
    /**
     *  The denominator of a fraction, is always >0
     */
    private int Denominator;

    /**
     * Creates a fraction object with nominator and denominator. The (de)nominator will be in their smallest forms that is allowed by the fraction.
     * @param nominator The nominator of a fraction
     * @param denominator The denominator of a fraction, cannot be 0
     * @exception NumberFormatException When denominator is 0
     */
    public RatNum(int nominator, int denominator){
        if(denominator == 0){
            throw new NumberFormatException();
        }
        if(denominator < 0){
            nominator *= -1;
            denominator *= -1;
        }

        int gcd = gcd(nominator, denominator);
        Nominator = nominator / gcd;
        Denominator = denominator / gcd;
    }

    /**
     * Converts a whole number to a fraction object where the denominator will be 1.
     * @param number The whole number which will be used as the nominator of the fraction object
     */
    public RatNum(int number){
        Nominator = number;
        Denominator = 1;
    }

    /**
     * Parses a string that may contain a fraction and converts it into a fraction object
     * @param fraction A string that contains a fraction which follows the format "a/b"
     * @see #parse(String) 
     */
    public RatNum(String fraction){
        RatNum num = parse(fraction);
        Nominator = num.getNumerator();
        Denominator = num.getDenominator();
    }

    /**
     * Creates a copy of an existing fraction object.
     * @see RatNum
     * @param ratNum An existing fraction object.
     */
    public RatNum(RatNum ratNum){
        Nominator = ratNum.getNumerator();
        Denominator = ratNum.getDenominator();
    }

    /**
     *  Creates a fraction object with the resulting value 0 (0/1 to be more specific)
     * @see RatNum
     */
    public RatNum(){
        Nominator = 0;
        Denominator = 1;
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
     * @return The nominator of the fraction object
     */
    public int getNumerator() {
        return Nominator;
    }

    /**
     * @return The denominator of the fraction object
     */
    public int getDenominator(){
        return Denominator;
    }

    /**
     * Converts the fraction object to a string
     * @return A string in the format of "a/b"
     */
    public String toString(){
        return "" + Nominator + '/' + Denominator;
    }

    public String toIntString(){
        int value = Nominator / Denominator;
        return String.valueOf(value);
    }

    /**
     * Parses a string that contains a fraction
     * @param str A string that contains the fraction in format of "a/b"
     * @return A fraction object that is derived from the string
     * @exception NumberFormatException When the parameter doesn't follow the "a/b" format or contains any non-numeric letters
     */
    public static RatNum parse(String str){
        String[] numbers = str.split("/");
        if(numbers.length != 2){
            throw new NumberFormatException();
        }
        int nominator = Integer.parseInt(numbers[0]);
        int denominator = Integer.parseInt(numbers[1]);
        return new RatNum(nominator, denominator);
    }

    // TODO Implement addition
    public RatNum add(RatNum ratNum){
        return null;
    }

    // TODO Implement subtraction
    public RatNum sub(RatNum ratNum){
        return null;
    }

    // TODO Implement multiplication
    public RatNum mul(RatNum ratNum){
        return null;
    }

    // TODO Implement division
    public RatNum div(RatNum ratNum){
        return null;
    }

    // TODO Implement a less than check
    public boolean lessThan(RatNum ratNum){
        return false;
    }

    /**
     * Compares two fractions to see if they've the same value
     * @param obj fraction value to be compared to
     * @return true when the fractions are the same value otherwise false
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof RatNum ratNum)){
            return false;
        }
        return Nominator == ratNum.getNumerator() && Denominator == ratNum.getDenominator();
    }
}
