import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskB {
    private TaskB() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        try {
            ArrayList<Integer> mass = new ArrayList<>(107 * 2 + 1);
            for (int i = 0; i < (107 * 2 + 1); i++) {
                mass.add(0);
            }
            int n = in.nextInt() - 1;
            int base = in.nextInt();
            mass.set(107, 1);
            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                mass.set(107 + x - base, (mass.get(107 + x - base) + 1));
            }
            for (int i = 0; i < mass.size(); i++) {
                for (int j = 0; j < mass.get(i); j++) {
                    out.print((base + i - 107) + " ");
                }
            }
            //System.out.println(mass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
