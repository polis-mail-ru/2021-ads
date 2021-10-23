import java.io.*;
import java.util.StringTokenizer;

public class TaskE {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] arrTrans = new int[size];

        for (int i = 0; i < size; i++) {
            arrTrans[i] = i + 1;
        }
        printTrans(arrTrans, out);

        while (nextSet(arrTrans)) {
            printTrans(arrTrans, out);
        }
    }

    private static void printTrans(int[] arrTrans, final PrintWriter out) {
        for (int arrTran : arrTrans) {
            out.print(arrTran + " ");
        }
        out.println();
    }

    private static boolean nextSet(int[] arrTrans) {
        int j = arrTrans.length - 2;
        while (j != -1 && arrTrans[j] >= arrTrans[j + 1]) {
            j--;
        }

        if (j == -1) {
            return false;
        }

        int k = arrTrans.length - 1;
        while (arrTrans[j] >= arrTrans[k]) {
            k--;
        }
        swap(arrTrans, j, k);

        int l = j + 1;
        int r = arrTrans.length - 1;
        while (l < r) {
            swap(arrTrans, l++, r--);
        }

        return true;
    }

    private static void swap(int[] arrTrans, int i, int j) {
        int temp = arrTrans[i];
        arrTrans[i] = arrTrans[j];
        arrTrans[j] = temp;
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
