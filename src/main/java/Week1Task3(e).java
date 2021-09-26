import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// Скобочные последовательности
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int c = 0;
        String s = in.next();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                c++;
            } else {
                if (c == 0) {
                    System.out.println("NO");
                    return;
                }
                c--;
            }
        }
        if (c == 0) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
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
        try (PrintWriter out = createPrintWriterForLocalTests()) {
            solve(in, out);
        }
    }
}