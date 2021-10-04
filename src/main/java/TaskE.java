import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayList<Integer> diffArrayA = takeDiffFromArray(in);
        boolean isSame = true;

        ArrayList<Integer> diffArrayB = new ArrayList<>();
        int sizeB = in.nextInt();
        for (int i = 0; i < sizeB; i++) {
            int number = in.nextInt();
            if (!hasNum(number, diffArrayA, 0, diffArrayA.size())) {
                isSame = false;
                break;
            }
            pasteElIfDiff(number, diffArrayB, 0, diffArrayB.size());
        }

        if (isSame && diffArrayA.size() == diffArrayB.size()) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    private static boolean hasNum(int number, ArrayList<Integer> diffNumbers, int fromInclusive, int toExclusive) {
        if (diffNumbers.size() == 0) {
            return false;
        }

        if (fromInclusive == toExclusive - 1) {
            if (number == diffNumbers.get(fromInclusive)) {
                return true;
            } else {
                return false;
            }
        }

        int mid = fromInclusive + (toExclusive - fromInclusive) / 2;
        if (number < diffNumbers.get(mid)) {
            return hasNum(number, diffNumbers, fromInclusive, mid);
        } else if (number > diffNumbers.get(mid)) {
            return hasNum(number, diffNumbers, mid, toExclusive);
        } else {
            return true;
        }
    }

    private static ArrayList<Integer> takeDiffFromArray(final FastScanner in) {
        int size = in.nextInt();
        ArrayList<Integer> diffArray = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int number = in.nextInt();
            pasteElIfDiff(number, diffArray, 0, diffArray.size());
        }
        return diffArray;
    }

    private static void pasteElIfDiff(int number, ArrayList<Integer> diffNumbers, int fromInclusive, int toExclusive) {
        if (diffNumbers.size() == 0) {
            diffNumbers.add(fromInclusive, number);
            return;
        }

        if (fromInclusive == toExclusive - 1) {
            if (number == diffNumbers.get(fromInclusive)) {
                return;
            } else if (number > diffNumbers.get(fromInclusive)) {
                diffNumbers.add(fromInclusive + 1, number);
                return;
            } else {
                diffNumbers.add(fromInclusive, number);
                return;
            }
        }

        int mid = fromInclusive + (toExclusive - fromInclusive) / 2;
        if (number < diffNumbers.get(mid)) {
            pasteElIfDiff(number, diffNumbers, fromInclusive, mid);
        } else if (number > diffNumbers.get(mid)) {
            pasteElIfDiff(number, diffNumbers, mid, toExclusive);
        } else {
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
