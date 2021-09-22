import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part1Task5 {
    private Part1Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<String> list = new LinkedList<String>();
        Scanner scanner = new Scanner(System.in);

        String expression = scanner.nextLine();
        String[] parts = expression.split(" ");

        int first = 0;
        int second = 0;
        for (String part : parts) {
            switch (part) {
                case "+":
                    second = Integer.parseInt(list.pollLast());
                    first = Integer.parseInt(list.pollLast());
                    list.offer(Integer.valueOf(first + second).toString());
                    break;
                case "-":
                    second = Integer.parseInt(list.pollLast());
                    first = Integer.parseInt(list.pollLast());
                    list.offer(Integer.valueOf(first - second).toString());
                    break;
                case "*":
                    second = Integer.parseInt(list.pollLast());
                    first = Integer.parseInt(list.pollLast());
                    list.offer(Integer.valueOf(first * second).toString());
                    break;
                default:
                    list.offer(part);
                    break;
            }
        }
        out.println(list.peek());
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
