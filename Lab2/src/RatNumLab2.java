/**
 *  An object that represents a fraction with the properties numerator and denominator
 */
public class RatNumLab2 {
    /**
     *  The numerator of a fraction, can be negative
     */
    private final int cNumerator;
    /**
     *  The denominator of a fraction, is always >0
     */
    private final int cDenominator;

    /**
     * Creates a fraction object with numerator and denominator. The (de)nominator will be in their smallest forms that is allowed by the fraction.
     * @param numerator The numerator of a fraction
     * @param denominator The denominator of a fraction, cannot be 0
     * @exception NumberFormatException When denominator is 0
     */
    public RatNumLab2(int numerator, int denominator){
        if(denominator == 0){
            throw new NumberFormatException();
        }
        if(denominator < 0){
            numerator *= -1;
            denominator *= -1;
        }

        int gcd = gcd(numerator, denominator);
        cNumerator = numerator / gcd;
        cDenominator = denominator / gcd;
    }

    /**
     * Converts a whole number to a fraction object where the denominator will be 1.
     * @param number The whole number which will be used as the numerator of the fraction object
     */
    public RatNumLab2(int number){
        cNumerator = number;
        cDenominator = 1;
    }

    /**
     * Parses a string that may contain a fraction and converts it into a fraction object
     * @param fraction A string that contains a fraction which follows the format "a/b"
     * @see #parse(String) 
     */
    public RatNumLab2(String fraction){
        RatNumLab2 num = parse(fraction);
        cNumerator = num.getNumerator();
        cDenominator = num.getDenominator();
    }

    /**
     * Creates a copy of an existing fraction object.
     * @see RatNumLab2
     * @param ratNum An existing fraction object.
     */
    public RatNumLab2(RatNumLab2 ratNum){
        cNumerator = ratNum.getNumerator();
        cDenominator = ratNum.getDenominator();
    }

    /**
     *  Creates a fraction object with the resulting value 0 (0/1 to be more specific)
     * @see RatNumLab2
     */
    public RatNumLab2(){
        cNumerator = 0;
        cDenominator = 1;
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
        return cNumerator;
    }

    /**
     * @return The denominator of the fraction object
     */
    public int getDenominator(){
        return cDenominator;
    }

    /**
     * Converts the fraction object to a string
     * @return A string in the format of "a/b"
     */
    public String toString(){
        String str = "";
        int whole = cNumerator / cDenominator;
        int rest = cNumerator % cDenominator;
        if(whole != 0 || cNumerator == 0){
            str += whole + " ";
        }
        if(rest != 0){
            str += (cNumerator - whole * cDenominator) + "/" + cDenominator;
        }
        return str;
    }

    public String toIntString(){
        int value = cNumerator / cDenominator;
        return String.valueOf(value);
    }

    /**
     * Parses a string that contains a fraction
     * @param str A string that contains the fraction in format of "a/b"
     * @return A fraction object that is derived from the string
     * @exception NumberFormatException When the parameter doesn't follow the "a/b" format or contains any non-numeric letters
     */
    public static RatNumLab2 parse(String str){
        String[] numbers = str.split("/");

        if(!str.contains("/")) {
            return new RatNumLab2(Integer.parseInt(str), 1);
        }
        else if(numbers.length != 2) {
            throw new NumberFormatException();
        }
        int numerator = Integer.parseInt(numbers[0]);
        int denominator = Integer.parseInt(numbers[1]);
        return new RatNumLab2(numerator, denominator);
    }

    /**
     * Adds together fractions and returns the result
     * @param ratNum A fraction to add with the current object
     * @return A {@link RatNumLab2} object with the sum from the addition
     */
    public RatNumLab2 add(RatNumLab2 ratNum){
        int numerator = cNumerator * ratNum.getDenominator() + ratNum.getNumerator() * cDenominator;
        // The constructor will shorten the fraction if needed
        return new RatNumLab2(numerator, cDenominator * ratNum.getDenominator());
    }

    /**
     * Subtracts the current fraction with the argument and returns the resulting fraction
     * @param ratNum The fraction that is in the right-hand side of the subtraction
     * @return A {@link RatNumLab2} object containing the difference from the subtraction
     */
    public RatNumLab2 sub(RatNumLab2 ratNum){
        int numerator = cNumerator * ratNum.getDenominator() - ratNum.getNumerator() * cDenominator;
        // The constructor will shorten the fraction if needed
        return new RatNumLab2(numerator, cDenominator * ratNum.getDenominator());
    }

    /**
     * Multiplies two fractions with each other and then returns the resulting fraction
     * @param ratNum A fraction to multiply with the current fraction
     * @return A {@link RatNumLab2} object containing the result of multiplication
     */
    public RatNumLab2 mul(RatNumLab2 ratNum){
        return new RatNumLab2(cNumerator * ratNum.getNumerator(), cDenominator * ratNum.getDenominator());
    }

    /**
     * Divides two fractions with each other and returns the resulting fraction
     * @param ratNum The fraction that will be used as the denominator of the division
     * @return A {@link RatNumLab2} object that contains the result from the division
     */
    public RatNumLab2 div(RatNumLab2 ratNum){
        // Uses inverted multiplication for division
        return new RatNumLab2(cNumerator * ratNum.getDenominator(), cDenominator * ratNum.getNumerator());
    }

    /**
     * Checks if the current fraction is less than the argument fraction
     * @param ratNum A fraction to be compared to the implicit argument to see if it's bigger
     * @return A boolean which is true when the argument is bigger than the implicit argument or otherwise false.
     */
    public boolean lessThan(RatNumLab2 ratNum){
        return cNumerator * ratNum.getDenominator() < ratNum.getNumerator() * cDenominator;
    }

    /**
     * Compares two fractions to see if they're the same value
     * @param obj fraction value to be compared to
     * @return true when the fractions are the same value otherwise false
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof RatNumLab2 ratNum)){
            return false;
        }
        return cNumerator == ratNum.getNumerator() && cDenominator == ratNum.getDenominator();
    }
}
