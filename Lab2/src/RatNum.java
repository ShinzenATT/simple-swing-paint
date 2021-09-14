/**
 *  An object that represents a fraction with the properties numerator and denominator
 */
public class RatNum {
    /**
     *  The numerator of a fraction, can be negative
     */
    private final int Numerator;
    /**
     *  The denominator of a fraction, is always >0
     */
    private final int Denominator;

    /**
     * Creates a fraction object with numerator and denominator. The (de)nominator will be in their smallest forms that is allowed by the fraction.
     * @param numerator The numerator of a fraction
     * @param denominator The denominator of a fraction, cannot be 0
     * @exception NumberFormatException When denominator is 0
     */
    public RatNum(int numerator, int denominator){
        if(denominator == 0){
            throw new NumberFormatException();
        }
        if(denominator < 0){
            numerator *= -1;
            denominator *= -1;
        }

        int gcd = gcd(numerator, denominator);
        Numerator = numerator / gcd;
        Denominator = denominator / gcd;
    }

    /**
     * Converts a whole number to a fraction object where the denominator will be 1.
     * @param number The whole number which will be used as the numerator of the fraction object
     */
    public RatNum(int number){
        Numerator = number;
        Denominator = 1;
    }

    /**
     * Parses a string that may contain a fraction and converts it into a fraction object
     * @param fraction A string that contains a fraction which follows the format "a/b"
     * @see #parse(String) 
     */
    public RatNum(String fraction){
        RatNum num = parse(fraction);
        Numerator = num.getNumerator();
        Denominator = num.getDenominator();
    }

    /**
     * Creates a copy of an existing fraction object.
     * @see RatNum
     * @param ratNum An existing fraction object.
     */
    public RatNum(RatNum ratNum){
        Numerator = ratNum.getNumerator();
        Denominator = ratNum.getDenominator();
    }

    /**
     *  Creates a fraction object with the resulting value 0 (0/1 to be more specific)
     * @see RatNum
     */
    public RatNum(){
        Numerator = 0;
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
     * @return The numerator of the fraction object
     */
    public int getNumerator() {
        return Numerator;
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
        return "" + Numerator + '/' + Denominator;
    }

    public String toIntString(){
        int value = Numerator / Denominator;
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
        if(numbers.length > 2)
            throw new NumberFormatException();
        else if(numbers.length == 1)
            return new RatNum(Integer.parseInt(numbers[0]), 1);
        // TODO Create a method to parse stings without parseInt
        int numerator = Integer.parseInt(numbers[0]);
        int denominator = Integer.parseInt(numbers[1]);
        return new RatNum(numerator, denominator);
    }

    /**
     * Adds together fractions and returns the result
     * @param ratNum A fraction to add with the current object
     * @return A {@link RatNum} object with the sum from the addition
     */
    public RatNum add(RatNum ratNum){
        int numerator = Numerator * ratNum.getDenominator() + ratNum.getNumerator() * Denominator;
        // The constructor will shorten the fraction if needed
        return new RatNum(numerator, Denominator * ratNum.getDenominator());
    }

    /**
     * Subtracts the current fraction with the argument and returns the resulting fraction
     * @param ratNum The fraction that is in the right-hand side of the subtraction
     * @return A {@link RatNum} object containing the difference from the subtraction
     */
    public RatNum sub(RatNum ratNum){
        int numerator = Numerator * ratNum.getDenominator() - ratNum.getNumerator() * Denominator;
        // The constructor will shorten the fraction if needed
        return new RatNum(numerator, Denominator * ratNum.getDenominator());
    }

    /**
     * Multiplies two fractions with each other and then returns the resulting fraction
     * @param ratNum A fraction to multiply with the current fraction
     * @return A {@link RatNum} object containing the result of multiplication
     */
    public RatNum mul(RatNum ratNum){
        return new RatNum(Numerator * ratNum.getNumerator(), Denominator * ratNum.getDenominator());
    }

    /**
     * Divides two fractions with each other and returns the resulting fraction
     * @param ratNum The fraction that will be used as the denominator of the division
     * @return A {@link RatNum} object that contains the result from the division
     */
    public RatNum div(RatNum ratNum){
        // Uses inverted multiplication for division
        return new RatNum(Numerator * ratNum.getDenominator(), Denominator * ratNum.getNumerator());
    }

    /**
     * Checks if the current fraction is less than the argument fraction
     * @param ratNum A fraction to be compared to the implicit argument to see if it's bigger
     * @return A boolean which is true when the argument is bigger than the implicit argument or otherwise false.
     */
    public boolean lessThan(RatNum ratNum){
        return Numerator * ratNum.getDenominator() < ratNum.getNumerator() * Denominator;
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
        return Numerator == ratNum.getNumerator() && Denominator == ratNum.getDenominator();
    }
}
