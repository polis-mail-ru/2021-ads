import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TaskD {
    private TaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        ArrayList<Integer> diffNumbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int number = in.nextInt();
            pasteElIfDiff(number, diffNumbers, 0, diffNumbers.size());
        }

        out.println(diffNumbers.size());
    }

    private static void pasteElIfDiff(int number, ArrayList<Integer> diffNumbers, int fromInclusive, int toExclusive) {
        if(diffNumbers.size() == 0) {
            diffNumbers.add(fromInclusive, number);
            return;
        }

        if (fromInclusive == toExclusive - 1) {
            if(number == diffNumbers.get(fromInclusive)) {
                return;
            }
            else if(number > diffNumbers.get(fromInclusive)) {
                diffNumbers.add(fromInclusive + 1, number);
                return;
            }
            else {
                diffNumbers.add(fromInclusive, number);
                return;
            }
        }

        int mid = fromInclusive + (toExclusive - fromInclusive) / 2;
        if(number < diffNumbers.get(mid)) {
            pasteElIfDiff(number, diffNumbers, fromInclusive, mid);
        }
        else if(number > diffNumbers.get(mid)) {
            pasteElIfDiff(number, diffNumbers, mid, toExclusive);
        }
        else {
            return;
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
