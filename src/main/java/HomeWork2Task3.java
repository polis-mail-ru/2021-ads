import java.io.*;
import java.util.StringTokenizer;


public final class HomeWork2Task3 {
    private HomeWork2Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int cIndex = in.nextInt();
        int squareNums = 0;
        int repeatedNums = 0;
        long iCub = 0;
        int iCubIndex;
        for (iCubIndex = 1; squareNums + iCubIndex - repeatedNums <= cIndex; iCubIndex++) {
            iCub = (long) iCubIndex * iCubIndex * iCubIndex;
            squareNums = (int) Math.sqrt(iCub);
            repeatedNums = (int) Math.pow(iCub, 1.0 / 6.0);
        }
        --iCubIndex;
        int numsBeforeICub = squareNums + iCubIndex - repeatedNums;
        if (numsBeforeICub == cIndex) {
            out.println(iCub);
        } else {
            long nextSquare = (long) Math.pow(iCubIndex, 3.0 / 2.0) + 1;
            out.println((nextSquare - (numsBeforeICub - cIndex)) * (nextSquare - (numsBeforeICub - cIndex)));
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
}
