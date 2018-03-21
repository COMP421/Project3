package connection;

import java.util.GregorianCalendar;

public class RandomDateOfBirth {

    public static void main(String[] args) {

        

        System.out.println();

    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
