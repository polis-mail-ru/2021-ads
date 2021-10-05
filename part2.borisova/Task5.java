import java.io.*;
import java.util.StringTokenizer;

public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int number1 = in.nextInt();
        int[] mass1 = new int[number1];
        for (int i =  0; i < number1; i++) {
            mass1[i] = in.nextInt();
        }
        quickSort(mass1, 0, number1 - 1);

        int number2 = in.nextInt();
        int[] mass2 = new int[number2];
        for (int i =  0; i < number2; i++) {
            mass2[i] = in.nextInt();
        }
        quickSort(mass2, 0, number2 - 1);

        int i = 0;
        int j = 0;
        while (i < number1 && j < number2) {
            if (mass1[i] != mass2[j]) {
                out.print("NO");
                return;
            }
            while (i < number1 - 1 && mass1[i] == mass1[i + 1]) {
                i++;
            }
            while (j < number2 - 1 && mass2[j] == mass2[j + 1]) {
                j++;
            }
            i++;
            j++;
        }
        if (i < number1 || j < number2) {
            out.print("NO");
        } else {
            out.print("YES");
        }

    }

    private static void quickSort(int[] source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = source[(leftMarker + rightMarker) / 2];
        do {
            while (source[leftMarker] < pivot) {
                leftMarker++;
            }
            while (source[rightMarker] > pivot) {
                rightMarker--;
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    int tmp = source[leftMarker];
                    source[leftMarker] = source[rightMarker];
                    source[rightMarker] = tmp;
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);
        if (leftMarker < rightBorder) {
            quickSort(source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(source, leftBorder, rightMarker);
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
