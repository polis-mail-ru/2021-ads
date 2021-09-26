import javax.management.ListenerNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = in.nextLine();
        String[] words = input.split(" ");
        LinkedList<Integer> stack = new LinkedList<>();

        for (String word : words) {
            switch (word) {
                case "+":
                    stack.add(stack.pollLast() + stack.pollLast());
                    break;
                case "-":
                    stack.add(-stack.pollLast() + stack.pollLast());
                    break;
                case "*":
                    stack.add(stack.pollLast() * stack.pollLast());
                    break;
                default:
                    stack.add(Integer.parseInt(word));
            }
        }
        out.println(stack.peekLast());
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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
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
