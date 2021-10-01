import java.util.Scanner;

public final class Week2Task3 {
    private Week2Task3() {
        // Should not be instantiated
    }

    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        int x = Integer.parseInt(in.nextLine());
        System.out.println(countC(x));
    }

    static long countC(int x) {
        int i = 1, j = 1;
        long a = 1, b = 1, c = 0;
        while (x > 0) {
            if (a <= b) {
                if (a == b)
                {
                    x += 1;
                }
                c = a;
                i += 1;
                a = (long) i * i;
            }
            else {
                c = b;
                j += 1;
                b = (long) j * j * j;
            }
            x -= 1;
        }
        return c;
    }
}