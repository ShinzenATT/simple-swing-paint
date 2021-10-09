import java.util.*;

/** En immutable klass som representerar datum.
    OBS: Det finns buggar i denna kod. */
public class MyDate {

    private int y;
    private int m;
    private int d;

    /** Skapar ett nytt MyDate objekt för dagen: year, month, day.
        Exempel datumet 2029-07-15 skapas med new MyDate(2029,7,15).
        @throws  IllegalArgumentException  ifall datumet inte är ett giltigt datum (t.ex. 2003-02-29). */
    public MyDate(int year, int month, int day){
        if(year >= 0  && (0 < month && month < 13) && (0 < day && day <= daysInMonth(month, year))){
            y = year;
            m = month;
            d = day;
        }else{
            throw new IllegalArgumentException();
        }
    }

    /** Returnerar värdet av year som gavs till construktorn */
    public int getYear() { return y; }

    /** Returnerar värdet av month som gavs till construktorn */
    public int getMonth() { return m; }

    /** Returnerar värdet av day som gavs till construktorn */
    public int getDay() { return d; }

    /** Returnerar en sträng representation av datumet */
    public String toString() {
        String res = y + "-";
        if (m < 10) { res = res + "0"; };
        res = res + m + "-";
        if (d < 10) { res = res + "0"; };
        res = res + d;
        return res;
    }

    /** Returnerar true ifall det givna objektet representerar samma datum */
    public boolean equals(Object other) {
        if (other == null || !(other instanceof MyDate x)) {
            return false;
        }
        boolean m = (x.getDay() == this.getDay());
        boolean n = (x.getMonth() == this.getMonth());
        boolean l = (x.getYear() == this.getYear());
        return (m == (n == l));
    }

    /** Returnerar -1 ifall this är ett tidigare datum än other.
        Returnerar 1 ifall other är ett tidigare datum än this.
        Returnerar 0 ifall this och other representerar samma datum. */

    public int compareTo(MyDate other) {
        if (this.y < other.y || this.m < other.m || this.d < other.d) {
            return -1;
        }
        if (this.y > other.y || this.m > other.m || this.d > other.d) {
            return 1;
        }
        return 0;
    }

    //compareTo with errors
    /*public int compareTo(MyDate other) {
        if (this.y < other.y || this.m < other.m || this.d < other.d) {
            return -1;
        }
        if (this.y > other.y || this.m > other.m || this.d > other.d) {
            return 1;
        }
        return 0;
    }*/

    /** Ger true ifall year är ett skottår */
    private static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /** Returnerar hur många dagar det finns i månad m för år y */
    private static int daysInMonth(int m, int y) {
        switch (m) {
          case 4: // apr
          case 6: // jun
          case 9: // sep
          case 11: // nov
              return 30;
          case 2: // feb
              if (isLeapYear(y)) {
                  return 29;
              } else {
                  return 28;
              }
          default: // resten
              return 31;
        }
    }

    /** Returnerar datumet av dagen som kommer direkt efter detta datum */
    public MyDate next() throws Exception {
        if ((m == 12) && (d == 31)) {
            return new MyDate(y+1,1,1);
        }
        if (d == daysInMonth(m,y)) {
            return new MyDate(y,m+1,1);
        }
        return new MyDate(y,m,d+1);
    }

    /** Returnerar en iterator som innehåller upprepningarna av detta datum med
        en veckas mellanrum. Det första datumet är samma som this; följande är
        7 dagar senare; och därefter 14 dagar senare, osv. Antalet datum i
        iterator bör vara samma som det givna repetitionCount (antas vara >= 0). */
    public Iterator<MyDate> repeatWeekly(int repetitionCount){
        if(repetitionCount < 0){
            throw new IllegalArgumentException();
        }

        LinkedList<MyDate> list = new LinkedList<MyDate>();
        list.add(this);

        int day = d,
            month = m,
            year = y;
        for(int i = 0; i < (repetitionCount - 1); i++){
            day += 7;
            if(day > daysInMonth(month, year)){
                day %= daysInMonth(month, year);
                ++month;
                if(month > 12){
                    month = 1;
                    ++year;
                }
            }

            list.add(new MyDate(year, month, day));
        }

        return list.iterator();
    }

}
