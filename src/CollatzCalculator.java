public class CollatzCalculator {

    public static int calculateCollatz(int n) {
        int number = n;
        int steps = 0;

        while (number != 1) {
            if (number % 2 == 0) {
                number = number / 2;
            } else {
                number = 3 * number + 1;
            }

            steps++;
        }

        System.out.println(n + ": " + steps + " кроків");
        return steps;
    }
}
