package part5.tkachenkoalexandra;

import java.io.*;
import java.util.StringTokenizer;

public final class FirstTask {

    private static final double MAX = 10e10;
    private static final double MIN = 1.0;
    private static final double ERROR = 10e-6;

    private FirstTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        double coefficient = checkCount(Double.parseDouble(in.next()));
        double low = 0;
        double high = Math.sqrt(coefficient);
        double middle;
        double result;
        do {
            middle = low + (high - low) / 2;
            if (Double.compare(function(middle), coefficient) < 0) {
                low = middle;
            } else {
                high = middle;
            }
        } while (Double.compare(Math.abs(function(middle) - coefficient), ERROR) >= 0);
        out.println(middle);
    }

    private static double function(double x) {
        return Math.pow(x, 2) + Math.sqrt(x);
    }

    private static double checkCount(double n) {
        if (Double.compare(n, MIN) < 0 || Double.compare(n, MAX) > 0) {
            throw new IllegalArgumentException("The entered number does not match the condition.\n");
        }
        return n;
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

        boolean hasNext() {
            return (tokenizer == null) || (tokenizer.hasMoreTokens());
        }

        String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
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

