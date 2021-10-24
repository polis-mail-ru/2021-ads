import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9634767
public class Permutations {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = i + 1;
        }
        out.println(printPermutation(a));
        int index = find(a);
        while (index != -1) {
            int element = a[index];
            int indexToSwap = findLastIndexBigger(a, element);
            swap(a, index, indexToSwap);
            reverse(a, index + 1);
            out.println(printPermutation(a));
            index = find(a);
        }
    }

    private static int find(int[] a) {
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    private static int findLastIndexBigger(int[] a, int element) {
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] > element) {
                return i;
            }
        }
        return -1;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void reverse(int[] a, int startIndex) {
        for (int i = 0; i < (a.length - startIndex) / 2; i++) {
            swap(a, startIndex + i, a.length - i - 1);
        }
    }

    private static String printPermutation(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i : a) {
            sb.append(i).append(' ');
        }
        return sb.toString();
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
